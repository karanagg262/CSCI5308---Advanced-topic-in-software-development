package com.triplify.app.Group.exception;

public class GroupException extends Exception{
    private final String errorMessage;

    public GroupException(String errorMessage){
        super(errorMessage);
        this.errorMessage = errorMessage;
    }

    public String getErrorMessage() {
        return errorMessage;
    }

    @Override
    public String toString() {
        return "GroupException{" + "errorMessage='" + errorMessage + '\'' + '}';
    }
}
