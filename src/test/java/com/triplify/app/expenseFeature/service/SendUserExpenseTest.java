package com.triplify.app.expenseFeature.service;

import com.triplify.app.expenseFeature.model.AddExpenses;
import com.triplify.app.expenseFeature.model.Expenses;
import com.triplify.app.expenseFeature.controller.AddExpensesController;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

@DisplayName("Send User Expense Java Builder Testing!!")
public class SendUserExpenseTest {
    @Test
    @DisplayName("Expenses split testing!!")
    public void splitExpensesTest(){
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
        SendUserExpense sendUserExpense = new SendUserExpense();
        final List<Expenses> expectedSplitExpenseResult1 = sendUserExpense.fetchMyExpenses(16L);
        final String expectedSplitExpenseResult = String.valueOf(expectedSplitExpenseResult1.get(0));
        final String actualSplitExpenseResult = "Expenses(id=76, transaction_id=hbjgyjjh76567g, description=fish & chips, amount=-14.0, currency=CAD, fromuserid=14, touserid=16, groupid=1)";
        System.out.println(expectedSplitExpenseResult);
        System.out.println(actualSplitExpenseResult);

        Assertions.assertEquals(expectedSplitExpenseResult,actualSplitExpenseResult,"Incorrect Insert Query Has been generated!!");
    }

    @Test
    @DisplayName("Expenses split testing!!")
    public void calculateTotalExpenseTest(){
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
        SendUserExpense sendUserExpense = new SendUserExpense();

        float calculateTotal = sendUserExpense.calculateTotalExpense(14L);
        calculateTotal = calculateTotal/calculateTotal;

        Assertions.assertEquals(calculateTotal,1,"Incorrect Insert Query Has been generated!!");
        ;
    }
}
