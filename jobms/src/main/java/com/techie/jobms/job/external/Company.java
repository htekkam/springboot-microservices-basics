package com.techie.jobms.job.external;


import jakarta.persistence.*;

import java.util.List;


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


    private Long id;
    private String name;



    private String description;

}


