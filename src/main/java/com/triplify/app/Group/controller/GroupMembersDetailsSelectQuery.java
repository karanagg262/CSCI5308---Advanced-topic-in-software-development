package com.triplify.app.Group.controller;

import static com.triplify.app.Group.database.GroupMemberDetailsDatabaseConstant.group_member_details_table;

public class GroupMembersDetailsSelectQuery implements IGroupMembersDetailsSelectQuery{
    @Override
    public String selectGroupMemberDetailsQuery() {
        return "SELECT * FROM "+ group_member_details_table;
    }
}
