package com.triplify.app.Group.controller;

import com.triplify.app.Group.model.GroupDetails;

public interface IGroupInsertQueryBuilder {
    String insertGroupQuery(GroupDetails groupDetails);
}
