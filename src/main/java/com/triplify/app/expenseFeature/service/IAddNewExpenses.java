package com.triplify.app.expenseFeature.service;

import com.triplify.app.expenseFeature.model.AddExpenses;
import com.triplify.app.expenseFeature.model.Expenses;

public interface IAddNewExpenses {
    public void splitExpenses(AddExpenses expenses);
    public void setExpenses(AddExpenses expenses, float splittedAmount, Long useridlist);
    public void settleMyExpenses(Expenses expenses);
}
