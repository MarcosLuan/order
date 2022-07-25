package com.order.controller;

import com.order.model.UserCreate;
import com.order.repository.UserCreateRepository;
import com.order.service.UserCreateService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/user-create")
public class UserCreateController {

    @Autowired
    private UserCreateRepository repository;

    @Autowired
    UserCreateService userCreateService;

    @GetMapping(value = "/search-user", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<List<UserCreate>> getUser() {
        try {
            return ResponseEntity.ok(repository.findAll());
        } catch (Exception e ) {
            return ResponseEntity.badRequest().build();
        }
    }

    @DeleteMapping(value = "/delete-user", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<UserCreate> deleteUser(@RequestBody UserCreate userCreate) {
        repository.delete(userCreate);
        return ResponseEntity.ok(userCreate);
    }

    @PostMapping(value = "/save-user", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public UserCreate postUser(@RequestBody UserCreate userCreate) {
        return repository.save(userCreate);
    }

    @PutMapping(value ="/update-user", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<UserCreate> updateUser(@RequestBody UserCreate userDetails) {
        return ResponseEntity.ok(userCreateService.userCreateChange(userDetails));
    }
}
