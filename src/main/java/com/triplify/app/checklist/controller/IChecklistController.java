package com.triplify.app.checklist.controller;

import com.triplify.app.checklist.model.Checklist;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;
import java.util.Map;

public interface IChecklistController {
    public Map<String, Object> postChecklist(long group_id, String checklist_name, boolean checklisted);
    public List<Checklist> getChecklist(long groupid);

    public Map<String, Object> patchChecklist(long group_id, String checklist_name, boolean checklisted);
}
