package com.asm.RestController;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.asm.entity.Role;
import com.asm.service.RoleService;

@CrossOrigin("*")
@RestController
@RequestMapping("/rest/roles")
public class RoleRestController {
    @Autowired
    RoleService rDao;

    @GetMapping()
    public List<Role> getAll() {
        return rDao.findAll();
    }
}
