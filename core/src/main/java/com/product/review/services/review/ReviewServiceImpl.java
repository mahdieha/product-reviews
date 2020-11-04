package com.product.review.services.review;

import com.product.review.exceptions.ServiceException;
import com.product.review.model.domain.review.ReviewAddRequest;
import com.product.review.model.dto.base.BaseDTO;
import com.product.review.model.dto.base.MetaDTO;
import com.product.review.model.dto.review.ReviewDto;
import com.product.review.model.enums.product.ProductReviewShowStatus;
import com.product.review.model.enums.review.ReviewCommentStatus;
import com.product.review.model.enums.review.ReviewRatingStatus;
import com.product.review.persistence.entity.product.Product;
import com.product.review.persistence.entity.review.Review;
import com.product.review.services.BaseService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.util.*;
import java.util.stream.Collectors;

@Service
public class ReviewServiceImpl extends BaseService implements ReviewService {


    @Override
    public BaseDTO addReview(ReviewAddRequest reviewAddRequest) {

        Product product = checkProductReviewConditions(reviewAddRequest);

        Review review = reviewMapper.reviewAddRequestToReview(reviewAddRequest);

        review.setReviewCommentStatus(ReviewCommentStatus.INITIAL);
        review.setReviewRatingStatus(ReviewRatingStatus.INITIAL);
        review.setProduct(product);
        product.setProductReviewShowStatus(ProductReviewShowStatus.PUBLIC_FOR_VISITORS);
        review = reviewRepository.save(review);

        return BaseDTO.builder()
                .meta(MetaDTO.getInstance(applicationProperties))
                .data(review)
                .build();

    }

    @Override
    public BaseDTO getAllReviewWithPagination(Integer pageNumber, Integer pageSize) {
        Page<Review> result = reviewRepository.findAll(PageRequest.of(pageNumber, pageSize));
        List<ReviewDto> reviewDtoList = new ArrayList<>();
        result.forEach(review -> reviewDtoList.add(reviewMapper.reviewToReviewDto(review)));
        return BaseDTO.builder()
                .meta(MetaDTO.getInstance(applicationProperties))
                .data(reviewDtoList)
                .build();
    }

    @Override
    public BaseDTO getAllReview() {
        List<ReviewDto> reviewDtoList = reviewRepository.findAll()
                .stream().map(reviewMapper::reviewToReviewDto).collect(Collectors.toList());

        return BaseDTO.builder()
                .meta(MetaDTO.getInstance(applicationProperties))
                .data(reviewDtoList)
                .build();
    }


    @Override
    public BaseDTO getUserReviews(Integer pageNumber, Integer pageSize) {

        Page<Review> reviewsResult = findAllApprovedCommentAndRating(pageNumber, pageSize);

        OptionalDouble averageRating = reviewsResult.stream().mapToInt(Review::getRating).average();

        if (averageRating.isPresent()) {

            Map<String, Object> configuration = new TreeMap<>();
            configuration.put("averageRating", averageRating);
            configuration.put("result", reviewsResult);

            return BaseDTO.builder()
                    .meta(MetaDTO.getInstance(applicationProperties))
                    .data(configuration)
                    .build();
        }

        return BaseDTO.builder()
                .meta(MetaDTO.getInstance(applicationProperties))
                .data(null)
                .build();

    }

    @Override
    public BaseDTO editReviewCommentStatus(Long reviewId, ReviewCommentStatus reviewCommentStatus) {
        Review review = getReviewEntity(reviewId);
        review.setReviewCommentStatus(reviewCommentStatus);
        reviewRepository.save(review);
        return BaseDTO.builder()
                .meta(MetaDTO.getInstance(applicationProperties))
                .data(reviewMapper.reviewToReviewDto(review))
                .build();
    }

    @Override
    public BaseDTO editReviewRatingStatus(Long reviewId, ReviewRatingStatus reviewRatingStatus) {
        Review review = getReviewEntity(reviewId);
        review.setReviewRatingStatus(reviewRatingStatus);
        reviewRepository.save(review);
        return BaseDTO.builder()
                .meta(MetaDTO.getInstance(applicationProperties))
                .data(reviewMapper.reviewToReviewDto(review))
                .build();
    }


    private Product getProductEntity(Long productId) {
        return productRepository.findById(productId).orElseThrow(() ->
                new ServiceException(applicationProperties.getCode("application.message.notFoundRecord.code"),
                        applicationProperties.getProperty("application.message.notFoundRecord.text"),
                        HttpStatus.NOT_FOUND));
    }

    private Review getReviewEntity(Long reviewId) {

        return reviewRepository.findById(reviewId).orElseThrow(() ->
                new ServiceException(applicationProperties.getCode("application.message.notFoundRecord.code"),
                        applicationProperties.getProperty("application.message.notFoundRecord.text"),
                        HttpStatus.NOT_FOUND));

    }

    private Page<Review> findAllApprovedCommentAndRating(Integer pageNumber, Integer pageSize) {

        Page<Review> reviewsResult = reviewRepository.findAllByReviewCommentStatusEqualsAndReviewRatingStatusEquals
                (ReviewCommentStatus.APPROVED, ReviewRatingStatus.APPROVED, PageRequest.of(pageNumber, pageSize, Sort.by("createDate").descending()));

        if (reviewsResult.getContent().isEmpty()) {
            throw new ServiceException(applicationProperties.getCode("application.message.review.not.approved.code"),
                    applicationProperties.getProperty("application.message.review.not.approved.text"),
                    HttpStatus.NOT_FOUND);
        }

        return reviewsResult;
    }

    private Product checkProductReviewConditions(ReviewAddRequest reviewAddRequest) {

        Product product = getProductEntity(reviewAddRequest.getProduct().getId());

        if (!product.isShow()) {
            throw new ServiceException(applicationProperties.getCode("application.message.add.review.not.allowed.code"),
                    applicationProperties.getProperty("application.message.add.review.not.allowed.text"),
                    HttpStatus.BAD_REQUEST);

        }
        if (!product.isComment() && !product.isRating()) {
            throw new ServiceException(applicationProperties.getCode("application.message.add.review.not.allowed.code"),
                    applicationProperties.getProperty("application.message.add.review.not.allowed.text"),
                    HttpStatus.BAD_REQUEST);
        }

        if (!product.isComment()) {
            reviewAddRequest.setComment(null);
        }

        if (!product.isRating()) {
            reviewAddRequest.setRating(null);
        }

        return product;
    }
}


