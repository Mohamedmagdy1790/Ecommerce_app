package com.magdy.ecommerce.dao;

import com.magdy.ecommerce.entity.Product;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.web.bind.annotation.CrossOrigin;

import java.util.List;

@CrossOrigin("http://localhost:4200")
public interface ProductRepository  extends JpaRepository<Product, Long> {
      Page<Product> findByCategoryId(@Param("id")Long id, Pageable pageable);

       //containing is equal in sql to LIKE
     // Page<Product> findProductsByNameContaining(@Param("name") String name, Pageable pageable);
      Page<Product> findByNameContaining(@Param("name") String name, Pageable pageable);
}
