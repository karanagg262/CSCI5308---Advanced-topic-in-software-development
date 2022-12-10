package com.triplify.app.controller;

import com.triplify.app.database.DatabaseConnection;
import com.triplify.app.database.DatabaseExceptionHandler;
import com.triplify.app.model.GroupDetails;
import org.springframework.web.bind.annotation.*;

import javax.xml.transform.Result;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import static com.triplify.app.database.GroupDetailsDatabaseConstant.*;

@RestController
// @RequestMapping(path = "api/v1/groups") //TODO: change url to a specific group based on user selection
public class GroupController {

    GroupCreationQueryBuilder groupCreationQueryBuilder =
            new GroupCreationQueryBuilder();

    @GetMapping("/groups")
    public List<GroupDetails> getAllGroupDetails() throws DatabaseExceptionHandler {
        Connection connection =
                DatabaseConnection.getInstance().getDatabaseConnection();
        List<GroupDetails> listOfGroups = new ArrayList<>();

        try {

            System.out.println(connection.getCatalog());

            ResultSet groupDetailsResultSet =
                    connection.createStatement().executeQuery("select * from group_details");

            while (groupDetailsResultSet.next()){
                Long id = groupDetailsResultSet.getLong(""+group_details_id);
                String groupName = groupDetailsResultSet.getString(""+group_name);
                String groupStartDate = groupDetailsResultSet.getString(""+group_trip_start_date);
                String groupEndDate = groupDetailsResultSet.getString(""+group_trip_end_date);
                String groupDestination = groupDetailsResultSet.getString(""+group_destination);
                String groupType = groupDetailsResultSet.getString(""+group_type);
                Long group_user_id = groupDetailsResultSet.getLong(""+group_creater_user_id);

                GroupDetails groupDetails = new GroupDetails(id,groupName,groupStartDate,groupEndDate,groupDestination,groupType,group_user_id);
                listOfGroups.add(groupDetails);
            }

            if(connection!=null){
                connection.close();
            }

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return listOfGroups;
    }

    @PostMapping("/groups/createGroup")
    public String createGroup(@RequestParam("groupName") String groupName,
                              @RequestParam("groupStartDate") String tripStartDate,
                              @RequestParam("groupEndDate") String tripEndDate,
                              @RequestParam("groupDestination") String destination,
                              @RequestParam("groupType") String tripType,
                              @RequestParam("user_id") Long user_id) throws DatabaseExceptionHandler {

        GroupDetails groupDetails = new GroupDetails();
        groupDetails.setGroupName(groupName);
        groupDetails.setTripStartDate(tripStartDate);
        groupDetails.setTripEndDate(tripEndDate);
        groupDetails.setDestination(destination);
        groupDetails.setTripType(tripType);
        groupDetails.setUser_id(user_id);

        List<GroupDetails> listOfGroups = getAllGroupDetails();
        for(GroupDetails group : listOfGroups){
            if(group.equals(groupDetails)){
                System.out.println("Group is already exists!!");
                return "GROUP_ALREADY_EXISTS";
            }
        }

        try{

            Connection connection = DatabaseConnection.getInstance().getDatabaseConnection();
            Statement statement = connection.createStatement();

            final String insertQuery = groupCreationQueryBuilder.insertGroupQuery(groupDetails);
            final int rowInserted =
                    statement.executeUpdate(insertQuery, Statement.RETURN_GENERATED_KEYS);

            if(rowInserted > 0){
                System.out.println("Yes row is inserted !!");
            }
            return "SUCCESSFULLY_GROUP_CREATED";

        } catch (SQLException e) {
            System.out.println(e.getMessage());
            return "GROUP_CREATION_FAILED";
        }
    }

}
