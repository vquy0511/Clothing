package com.asm.DAO;

import org.springframework.data.jpa.repository.JpaRepository;

import com.asm.entity.Color;

public interface ColorDAO extends JpaRepository<Color,String> {
    
    
}
