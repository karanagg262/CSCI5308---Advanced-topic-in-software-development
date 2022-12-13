package com.triplify.app.Explore.exception;

public class ExplorationException extends Exception{

    private final String errorMessage;

    public ExplorationException(String errorMessage){
        super(errorMessage);
        this.errorMessage = errorMessage;
    }

    public String getErrorMessage() {
        return errorMessage;
    }

    @Override
    public String toString() {
        return "ExplorationException{" + "errorMessage='" + errorMessage + '\'' + '}';
    }
}
