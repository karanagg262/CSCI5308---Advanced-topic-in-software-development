package com.triplify.app.expenseFeature.controller;

import com.triplify.app.expenseFeature.model.AddExpenses;
import com.triplify.app.expenseFeature.model.Expenses;
import com.triplify.app.expenseFeature.service.AddNewExpenses;

import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(path = "api/v1/users")

public class AddExpensesController implements  IAddExpensesController{
    @PostMapping("/addexpenses")
    @Override
    public void postExpense(@RequestBody AddExpenses expenses) {
        AddNewExpenses addNewExpenses = new AddNewExpenses();
        addNewExpenses.splitExpenses(expenses);
    }

    @PostMapping("/settleexpenses")
    @Override
    public void settleExpense(@RequestBody Expenses expenses) {
        AddNewExpenses addNewExpenses = new AddNewExpenses();
        addNewExpenses.settleMyExpenses(expenses);
    }
}