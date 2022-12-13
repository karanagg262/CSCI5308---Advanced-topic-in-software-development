package com.triplify.app.Group.database;

import com.triplify.app.Group.model.GroupMembersDetails;

import static com.triplify.app.Group.database.GroupMemberDetailsDatabaseConstant.*;

public class GroupMemberDetailsQueryBuilder implements IGroupMemberDetailsQueryBuilder{
    public String groupMemberInsertQuery(final GroupMembersDetails groupMembersDetails){
        return "INSERT INTO "+ group_member_details_table + "(" +
                group_member_details_group_name + ", " +
                group_member_details_destination + ", " +
                group_member_details_first_name + ", " +
                group_member_details_last_name + ", " +
                group_member_details_user_id + ") " +
                "VALUES (" +
                "\"" +groupMembersDetails.getGroupName()+ "\", " +
                "\"" +groupMembersDetails.getGroupDestination()+ "\", " +
                "\"" +groupMembersDetails.getGroupMemberFirstName() + "\", " +
                "\"" +groupMembersDetails.getGroupMemberLastName() + "\", " +
                groupMembersDetails.getUser_id() +
                ");";
    }
}
