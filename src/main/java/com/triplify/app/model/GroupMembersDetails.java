package com.triplify.app.model;

public class GroupMembersDetails {

    private Long id;
    private String groupName;
    private String groupDestination;
    private String groupMemberFirstName;
    private String groupMemberLastName;

    private Long user_id;

    public GroupMembersDetails() {

    }

    public GroupMembersDetails(Long id, String groupName, String groupDestination, String groupMemberFirstName, String groupMemberLastName, Long user_id) {
        this.id = id;
        this.groupName = groupName;
        this.groupDestination = groupDestination;
        this.groupMemberFirstName = groupMemberFirstName;
        this.groupMemberLastName = groupMemberLastName;
        this.user_id = user_id;
    }

    public GroupMembersDetails(String groupName, String groupDestination, String groupMemberFirstName, String groupMemberLastName, Long user_id) {
        this.groupName = groupName;
        this.groupDestination = groupDestination;
        this.groupMemberFirstName = groupMemberFirstName;
        this.groupMemberLastName = groupMemberLastName;
        this.user_id = user_id;
    }

    public Long getUser_id() {
        return user_id;
    }

    public void setUser_id(Long user_id) {
        this.user_id = user_id;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getGroupName() {
        return groupName;
    }

    public void setGroupName(String groupName) {
        this.groupName = groupName;
    }

    public String getGroupDestination() {
        return groupDestination;
    }

    public void setGroupDestination(String groupDestination) {
        this.groupDestination = groupDestination;
    }

    public String getGroupMemberFirstName() {
        return groupMemberFirstName;
    }

    public void setGroupMemberFirstName(String groupMemberFirstName) {
        this.groupMemberFirstName = groupMemberFirstName;
    }

    public String getGroupMemberLastName() {
        return groupMemberLastName;
    }

    public void setGroupMemberLastName(String groupMemberLastName) {
        this.groupMemberLastName = groupMemberLastName;
    }

    @Override
    public String toString() {
        return "GroupMembersDetails{" +
                "id=" + id +
                ", groupName='" + groupName + '\'' +
                ", groupDestination='" + groupDestination + '\'' +
                ", groupMemberFirstName='" + groupMemberFirstName + '\'' +
                ", groupMemberLastName='" + groupMemberLastName + '\'' +
                '}';
    }
}
