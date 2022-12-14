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
        List<GroupDetails> groupDetailsList = groupDetails.createAllGroupDetailsList();
        return groupDetailsList;
    }
    @GetMapping("/groups/{group_id}")
    public Map<String, Object> getGroup(@PathVariable("group_id") long group_id) throws DatabaseExceptionHandler, SQLException {
        Connection dbConnection = DatabaseConnection.getInstance().getDatabaseConnection();
        String query = "SELECT * FROM "+group_details_table +
                " WHERE "+ group_details_id +
                "=?";
        PreparedStatement pstmt = dbConnection.prepareStatement(query);
        pstmt.setLong(1,group_id);
        ResultSet result = pstmt.executeQuery();
        Map<String, Object> group = new HashMap<>();
        while (result.next()){
            group.put("group_id", result.getLong(group_details_id));
            group.put("name", result.getString(group_name));
            group.put("start_date", result.getString(group_trip_start_date));
            group.put("end-date", result.getString(group_trip_end_date));
            group.put("destination", result.getString(group_destination));
            group.put("description", result.getString(group_description));
            group.put("type", result.getString(group_type));
            group.put("creator", result.getLong(group_creator_user_id));
        }
        return group;
    }
    @GetMapping("/groups/{group_id}/members")
    public Map<String ,Object> getGroupMembers(@PathVariable("group_id") long group_id) throws DatabaseExceptionHandler{
        Map<String, Object> response = new HashMap<>();
        try {
        Connection dbConnection = DatabaseConnection.getInstance().getDatabaseConnection();
        GroupMemberDetailsQueryBuilder queries = new GroupMemberDetailsQueryBuilder();
        PreparedStatement pstmt = dbConnection.prepareStatement(queries.groupMemberRelationshipGetQuery());
        pstmt.setLong(1,group_id);
        ResultSet results = pstmt.executeQuery();
        List<Long> member_ids = new ArrayList<>();
        while(results.next()){
            member_ids.add(results.getLong(GROUP_HAS_MEMBERS_USERNAME));
        }
        response.put("members", member_ids);
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
        Long user_id = seeker.findUserIdByUsername(username);
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
        }
        return null;
    }
    @PostMapping("/groups/createGroup")
    public Map<String, Object> createGroup(@RequestParam("groupName") String groupName,
                                           @RequestParam("groupStartDate") String tripStartDate,
                                           @RequestParam("groupEndDate") String tripEndDate,
                                           @RequestParam("groupDestination") String destination,
                                           @RequestParam("groupDescription") String groupDescription,
                                           @RequestParam("groupType") String tripType,
                                           @RequestParam("user_id") Long user_id)
            throws DatabaseExceptionHandler {

        GroupDetails groupDetails = createGroupDetails();
        groupDetails.setGroupName(groupName);
        groupDetails.setTripStartDate(tripStartDate);
        groupDetails.setTripEndDate(tripEndDate);
        groupDetails.setDestination(destination);
        groupDetails.setGroupDescription(groupDescription);
        groupDetails.setTripType(tripType);
        groupDetails.setUser_id(user_id);

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
