package com.techie.companyms.company.model;


import jakarta.persistence.*;

import java.util.List;

@Entity
public class Company {

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }


    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;

    public Company() {
    }

    public Company(String description, String name, Long id) {
        this.description = description;
        this.name = name;
        this.id = id;
    }

    private String description;

}


