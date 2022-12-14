package com.triplify.app.expenseFeature.controller;

import com.triplify.app.database.DatabaseExceptionHandler;
import com.triplify.app.expenseFeature.model.Expenses;
import com.triplify.app.expenseFeature.service.SendUserExpense;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(path = "api/v1/users")
@CrossOrigin

public class SendUserExpenseDataController implements ISendUserExpenseDataController {
    @GetMapping("/userexpenses")
    @Override
    public List<Expenses> getAllExpenseDetails(@RequestParam String username, @RequestParam long groupid) {
        SendUserExpense sendUserExpense = new SendUserExpense();
        return sendUserExpense.fetchMyExpenses(username, groupid);
    }

    @GetMapping("/calculatetotal")
    @Override
    public float calculateUserTotalExpense(@RequestParam String username) {
        SendUserExpense sendUserExpense = new SendUserExpense();
        return sendUserExpense.calculateTotalExpense(username);
    }
}
