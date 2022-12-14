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
import java.util.HashMap;
import java.util.Map;
import java.util.Random;

import static com.triplify.app.checklist.database.ChecklistDatabaseConstant.*;

@RestController
@RequestMapping(path = "api/v1/groups")
@CrossOrigin

public class ChecklistController implements IChecklistController {
    @PostMapping("/addchecklist")
    @Override
    public Map<String, Object> postChecklist(@RequestParam("group_id") long group_id,
                                             @RequestParam("checklist_name") String checklist_name,
                                             @RequestParam("checklisted") boolean checklisted)
    {
        Random rand = new Random();
        int upperbound = 10000;
        long checklist_id = rand.nextLong(upperbound);
        Map<String,Object> response = new HashMap<>();
        Checklist checklist = new Checklist();
        checklist.setGroup_id(group_id);
        checklist.setChecklist_name(checklist_name);
        checklist.setChecklisted(checklisted);
        checklist.setchecklist_id(checklist_id);
        try {
            Connection connection =
                    DatabaseConnection.getInstance().getDatabaseConnection();
            ChecklistQueryBuilder checklistQueryBuilder = new ChecklistQueryBuilder();
            final int rowInserted =
                    checklistQueryBuilder.insertChecklistQuery(checklist, connection);
            response.put("SUCCESS",true);
            response.put("MESSAGE","Checklist is added successfully");
            if (connection != null) {
                connection.close();
            }
        }  catch (SQLException e) {
            response.put("SUCCESS",false);
            response.put("MESSAGE","Checklist is not added!!");
            throw new RuntimeException(e);
        } catch (DatabaseExceptionHandler e) {
            response.put("SUCCESS",false);
            response.put("MESSAGE","Checklist is not added!!");
            throw new RuntimeException(e);
        }
        return response;
    }

    @GetMapping("/showchecklist")
    @Override
    public Checklist getChecklist(@RequestParam long groupid) {
        try{
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
        }  catch (SQLException e) {
            throw new RuntimeException(e);
        } catch (DatabaseExceptionHandler e) {
            throw new RuntimeException(e);
        }

        return null;
    }
}
