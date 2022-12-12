package com.triplify.app.model;

import javax.persistence.*;

@Entity
public class GroupDetails {
    private Long id;
    private String groupName;
    private String tripStartDate;
    private String tripEndDate;
    private String destination;
    private String groupDescription;
    private String tripType;
    private Long user_id;

    public GroupDetails(){

    }

    public GroupDetails(String groupName, String tripStartDate, String tripEndDate, String destination, String tripType, String groupDescription, Long user_id) {
        this.groupName = groupName;
        this.tripStartDate = tripStartDate;
        this.tripEndDate = tripEndDate;
        this.destination = destination;
        this.tripType = tripType;
        this.groupDescription = groupDescription;
        this.user_id = user_id;
    }

    public GroupDetails(Long id, String groupName, String tripStartDate, String tripEndDate, String destination, String tripType, String groupDescription, Long user_id) {
        this.id = id;
        this.groupName = groupName;
        this.tripStartDate = tripStartDate;
        this.tripEndDate = tripEndDate;
        this.destination = destination;
        this.tripType = tripType;
        this.groupDescription = groupDescription;
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

    public String getTripStartDate() {
        return tripStartDate;
    }

    public void setTripStartDate(String tripStartDate) {
        this.tripStartDate = tripStartDate;
    }

    public String getTripEndDate() {
        return tripEndDate;
    }

    public void setTripEndDate(String tripEndDate) {
        this.tripEndDate = tripEndDate;
    }

    public String getDestination() {
        return destination;
    }

    public void setDestination(String destination) {
        this.destination = destination;
    }
    public String getGroupDescription() {
        return groupDescription;
    }

    public void setGroupDescription(String groupDescription) {
        this.groupDescription = groupDescription;
    }

    public String getTripType() {
        return tripType;
    }

    public void setTripType(String tripType) {
        this.tripType = tripType;
    }

    public Long getUser_id() {
        return user_id;
    }

    public void setUser_id(Long user_id) {
        this.user_id = user_id;
    }

    @Override
    public String toString() {
        return "GroupDetails{" +
                "id=" + id +
                ", groupName='" + groupName + '\'' +
                ", tripStartDate='" + tripStartDate + '\'' +
                ", tripEndDate='" + tripEndDate + '\'' +
                ", destination='" + destination + '\'' +
                ", groupDescription='" + groupDescription + '\'' +
                ", tripType='" + tripType + '\'' +
                ", user_id=" + user_id +
                '}';
    }
}
