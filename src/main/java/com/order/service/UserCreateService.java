package com.order.service;

import com.order.exception.ResourceNotFoundException;
import com.order.model.UserCreate;
import com.order.repository.UserCreateRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserCreateService implements UserCreateServiceInterface {

    @Autowired
    private UserCreateRepository repository;

    public UserCreate userCreateChange(UserCreate userDetails) {
        UserCreate updateUserCreate = repository.findById(userDetails.getId())
                .orElseThrow(() -> new ResourceNotFoundException("User not exist with id: " + userDetails.getId()));

        updateUserCreate.setEmail(userDetails.getEmail());
        repository.save(updateUserCreate);

        return updateUserCreate;
    }
}
