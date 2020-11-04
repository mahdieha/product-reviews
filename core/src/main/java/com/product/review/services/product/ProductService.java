package com.product.review.services.product;

import com.product.review.model.domain.product.ProductAddRequest;
import com.product.review.model.domain.product.ProductEditRequest;
import com.product.review.model.dto.base.BaseDTO;
import com.product.review.model.enums.product.ProductReviewShowStatus;

public interface ProductService {

    BaseDTO addProduct(ProductAddRequest productAddRequest);

    BaseDTO editProduct(ProductEditRequest productEditRequest);

    BaseDTO editIsShowProduct(Long productId, Boolean isShow);

    BaseDTO editProductIsComment(Long productId, Boolean isComment);

    BaseDTO editProductIsRating(Long productId, Boolean isRating);

    BaseDTO editProductReviewShowStatus(Long productId, ProductReviewShowStatus productReviewShowStatus);

    BaseDTO getAllProduct(Integer pageNumber, Integer pageSize);

    BaseDTO getProduct(Long productId);

    BaseDTO getApprovedProduct(Long productId);
}

