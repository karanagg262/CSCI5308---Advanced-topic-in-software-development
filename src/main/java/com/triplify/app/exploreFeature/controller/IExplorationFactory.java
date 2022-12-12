package com.triplify.app.exploreFeature.controller;

import com.triplify.app.exploreFeature.model.Exploration;
import org.springframework.stereotype.Service;

@Service
public interface IExplorationFactory {
    public Exploration makeExploration();
}
