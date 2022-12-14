package com.triplify.app.Group.database;

import com.triplify.app.Group.controller.IGroupCreationQueryBuilder;
import com.triplify.app.Group.model.GroupDetails;

import static com.triplify.app.Group.database.GroupDetailsDatabaseConstant.*;

public class GroupCreationQueryBuilder implements IGroupCreationQueryBuilder {

    public String insertGroupQuery(final GroupDetails groupDetails){
        return "INSERT INTO "+ group_details_table + "(" +
                group_name + ", " +
                group_trip_start_date + ", " +
                group_trip_end_date + ", " +
                group_destination + ", " +
                group_description + ", " +
                group_type + ", " +
                group_creator_user_id + ") " +
                "VALUES (" +
                "\"" +groupDetails.getGroupName()+ "\", " +
                "\"" +groupDetails.getTripStartDate()+ "\", " +
                "\"" +groupDetails.getTripEndDate() + "\", " +
                "\"" +groupDetails.getDestination() + "\", " +
                "\"" +groupDetails.getGroupDescription() + "\", " +
                "\"" +groupDetails.getTripType() + "\", " +
                groupDetails.getCreator_user_id() +
                ");";
    }
}
