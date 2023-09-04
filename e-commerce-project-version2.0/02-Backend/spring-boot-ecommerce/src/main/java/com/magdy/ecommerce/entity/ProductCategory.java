package com.magdy.ecommerce.entity;


import jakarta.persistence.*;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Entity
@Table(name = "product_category")
//known bug when using lomok with relationship entites
@Getter
@Setter

public class ProductCategory {

@Id
@GeneratedValue(strategy = GenerationType.IDENTITY)
@Column(name = "id")
private Long id;

@Column(name = "category_name")
private String categoryName;

@OneToMany(cascade = CascadeType.ALL ,mappedBy = "category")
List<Product> product;

}
