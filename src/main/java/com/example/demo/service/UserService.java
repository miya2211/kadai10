package com.example.demo.service;

import com.example.demo.entity.User;

import java.util.List;

public interface UserService {

    List<User> findAll();

    User findById(int id) throws Exception;

    void create(String name);

    void update(int id, String name) throws Exception;

    void delete(int id) throws Exception;

}
