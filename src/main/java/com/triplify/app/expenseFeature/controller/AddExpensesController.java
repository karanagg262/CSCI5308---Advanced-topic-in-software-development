package com.triplify.app.expenseFeature.controller;

import com.triplify.app.expenseFeature.model.AddExpenses;
import com.triplify.app.expenseFeature.model.Expenses;
import com.triplify.app.expenseFeature.service.AddNewExpenses;

import org.springframework.web.bind.annotation.*;

import java.nio.charset.Charset;
import java.sql.Date;
import java.util.ArrayList;
import java.util.Random;

@RestController
@RequestMapping(path = "api/v1/users")
@CrossOrigin

public class AddExpensesController implements  IAddExpensesController{
    @PostMapping("/addexpenses")
    @Override
    public void postExpense(@RequestParam("description") String description,
                            @RequestParam("amount") float amount,
                            @RequestParam("currency") String currency,
                            @RequestParam("usernamelist") ArrayList<String> usernamelist,
                            @RequestParam("paidbyusername") String paidbyusername,
                            @RequestParam("groupid") long groupid,
                            @RequestParam("dateadded") String date_added) {
        AddExpenses expenses = new AddExpenses();
        int upperbound = 25;
        Random rand = new Random();;
        long int_random = rand.nextLong(upperbound);
        expenses.setId(int_random);
        byte[] array = new byte[7];
        new Random().nextBytes(array);
        String generatedString = new String(array, Charset.forName("UTF-8"));
        expenses.setTransaction_id(generatedString);
        expenses.setDescription(description);
        expenses.setAmount(amount);
        expenses.setCurrency(currency);
        expenses.setUsernamelist(usernamelist);
        expenses.setPaidbyusername(paidbyusername);
        expenses.setGroupid(groupid);
        expenses.setFull_amount(amount);
        expenses.setDate_added(date_added);
        AddNewExpenses addNewExpenses = new AddNewExpenses();
        addNewExpenses.splitExpenses(expenses);
    }

    @PostMapping("/settleexpenses")
    @Override
    public void settleExpense(@RequestParam("amount") float amount,
                              @RequestParam("fromusername") String fromusername,
                              @RequestParam("tousername") String tousername,
                              @RequestParam("groupid") long groupid) {
        Expenses expenses = new Expenses();
        int upperbound = 25;
        Random rand = new Random();;
        long int_random = rand.nextLong(upperbound);
        expenses.setId(int_random);
        byte[] array = new byte[7];
        new Random().nextBytes(array);
        String generatedString = new String(array, Charset.forName("UTF-8"));
        expenses.setTransaction_id(generatedString);
        expenses.setDescription(" ");
        expenses.setAmount(amount);
        expenses.setCurrency("CAD");
        expenses.setFromUsername(fromusername);
        expenses.setToUsername(tousername);
        expenses.setGroupid(groupid);
        expenses.setFull_amount(amount);
        expenses.setDate_added(null);
        AddNewExpenses addNewExpenses = new AddNewExpenses();
        addNewExpenses.settleMyExpenses(expenses);
    }
}