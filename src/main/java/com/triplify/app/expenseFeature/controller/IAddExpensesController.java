package com.triplify.app.expenseFeature.controller;

import com.triplify.app.expenseFeature.model.AddExpenses;
import com.triplify.app.expenseFeature.model.Expenses;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;

import java.sql.Date;
import java.util.ArrayList;
import java.util.Map;

public interface IAddExpensesController {
    public Map<String, Object> postExpense(String description, float amount, String currency, ArrayList<String> usernamelist,
                                           String paidbyusername, long groupid, String date_added);
    public Map<String, Object> settleExpense(float amount, String fromusername,
                              String tousername, long groupid);
}
