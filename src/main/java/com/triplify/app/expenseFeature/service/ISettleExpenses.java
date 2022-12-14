package com.triplify.app.expenseFeature.service;

import java.util.HashMap;

public interface ISettleExpenses {
    public HashMap<String, Float> fetchSettleExpenses(String userid, Long groupid);
}
