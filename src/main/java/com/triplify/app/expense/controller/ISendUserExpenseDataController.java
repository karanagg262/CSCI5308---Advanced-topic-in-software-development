package com.triplify.app.expense.controller;

import com.triplify.app.expense.model.Expenses;

import java.util.List;

public interface ISendUserExpenseDataController {
    public List<Expenses> getAllExpenseDetails(String username, long groupid);
    public float calculateUserTotalExpense(String username);
}
