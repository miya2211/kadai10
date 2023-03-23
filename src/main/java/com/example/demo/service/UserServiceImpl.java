package com.example.demo.service;

import com.example.demo.entity.User;
import com.example.demo.repository.UserMapper;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@AllArgsConstructor
@Service
public class UserServiceImpl implements UserService {

    private final UserMapper userMapper;

    @Override
    public List<User> findAll(){
        return userMapper.findAll();
    }

    @Override
    public User findById(int id) throws Exception {
        return userMapper.findById(id).orElseThrow(() -> new ResourceNotFoundException("指定されたユーザーが見つかりません。"));
    }

    @Override
    public User create(String name) {
        User newUser = userMapper.create(name);
        return newUser;
    }

    @Override
    public void update(int id, String name) throws Exception {
        Optional<User> user = userMapper.findById(id);
        user.stream().peek(u -> userMapper.update(id, name)).findFirst().orElseThrow(() -> new ResourceNotFoundException("指定されたユーザーが見つかりません。"));
    }

    @Override
    public void delete(int id) throws Exception {
        Optional<User> user = userMapper.findById(id);
        user.stream().peek(u ->userMapper.delete(id)).findFirst().orElseThrow(() -> new ResourceNotFoundException("指定されたユーザーが見つかりません。"));
    }

}
