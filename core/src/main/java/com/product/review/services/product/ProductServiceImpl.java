package com.product.review.services.product;

import com.product.review.exceptions.ServiceException;
import com.product.review.model.domain.product.ProductAddRequest;
import com.product.review.model.domain.product.ProductEditRequest;
import com.product.review.model.dto.base.BaseDTO;
import com.product.review.model.dto.base.MetaDTO;
import com.product.review.model.dto.product.ProductDto;
import com.product.review.model.enums.product.ProductReviewShowStatus;
import com.product.review.persistence.entity.product.Product;
import com.product.review.services.BaseService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class ProductServiceImpl extends BaseService implements ProductService {

    @Override
    public BaseDTO addProduct(ProductAddRequest productAddRequest) {

        if (productRepository.existsProductByProductTitleIgnoreCase(productAddRequest.getProductTitle()).equals(true)) {
            throw ServiceException.builder()
                    .code(applicationProperties.getCode("application.message.duplicate.entity.by.title.code"))
                    .message(applicationProperties.getProperty("application.message.duplicate.entity.by.title.text"))
                    .httpStatus(HttpStatus.BAD_REQUEST)
                    .build();
        }

        Product product = productMapper.productAddRequestToProduct(productAddRequest);

        productRepository.save(product);
        return BaseDTO.builder()
                .meta(MetaDTO.getInstance(applicationProperties))
                .data(product)
                .build();

    }

    @Override
    public BaseDTO editProduct(ProductEditRequest productEditRequest) {
        Product product = getProductEntity(productEditRequest.getProductId());
        if (productEditRequest.getProductTitle().equalsIgnoreCase(product.getProductTitle()) && productRepository.existsProductByProductTitleIgnoreCase(productEditRequest.getProductTitle()).equals(true)) {
            throw ServiceException.builder()
                    .code(applicationProperties.getCode("application.message.duplicate.entity.by.title.code"))
                    .message(applicationProperties.getProperty("application.message.duplicate.entity.by.title.text"))
                    .httpStatus(HttpStatus.BAD_REQUEST)
                    .build();
        }
        product = productMapper.productUpdateRequestToProduct(product, productEditRequest);
        productRepository.save(product);
        return BaseDTO.builder()
                .meta(MetaDTO.getInstance(applicationProperties))
                .data(productMapper.productToProductDto(product))
                .build();
    }

    @Override
    public BaseDTO editIsShowProduct(Long productId, Boolean isShow) {
        Product product = getProductEntity(productId);
        product.setShow(isShow);
        productRepository.save(product);
        return BaseDTO.builder()
                .meta(MetaDTO.getInstance(applicationProperties))
                .data(productMapper.productToProductDto(product))
                .build();
    }

    @Override
    public BaseDTO editProductIsComment(Long productId, Boolean isComment) {
        Product product = getProductEntity(productId);
        product.setComment(isComment);
        productRepository.save(product);
        return BaseDTO.builder()
                .meta(MetaDTO.getInstance(applicationProperties))
                .data(productMapper.productToProductDto(product))
                .build();
    }

    @Override
    public BaseDTO editProductIsRating(Long productId, Boolean isRating) {
        Product product = getProductEntity(productId);
        product.setRating(isRating);
        productRepository.save(product);
        return BaseDTO.builder()
                .meta(MetaDTO.getInstance(applicationProperties))
                .data(productMapper.productToProductDto(product))
                .build();
    }

    @Override
    public BaseDTO editProductReviewShowStatus(Long productId, ProductReviewShowStatus productReviewShowStatus) {
        Product product = getProductEntity(productId);
        product.setProductReviewShowStatus(productReviewShowStatus);
        productRepository.save(product);
        return BaseDTO.builder()
                .meta(MetaDTO.getInstance(applicationProperties))
                .data(productMapper.productToProductDto(product))
                .build();
    }

    @Override
    public BaseDTO getAllProduct(Integer pageNumber, Integer pageSize) {
        Page<Product> result = productRepository.findAll(PageRequest.of(pageNumber, pageSize));
        List<ProductDto> productDtoList = new ArrayList<>();
        result.forEach(product -> productDtoList.add(productMapper.productToProductDto(product)));
        return BaseDTO.builder()
                .meta(MetaDTO.getInstance(applicationProperties))
                .data(productDtoList)
                .build();
    }


    @Override
    public BaseDTO getProduct(Long productId) {
        Product product = getProductEntity(productId);
        return BaseDTO.builder()
                .meta(MetaDTO.getInstance(applicationProperties))
                .data(productMapper.productToProductDto(product))
                .build();
    }

    @Override
    public BaseDTO getApprovedProduct(Long productId) {
        Product product = getApprovedProductEntity(productId);
        return BaseDTO.builder()
                .meta(MetaDTO.getInstance(applicationProperties))
                .data(productMapper.productToProductDto(product))
                .build();
    }

    private Product getProductEntity(Long productId) {
        return productRepository.findById(productId).orElseThrow(() ->
                new ServiceException(applicationProperties.getCode("application.message.product.not.found.code"),
                        applicationProperties.getProperty("application.message.product.not.found.text"),
                        HttpStatus.NOT_FOUND));

    }

    private Product getApprovedProductEntity(Long productId) {
        Optional<Product> product = productRepository.findById(productId);
        if (!product.isPresent() || !product.get().isShow()) {
            throw new ServiceException(applicationProperties.getCode("application.message.product.not.found.code"),
                    applicationProperties.getProperty("application.message.product.not.found.text"),
                    HttpStatus.NOT_FOUND);
        }

        return product.get();
    }
}
