package com.product.review.controller.v1.product;

import com.product.review.controller.BaseController;
import com.product.review.model.domain.product.ProductAddRequest;
import com.product.review.model.domain.product.ProductEditRequest;
import com.product.review.model.dto.base.BaseDTO;
import com.product.review.model.enums.product.ProductReviewShowStatus;
import io.swagger.annotations.ApiParam;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import javax.validation.constraints.Min;

@RestController
@RequestMapping("/product")
public class ProductController extends BaseController {

    @PostMapping
    public ResponseEntity addProduct(@Valid @RequestBody ProductAddRequest productAddRequest) {
        return new ResponseEntity<>(productServiceImpl.addProduct(productAddRequest), HttpStatus.CREATED);
    }

    @PutMapping
    public ResponseEntity editProduct(@Valid @RequestBody ProductEditRequest productEditRequest) {
        return new ResponseEntity<>(productServiceImpl.editProduct(productEditRequest), HttpStatus.OK);
    }

    @PatchMapping("/isShowProduct/{productId}")
    public ResponseEntity editIsShowProduct(@PathVariable("productId") Long productId, @ApiParam(name = "isShowProduct", required = true) @RequestParam("isShowProduct") Boolean isShowProduct) {
        return new ResponseEntity<>(productServiceImpl.editIsShowProduct(productId, isShowProduct), HttpStatus.OK);
    }

    @PatchMapping("/isComment/{productId}")
    public ResponseEntity editProductIsComment(@PathVariable("productId") Long productId, @ApiParam(name = "isComment", required = true) @RequestParam("isComment") Boolean isComment) {
        return new ResponseEntity<>(productServiceImpl.editProductIsComment(productId, isComment), HttpStatus.OK);
    }


    @PatchMapping("/isRating/{productId}")
    public ResponseEntity editProductIsRating(@PathVariable("productId") Long productId, @ApiParam(name = "isRating", required = true) @RequestParam("isRating") Boolean isRating) {
        return new ResponseEntity<>(productServiceImpl.editProductIsRating(productId, isRating), HttpStatus.OK);
    }


    @PatchMapping("/review-showStatus/{productId}")
    public ResponseEntity editProductReviewShowStatus(@PathVariable("productId") Long productId, @ApiParam(name = "productReviewShowStatus", required = true) @RequestParam("productReviewShowStatus") ProductReviewShowStatus productReviewShowStatus) {
        return new ResponseEntity<>(productServiceImpl.editProductReviewShowStatus(productId, productReviewShowStatus), HttpStatus.OK);
    }

    @GetMapping(value = "page")
    public ResponseEntity getProducts(@ApiParam(value = "0", name = "pageNumber", required = true) @RequestParam @Min(0) Integer pageNumber,
                                      @ApiParam(value = "10", name = "pageSize", required = true) @RequestParam @Min(1) Integer pageSize) {
        BaseDTO baseDTO = productServiceImpl.getAllProduct(pageNumber, pageSize);
        return new ResponseEntity<>(baseDTO, HttpStatus.OK);
    }

    @GetMapping
    public ResponseEntity getProduct(@ApiParam(name = "productId", required = true) @RequestParam Long productId) {
        BaseDTO baseDTO = productServiceImpl.getProduct(productId);
        return new ResponseEntity<>(baseDTO, HttpStatus.OK);
    }

    @GetMapping("/approved")
    public ResponseEntity getApprovedProduct(@ApiParam(name = "productId", required = true) @RequestParam Long productId) {
        BaseDTO baseDTO = productServiceImpl.getApprovedProduct(productId);
        return new ResponseEntity<>(baseDTO, HttpStatus.OK);
    }
}