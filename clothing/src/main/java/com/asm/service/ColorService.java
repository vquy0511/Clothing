package com.asm.service;

import java.util.List;

import com.asm.entity.Color;

public interface ColorService {

    List<Color> findAll();

    Color findById(String string);

}
