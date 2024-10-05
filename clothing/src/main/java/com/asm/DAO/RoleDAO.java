package com.asm.DAO;

import org.springframework.data.jpa.repository.JpaRepository;

import com.asm.entity.Role;

public interface RoleDAO extends JpaRepository<Role,String> {
    
}
