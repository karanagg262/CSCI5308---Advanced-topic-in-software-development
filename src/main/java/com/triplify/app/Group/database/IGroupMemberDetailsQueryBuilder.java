package com.triplify.app.Group.database;

import com.triplify.app.Group.model.GroupMembersDetails;

public interface IGroupMemberDetailsQueryBuilder {
    String groupMemberInsertQuery(GroupMembersDetails groupMembersDetails);
}
