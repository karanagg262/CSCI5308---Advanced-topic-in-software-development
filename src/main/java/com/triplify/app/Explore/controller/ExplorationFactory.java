package com.triplify.app.Explore.controller;

import com.triplify.app.Explore.model.Exploration;

public class ExplorationFactory implements IExplorationFactory{

    private static ExplorationFactory explorationFactory = null;

    public static IExplorationFactory factorySingleton(){
        if (explorationFactory == null){
            explorationFactory = new ExplorationFactory();
        }
        return explorationFactory;
    }

    @Override
    public Exploration makeExploration() {
        return new Exploration();
    }
}
