package com.login.RoleBased.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.login.RoleBased.model.Role;

public interface RoleRepository extends JpaRepository<Role, Long> {
    Role findByName(String name);
}