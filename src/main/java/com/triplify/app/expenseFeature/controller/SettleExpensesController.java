package com.triplify.app.expenseFeature.controller;

import com.triplify.app.expenseFeature.service.SettleExpenses;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;

@RestController
@RequestMapping(path = "api/v1/users")
@CrossOrigin

public class SettleExpensesController {
    @GetMapping("/settleExpense")
    public HashMap<String, Float> getAllExpenseDetails(@RequestParam String username, @RequestParam Long groupid) {
        SettleExpenses settleExpenses = new SettleExpenses();
        return settleExpenses.fetchSettleExpenses(username, groupid);
    }
}
