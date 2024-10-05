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
@Table(name="order_details")
public class OrderDetail {
    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    Long id;
    double price;
    int quantity;
    String size;
    String color;
    @ManyToOne
    @JoinColumn(name="product_id")
    Product product;
    @ManyToOne
    @JoinColumn(name="order_id")
    Order order;
}
