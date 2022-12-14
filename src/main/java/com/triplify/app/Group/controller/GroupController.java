package com.triplify.app.Group.controller;

import com.triplify.app.Group.controller.algo.Find;
import com.triplify.app.Group.database.GroupMemberDetailsQueryBuilder;
import com.triplify.app.Group.model.GroupDetails;
import com.triplify.app.database.DatabaseConnection;
import com.triplify.app.database.DatabaseExceptionHandler;
import org.springframework.web.bind.annotation.*;

import java.sql.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static com.triplify.app.Group.database.GroupDetailsDatabaseConstant.*;
import static com.triplify.app.Group.database.GroupMemberDetailsDatabaseConstant.GROUP_HAS_MEMBERS_USERNAME;

@RestController
@CrossOrigin
public class GroupController implements IGroupController {

    private GroupDetails createGroupDetails() {
        IGroupCreationFactory iGroupCreationFactory =
                GroupFactory.factorySingleton();
        return iGroupCreationFactory.makeGroupDetails();
    }

    @GetMapping("/groups")
    public List<GroupDetails> getAllGroupDetails() throws DatabaseExceptionHandler {
        GroupDetails groupDetails = createGroupDetails();
        return groupDetails.createAllGroupDetailsList();
    }
    @GetMapping("/groups/{group_id}")
    public Map<String, Object> getGroup(@PathVariable("group_id") long group_id) throws DatabaseExceptionHandler, SQLException {
        Connection dbConnection = DatabaseConnection.getInstance().getDatabaseConnection();
        String query = "SELECT * FROM "+group_details_table +
                " WHERE "+ group_details_id +
                "=?";
        PreparedStatement preparedStatement = dbConnection.prepareStatement(query);
        preparedStatement.setLong(1,group_id);
        ResultSet result = preparedStatement.executeQuery();
        Map<String, Object> group = new HashMap<>();
        while (result.next()){
            group.put("group_id", result.getLong(group_details_id));
            group.put("name", result.getString(group_name));
            group.put("start_date", result.getString(group_trip_start_date));
            group.put("end-date", result.getString(group_trip_end_date));
            group.put("destination", result.getString(group_destination));
            group.put("description", result.getString(group_description));
            group.put("type", result.getString(group_type));
            group.put("username", result.getString(group_member_username));
        }
        return group;
    }
    @GetMapping("/groups/{group_id}/members")
    public Map<String ,Object> getGroupMembers(@PathVariable("group_id") long group_id) throws DatabaseExceptionHandler{
        Map<String, Object> response = new HashMap<>();
        try {
        Connection dbConnection = DatabaseConnection.getInstance().getDatabaseConnection();
        GroupMemberDetailsQueryBuilder queries = new GroupMemberDetailsQueryBuilder();
        PreparedStatement preparedStatement = dbConnection.prepareStatement(queries.groupMemberRelationshipGetQuery());
        preparedStatement.setLong(1,group_id);
        ResultSet results = preparedStatement.executeQuery();
        List<Map<String, Object>> members = new ArrayList<>();
        Find seeker = new Find();
        while(results.next()){
            Map<String, Object> userMap= new HashMap<>();
            String username = results.getString(GROUP_HAS_MEMBERS_USERNAME);
            userMap.put("username",username);
            members.add(userMap);
        }
        for(Map<String, Object> member: members){
            member.put("id", seeker.findUserIdByUsername((String) member.get("username")));
        }
        response.put("members", members);
        }
        catch (SQLIntegrityConstraintViolationException e){
            response.put("Error", "User does not exist.");
            return response;
        }
        catch (SQLException sqlException){
            response.put("Error", sqlException.getMessage());
        }
        return response;
    }
    @PostMapping("/groups/{group_id}/add/member")
    public Map<Long, List<String>> addGroupMembers(@PathVariable("group_id") long group_id,
                                               @RequestParam("username") String username) throws DatabaseExceptionHandler, SQLException{
        Find seeker = new Find();
        GroupMemberDetailsQueryBuilder queries = new GroupMemberDetailsQueryBuilder();
        Connection dbConnection = DatabaseConnection.getInstance().getDatabaseConnection();
        PreparedStatement insertStatement = dbConnection.prepareStatement(queries.groupMemberRelationshipInsertQuery());
        insertStatement.setLong(1,group_id);
        insertStatement.setString(2, username);
        try {
            insertStatement.execute();
            Map<Long, List<String>> response = new HashMap<>();
            List<String> usernames = seeker.findUsersInGroup(group_id);
            response.put(group_id, usernames);
            return response;
        }
        catch (Exception e){
            e.printStackTrace();
            Map<Long,List<String>> failureResponse = new HashMap<>();
            failureResponse.put(Long.valueOf(1),new ArrayList<String>());
            return failureResponse;
        }
    }
    @PostMapping("/groups/createGroup")
    public Map<String, Object> createGroup(@RequestParam("groupName") String groupName,
                                           @RequestParam("groupStartDate") String tripStartDate,
                                           @RequestParam("groupEndDate") String tripEndDate,
                                           @RequestParam("groupDestination") String destination,
                                           @RequestParam("groupDescription") String groupDescription,
                                           @RequestParam("groupType") String tripType,
                                           @RequestParam("username") String username)
            throws DatabaseExceptionHandler {

        GroupDetails groupDetails = createGroupDetails();
        groupDetails.setGroupName(groupName);
        groupDetails.setTripStartDate(tripStartDate);
        groupDetails.setTripEndDate(tripEndDate);
        groupDetails.setDestination(destination);
        groupDetails.setGroupDescription(groupDescription);
        groupDetails.setTripType(tripType);
        groupDetails.setUsername(username);

        List<GroupDetails> listOfGroups = groupDetails.createAllGroupDetailsList();
        Map<String, Object> response = new HashMap<>();

        for(GroupDetails group : listOfGroups){
            if(group.getGroupName().equalsIgnoreCase(groupDetails.getGroupName()) &&
                group.getGroupDescription().equalsIgnoreCase(groupDetails.getGroupDescription()) &&
                group.getTripStartDate().equalsIgnoreCase(groupDetails.getTripStartDate()) &&
                group.getTripEndDate().equalsIgnoreCase(groupDetails.getTripEndDate()) &&
                group.getTripType().equalsIgnoreCase(groupDetails.getTripType()) &&
                group.getDestination().equalsIgnoreCase(groupDetails.getDestination())){

                response.put("SUCCESS", false);
                response.put("MESSAGE", "Group is already exists!!");
                return response;
            }
        }

        GroupDetails groupDetailsForCreateGroupResponse = createGroupDetails();
        response = groupDetailsForCreateGroupResponse.createGroupResponse(groupDetails);
        return response;
    }

}
