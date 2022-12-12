package com.triplify.app.exploreFeature.database;

import com.triplify.app.exploreFeature.model.Exploration;

public interface IExplorationTableInsertQueryBuilder {
    String explorationTableInsertQuery(Exploration exploration);
}
