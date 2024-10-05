package com.asm.entity;

import java.util.Date;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import com.fasterxml.jackson.annotation.JsonIgnore;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name="Products")
public class Product {
    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    int id;
    String name;
    String image;
    Double price;
    int quantity;
    @Temporal(TemporalType.DATE)
    @Column(name="create_date")
    Date create_date = new Date();
    Boolean available;
    Boolean sex;
    @ManyToOne
    @JoinColumn(name="categoryID")
    Category category;

    @JsonIgnore
    @OneToMany(mappedBy="product")
    List<OrderDetail> orderDetails;

    @JsonIgnore
    @OneToMany(mappedBy="product")
    List<SizeDivision> size;
    @JsonIgnore
    @OneToMany(mappedBy="product")
    List<ColorDivision> color;



    

}
