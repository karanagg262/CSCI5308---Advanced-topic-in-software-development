package com.triplify.app.checklist.controller;

import com.triplify.app.checklist.model.Checklist;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.Map;
import java.util.Random;

@DisplayName("Checklist Controller Testing!!")
public class ChecklistControllerTest {

    @Test
    @DisplayName("checklist insert testing!!")
    public void postChecklistTest() {
        Random rand = new Random();
        int upperbound = 10000;
        final long group_id = 4;
        final String checklist_name = "Bags";
        final boolean checklisted = true;
        final long checklist_id = rand.nextLong(upperbound);

        ChecklistController checklistController = new ChecklistController();
        Map<String,Object> data = checklistController.postChecklist(group_id, checklist_name, checklisted, checklist_id);

        System.out.println(data.get("SUCCESS"));

        Assertions.assertTrue(data.get("SUCCESS").equals(true));
    }

    @Test
    @DisplayName("checklist insert testing!!")
    public void getChecklist() {
        final long groupid = 4;

        ChecklistController checklistController = new ChecklistController();

        final String expected_checklist = String.valueOf(checklistController.getChecklist(groupid));

        final String actual_checklist = "com.triplify.app.checklist.model.Checklist";

        System.out.println(expected_checklist);
        System.out.println(actual_checklist);

        Assertions.assertTrue(expected_checklist.contains(actual_checklist));
    }
}
