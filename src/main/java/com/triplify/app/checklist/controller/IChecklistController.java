package com.triplify.app.checklist.controller;

import com.triplify.app.checklist.model.Checklist;

import java.util.Map;

public interface IChecklistController {
    public Map<String, Object> postChecklist(long group_id, String checklist_name, boolean checklisted);
    public Map<String, Object> getChecklist(long groupid);
}
