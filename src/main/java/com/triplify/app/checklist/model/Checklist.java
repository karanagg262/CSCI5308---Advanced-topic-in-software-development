package com.triplify.app.checklist.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Checklist {
    @Column(name = "group_id")
    private Long group_id;

    @Column(name = "checklist_name")
    private String checklist_name;

    @Column(name = "checklisted")
    private boolean checklisted;

    @Column(name = "user_id")
    private Long user_id;

    public Long getGroup_id() {
        return group_id;
    }

    public void setGroup_id(Long group_id) {
        this.group_id = group_id;
    }

    public String getChecklist_name() {
        return checklist_name;
    }

    public void setChecklist_name(String checklist_name) {
        this.checklist_name = checklist_name;
    }

    public boolean isChecklisted() {
        return checklisted;
    }

    public void setChecklisted(boolean checklisted) {
        this.checklisted = checklisted;
    }

    public Long getUser_id() {
        return user_id;
    }

    public void setUser_id(Long user_id) {
        this.user_id = user_id;
    }
}
