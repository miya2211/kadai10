package com.example.demo.controller;

import com.example.demo.entity.User;
import com.example.demo.service.ResourceNotFoundException;
import com.example.demo.service.UserService;
import jakarta.servlet.http.HttpServletRequest;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

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
    public void createUser(@RequestParam("name") String name) {
        userService.create(name);
    }

    @PutMapping("/users/{id}")
    public void updateUser(@PathVariable("id") int id, @RequestParam("name") String name) throws Exception {
        userService.update(id, name);
    }

    @DeleteMapping("/users/{id}")
    public void deleteUser(@PathVariable("id") int id) throws Exception {
        userService.delete(id);
    }

    @ExceptionHandler(value = ResourceNotFoundException.class)
    public ResponseEntity<Map<String, String>> handleNoResourceFound(ResourceNotFoundException e, HttpServletRequest request) {
        Map<String, String> body = Map.of(
                "timestamp", ZonedDateTime.now().toString(),
                "status", String.valueOf(HttpStatus.NOT_FOUND.value()),
                "error", HttpStatus.NOT_FOUND.getReasonPhrase(),
                "message", e.getMessage(),
                "path", request.getRequestURI());
        return new ResponseEntity(body, HttpStatus.NOT_FOUND);
    }

}
