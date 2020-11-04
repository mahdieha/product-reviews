package com.product.review.controller;

import com.product.review.services.product.ProductServiceImpl;
import com.product.review.services.review.ReviewServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;

public abstract class BaseController {

    @Autowired
    protected ProductServiceImpl productServiceImpl;
    @Autowired
    protected ReviewServiceImpl reviewServiceImpl;
}
