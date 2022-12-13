package com.triplify.app.expenseFeature.controller;

import com.triplify.app.database.DatabaseExceptionHandler;
import com.triplify.app.expenseFeature.model.Expenses;
import com.triplify.app.expenseFeature.service.SendUserExpense;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(path = "api/v1/users")
public class SendUserExpenseDataController implements ISendUserExpenseDataController {
    @GetMapping("/userexpenses")
    @Override
    public List<Expenses> getAllExpenseDetails(@RequestParam Long userid) {
        SendUserExpense sendUserExpense = new SendUserExpense();
        return sendUserExpense.fetchMyExpenses(userid);
    }

    @GetMapping("/calculatetotal")
    @Override
    public float calculateUserTotalExpense(@RequestParam Long userid) {
        SendUserExpense sendUserExpense = new SendUserExpense();
        return sendUserExpense.calculateTotalExpense(userid);
    }
}
