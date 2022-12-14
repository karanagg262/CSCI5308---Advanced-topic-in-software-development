package com.triplify.app.expenseFeature.service;

import com.triplify.app.expenseFeature.controller.AddExpensesController;
import com.triplify.app.expenseFeature.model.AddExpenses;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

@DisplayName("Settle Expenses Testing!!")
public class SettleExpensesTest {
    @Test
    @DisplayName("Fetch Settle Expenses testing!!")
    public void fetchSettleExpensesTest(){
        final long id = 45;
        final String transaction_id = "hbjgyjjh76567g";
        final String description = "fish & chips";
        final float amount = 56;
        final String currency = "CAD";
        final ArrayList<Long> useridlist= new ArrayList<Long>(Arrays.asList(13L, 14L, 15L,16L));;
        final Long paidbyuserid = Long.valueOf(14);
        final Long groupId = Long.valueOf(1);

        AddExpenses addExpenses = new AddExpenses();

        addExpenses.setId(id);
        addExpenses.setTransaction_id(transaction_id);
        addExpenses.setDescription(description);
        addExpenses.setAmount(amount);
        addExpenses.setCurrency(currency);
        addExpenses.setUseridlist(useridlist);
        addExpenses.setPaidbyuserid(paidbyuserid);
        addExpenses.setGroupid(groupId);
        AddExpensesController addExpensesController = new AddExpensesController();
        addExpensesController.postExpense(addExpenses);

        final long userid = 15;
        final long groupid_demo = 1;

        SettleExpenses settleExpenses = new SettleExpenses();
        HashMap<Long, Float> fetchExpenses = settleExpenses.fetchSettleExpenses(
                addExpenses.getPaidbyuserid(), addExpenses.getGroupid());
        for(Map.Entry m:fetchExpenses.entrySet()){
            System.out.println(m.getKey()+" "+m.getValue());
        }
        Assertions.assertTrue(fetchExpenses.containsKey(userid));
        Assertions.assertNull(fetchExpenses.get(groupid_demo));
    }
}
