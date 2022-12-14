package com.triplify.app.expenseFeature.controller;

import com.triplify.app.expenseFeature.model.AddExpenses;
import com.triplify.app.expenseFeature.model.Expenses;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.ArrayList;

public interface IAddExpensesController {
    public void postExpense(String description, float amount, String currency, ArrayList<String> usernamelist,
                            String paidbyusername, long groupid);
    public void settleExpense(float amount, String fromusername,
                              String tousername, long groupid);
}
