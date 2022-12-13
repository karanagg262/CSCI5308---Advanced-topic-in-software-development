package com.triplify.app.Group.controller;

import com.triplify.app.Group.model.GroupDetails;

public interface IGroupCreationQueryBuilder {
    String insertGroupQuery(GroupDetails groupDetails);
}
