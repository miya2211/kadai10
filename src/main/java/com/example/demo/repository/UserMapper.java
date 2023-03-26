package com.example.demo.repository;

import com.example.demo.entity.User;
import org.apache.ibatis.annotations.*;
import java.util.List;
import java.util.Optional;

@Mapper
public interface UserMapper {

    @Select("SELECT * FROM users")
    List<User> findAll();

    @Select("SELECT * FROM users WHERE id = #{id} ")
    Optional<User> findById(int id);

    @Insert("INSERT INTO users(name) VALUES(#{name})")
    @Options(useGeneratedKeys = true, keyProperty = "id")
    void create(User user);


    @Update("UPDATE users SET name = #{name} WHERE id = #{id}")
    void update(int id, String name);

    @Delete("DELETE FROM users WHERE id = #{id}")
    void delete(int id);

}
