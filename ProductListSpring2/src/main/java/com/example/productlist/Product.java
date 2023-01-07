package com.example.productlist;


import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.Table;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Product {

    @Id @GeneratedValue(strategy=GenerationType.IDENTITY)
    private Long id;
    private String name;
    private double weight;
    private double price;

    @OneToMany(fetch = FetchType.LAZY)
    @JoinColumn(name="category_id")
    private Category category;
}
