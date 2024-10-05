package com.asm.service;

import com.asm.entity.ColorDivision;

public interface ColorDivisionService {

    void create(ColorDivision cd);

    void deleteByProductId(Integer id);

}
