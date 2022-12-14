package com.triplify.app.controller;

import com.triplify.app.Explore.controller.ExplorationFactory;
import com.triplify.app.Explore.controller.IExplorationFactory;
import com.triplify.app.model.UserTable;

public class UserFactory implements IUserCreationFactory{

    private static UserFactory userFactory = null;

    public static IUserCreationFactory factorySingleton(){
        if (userFactory == null){
            userFactory = new UserFactory();
        }
        return userFactory;
    }

    @Override
    public UserTable makeUserTable() {
        return new UserTable();
    }
}
