package com.triplify.app.exploreFeature.database;

import com.triplify.app.Explore.database.ExplorationTableInsertQueryBuilder;
import com.triplify.app.Explore.model.Exploration;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

@DisplayName("Exploration Table Insert Query Builder Testing!!")
public class ExplorationTableInsertQueryBuilderTest {

    @Test
    @DisplayName("Insert Query By Exploration Object!!")
    public void explorationTableInsertQueryTest(){
        final String groupName = "Australia";
        final String groupDestination = "AustraliaHome";
        final int numberOfMembers = 4;
        final Long groupId = Long.valueOf(14);

        Exploration exploration = new Exploration(groupName,groupDestination,numberOfMembers,groupId);

        ExplorationTableInsertQueryBuilder explorationTableInsertQueryBuilder =
                ExplorationTableInsertQueryBuilder.getInstance();

        final String expectedInsertQuery = explorationTableInsertQueryBuilder.explorationTableInsertQuery(exploration);
        final String actualInsertQuery =
                "INSERT INTO explore_table(group_name, group_description, group_members, id_group) VALUES (\"Australia\", \"AustraliaHome\", \"4\", 14);";

        System.out.println(expectedInsertQuery);
        System.out.println(actualInsertQuery);
        Assertions.assertEquals(expectedInsertQuery,actualInsertQuery,"Incorrect Insert Query Has been generated!!");
    }

}
