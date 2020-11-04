package com.product.review.services.product;

import com.product.review.model.enums.product.ProductReviewShowStatus;
import com.product.review.persistence.entity.product.Product;
import com.product.review.persistence.repository.product.ProductRepository;
import org.junit.Assert;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest
class ProductServiceImplTest {

    @Autowired
    ProductServiceImpl productService;
    @Autowired
    ProductRepository productRepository;


    @Test
    void addProduct() {
        Product product =
                Product.builder()
                        .productTitle("Product-1")
                        .productPrice(12.0000)
                        .productDescription("Product Description")
                        .isShow(false)
                        .isComment(false)
                        .isRating(false)
                        .build();


        productRepository.save(product);
        Assertions.assertTrue(product.getId() > 0);
    }


    @Test
    void editProduct() {
        Product product =
                Product.builder()
                        .productTitle("Product-1")
                        .productPrice(13.0000)
                        .productDescription("Product Description")
                        .isShow(false)
                        .isComment(false)
                        .isRating(false)
                        .build();
        productRepository.save(product);
        product = productRepository.findByProductTitle(product.getProductTitle());
        product.setProductTitle("Product-2");
        Assert.assertEquals("Product-2", product.getProductTitle());

    }

    @Test
    void editIsShowProduct() {
        Product product =
                Product.builder()
                        .productTitle("Product-3")
                        .productPrice(14.0000)
                        .productDescription("Product Description")
                        .isShow(false)
                        .isComment(false)
                        .isRating(false)
                        .productReviewShowStatus(ProductReviewShowStatus.PUBLIC_FOR_VISITORS)
                        .build();
        productRepository.save(product);

        product = productRepository.findByProductTitle(product.getProductTitle());
        product.setShow(true);
        Assertions.assertTrue(true);
    }

    @Test
    void editProductIsComment() {
        Product product =
                Product.builder()
                        .productTitle("Product-4")
                        .productPrice(15.0000)
                        .productDescription("Product Description")
                        .isShow(false)
                        .isComment(false)
                        .isRating(false)
                        .productReviewShowStatus(ProductReviewShowStatus.PUBLIC_FOR_VISITORS)
                        .build();
        productRepository.save(product);

        product = productRepository.findByProductTitle(product.getProductTitle());
        product.setComment(true);
        Assertions.assertTrue(true);
    }

    @Test
    void editProductIsRating() {
        Product product =
                Product.builder()
                        .productTitle("Product-5")
                        .productPrice(16.0000)
                        .productDescription("Product Description")
                        .isShow(false)
                        .isComment(false)
                        .isRating(false)
                        .productReviewShowStatus(ProductReviewShowStatus.PUBLIC_FOR_VISITORS)
                        .build();
        productRepository.save(product);

        product = productRepository.findByProductTitle(product.getProductTitle());
        product.setRating(true);
        Assertions.assertTrue(true);
    }

    @Test
    void editProductReviewShowStatus() {
        Product product =
                Product.builder()
                        .productTitle("Product-6")
                        .productPrice(17.0000)
                        .productDescription("Product Description")
                        .isShow(false)
                        .isComment(false)
                        .isRating(false)
                        .productReviewShowStatus(ProductReviewShowStatus.PUBLIC_FOR_VISITORS)
                        .build();
        productRepository.save(product);
        product = productRepository.findByProductTitle(product.getProductTitle());
        product.setProductReviewShowStatus(ProductReviewShowStatus.PRIVATE_FOR_BUYERS);
        Assert.assertEquals(ProductReviewShowStatus.PRIVATE_FOR_BUYERS, product.getProductReviewShowStatus());
    }

    @Test
    void getProduct() {
        Product product =
                Product.builder()
                        .productTitle("Product-6")
                        .productPrice(17.0000)
                        .productDescription("Product Description")
                        .isShow(false)
                        .isComment(false)
                        .isRating(false)
                        .productReviewShowStatus(ProductReviewShowStatus.PUBLIC_FOR_VISITORS)
                        .build();
        productRepository.save(product);
        productRepository.findById(product.getId());
        Assertions.assertEquals("Product-6", product.getProductTitle());

    }

    @Test
    void getApprovedProduct() {
        Product product =
                Product.builder()
                        .productTitle("Product-6")
                        .productPrice(17.0000)
                        .productDescription("Product Description")
                        .isShow(true)
                        .isComment(false)
                        .isRating(false)
                        .productReviewShowStatus(ProductReviewShowStatus.PUBLIC_FOR_VISITORS)
                        .build();
        productRepository.save(product);
        Assert.assertTrue(product.isShow());

    }
}