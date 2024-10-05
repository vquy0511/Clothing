package com.asm.entity;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name="Roles")
public class Role {
    
    @Id
    String id;
    String name;

    @JsonIgnore
    @OneToMany(mappedBy="role")
    List<Authority> authorities;

}
