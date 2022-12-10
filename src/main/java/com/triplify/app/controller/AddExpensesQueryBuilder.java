package com.triplify.app.controller;

import com.triplify.app.model.Expenses;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import static com.triplify.app.database.ExpenseDatabaseContstant.*;
public class AddExpensesQueryBuilder {
    public static int insertExpenseQuery(final Expenses expenses, Connection connection){
        String query = "INSERT INTO "+ expenses_table + "(" +
                expenses_table_transaction_id + ", " +
                expenses_table_description + ", " +
                expenses_table_amount + ", " +
                expenses_table_currency + ", " +
                expenses_table_user_id + ", " +
                expenses_table_id_group_details + ") " +
                "VALUES (?,?,?,?,?,?);";
        try{
            PreparedStatement pstmt = connection.prepareStatement(query);
            pstmt.setString(1, expenses.getTransaction_id());
            pstmt.setString(2, expenses.getDescription());
            pstmt.setFloat(3, expenses.getAmount());
            pstmt.setString(4, expenses.getCurrency());
            pstmt.setLong(5, expenses.getUserid());
            pstmt.setLong(6, expenses.getGroupid());
            return pstmt.executeUpdate();

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

    }
}
