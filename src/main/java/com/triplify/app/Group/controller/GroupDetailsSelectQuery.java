package com.triplify.app.Group.controller;

import static com.triplify.app.Group.database.GroupDetailsDatabaseConstant.group_details_table;

public class GroupDetailsSelectQuery implements IGroupDetailsSelectQuery {
    @Override
    public String selectQueryForGroup() {
        return "SELECT * from " + group_details_table;
    }
}
