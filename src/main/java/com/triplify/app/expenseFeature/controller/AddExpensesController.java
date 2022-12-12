package com.triplify.app.expenseFeature.controller;

import com.triplify.app.database.DatabaseConnection;
import com.triplify.app.database.DatabaseExceptionHandler;
import com.triplify.app.expenseFeature.database.AddExpensesQueryBuilder;
import com.triplify.app.expenseFeature.model.AddExpenses;
import com.triplify.app.expenseFeature.model.Expenses;
import com.triplify.app.expenseFeature.service.AddNewExpenses;

import org.springframework.web.bind.annotation.*;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
@RestController
@RequestMapping(path = "api/v1/users")

public class AddExpensesController {
    @PostMapping("/addexpenses")
    public void postExpense(@RequestBody AddExpenses expenses) throws DatabaseExceptionHandler, SQLException {

        AddNewExpenses.splitExpenses(expenses);
    }

    @PostMapping("/settleexpenses")
    public void settleExpense(@RequestBody Expenses expenses) throws DatabaseExceptionHandler, SQLException {
        AddNewExpenses.settleMyExpenses(expenses);
    }
}