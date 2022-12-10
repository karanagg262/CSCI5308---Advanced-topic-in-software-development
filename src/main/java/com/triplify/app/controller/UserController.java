package com.triplify.app.controller;

import com.triplify.app.database.DatabaseConnection;
import com.triplify.app.database.DatabaseExceptionHandler;
import com.triplify.app.model.UserTable;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.sql.rowset.serial.SerialBlob;
import java.io.IOException;
import java.sql.*;
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

                try{
                    Connection connection = DatabaseConnection.getInstance().getDatabaseConnection();
                    Statement statement = connection.createStatement();

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
                String username = resultSet.getString("username");
                String firstname = resultSet.getString("firstname");
                String lastname = resultSet.getString("lastname");
                String emailAddress = resultSet.getString("email_address");
                String password = resultSet.getString("password");
                boolean isLoggedIn = resultSet.getBoolean("is_logged_in");
                Blob image = resultSet.getBlob("image");

                UserTable userTable = new UserTable(id,username,firstname,lastname,emailAddress,password,isLoggedIn, image);
                listOfUserTables.add(userTable);
            }

            if(connection!=null){
                connection.close();
            }

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return listOfUserTables;
    }

    @PostMapping(value = "/users/register", consumes = {"multipart/form-data"})
    public String register( @RequestParam("username") String username,
                            @RequestParam("first_name") String firstname,
                            @RequestParam("last_name") String lastname,
                            @RequestParam("email") String emailAddress,
                            @RequestParam("password") String password,
                            @RequestParam("dob") String dob,
                            @RequestParam("avatar") MultipartFile imageFile) throws DatabaseExceptionHandler{

        UserTable userTable = new UserTable();
        userTable.setFirstname(firstname);
        userTable.setLastname(lastname);
        userTable.setEmailAddress(emailAddress);
        userTable.setPassword(password);
        userTable.setLoggedIn(false);
        userTable.setUsername(username);
        userTable.setDob(dob);
        try{
            byte[] img = imageFile.getBytes();
            try {
                Blob imgblob = new SerialBlob(img);
            } catch (SQLException e) {
                throw new RuntimeException(e);
            }
            userTable.setProfPic(img);
        } catch (IOException e) {
            e.printStackTrace();
        }

        List<UserTable> listOfUsers = getAllUsers();
        for(UserTable user : listOfUsers){
            if(user.getUsername().equals(userTable.getUsername())){
                System.out.println("User already exists!!");
                return "USER_ALREADY_EXISTS";
            }
        }

        try(final Connection connection = DatabaseConnection.getInstance().getDatabaseConnection();
            final Statement statement = connection.createStatement()){

            final int rowInserted =
                    userRegistrationQueryBuild.insertQuery(userTable, connection);

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
