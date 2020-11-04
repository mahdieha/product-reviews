package com.product.review.model.dto.product;

import com.product.review.model.enums.product.ProductReviewShowStatus;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ProductDto {
    private Long id;
    private String productTitle;
    private long productPrice;
    private String productDescription;
    private boolean isShow;
    private boolean isComment;
    private boolean isRating;
    private ProductReviewShowStatus productReviewShowStatus;
}