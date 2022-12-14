package com.triplify.app.Group.controller;

import static com.triplify.app.Group.database.GroupMemberDetailsDatabaseConstant.GROUP_MEMBER_DETAILS_TABLE;

public class GroupMembersDetailsSelectQuery implements IGroupMembersDetailsSelectQuery{
    @Override
    public String selectGroupMemberDetailsQuery() {
        return "SELECT * FROM "+ GROUP_MEMBER_DETAILS_TABLE;
    }
}
