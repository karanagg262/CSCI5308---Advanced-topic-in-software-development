package com.triplify.app.controller;

import com.triplify.app.database.DatabaseConnection;
import com.triplify.app.database.DatabaseExceptionHandler;
import com.triplify.app.model.UserTable;
import org.springframework.web.bind.annotation.*;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import static com.triplify.app.database.UserDatabaseConstant.*;

@RestController
@CrossOrigin
public class UserController {

    UserRegistrationQueryBuilder userRegistrationQueryBuild
            = new UserRegistrationQueryBuilder();

    @PostMapping("users/login")
    public String loginUser(@RequestParam("emailAddress") String emailAddress,
                            @RequestParam("password") String password) throws DatabaseExceptionHandler {
        List<UserTable> listOfUsers = getAllUsers();

        for(UserTable user : listOfUsers){
            if(user.getEmailAddress().equalsIgnoreCase(emailAddress) &&
                user.getPassword().equalsIgnoreCase(password)){
                user.setLoggedIn(true);
                String query =
                        "UPDATE "+ user_table + " SET " + user_table_is_logged_in + " = true " +
                                "WHERE " + user_table_email_address + " = '" +user.getEmailAddress() + "' ";

                try(final Connection connection = DatabaseConnection.getInstance().getDatabaseConnection();
                    final Statement statement = connection.createStatement()){

                    final int rowUpdated =
                            statement.executeUpdate(query,Statement.RETURN_GENERATED_KEYS);

                    if(rowUpdated > 0){
                        return "LOGGED_IN_SUCCESSFULLY_DONE";
                    }
                }catch (Exception e){
                    System.out.println(e.getMessage());
                }
            }
        }
        return "SOMETHING_WENT_WRONG";
    }

    @GetMapping("/users")
    public List<UserTable> getAllUsers() throws DatabaseExceptionHandler {
        Connection connection =
                DatabaseConnection.getInstance().getDatabaseConnection();
        List<UserTable> listOfUserTables = new ArrayList<>();

        try {
            System.out.println(connection.getCatalog());

            ResultSet resultSet =
                    connection.createStatement().executeQuery("select * from user_table");

            while (resultSet.next()){
                Long id = resultSet.getLong("id");
                String firstname = resultSet.getString("firstname");
                String lastname = resultSet.getString("lastname");
                String emailAddress = resultSet.getString("email_address");
                String password = resultSet.getString("password");
                boolean isLoggedIn = resultSet.getBoolean("is_logged_in");

                UserTable userTable = new UserTable(id,firstname,lastname,emailAddress,password,isLoggedIn);
                listOfUserTables.add(userTable);
            }

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return listOfUserTables;
    }

    @PostMapping("/users/register")
    public String register(@RequestParam("firstname") String firstname,
                            @RequestParam("lastname") String lastname,
                            @RequestParam("emailAddress") String emailAddress,
                            @RequestParam("password") String password) throws DatabaseExceptionHandler{

        UserTable userTable = new UserTable();
        userTable.setFirstname(firstname);
        userTable.setLastname(lastname);
        userTable.setEmailAddress(emailAddress);
        userTable.setPassword(password);
        userTable.setLoggedIn(false);

        List<UserTable> listOfUsers = getAllUsers();
        for(UserTable user : listOfUsers){
            if(user.equals(userTable)){
                System.out.println("User is already exists!!");
                return "USER_ALREADY_EXISTS";
            }
        }

        try(final Connection connection = DatabaseConnection.getInstance().getDatabaseConnection();
            final Statement statement = connection.createStatement()){

            final String insertQuery = userRegistrationQueryBuild.insertQuery(userTable);
            final int rowInserted =
                    statement.executeUpdate(insertQuery, Statement.RETURN_GENERATED_KEYS);

            if(rowInserted > 0){
                System.out.println("Yes row is inserted !!");
            }
            return "SUCCESSFULLY_REGISTERED";

        } catch (SQLException e) {
            System.out.println(e.getMessage());
            return "REGISTRATION_FAILED";
        }
    }
}
