package com.triplify.app.Explore.controller;

import com.triplify.app.database.DatabaseExceptionHandler;
import com.triplify.app.Explore.exception.ExplorationException;
import com.triplify.app.Explore.model.Exploration;

import java.sql.SQLException;
import java.util.List;

public interface IExplorationController {

    List<Exploration> searchGroups(final String location) throws ExplorationException, SQLException, DatabaseExceptionHandler;

    String joinGroupByUsernameAndGroupId(String username, Long groupId) throws DatabaseExceptionHandler, SQLException;

}
