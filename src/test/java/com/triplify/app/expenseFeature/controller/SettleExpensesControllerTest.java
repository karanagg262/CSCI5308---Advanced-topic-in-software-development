package com.triplify.app.expenseFeature.controller;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.HashMap;

@DisplayName("Settle User Testing!!")
public class SettleExpensesControllerTest {
    @Test
    @DisplayName("post expense testing!!")
    public void getAllExpenseDetailsTest() {
        final String username = "karan";
        final Long groupid = 4L;

        SettleExpensesController settleExpensesController = new SettleExpensesController();

        HashMap<String, Float> result = settleExpensesController.getAllExpenseDetails(username, groupid);

        Assertions.assertTrue(result.size() > 0);
    }
}
