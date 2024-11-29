package com.login.RoleBased.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.login.RoleBased.model.User;

public interface UserRepository extends JpaRepository<User, Long> {
    User findByEmail(String email);
}
