package com.triplify.app.exploreFeature.controller;

import com.triplify.app.exploreFeature.exception.ExplorationException;
import com.triplify.app.exploreFeature.model.Exploration;

import java.sql.SQLException;
import java.util.List;

public interface IExplorationController {

    List<Exploration> searchGroups(final String location) throws ExplorationException, SQLException;

}
