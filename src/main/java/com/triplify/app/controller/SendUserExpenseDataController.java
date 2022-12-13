package com.triplify.app.controller;

import com.triplify.app.database.DatabaseConnection;
import com.triplify.app.database.DatabaseExceptionHandler;
import com.triplify.app.model.Expenses;
import org.springframework.web.bind.annotation.*;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import static com.triplify.app.database.ExpenseDatabaseContstant.*;

@RestController
@RequestMapping(path = "api/v1/users")
public class SendUserExpenseDataController {
    @GetMapping("/userexpenses")
    public List<Expenses> getAllExpenseDetails() throws DatabaseExceptionHandler {
        Connection connection =
                DatabaseConnection.getInstance().getDatabaseConnection();
        List<Expenses> listOfuserExpenses = new ArrayList<>();

        try {

            System.out.println(connection.getCatalog());

            ResultSet userDetailsResultSet =
                    connection.createStatement().executeQuery("select * from User_expenses");

            while (userDetailsResultSet.next()) {
                Long from_user_id = userDetailsResultSet.getLong("" + expenses_table_from_user_id);
                Long to_user_id = userDetailsResultSet.getLong("" + expenses_table_to_user_id);
                Long userid = 12343L;
                if((from_user_id.equals(userid) || to_user_id.equals(userid)) && !from_user_id.equals(to_user_id)) {

                    Long id = userDetailsResultSet.getLong("" + expenses_table_id);
                    String transaction_id = userDetailsResultSet.getString("" + expenses_table_transaction_id);
                    String description = userDetailsResultSet.getString("" + expenses_table_description);
                    Float amount = userDetailsResultSet.getFloat("" + expenses_table_amount);
                    String currency = userDetailsResultSet.getString("" + expenses_table_currency);
                    Long id_group_details = userDetailsResultSet.getLong("" + expenses_table_id_group_details);

                    Expenses expense = new Expenses();
                    expense.setId(id);
                    expense.setTransaction_id(transaction_id);
                    expense.setDescription(description);
                    expense.setAmount(amount);
                    expense.setCurrency(currency);
                    expense.setGroupid(id_group_details);
                    expense.setFromuserid(from_user_id);
                    expense.setTouserid(to_user_id);
                    listOfuserExpenses.add(expense);
                }
            }

            if (connection != null) {
                connection.close();
            }

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return listOfuserExpenses;
    }

    @GetMapping("/calculatetotal")
    public float calculateUserTotalExpense() throws DatabaseExceptionHandler {
        Connection connection =
                DatabaseConnection.getInstance().getDatabaseConnection();
        long total = 0;
        try {

            ResultSet userDetailsResultSet =
                    connection.createStatement().executeQuery("select * from User_expenses");
            while (userDetailsResultSet.next()) {
                Long from_user_id = userDetailsResultSet.getLong("" + expenses_table_from_user_id);
                Long to_user_id = userDetailsResultSet.getLong("" + expenses_table_to_user_id);
                Long userid = 12343L;
                if(from_user_id.equals(userid) || to_user_id.equals(userid)) {

                    Float amount = userDetailsResultSet.getFloat("" + expenses_table_amount);
                    total = (long) (total + amount);
                }
            }

            if (connection != null) {
                connection.close();
            }

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return total;
    }
}
