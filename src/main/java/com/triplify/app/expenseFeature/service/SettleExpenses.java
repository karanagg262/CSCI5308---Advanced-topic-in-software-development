package com.triplify.app.expenseFeature.service;

import com.triplify.app.database.DatabaseConnection;
import com.triplify.app.database.DatabaseExceptionHandler;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.Map;

import static com.triplify.app.expenseFeature.database.ExpenseDatabaseContstant.*;

public class SettleExpenses implements ISettleExpenses {
    public HashMap<String, Float> fetchSettleExpenses(String username, Long groupid){
        HashMap<String, Float> map = new HashMap<String, Float>();
        try {
            Connection connection =
                    DatabaseConnection.getInstance().getDatabaseConnection();
            ResultSet userDetailsResultSet =
                    connection.createStatement().executeQuery("select * from User_expenses where id_group_details = " + groupid +
                            " and ( from_username = " + username + " or to_username = " + username + ");");
            while (userDetailsResultSet.next()) {

                Float amount = userDetailsResultSet.getFloat("" + expenses_table_amount);
                String from_username = userDetailsResultSet.getString("" + expenses_table_from_username);
                String to_username = userDetailsResultSet.getString("" + expenses_table_to_username);
                if(!from_username.equals(to_username)){
                        if(from_username.equals(username)){
                            if(map.containsKey(to_username)) {
                                map.put(to_username, map.get(to_username) + (-1 * amount));
                            } else {
                                map.put(to_username, -1 * amount);
                            }
                        }
                        if(to_username.equals(username)){
                            if(map.containsKey(to_username)) {
                                map.put(from_username, map.get(from_username) + amount);
                            } else {
                                map.put(from_username, amount);
                            }
                        }
                }
            }
            for(Map.Entry m:map.entrySet()){
                System.out.println(m.getKey()+" "+m.getValue());
            }
        }  catch (SQLException e) {
            throw new RuntimeException(e);
        }  catch (DatabaseExceptionHandler e) {
            throw new RuntimeException(e);
        }

        return map;
    }
}
