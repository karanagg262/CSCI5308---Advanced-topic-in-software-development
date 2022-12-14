package com.triplify.app.controller;

import com.triplify.app.database.DatabaseConnection;
import com.triplify.app.database.DatabaseExceptionHandler;
import com.triplify.app.model.UserTable;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.sql.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@CrossOrigin
public class UserController {

    UserRegistrationQueryBuilder userRegistrationQueryBuild = new UserRegistrationQueryBuilder();

    @PostMapping("users/login")
    public Map<String, Object> loginUser(@RequestParam("emailAddress") String username,
                            @RequestParam("password") String password) throws DatabaseExceptionHandler {
        List<UserTable> listOfUsers = getAllUsers();
        Map<String, Object> response = new HashMap<>();
        for(UserTable user : listOfUsers){
            if(user.getEmailAddress().equalsIgnoreCase(username) &&
                user.getPassword().equalsIgnoreCase(password)){
                user.setLoggedIn(true);
                response.put("SUCCESS", true);
                response.put("USERNAME", username);
                response.put("MESSAGE", "Login successful");
            }
            else{
                response.put("SUCCESS", false);
                response.put("MESSAGE", "Incorrect username or password");
            }
        }
        return response;
    }

    @GetMapping("/users")
    public List<UserTable> getAllUsers() throws DatabaseExceptionHandler {
        Connection connection = DatabaseConnection.getInstance().getDatabaseConnection();
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

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return listOfUserTables;
    }

    @PostMapping(value = "/users/register", consumes = {"multipart/form-data"})
    public Map<String, Object> register( @RequestParam("username") String username,
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
        userTable.setUsername(username);
        userTable.setLoggedIn(false);
        userTable.setUsername(username);
        userTable.setDob(dob);
        byte[] img = new byte[0];
        try {
            img = imageFile.getBytes();
            userTable.setProfPic(img);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        List<UserTable> listOfUsers = getAllUsers();
        Map<String, Object> response = new HashMap<>();
        for(UserTable user : listOfUsers){
            if(user.getUsername().equals(userTable.getUsername())){
                response.put("MESSAGE", "User already exists!");
                response.put("REDIRECT", true);
                response.put("USERNAME", username);
                response.put("SUCCESS", false);
            }
        }

        try(final Connection connection = DatabaseConnection.getInstance().getDatabaseConnection();
            final Statement statement = connection.createStatement()){

            final int rowInserted = userRegistrationQueryBuild.insertQuery(userTable, connection);
            if(rowInserted > 0){
                System.out.println("User record inserted into DB");
            }
            response.put("SUCCESS", true);
            response.put("MESSAGE", "registration successful");

        } catch (SQLException e) {
            System.out.println(e.getMessage());
            response.put("SUCCESS", false);
            response.put("MESSAGE", "Registration failed");
        }
        return response;
    }
}
