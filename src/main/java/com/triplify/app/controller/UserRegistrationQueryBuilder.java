package com.triplify.app.controller;

import com.triplify.app.model.UserTable;
import static com.triplify.app.database.UserDatabaseConstant.*;

public class UserRegistrationQueryBuilder {

    public String insertQuery(final UserTable userTable){
        return "INSERT INTO "+ user_table + "(" +
                user_table_first_name + ", " +
                user_table_last_name + ", " +
                user_table_email_address + ", " +
                user_table_password + ", " +
                user_table_is_logged_in + ") " +
                "VALUES (" +
                "\"" +userTable.getFirstname()+ "\", " +
                "\"" +userTable.getLastname()+ "\", " +
                "\"" +userTable.getEmailAddress() + "\", " +
                "\"" +userTable.getPassword() + "\", " +
                userTable.isLoggedIn() +
                ");";
    }
}
