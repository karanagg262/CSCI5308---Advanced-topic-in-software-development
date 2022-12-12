package com.triplify.app.expenseFeature.service;

import com.triplify.app.database.DatabaseConnection;
import com.triplify.app.database.DatabaseExceptionHandler;
import com.triplify.app.expenseFeature.database.AddExpensesQueryBuilder;
import com.triplify.app.expenseFeature.model.Expenses;
import com.triplify.app.expenseFeature.model.AddExpenses;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class AddNewExpenses {
    private static List<Expenses> addUserExpense = new ArrayList<>();
    public static void splitExpenses(AddExpenses expenses) throws DatabaseExceptionHandler, SQLException {
        ArrayList<Long> useridlist = expenses.getUseridlist();
        int size = useridlist.size();
        float split = expenses.getAmount()/size;
        int id = -1;

        for (int i = 0; i < size; i++) {
            if (useridlist.get(i).equals(expenses.getPaidbyuserid())) {
                setExpenses(expenses, expenses.getAmount() - split, useridlist.get(i));
                id = i;
            } else {
                setExpenses(expenses, split * -1, useridlist.get(i));
            }
        }
        if (id < 0) {
            setExpenses(expenses, expenses.getAmount(), 0L);
        }

    }

    public static void setExpenses(AddExpenses expenses, float splittedAmount, Long useridlist)
            throws DatabaseExceptionHandler, SQLException {

        Connection connection =
                DatabaseConnection.getInstance().getDatabaseConnection();

        try {
            Expenses expense = new Expenses();
            expense.setId(expenses.getId());
            expense.setTransaction_id(expenses.getTransaction_id());
            expense.setDescription(expenses.getDescription());
            expense.setAmount(splittedAmount);
            expense.setCurrency(expenses.getCurrency());
            expense.setGroupid(expenses.getGroupid());
            expense.setFromuserid(expenses.getPaidbyuserid());
            expense.setTouserid(useridlist);
            addUserExpense.add(expense);

            final int rowInserted =
                    AddExpensesQueryBuilder.insertExpenseQuery(expense, connection);

            if(rowInserted > 0){
                System.out.println("Yes row is inserted !!");
            }
            if(connection!=null){
                connection.close();
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public static void settleMyExpenses(Expenses expenses) throws DatabaseExceptionHandler {
        Connection connection =
                DatabaseConnection.getInstance().getDatabaseConnection();
        try {
            expenses.setAmount(expenses.getAmount()*-1);
            final int rowInserted =
                    AddExpensesQueryBuilder.insertExpenseQuery(expenses, connection);

            if(rowInserted > 0){
                System.out.println("Yes row is inserted !!");
            }
            if(connection!=null){
                connection.close();
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

}
