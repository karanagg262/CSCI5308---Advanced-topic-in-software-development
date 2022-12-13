package com.triplify.app.expenseFeature.database;

import com.triplify.app.expenseFeature.model.Expenses;

import java.sql.Connection;

public interface IAddExpensesQueryBuilder {
    public int insertExpenseQuery(final Expenses expenses, Connection connection);
}
