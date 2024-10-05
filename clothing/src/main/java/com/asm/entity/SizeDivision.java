package com.asm.entity;


import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "size_division")
public class SizeDivision {
    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    int id;
    
    @ManyToOne
    @JoinColumn(name="product_id")
    Product product;

    @ManyToOne
    @JoinColumn(name="size_id")
    Size size;

    
    
    
}
