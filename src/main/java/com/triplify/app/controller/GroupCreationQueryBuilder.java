package com.triplify.app.controller;

import com.triplify.app.model.GroupDetails;

import static com.triplify.app.database.GroupDetailsDatabaseConstant.*;

public class GroupCreationQueryBuilder {

    public String insertGroupQuery(final GroupDetails groupDetails){
        return "INSERT INTO "+ group_details_table + "(" +
                group_name + ", " +
                group_trip_start_date + ", " +
                group_trip_end_date + ", " +
                group_destination + ", " +
                group_description + ", " +
                group_type + ", " +
                group_creater_user_id + ") " +
                "VALUES (" +
                "\"" +groupDetails.getGroupName()+ "\", " +
                "\"" +groupDetails.getTripStartDate()+ "\", " +
                "\"" +groupDetails.getTripEndDate() + "\", " +
                "\"" +groupDetails.getDestination() + "\", " +
                "\"" +groupDetails.getGroupDescription() + "\", " +
                "\"" +groupDetails.getTripType() + "\", " +
                groupDetails.getUser_id() +
                ");";
    }
}
