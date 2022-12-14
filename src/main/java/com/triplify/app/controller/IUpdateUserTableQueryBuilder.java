package com.triplify.app.controller;

import com.triplify.app.model.UserTable;

import java.sql.Connection;

public interface IUpdateUserTableQueryBuilder {

    int updateQuery(UserTable userTable, Connection connection);

}
