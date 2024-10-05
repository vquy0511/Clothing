package com.asm.DAO;

import org.springframework.data.jpa.repository.JpaRepository;

import com.asm.entity.Size;

public interface SizeDAO extends JpaRepository<Size,String> {
    
}
