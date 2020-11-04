package com.product.review.model.mapper.review;

import com.product.review.model.domain.review.ReviewAddRequest;
import com.product.review.model.dto.review.ReviewDto;
import com.product.review.persistence.entity.review.Review;
import org.mapstruct.Mapper;

@Mapper
public interface ReviewMapper {

    ReviewDto reviewToReviewDto(Review review);

    Review reviewAddRequestToReview(ReviewAddRequest reviewAddRequest);
}
