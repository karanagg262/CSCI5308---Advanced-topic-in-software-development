package com.triplify.app.exploreFeature.database;

import com.triplify.app.exploreFeature.model.Exploration;

import static com.triplify.app.exploreFeature.database.ExplorationDatabaseConstant.*;

public class ExplorationTableInsertQueryBuilder implements IExplorationTableInsertQueryBuilder {

    private static ExplorationTableInsertQueryBuilder instance;

    public ExplorationTableInsertQueryBuilder(){

    }

    public static ExplorationTableInsertQueryBuilder getInstance(){
        if (instance == null){
            instance = new ExplorationTableInsertQueryBuilder();
        }
        return instance;
    }

    public String explorationTableInsertQuery(final Exploration exploration) {
        return "INSERT INTO " + exploration_table_name + "(" +
                exploration_group_name + ", " +
                exploration_group_description + ", " +
                exploration_group_members + ", " +
                exploration_group_id + ") " +
                "VALUES (" +
                "\"" +exploration.getGroupName() + "\", " +
                "\"" +exploration.getPlaceDescription() + "\", " +
                "\"" +exploration.getNumberOfMembers() + "\", " +
                exploration.getGroupId() +
                ");";
    }
}
