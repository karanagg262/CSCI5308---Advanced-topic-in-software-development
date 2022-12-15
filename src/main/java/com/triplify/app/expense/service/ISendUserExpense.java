package com.triplify.app.expense.service;

import com.triplify.app.expense.model.Expenses;

import java.util.List;

public interface ISendUserExpense {
    public List<Expenses> fetchMyExpenses(String userid, long groupid);
    public float calculateTotalExpense(String userid);
}
