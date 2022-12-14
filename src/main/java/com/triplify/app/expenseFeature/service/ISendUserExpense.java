package com.triplify.app.expenseFeature.service;

import com.triplify.app.expenseFeature.model.Expenses;

import java.util.List;

public interface ISendUserExpense {
    public List<Expenses> fetchMyExpenses(String userid);
    public float calculateTotalExpense(String userid);
}
