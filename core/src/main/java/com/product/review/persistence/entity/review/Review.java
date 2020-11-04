package com.product.review.persistence.entity.review;

import com.product.review.model.enums.review.ReviewCommentStatus;
import com.product.review.model.enums.review.ReviewRatingStatus;
import com.product.review.persistence.entity.BaseEntity;
import com.product.review.persistence.entity.product.Product;
import lombok.*;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;
import org.hibernate.validator.constraints.Length;

import javax.persistence.*;
import javax.validation.constraints.Max;
import javax.validation.constraints.Min;

@Entity
@Table(name = "reviews")
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(callSuper = true)
public class Review extends BaseEntity<Long> {
    @Length(max = 255, message = "The field must be less than 255 characters")
    private String reviewTitle;
    @Length(max = 255, message = "The field must be less than 255 characters")
    private String comment;
    private ReviewCommentStatus reviewCommentStatus;
    @Min(value = 1)
    @Max(value = 5)
    private Integer rating;
    private ReviewRatingStatus reviewRatingStatus;
    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "product_id", nullable = false)
    @OnDelete(action = OnDeleteAction.CASCADE)
    private Product product;
}
