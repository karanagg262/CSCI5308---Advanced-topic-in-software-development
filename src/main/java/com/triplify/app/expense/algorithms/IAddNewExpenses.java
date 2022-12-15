package com.triplify.app.expense.algorithms;

import com.triplify.app.expense.model.AddExpenses;
import com.triplify.app.expense.model.Expenses;

import java.util.Map;

public interface IAddNewExpenses {
    public Map<String, Object> splitExpenses(AddExpenses expenses);
    public Map<String, Object> setExpenses(AddExpenses expenses, float splittedAmount, String useridlist);
    public Map<String, Object> settleMyExpenses(Expenses expenses);
}
