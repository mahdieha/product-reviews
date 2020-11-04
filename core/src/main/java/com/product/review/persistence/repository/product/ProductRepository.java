package com.product.review.persistence.repository.product;

import com.product.review.persistence.entity.product.Product;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ProductRepository extends JpaRepository<Product, Long> {

    Boolean existsProductByProductTitleIgnoreCase(String productTitle);

    Page<Product> findAll(Pageable pageable);

    Product findByProductTitle(String title);
}
