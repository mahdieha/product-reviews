package com.product.review.services.review;

import com.product.review.model.enums.review.ReviewCommentStatus;
import com.product.review.model.enums.review.ReviewRatingStatus;
import com.product.review.persistence.entity.product.Product;
import com.product.review.persistence.entity.review.Review;
import com.product.review.persistence.repository.product.ProductRepository;
import com.product.review.persistence.repository.review.ReviewRepository;
import org.junit.Assert;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest
class ReviewServiceImplTest {

    @Autowired
    ProductRepository productRepository;
    @Autowired
    ReviewRepository reviewRepository;
    @Autowired
    ReviewService reviewService;

    @Test
    void addReview() {
        Product product =
                Product.builder()
                        .productTitle("Product-1")
                        .productPrice(12.0000)
                        .productDescription("Product Description")
                        .isShow(true)
                        .isComment(true)
                        .isRating(true)
                        .build();
        product = productRepository.save(product);

        Review review =
                Review.builder()
                        .reviewTitle("Review-1")
                        .comment("Review Comment")
                        .reviewCommentStatus(ReviewCommentStatus.INITIAL)
                        .rating(3)
                        .reviewRatingStatus(ReviewRatingStatus.INITIAL)
                        .product(product)
                        .build();


        reviewRepository.save(review);
        Assertions.assertTrue(review.getId() > 0);


    }

    @Test
    void editReviewCommentStatus() {
        Product product =
                Product.builder()
                        .productTitle("Product-2")
                        .productPrice(13.0000)
                        .productDescription("Product Description")
                        .isShow(true)
                        .isComment(true)
                        .isRating(true)
                        .build();
        product = productRepository.save(product);

        Review review =
                Review.builder()
                        .reviewTitle("Review-2")
                        .comment("Review Comment")
                        .reviewCommentStatus(ReviewCommentStatus.INITIAL)
                        .rating(3)
                        .reviewRatingStatus(ReviewRatingStatus.INITIAL)
                        .product(product)
                        .build();
        review = reviewRepository.save(review);
        review.setReviewCommentStatus(ReviewCommentStatus.APPROVED);
        Assert.assertEquals(ReviewCommentStatus.APPROVED, review.getReviewCommentStatus());

    }

    @Test
    void editReviewRatingStatus() {
        Product product =
                Product.builder()
                        .productTitle("Product-3")
                        .productPrice(14.0000)
                        .productDescription("Product Description")
                        .isShow(true)
                        .isComment(true)
                        .isRating(true)
                        .build();
        product = productRepository.save(product);

        Review review =
                Review.builder()
                        .reviewTitle("Review-3")
                        .comment("Review Comment")
                        .reviewCommentStatus(ReviewCommentStatus.INITIAL)
                        .rating(3)
                        .reviewRatingStatus(ReviewRatingStatus.INITIAL)
                        .product(product)
                        .build();
        review = reviewRepository.save(review);
        review.setReviewRatingStatus(ReviewRatingStatus.APPROVED);
        Assert.assertEquals(ReviewRatingStatus.APPROVED, review.getReviewRatingStatus());

    }
}