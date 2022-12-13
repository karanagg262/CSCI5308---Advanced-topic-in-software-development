package com.triplify.app.Group.controller;

import com.triplify.app.database.DatabaseConnection;
import com.triplify.app.database.DatabaseExceptionHandler;
import com.triplify.app.Group.model.GroupDetails;
import org.springframework.web.bind.annotation.*;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static com.triplify.app.Group.database.GroupDetailsDatabaseConstant.group_details_id;
import static com.triplify.app.Group.database.GroupDetailsDatabaseConstant.group_details_table;

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
    @PostMapping("/groups/{id}")
    public Map<String, Object> getGroup(@PathVariable("group_id") long group_id) throws DatabaseExceptionHandler, SQLException {
        Connection dbConnection = DatabaseConnection.getInstance().getDatabaseConnection();
        String query = "SELECT * FROM "+group_details_table +
                "WHERE "+ group_details_id +
                "(?)";
        PreparedStatement pstmt = dbConnection.prepareStatement(query);
        pstmt.setLong(1,group_id);
        ResultSet result = pstmt.executeQuery();
        System.out.println(result.toString());
        GroupDetails group = new GroupDetails();
        return new HashMap<String, Object>();
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
