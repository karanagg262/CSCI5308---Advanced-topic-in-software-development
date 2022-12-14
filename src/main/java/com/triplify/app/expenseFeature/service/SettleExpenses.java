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
    public HashMap<Long, Float> fetchSettleExpenses(Long userid, Long groupid){
        HashMap<Long, Float> map = new HashMap<Long, Float>();
        try {
            Connection connection =
                    DatabaseConnection.getInstance().getDatabaseConnection();
            ResultSet userDetailsResultSet =
                    connection.createStatement().executeQuery("select * from User_expenses where id_group_details = " + groupid +
                            " and ( from_user_id = " + userid + " or to_user_id= " + userid + ");");
            while (userDetailsResultSet.next()) {

                Float amount = userDetailsResultSet.getFloat("" + expenses_table_amount);
                Long from_user_id = userDetailsResultSet.getLong("" + expenses_table_from_user_id);
                Long to_user_id = userDetailsResultSet.getLong("" + expenses_table_to_user_id);
                if(!from_user_id.equals(to_user_id)){
                        if(from_user_id.equals(userid)){
                            if(map.containsKey(to_user_id)) {
                                map.put(to_user_id, map.get(to_user_id) + (-1 * amount));
                            } else {
                                map.put(to_user_id, -1 * amount);
                            }
                        }
                        if(to_user_id.equals(userid)){
                            if(map.containsKey(to_user_id)) {
                                map.put(from_user_id, map.get(from_user_id) + amount);
                            } else {
                                map.put(from_user_id, amount);
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
