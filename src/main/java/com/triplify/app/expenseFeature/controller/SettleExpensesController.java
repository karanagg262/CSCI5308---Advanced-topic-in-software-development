package com.triplify.app.expenseFeature.controller;

import com.triplify.app.expenseFeature.service.SettleExpenses;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;

@RestController
@RequestMapping(path = "api/v1/users")
public class SettleExpensesController {
    @GetMapping("/settleExpense")
    public HashMap<Long, Float> getAllExpenseDetails(@RequestParam Long userid, @RequestParam Long groupid) {
        SettleExpenses settleExpenses = new SettleExpenses();
        return settleExpenses.fetchSettleExpenses(userid, groupid);
    }
}
