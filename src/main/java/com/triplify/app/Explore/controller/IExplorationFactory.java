package com.triplify.app.Explore.controller;

import com.triplify.app.Explore.model.Exploration;
import org.springframework.stereotype.Service;

@Service
public interface IExplorationFactory {
    public Exploration makeExploration();
}
