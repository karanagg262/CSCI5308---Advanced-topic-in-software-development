package com.triplify.app.expenseFeature.service;

import com.triplify.app.expenseFeature.model.Expenses;

import java.util.List;

public interface ISendUserExpense {
    public List<Expenses> fetchMyExpenses(Long userid);
    public float calculateTotalExpense(Long userid);
}
