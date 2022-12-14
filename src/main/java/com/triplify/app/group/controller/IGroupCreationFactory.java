package com.triplify.app.group.controller;

import com.triplify.app.group.model.GroupDetails;
import com.triplify.app.group.model.GroupMembersDetails;

public interface IGroupCreationFactory {
    public GroupDetails makeGroupDetails();
    public GroupMembersDetails makeGroupMemberDetails();
}
