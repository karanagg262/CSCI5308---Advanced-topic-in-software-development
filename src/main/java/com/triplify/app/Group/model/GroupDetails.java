package com.triplify.app.Group.model;

import com.triplify.app.Group.controller.GroupDetailsSelectQuery;
import com.triplify.app.Group.database.GroupCreationQueryBuilder;
import com.triplify.app.database.DatabaseConnection;
import com.triplify.app.database.DatabaseExceptionHandler;

import javax.persistence.*;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static com.triplify.app.Group.database.GroupDetailsDatabaseConstant.*;

@Entity
public class GroupDetails implements IGroupDetails{
    private Long id;
    private String groupName;
    private String tripStartDate;
    private String tripEndDate;
    private String destination;
    private String groupDescription;
    private String tripType;
    private Long user_id;

    public GroupDetails(){

    }

    public GroupDetails(String groupName, String tripStartDate, String tripEndDate, String destination, String tripType, String groupDescription, Long user_id) {
        this.groupName = groupName;
        this.tripStartDate = tripStartDate;
        this.tripEndDate = tripEndDate;
        this.destination = destination;
        this.tripType = tripType;
        this.groupDescription = groupDescription;
        this.user_id = user_id;
    }

    public GroupDetails(Long id, String groupName, String tripStartDate, String tripEndDate, String destination, String tripType, String groupDescription, Long user_id) {
        this.id = id;
        this.groupName = groupName;
        this.tripStartDate = tripStartDate;
        this.tripEndDate = tripEndDate;
        this.destination = destination;
        this.tripType = tripType;
        this.groupDescription = groupDescription;
        this.user_id = user_id;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getGroupName() {
        return groupName;
    }

    public void setGroupName(String groupName) {
        this.groupName = groupName;
    }

    public String getTripStartDate() {
        return tripStartDate;
    }

    public void setTripStartDate(String tripStartDate) {
        this.tripStartDate = tripStartDate;
    }

    public String getTripEndDate() {
        return tripEndDate;
    }

    public void setTripEndDate(String tripEndDate) {
        this.tripEndDate = tripEndDate;
    }

    public String getDestination() {
        return destination;
    }

    public void setDestination(String destination) {
        this.destination = destination;
    }
    public String getGroupDescription() {
        return groupDescription;
    }

    public void setGroupDescription(String groupDescription) {
        this.groupDescription = groupDescription;
    }

    public String getTripType() {
        return tripType;
    }

    public void setTripType(String tripType) {
        this.tripType = tripType;
    }

    public Long getUser_id() {
        return user_id;
    }

    public void setUser_id(Long user_id) {
        this.user_id = user_id;
    }

    @Override
    public String toString() {
        return "GroupDetails{" +
                "id=" + id +
                ", groupName='" + groupName + '\'' +
                ", tripStartDate='" + tripStartDate + '\'' +
                ", tripEndDate='" + tripEndDate + '\'' +
                ", destination='" + destination + '\'' +
                ", groupDescription='" + groupDescription + '\'' +
                ", tripType='" + tripType + '\'' +
                ", user_id=" + user_id +
                '}';
    }

    public Connection makeDBConnection() throws DatabaseExceptionHandler {
        return DatabaseConnection.getInstance().getDatabaseConnection();
    }

    public List<GroupDetails> createAllGroupDetailsList() throws DatabaseExceptionHandler {

        Connection connection = makeDBConnection();
        List<GroupDetails> groupDetailsList = new ArrayList<>();

        try {
            GroupDetailsSelectQuery groupDetailsSelectQuery = new GroupDetailsSelectQuery();
            ResultSet groupDetailsResultSet = connection.createStatement().executeQuery(groupDetailsSelectQuery.selectQueryForGroup());

            while (groupDetailsResultSet.next()){
                Long id = groupDetailsResultSet.getLong(""+group_details_id);
                String groupName = groupDetailsResultSet.getString(""+group_name);
                String groupStartDate = groupDetailsResultSet.getString(""+group_trip_start_date);
                String groupEndDate = groupDetailsResultSet.getString(""+group_trip_end_date);
                String groupDestination = groupDetailsResultSet.getString(""+group_destination);
                String groupDescription = groupDetailsResultSet.getString(""+group_description);
                String groupType = groupDetailsResultSet.getString(""+group_type);
                Long group_user_id = groupDetailsResultSet.getLong(""+group_creater_user_id);

                GroupDetails groupDetails = new GroupDetails(id,groupName,groupStartDate,groupEndDate,groupDestination,groupType,groupDescription,group_user_id);
                groupDetailsList.add(groupDetails);
            }

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

        return groupDetailsList;
    }

    public Map<String, Object> createGroupResponse(GroupDetails groupDetails) throws DatabaseExceptionHandler {

        Map<String, Object> response = new HashMap<>();

        try{
            Connection connection = makeDBConnection();
            Statement statement = connection.createStatement();

            GroupCreationQueryBuilder groupCreationQueryBuilder =
                    new GroupCreationQueryBuilder();

            final String insertQuery = groupCreationQueryBuilder.insertGroupQuery(groupDetails);
            final int rowInserted =
                    statement.executeUpdate(insertQuery, Statement.RETURN_GENERATED_KEYS);

            if(rowInserted > 0){
                System.out.println("Yes row is inserted !!");
            }

            response.put("SUCCESS", true);
            response.put("MESSAGE", "SUCCESSFULLY_GROUP_CREATED");

        } catch (SQLException e) {
            System.out.println(e.getMessage());
            response.put("SUCCESS", true);
            response.put("MESSAGE", "GROUP_CREATION_FAILED");
        }
        return response;
    }
}
