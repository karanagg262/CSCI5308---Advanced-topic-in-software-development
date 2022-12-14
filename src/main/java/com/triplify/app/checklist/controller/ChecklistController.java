package com.triplify.app.checklist.controller;

import com.triplify.app.checklist.database.ChecklistQueryBuilder;
import com.triplify.app.database.DatabaseConnection;
import com.triplify.app.database.DatabaseExceptionHandler;
import com.triplify.app.checklist.model.Checklist;
import org.springframework.web.bind.annotation.*;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.*;

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

    @PostMapping("/showchecklist")
    @Override
    public Map<String, Object> getChecklist(@RequestParam("group_id") long groupid) {

        List<Checklist> checklists = new ArrayList<>();
        Map<String, Object> responseObject = new HashMap<>();

        try{
            Connection connection =
                    DatabaseConnection.getInstance().getDatabaseConnection();
            ResultSet userChecklistResultSet =
                    connection.createStatement().executeQuery("select * from checklist where group_id = "
                            + groupid + ";");

            while (userChecklistResultSet.next()) {
                Checklist checklist = new Checklist();
                checklist.setGroup_id(userChecklistResultSet.getLong("" + checklist_groupid));
                checklist.setChecklist_name(userChecklistResultSet.getString("" + checklist_checklist));
                checklist.setChecklisted(userChecklistResultSet.getBoolean("" + checklist_checklisted));
                checklist.setchecklist_id(userChecklistResultSet.getLong("" + checklisted_id));

                checklists.add(checklist);
            }

            responseObject.put("SUCCESS",true);
            responseObject.put("MESSAGE","Successfully checklists retrieved!!");
            responseObject.put("checkList",checklists);

        }  catch (SQLException e) {
            throw new RuntimeException(e);
        } catch (DatabaseExceptionHandler e) {
            throw new RuntimeException(e);
        }

        if (checklists.size() > 0){
            return responseObject;
        }else{
            responseObject.put("SUCCESS",false);
            responseObject.put("MESSAGE","Something went wrong!!");
            responseObject.put("checkList",new ArrayList<>());
            return responseObject;
        }
    }
}
