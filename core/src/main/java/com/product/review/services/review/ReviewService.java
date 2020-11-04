package com.product.review.services.review;

import com.product.review.model.domain.review.ReviewAddRequest;
import com.product.review.model.dto.base.BaseDTO;
import com.product.review.model.enums.review.ReviewCommentStatus;
import com.product.review.model.enums.review.ReviewRatingStatus;

public interface ReviewService {

    BaseDTO addReview(ReviewAddRequest reviewAddRequest);

    BaseDTO getAllReviewWithPagination(Integer pageNumber, Integer pageSize);

    BaseDTO getAllReview();

    BaseDTO getUserReviews(Integer pageNumber, Integer pageSize);

    BaseDTO editReviewCommentStatus(Long reviewId, ReviewCommentStatus reviewCommentStatus);

    BaseDTO editReviewRatingStatus(Long reviewId, ReviewRatingStatus reviewRatingStatus);
}
