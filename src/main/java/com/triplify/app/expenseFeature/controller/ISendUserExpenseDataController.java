package com.triplify.app.expenseFeature.controller;

import com.triplify.app.expenseFeature.model.Expenses;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

public interface ISendUserExpenseDataController {
    public List<Expenses> getAllExpenseDetails(String username, long groupid);
    public float calculateUserTotalExpense(String username);
}
