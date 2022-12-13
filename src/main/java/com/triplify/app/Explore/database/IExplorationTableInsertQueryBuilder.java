package com.triplify.app.Explore.database;

import com.triplify.app.Explore.model.Exploration;

public interface IExplorationTableInsertQueryBuilder {
    String explorationTableInsertQuery(Exploration exploration);
}
