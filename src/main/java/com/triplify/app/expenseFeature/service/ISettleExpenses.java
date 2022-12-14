package com.triplify.app.expenseFeature.service;

import java.util.HashMap;

public interface ISettleExpenses {
    public HashMap<Long, Float> fetchSettleExpenses(Long userid, Long groupid);
}
