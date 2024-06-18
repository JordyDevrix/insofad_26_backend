package com.juwelier.webshop.models;

import jakarta.persistence.*;

@Entity(name = "category")
public class Category {
    @Id
    @GeneratedValue
    private long id;
    private String name;

    public Category() {}
    public Category(String name) {
        this.name = name;
    }


    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

}
