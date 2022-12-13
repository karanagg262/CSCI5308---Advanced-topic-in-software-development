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

public class AddNewExpenses implements IAddNewExpenses {
    private static List<Expenses> addUserExpense = new ArrayList<>();
    @Override
    public void splitExpenses(AddExpenses expenses) {
        ArrayList<Long> useridlist = expenses.getUseridlist();
        int size = useridlist.size();
        float split = expenses.getAmount()/size;
        int id = -1;
        for (int i = 0; i < size; i++) {
            if (useridlist.get(i).equals(expenses.getPaidbyuserid()))
            {
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
    @Override
    public void setExpenses(AddExpenses expenses, float splittedAmount, Long useridlist)
    {
        try{

            Connection connection =
                    DatabaseConnection.getInstance().getDatabaseConnection();
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
            AddExpensesQueryBuilder addExpensesQueryBuilder = new AddExpensesQueryBuilder();
            final int rowInserted =
                    addExpensesQueryBuilder.insertExpenseQuery(expense, connection);

            if(rowInserted > 0){
                System.out.println("Yes row is inserted !!");
            }
            if(connection!=null){
                connection.close();
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        } catch (DatabaseExceptionHandler e) {
            throw new RuntimeException(e);
        }
    }
    @Override
    public void settleMyExpenses(Expenses expenses){
        try {
        Connection connection =
                DatabaseConnection.getInstance().getDatabaseConnection();
            expenses.setAmount(expenses.getAmount()*-1);
            AddExpensesQueryBuilder addExpensesQueryBuilder = new AddExpensesQueryBuilder();
            final int rowInserted =
                    addExpensesQueryBuilder.insertExpenseQuery(expenses, connection);

            if(rowInserted > 0){
                System.out.println("Yes row is inserted !!");
            }
            if(connection!=null){
                connection.close();
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        } catch (DatabaseExceptionHandler e) {
            throw new RuntimeException(e);
        }
    }

}
