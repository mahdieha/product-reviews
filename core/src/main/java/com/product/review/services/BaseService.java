package com.product.review.services;

import com.product.review.configuration.ApplicationProperties;
import com.product.review.model.mapper.product.ProductMapper;
import com.product.review.model.mapper.review.ReviewMapper;
import com.product.review.persistence.repository.product.ProductRepository;
import com.product.review.persistence.repository.review.ReviewRepository;
import org.springframework.beans.factory.annotation.Autowired;

public abstract class BaseService {
    @Autowired
    protected ProductRepository productRepository;
    @Autowired
    protected ProductMapper productMapper;
    @Autowired
    protected ReviewRepository reviewRepository;
    @Autowired
    protected ReviewMapper reviewMapper;
    @Autowired
    protected ApplicationProperties applicationProperties;
}