package com.triplify.app.checklist.database;

import com.triplify.app.checklist.model.Checklist;

import java.sql.Connection;

public interface IChecklistQueryBuilder {
    public int insertChecklistQuery(final Checklist checklist, Connection connection);
    public int updateChecklistQuery(final Checklist checklist, Connection connection);
}
