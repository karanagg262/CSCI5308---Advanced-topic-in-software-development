package com.triplify.app.controller;

import com.triplify.app.database.DatabaseConnection;
import com.triplify.app.database.DatabaseExceptionHandler;
import com.triplify.app.model.Checklist;
import org.springframework.web.bind.annotation.*;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;

import static com.triplify.app.database.ChecklistDatabaseConstant.*;

@RestController
@RequestMapping(path = "api/v1/groups")
public class ChecklistController {
    @PostMapping("/addchecklist")
    public void postChecklist(@RequestBody Checklist checklist) throws DatabaseExceptionHandler, SQLException {
        Connection connection =
                DatabaseConnection.getInstance().getDatabaseConnection();
        final int rowInserted =
                ChecklistQueryBuilder.insertChecklistQuery(checklist, connection);
        if (connection != null) {
            connection.close();
        }
    }

    @GetMapping("/showchecklist")
    public Checklist getChecklist() throws DatabaseExceptionHandler, SQLException {
        Connection connection =
                DatabaseConnection.getInstance().getDatabaseConnection();
        ResultSet userChecklistResultSet =
                connection.createStatement().executeQuery("select * from checklist");
        while (userChecklistResultSet.next()) {
            //add if condition in while loop
            Long groupId = userChecklistResultSet.getLong("" + checklist_groupid);
            String checklistName = userChecklistResultSet.getString("" + checklist_checklist);
            Boolean checklisted = userChecklistResultSet.getBoolean("" + checklist_checklisted);
            Long userId = userChecklistResultSet.getLong("" + checklist_userid);

            Checklist checklist = new Checklist();
            checklist.setGroup_id(groupId);
            checklist.setChecklist_name(checklistName);
            checklist.setChecklisted(checklisted);
            checklist.setUser_id(userId);
            return checklist;
        }
        return null;
    }
}
