package com.asm.service;

import java.util.List;

import com.asm.entity.Size;

public interface SizeService {


    List<Size> findAll();

    Size findById(String string);
    
}
