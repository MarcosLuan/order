package com.order.repository;

import com.order.model.UserCreate;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserCreateRepository extends JpaRepository<UserCreate, Long> {

}
