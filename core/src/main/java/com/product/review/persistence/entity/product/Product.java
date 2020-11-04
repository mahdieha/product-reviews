package com.product.review.persistence.entity.product;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.product.review.model.enums.product.ProductReviewShowStatus;
import com.product.review.persistence.entity.BaseEntity;
import lombok.*;
import org.hibernate.validator.constraints.Length;

import javax.persistence.Entity;
import javax.persistence.Table;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;

@Entity
@Table(name = "product")
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(callSuper = true)
@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
public class Product extends BaseEntity<Long> {

    @NotBlank(message = "The name must not be null")
    @Length(max = 255, message = "The field must be less than 255 characters")
    private String productTitle;
    @Min(value = 0, message = "Product price must no be less then zero.")
    private double productPrice;
    private String productDescription;
    private boolean isShow;
    private boolean isComment = false;
    private boolean isRating = false;
    private ProductReviewShowStatus productReviewShowStatus;
}
