package com.product.review.model.domain.product;

import com.product.review.model.enums.product.ProductReviewShowStatus;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.validator.constraints.Length;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;

@NoArgsConstructor
@AllArgsConstructor
@Data
@Builder
public class ProductAddRequest {
    @NotBlank(message = "The name must not be null")
    @Length(max = 255, message = "The field must be less than 255 characters")
    private String productTitle;
    @Min(value = 0, message = "Product price must no be less then zero.")
    private double productPrice;
    @Length(max = 255, message = "The field must be less than 255 characters")
    private String productDescription;
    private boolean isShow;
    private boolean isComment;
    private boolean isRating;
    private ProductReviewShowStatus productReviewShowStatus;
}
