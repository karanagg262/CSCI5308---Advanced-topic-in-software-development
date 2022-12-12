package com.triplify.app.controller;

import com.triplify.app.database.DatabaseConnection;
import com.triplify.app.database.DatabaseExceptionHandler;
import com.triplify.app.model.GroupDetails;
import com.triplify.app.model.GroupMembersDetails;
import com.triplify.app.model.UserTable;
import org.springframework.web.bind.annotation.*;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import static com.triplify.app.database.GroupMemberDetailsDatabaseConstant.*;

@RestController
@CrossOrigin
public class GroupAddMemberController {

    GroupMemberDetailsQueryBuilder groupMemberDetailsQueryBuilder =
            new GroupMemberDetailsQueryBuilder();

    @GetMapping("/groups/allMembers")
    public List<GroupMembersDetails> getAllMembers() throws DatabaseExceptionHandler{
        Connection connection =
                DatabaseConnection.getInstance().getDatabaseConnection();
        List<GroupMembersDetails> listOfUserTables = new ArrayList<>();

        try {
            ResultSet resultSet =
                    connection.createStatement().executeQuery("select * from group_members");

            while (resultSet.next()){
                Long id = resultSet.getLong(""+group_member_details_id);
                String groupName = resultSet.getString(""+group_member_details_group_name);
                String destination = resultSet.getString(""+group_member_details_destination);
                String firstname = resultSet.getString(""+group_member_details_first_name);
                String lastname = resultSet.getString(""+group_member_details_last_name);
                Long user_id = resultSet.getLong(""+group_member_details_user_id);

                GroupMembersDetails groupMembersDetails =
                        new GroupMembersDetails(id, groupName, destination, firstname, lastname, user_id);
                listOfUserTables.add(groupMembersDetails);
            }

            if(connection!=null){
                connection.close();
            }

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return listOfUserTables;
    }

    @PostMapping("group/addMember")
    public String addGroupMember(@RequestParam("username") String username) throws DatabaseExceptionHandler {

        List<UserTable> userTableList = new UserController().getAllUsers();
        List<GroupDetails> groupDetailsList = new GroupController().getAllGroupDetails();
        Long user_id = Long.valueOf(0);
        String userFirstName = "";
        String userLastName = "";
        GroupMembersDetails groupMembersDetails = null;

        for (int i = 0 ; i < userTableList.size() ; i++){
            if(userTableList.get(i).getUsername().equalsIgnoreCase(username)){
                user_id = userTableList.get(i).getId();
                userFirstName = userTableList.get(i).getFirstname();
                userLastName = userTableList.get(i).getLastname();
            }
        }

        List<GroupMembersDetails> groupMembersDetailsList
                = getAllMembers();

        for(int i = 0 ; i < groupDetailsList.size() ; i++){
            for(int j = 0 ; j < groupMembersDetailsList.size() ; j++){
                if (groupDetailsList.get(i).getUser_id().equals(user_id) &&
                        groupDetailsList.get(i).getGroupName().equalsIgnoreCase(groupMembersDetailsList.get(j).getGroupName())) {
                    return "Already " + groupMembersDetailsList.get(j).getGroupMemberFirstName() + " is added";
                } else if(groupMembersDetailsList.get(j).getUser_id().equals(user_id)){
                    return "Already "+groupMembersDetailsList.get(j).getGroupMemberFirstName()+" is added";
                }
            }
            String groupName = groupDetailsList.get(i).getGroupName();
            String groupDestination = groupDetailsList.get(i).getDestination();

            groupMembersDetails = new GroupMembersDetails();
            groupMembersDetails.setGroupName(groupName);
            groupMembersDetails.setGroupDestination(groupDestination);
            groupMembersDetails.setGroupMemberFirstName(userFirstName);
            groupMembersDetails.setGroupMemberLastName(userLastName);
            groupMembersDetails.setUser_id(user_id);
            break;
        }

        Connection connection = DatabaseConnection.getInstance().getDatabaseConnection();
        Statement statement = null;
        try {
            statement = connection.createStatement();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

        try{

            final String insertQueryGroupMember =
                    groupMemberDetailsQueryBuilder.groupMemberInsertQuery(groupMembersDetails);

            final int rawInserted =
                    statement.executeUpdate(insertQueryGroupMember, Statement.RETURN_GENERATED_KEYS);

            if(rawInserted > 0){
                System.out.println("Group Member created!!");
            }else{
                System.out.println("Something went wrong!!");
            }

            return "SuccessFully Member Added";

        }catch (SQLException e){
            System.out.println(e.getMessage());
            return "Group Member not created yet!! Something went wrong!!";
        } finally {
            if(connection!=null){
                try {
                    connection.close();
                } catch (SQLException e) {
                    throw new RuntimeException(e);
                }
            }
        }
    }

}
