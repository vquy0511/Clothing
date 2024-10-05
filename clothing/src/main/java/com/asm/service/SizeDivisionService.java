package com.asm.service;

import com.asm.entity.SizeDivision;

public interface SizeDivisionService {

    void create(SizeDivision sD);

    void deleteByProductId(Integer id);

}
