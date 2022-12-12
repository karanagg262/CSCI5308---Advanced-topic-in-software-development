package com.triplify.app.exploreFeature.model;

public class Exploration {
    private Long explore_id;
    private String groupName;
    private String placeDescription;
    private int numberOfMembers;

    private Long groupId;

    public Exploration(){

    }

    public Exploration(String groupName, String placeDescription, int numberOfMembers, Long groupId) {
        this.groupName = groupName;
        this.placeDescription = placeDescription;
        this.numberOfMembers = numberOfMembers;
        this.groupId = groupId;
    }

    public Exploration(Long explore_id, String groupName, String placeDescription, int numberOfMembers, Long groupId) {
        this.explore_id = explore_id;
        this.groupName = groupName;
        this.placeDescription = placeDescription;
        this.numberOfMembers = numberOfMembers;
        this.groupId = groupId;
    }

    public Long getExplore_id() {
        return explore_id;
    }

    public void setExplore_id(Long explore_id) {
        this.explore_id = explore_id;
    }

    public String getGroupName() {
        return groupName;
    }

    public void setGroupName(String groupName) {
        this.groupName = groupName;
    }

    public String getPlaceDescription() {
        return placeDescription;
    }

    public void setPlaceDescription(String placeDescription) {
        this.placeDescription = placeDescription;
    }

    public int getNumberOfMembers() {
        return numberOfMembers;
    }

    public void setNumberOfMembers(int numberOfMembers) {
        this.numberOfMembers = numberOfMembers;
    }

    public Long getGroupId() {
        return groupId;
    }

    public void setGroupId(Long groupId) {
        this.groupId = groupId;
    }

    @Override
    public String toString() {
        return "Exploration{" +
                "explore_id=" + explore_id +
                ", groupName='" + groupName + '\'' +
                ", placeDescription='" + placeDescription + '\'' +
                ", numberOfMembers=" + numberOfMembers +
                ", groupId=" + groupId +
                '}';
    }
}
