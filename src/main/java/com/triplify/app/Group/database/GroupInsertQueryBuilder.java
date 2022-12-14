package com.triplify.app.Group.database;

import com.triplify.app.Group.controller.IGroupInsertQueryBuilder;
import com.triplify.app.Group.model.GroupDetails;

import static com.triplify.app.Group.database.GroupDetailsDatabaseConstant.*;

public class GroupInsertQueryBuilder implements IGroupInsertQueryBuilder {

    public String insertGroupQuery(final GroupDetails groupDetails){
        return "INSERT INTO "+ GROUP_DETAILS_TABLE + "(" +
                GROUP_NAME + ", " +
                GROUP_TRIP_START_DATE + ", " +
                GROUP_TRIP_END_DATE + ", " +
                GROUP_DESTINATION + ", " +
                GROUP_DESCRIPTION + ", " +
                GROUP_TYPE + ", " +
                GROUP_MEMBER_USERNAME + ") " +
                "VALUES (" +
                "\"" +groupDetails.getGroupName()+ "\", " +
                "\"" +groupDetails.getTripStartDate()+ "\", " +
                "\"" +groupDetails.getTripEndDate() + "\", " +
                "\"" +groupDetails.getDestination() + "\", " +
                "\"" +groupDetails.getGroupDescription() + "\", " +
                "\"" +groupDetails.getTripType() + "\", " +
                "\"" +groupDetails.getUsername() + "\"" +
                ");";
    }
}
