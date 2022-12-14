package com.triplify.app.expenseFeature.service;

import com.triplify.app.expenseFeature.model.AddExpenses;
import com.triplify.app.expenseFeature.model.Expenses;

import java.util.Map;

public interface IAddNewExpenses {
    public Map<String, Object> splitExpenses(AddExpenses expenses);
    public Map<String, Object> setExpenses(AddExpenses expenses, float splittedAmount, String useridlist);
    public Map<String, Object> settleMyExpenses(Expenses expenses);
}
