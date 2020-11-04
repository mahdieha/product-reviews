package com.product.review.persistence.repository.review;

import com.product.review.model.enums.review.ReviewCommentStatus;
import com.product.review.model.enums.review.ReviewRatingStatus;
import com.product.review.persistence.entity.review.Review;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ReviewRepository extends JpaRepository<Review, Long> {

    Page<Review> findAllByReviewCommentStatusEqualsAndReviewRatingStatusEquals
            (ReviewCommentStatus reviewCommentStatus, ReviewRatingStatus reviewRatingStatus, Pageable pageable);

}
