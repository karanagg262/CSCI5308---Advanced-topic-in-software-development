package com.triplify.app.group.controller;

import com.triplify.app.group.exception.GroupException;
import com.triplify.app.database.DatabaseExceptionHandler;

import java.sql.SQLException;
import java.util.Map;

public interface IGroupController {

    Map<String, Object> createGroup(String groupName, String tripStartDate,
                                    String tripEndDate, String destination,
                                    String groupDescription, String tripType, String username)
        throws GroupException, DatabaseExceptionHandler, SQLException;
}
