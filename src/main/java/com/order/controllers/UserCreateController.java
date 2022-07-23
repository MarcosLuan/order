package com.order.controllers;

import com.order.model.UserCreate;
import com.order.repository.UserCreateRepository;
import com.order.service.UserCreateService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin("*")
@RestController
@RequestMapping("/user-create")
public class UserCreateController {

    @Autowired
    private UserCreateRepository repository;

    @Autowired
    UserCreateService userCreateService;

    @RequestMapping(value = "/search-user", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<List<UserCreate>> getUser() {
        try {
            return ResponseEntity.ok(repository.findAll());
        } catch (Exception e ) {
            return ResponseEntity.badRequest().build();
        }
    }

    @RequestMapping(value = "/delete-user", method = RequestMethod.DELETE, consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public void deleteUser(@RequestBody UserCreate userCreate) {
        repository.delete(userCreate);
    }

    @RequestMapping(value = "/save-user", method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public UserCreate postUser(@RequestBody UserCreate userCreate) {
        return repository.save(userCreate);
    }

    @RequestMapping(value ="/update-user", method = RequestMethod.PUT, consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<UserCreate> updateUser(@RequestBody UserCreate userDetails) {
        return ResponseEntity.ok(userCreateService.userCreateChange(userDetails));
    }
}
