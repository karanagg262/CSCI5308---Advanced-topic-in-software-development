package com.triplify.app.expenseFeature.controller;

import com.triplify.app.expenseFeature.model.AddExpenses;
import com.triplify.app.expenseFeature.model.Expenses;
import org.springframework.web.bind.annotation.RequestBody;

public interface IAddExpensesController {
    public void postExpense(AddExpenses expenses);
    public void settleExpense(Expenses expenses);
}
