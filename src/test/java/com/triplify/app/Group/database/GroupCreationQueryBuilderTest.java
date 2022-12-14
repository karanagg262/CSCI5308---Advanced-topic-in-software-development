package com.triplify.app.Group.database;

import com.triplify.app.Group.model.GroupDetails;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

@DisplayName("Testing Group Insert Query Builder")
public class GroupCreationQueryBuilderTest {

    @Test
    @DisplayName("Insert Query for Group Object!!")
    public void insertGroupQueryTest(){

        GroupDetails groupDetails = new GroupDetails();
        groupDetails.setGroupName("Australia");
        groupDetails.setGroupDescription("Coming Soon");
        groupDetails.setDestination("Australia");
        groupDetails.setTripStartDate("12-12-2022");
        groupDetails.setTripEndDate("12-14-2022");
        groupDetails.setTripType("Public");
        groupDetails.setCreator_user_id(Long.valueOf(15));

        GroupCreationQueryBuilder groupCreationQueryBuilder = new GroupCreationQueryBuilder();

        final String expectedInsertQuery = groupCreationQueryBuilder.insertGroupQuery(groupDetails);
        final String actualInsertQuery =
                "INSERT INTO group_details(group_name, trip_start_date, trip_end_date, destination, group_description, trip_type, user_id) VALUES (\"Australia\", \"12-12-2022\", \"12-14-2022\", \"Australia\", \"Coming Soon\", \"Public\", 15);";

        System.out.println(expectedInsertQuery);
        System.out.println(actualInsertQuery);
        Assertions.assertEquals(expectedInsertQuery,actualInsertQuery,"Incorrect Insert Query Has been generated!!");

    }

}
