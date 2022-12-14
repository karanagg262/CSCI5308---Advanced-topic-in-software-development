package com.triplify.app.Group.controller;

public class GroupHasMembersSelectQuery implements IGroupHasMembersSelectQuery{
    @Override
    public String selectQuery() {
        return "select * from group_has_members";
    }
}
