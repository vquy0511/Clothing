package com.asm.entity;

import java.util.List;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIgnore;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "color_division")
public class ColorDivision {
    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    int id;
    
    @ManyToOne
    @JoinColumn(name="product_id")
    Product product;

    @ManyToOne
    @JoinColumn(name="color_id")
    Color color;

    
    
    
}
