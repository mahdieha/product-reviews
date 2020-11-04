package com.product.review.model.dto.review;

import com.product.review.model.enums.review.ReviewCommentStatus;
import com.product.review.model.enums.review.ReviewRatingStatus;
import com.product.review.persistence.entity.product.Product;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ReviewDto {
    private Long id;
    private String reviewTitle;
    private String comment;
    private ReviewCommentStatus reviewCommentStatus;
    private Integer rating;
    private ReviewRatingStatus reviewRatingStatus;
    private Product product;
}
