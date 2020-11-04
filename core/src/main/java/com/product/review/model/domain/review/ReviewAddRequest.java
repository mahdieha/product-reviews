package com.product.review.model.domain.review;

import com.product.review.model.enums.review.ReviewCommentStatus;
import com.product.review.model.enums.review.ReviewRatingStatus;
import com.product.review.persistence.entity.product.Product;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.validator.constraints.Length;

import javax.validation.constraints.Max;
import javax.validation.constraints.Min;

@NoArgsConstructor
@AllArgsConstructor
@Data
public class ReviewAddRequest {
    @Length(max = 255, message = "The field must be less than 255 characters")
    private String reviewTitle;
    @Length(max = 255, message = "The field must be less than 255 characters")
    private String comment;
    private ReviewCommentStatus reviewCommentStatus;
    @Min(value = 1)
    @Max(value = 5)
    private Integer rating;
    private ReviewRatingStatus reviewRatingStatus;
    private Product product;
}

