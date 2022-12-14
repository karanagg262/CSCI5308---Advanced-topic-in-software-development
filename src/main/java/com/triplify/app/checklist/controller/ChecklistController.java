package com.triplify.app.checklist.controller;

import com.triplify.app.checklist.database.ChecklistQueryBuilder;
import com.triplify.app.database.DatabaseConnection;
import com.triplify.app.database.DatabaseExceptionHandler;
import com.triplify.app.checklist.model.Checklist;
import org.springframework.web.bind.annotation.*;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import static com.triplify.app.checklist.database.ChecklistDatabaseConstant.*;

@RestController
@RequestMapping(path = "api/v1/groups")
@CrossOrigin

public class ChecklistController {
    @PostMapping("/addchecklist")
    public void postChecklist(@RequestParam("group_id") long group_id,
                              @RequestParam("checklist_name") String checklist_name,
                              @RequestParam("checklisted") boolean checklisted,
                              @RequestParam("checklist_id") long checklist_id)
    {
        Checklist checklist = new Checklist();
        checklist.setGroup_id(group_id);
        checklist.setChecklist_name(checklist_name);
        checklist.setChecklisted(checklisted);
        checklist.setchecklist_id(checklist_id);
        try {
            Connection connection =
                    DatabaseConnection.getInstance().getDatabaseConnection();
            final int rowInserted =
                    ChecklistQueryBuilder.insertChecklistQuery(checklist, connection);
            if (connection != null) {
                connection.close();
            }
        }  catch (SQLException e) {
            throw new RuntimeException(e);
        } catch (DatabaseExceptionHandler e) {
            throw new RuntimeException(e);
        }
    }

    @GetMapping("/showchecklist")
    public Checklist getChecklist(@RequestParam long groupid) throws DatabaseExceptionHandler, SQLException {
        Connection connection =
                DatabaseConnection.getInstance().getDatabaseConnection();
        ResultSet userChecklistResultSet =
                connection.createStatement().executeQuery("select * from checklist where group_id = "
                 + groupid + ";");
        while (userChecklistResultSet.next()) {
            Long groupId = userChecklistResultSet.getLong("" + checklist_groupid);
            String checklistName = userChecklistResultSet.getString("" + checklist_checklist);
            Boolean checklisted = userChecklistResultSet.getBoolean("" + checklist_checklisted);
            Long checklist_id = userChecklistResultSet.getLong("" + checklisted_id);

            Checklist checklist = new Checklist();
            checklist.setGroup_id(groupId);
            checklist.setChecklist_name(checklistName);
            checklist.setChecklisted(checklisted);
            checklist.setchecklist_id(checklist_id);
            return checklist;
        }
        return null;
    }
}
