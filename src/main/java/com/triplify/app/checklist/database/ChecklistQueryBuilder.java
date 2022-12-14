package com.triplify.app.checklist.database;

import com.triplify.app.checklist.model.Checklist;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import static com.triplify.app.checklist.database.ChecklistDatabaseConstant.*;

public class ChecklistQueryBuilder {
    public static int insertChecklistQuery(final Checklist checklist, Connection connection){
        String query = "INSERT INTO "+ checklist_table + "(" +
                checklist_groupid + ", " +
                checklist_checklist + ", " +
                checklist_checklisted + ", " +
                checklist_userid + ") " +
                "VALUES (?,?,?,?);";
        try{
            PreparedStatement pstmt = connection.prepareStatement(query);
            pstmt.setLong(1, checklist.getGroup_id());
            pstmt.setString(2, checklist.getChecklist_name());
            pstmt.setBoolean(3, checklist.isChecklisted());
            pstmt.setLong(4, checklist.getUser_id());

            return pstmt.executeUpdate();

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

    }
}
