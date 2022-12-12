package com.triplify.app.expenseFeature.controller;

import com.triplify.app.database.DatabaseConnection;
import com.triplify.app.database.DatabaseExceptionHandler;
import com.triplify.app.expenseFeature.model.Expenses;
import com.triplify.app.expenseFeature.service.sendUserExpense;
import org.springframework.web.bind.annotation.*;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import static com.triplify.app.expenseFeature.database.ExpenseDatabaseContstant.*;

@RestController
@RequestMapping(path = "api/v1/users")
public class SendUserExpenseDataController {
    @GetMapping("/userexpenses")
    public List<Expenses> getAllExpenseDetails(@RequestParam Long userid) throws DatabaseExceptionHandler {
        return sendUserExpense.fetchMyExpenses(userid);
    }

    @GetMapping("/calculatetotal")
    public float calculateUserTotalExpense(@RequestParam Long userid) throws DatabaseExceptionHandler {
        return sendUserExpense.calculateTotalExpense(userid);
    }
}
