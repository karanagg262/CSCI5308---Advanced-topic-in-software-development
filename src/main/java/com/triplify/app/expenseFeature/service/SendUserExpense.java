package com.triplify.app.expenseFeature.service;

import com.triplify.app.database.DatabaseConnection;
import com.triplify.app.database.DatabaseExceptionHandler;
import com.triplify.app.expenseFeature.model.Expenses;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import static com.triplify.app.expenseFeature.database.ExpenseDatabaseContstant.*;
import static com.triplify.app.expenseFeature.database.ExpenseDatabaseContstant.expenses_table_id_group_details;

public class SendUserExpense implements ISendUserExpense {
    @Override
    public List<Expenses> fetchMyExpenses(Long userid) {
        List<Expenses> listOfuserExpenses = new ArrayList<>();
        try {
            Connection connection =
                    DatabaseConnection.getInstance().getDatabaseConnection();
            try {

                System.out.println(connection.getCatalog());

                ResultSet userDetailsResultSet =
                        connection.createStatement().executeQuery("select * from User_expenses");

                while (userDetailsResultSet.next()) {
                    Long from_user_id = userDetailsResultSet.getLong("" + expenses_table_from_user_id);
                    Long to_user_id = userDetailsResultSet.getLong("" + expenses_table_to_user_id);
                    if ((from_user_id.equals(userid) || to_user_id.equals(userid)) && !from_user_id.equals(to_user_id)) {

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
        }catch (DatabaseExceptionHandler dbe) {
            throw new RuntimeException(dbe);
        }
            return listOfuserExpenses;
    }
    @Override
    public float calculateTotalExpense(Long userid){
        long total = 0;
        try {
            Connection connection =
                    DatabaseConnection.getInstance().getDatabaseConnection();
                ResultSet userDetailsResultSet =
                    connection.createStatement().executeQuery("select * from User_expenses");
                while (userDetailsResultSet.next()) {
                    Long from_user_id = userDetailsResultSet.getLong("" + expenses_table_from_user_id);
                    Long to_user_id = userDetailsResultSet.getLong("" + expenses_table_to_user_id);
                    Float amount = userDetailsResultSet.getFloat("" + expenses_table_amount);
                    if(from_user_id.equals(userid) && (to_user_id.equals(userid))){
                        total = (long) (total + amount);
                    }
                    if(to_user_id.equals(userid) && !from_user_id.equals(to_user_id)) {
                        total = (long) (total + amount);
                    }
                }
                if (connection != null) {
                    connection.close();
                }

            } catch (SQLException e) {
                throw new RuntimeException(e);
            } catch (DatabaseExceptionHandler dbe) {
            throw new RuntimeException(dbe);
        }
        return total;
    }
}
