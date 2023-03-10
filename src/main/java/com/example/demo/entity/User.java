package com.example.demo.entity;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@AllArgsConstructor
@Getter
public class User {

    private int id;

    @Setter
    private String name;

}
