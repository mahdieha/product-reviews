package com.product.review.controller.v1.review;

import com.product.review.controller.BaseController;
import com.product.review.model.domain.review.ReviewAddRequest;
import com.product.review.model.dto.base.BaseDTO;
import com.product.review.model.enums.review.ReviewCommentStatus;
import com.product.review.model.enums.review.ReviewRatingStatus;
import io.swagger.annotations.ApiParam;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import javax.validation.constraints.Min;

@RestController
@RequestMapping("/review")
public class ReviewController extends BaseController {

    @PostMapping
    public ResponseEntity addReview(@Valid @RequestBody ReviewAddRequest reviewAddRequest) {
        return new ResponseEntity<>(reviewServiceImpl.addReview(reviewAddRequest), HttpStatus.CREATED);
    }

    @GetMapping(value = "page")
    public ResponseEntity getAllReviewWithPagination(@ApiParam(value = "0", name = "pageNumber", required = true) @RequestParam @Min(0) Integer pageNumber,
                                                     @ApiParam(value = "10", name = "pageSize", required = true) @RequestParam @Min(1) Integer pageSize) {
        BaseDTO baseDTO = reviewServiceImpl.getAllReviewWithPagination(pageNumber, pageSize);
        return new ResponseEntity<>(baseDTO, HttpStatus.OK);
    }

    @GetMapping()
    public ResponseEntity getAllReview() {
        return new ResponseEntity<>(reviewServiceImpl.getAllReview(), HttpStatus.OK);
    }


    @PatchMapping("/comment-status/{reviewId}")
    public ResponseEntity editReviewCommentStatus(@ApiParam(name = "reviewId") @RequestParam Long reviewId, @ApiParam(name = "reviewCommentStatus", required = true) @RequestParam ReviewCommentStatus reviewCommentStatus) {
        return new ResponseEntity<>(reviewServiceImpl.editReviewCommentStatus(reviewId, reviewCommentStatus), HttpStatus.OK);
    }

    @PatchMapping("/rating-status/{reviewId}")
    public ResponseEntity editReviewRatingStatus(@ApiParam(name = "reviewId") @RequestParam Long reviewId, @ApiParam(name = "reviewRatingStatus", required = true) @RequestParam ReviewRatingStatus reviewRatingStatus) {
        return new ResponseEntity<>(reviewServiceImpl.editReviewRatingStatus(reviewId, reviewRatingStatus), HttpStatus.OK);
    }

    @GetMapping("/user-reviews")
    public ResponseEntity getUserReviews(@ApiParam(value = "0", name = "pageNumber", required = true) @RequestParam @Min(0) Integer pageNumber,
                                         @ApiParam(value = "3", name = "pageSize", required = true) @RequestParam @Min(3) Integer pageSize) {
        BaseDTO baseDTO = reviewServiceImpl.getUserReviews(pageNumber, pageSize);
        return new ResponseEntity<>(baseDTO, HttpStatus.OK);

    }
}