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
        Optional<User> user = userMapper.findById(id);
        if (user.isPresent()) {
            return user.get();
        } else {
            throw new ResourceNotFoundException("指定されたユーザーが見つかりません。");
        }
    }

    @Override
    public void create(String name) {
        userMapper.create(name);
    }

    @Override
    public void update(int id, String name) throws Exception {
        Optional<User> user = userMapper.findById(id);
        if (user.isPresent()) {
            userMapper.update(id, name);
        } else {
            throw new ResourceNotFoundException("指定されたIDが見つからないので更新できません。");
        }
    }

    @Override
    public void delete(int id) throws Exception {
        Optional<User> user = userMapper.findById(id);
        if (user.isPresent()) {
            userMapper.delete(id);
        } else {
            throw new ResourceNotFoundException("指定されたIDが見つからないので削除はできません。");
        }
    }

}
