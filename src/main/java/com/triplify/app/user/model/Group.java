package com.triplify.app.user.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import javax.validation.constraints.NotEmpty;
import java.util.ArrayList;
import java.util.List;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Group {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    @Column(name = "id", nullable = false)
    private Long id;

    @Column
    @NotEmpty(message = "A group must be named. Its neither Arya nor Voldy!")
    private String name;
    private String destination;
    private String startDate;
    private String endDate;
    private String adminUsername;
    private ArrayList<User> members;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }
}
