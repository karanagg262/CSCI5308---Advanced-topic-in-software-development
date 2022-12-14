package com.triplify.app.Group.controller;

import static com.triplify.app.Group.database.GroupDetailsDatabaseConstant.GROUP_DETAILS_TABLE;

public class GroupDetailsSelectQuery implements IGroupDetailsSelectQuery {
    @Override
    public String selectQueryForGroup() {
        return "SELECT * from " + GROUP_DETAILS_TABLE;
    }
}
