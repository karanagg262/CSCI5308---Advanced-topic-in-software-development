package com.triplify.app.Group.controller;

import com.triplify.app.database.DatabaseExceptionHandler;
import com.triplify.app.Group.model.GroupDetails;
import org.springframework.web.bind.annotation.*;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@CrossOrigin
public class GroupController implements IGroupController {

    private GroupDetails createGroupDetails() {
        IGroupCreationFactory iGroupCreationFactory =
                GroupFactory.factorySingleton();
        return iGroupCreationFactory.makeGroupDetails();
    }

    @GetMapping("/groups")
    public List<GroupDetails> getAllGroupDetails() throws DatabaseExceptionHandler {
        GroupDetails groupDetails = createGroupDetails();
        List<GroupDetails> groupDetailsList = groupDetails.createAllGroupDetailsList();
        return groupDetailsList;
    }

    @PostMapping("/groups/createGroup")
    public Map<String, Object> createGroup(@RequestParam("groupName") String groupName,
                                           @RequestParam("groupStartDate") String tripStartDate,
                                           @RequestParam("groupEndDate") String tripEndDate,
                                           @RequestParam("groupDestination") String destination,
                                           @RequestParam("groupDescription") String groupDescription,
                                           @RequestParam("groupType") String tripType,
                                           @RequestParam("creator_user_id") Long creator_user_id)
            throws DatabaseExceptionHandler {

        GroupDetails groupDetails = createGroupDetails();
        groupDetails.setGroupName(groupName);
        groupDetails.setTripStartDate(tripStartDate);
        groupDetails.setTripEndDate(tripEndDate);
        groupDetails.setDestination(destination);
        groupDetails.setGroupDescription(groupDescription);
        groupDetails.setTripType(tripType);
        groupDetails.setCreator_user_id(creator_user_id);

        List<GroupDetails> listOfGroups = groupDetails.createAllGroupDetailsList();
        Map<String, Object> response = new HashMap<>();

        for(GroupDetails group : listOfGroups){
            if(group.getGroupName().equalsIgnoreCase(groupDetails.getGroupName()) &&
                group.getGroupDescription().equalsIgnoreCase(groupDetails.getGroupDescription()) &&
                group.getTripStartDate().equalsIgnoreCase(groupDetails.getTripStartDate()) &&
                group.getTripEndDate().equalsIgnoreCase(groupDetails.getTripEndDate()) &&
                group.getTripType().equalsIgnoreCase(groupDetails.getTripType()) &&
                group.getDestination().equalsIgnoreCase(groupDetails.getDestination())){

                response.put("SUCCESS", false);
                response.put("MESSAGE", "Group is already exists!!");
                return response;
            }
        }

        GroupDetails groupDetailsForCreateGroupResponse = createGroupDetails();
        response = groupDetailsForCreateGroupResponse.createGroupResponse(groupDetails);
        return response;
    }

}
