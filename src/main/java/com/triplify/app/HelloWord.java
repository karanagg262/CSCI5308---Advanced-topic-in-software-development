package com.triplify.app;

import com.triplify.app.database.DatabaseConnection;
import com.triplify.app.database.DatabaseExceptionHandler;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;

public class HelloWord {

    public static void main(String[] args) throws DatabaseExceptionHandler {
        Connection connection =
                DatabaseConnection.getInstance().getDatabaseConnection();

        /**
        *       As of now the code is committed for just database connection and testing
         *       we will make further changes...
        * */
        try {
            System.out.println(connection.getCatalog());

            ResultSet resultSet =
                    connection.createStatement().executeQuery("select * from UserTable");

            while (resultSet.next()){
                System.out.println(resultSet.getInt("idUserTable") + " "+
                        resultSet.getString("firstname")+ " "+
                        resultSet.getString("emailAddress"));
            }

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}
