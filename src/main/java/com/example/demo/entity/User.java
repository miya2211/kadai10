package com.example.demo.entity;

import lombok.AllArgsConstructor;

@AllArgsConstructor
public class User {

    private int id;
    private String name;

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

}
