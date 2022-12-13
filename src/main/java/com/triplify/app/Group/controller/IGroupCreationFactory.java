package com.triplify.app.Group.controller;

import com.triplify.app.Group.model.GroupDetails;
import com.triplify.app.Group.model.GroupMembersDetails;

public interface IGroupCreationFactory {
    public GroupDetails makeGroupDetails();
    public GroupMembersDetails makeGroupMemberDetails();
}
