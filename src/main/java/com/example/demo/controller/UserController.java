package com.example.demo.controller;

import com.example.demo.entity.User;
import com.example.demo.service.ResourceNotFoundException;
import com.example.demo.service.UserService;
import jakarta.servlet.http.HttpServletRequest;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.time.ZonedDateTime;
import java.util.List;
import java.util.Map;

@AllArgsConstructor
@RestController
public class UserController {

    private final UserService userService;

    @GetMapping("/users")
    public List<User> getUsers() {
        return userService.findAll();
    }

    @GetMapping("/users/{id}")
    public User getUserById(@PathVariable("id") int id) throws Exception {
        return userService.findById(id);
    }

    @PostMapping("/users")
    public ResponseEntity<User> createUser(@RequestParam("name") String name) {
            User newUser = userService.create(name);
            URI location = ServletUriComponentsBuilder
                    .fromCurrentRequest()
                    .path("/{id}")
                    .buildAndExpand(newUser.getId())
                    .toUri();
            return ResponseEntity.created(location).body(newUser);
    }

    @PutMapping("/users/{id}")
    public ResponseEntity<String> updateUser(@PathVariable("id") int id, @RequestParam("name") String name) throws Exception{
        userService.update(id, name);
        return ResponseEntity.status(HttpStatus.OK).body("ユーザー名を更新しました。");
    }

    @DeleteMapping("/users/{id}")
    public ResponseEntity<String> deleteUser(@PathVariable("id") int id) throws Exception {
        userService.delete(id);
        return ResponseEntity.status(HttpStatus.OK).body("ユーザーを削除しました。");
    }

    @ExceptionHandler(value = ResourceNotFoundException.class)
    public ResponseEntity<Map<String, String>> handleNoResourceFound(ResourceNotFoundException e, HttpServletRequest request) {
        Map<String, String> body = Map.of(
                "timestamp", ZonedDateTime.now().toString(),
                "status", String.valueOf(HttpStatus.NOT_FOUND.value()),
                "error", HttpStatus.NOT_FOUND.getReasonPhrase(),
                "message", e.getMessage(),
                "path", request.getRequestURI());
        return new ResponseEntity<>(body, HttpStatus.NOT_FOUND);

    }

}
