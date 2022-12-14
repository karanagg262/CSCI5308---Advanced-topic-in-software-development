package com.triplify.app.Group.controller;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

@DisplayName("Testing for select query in group!!")
public class GroupDetailsSelectQueryTest {

    @Test
    public void selectQueryForGroupTest(){

        GroupDetailsSelectQuery groupDetailsSelectQuery = new GroupDetailsSelectQuery();

        final String expectedSelectQuery = groupDetailsSelectQuery.selectQueryForGroup();
        final String actualSelectQuery = "SELECT * from group_details";
        Assertions.assertEquals(expectedSelectQuery,actualSelectQuery,"Incorrect Select Query Has been generated!!");
    }

}
