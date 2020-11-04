package com.product.review.model.mapper.product;

import com.product.review.model.domain.product.ProductAddRequest;
import com.product.review.model.domain.product.ProductEditRequest;
import com.product.review.model.dto.product.ProductDto;
import com.product.review.persistence.entity.product.Product;
import org.mapstruct.Mapper;
import org.mapstruct.MappingTarget;

@Mapper
public interface ProductMapper {

    ProductDto productToProductDto(Product product);

    Product productAddRequestToProduct(ProductAddRequest productAddRequest);

    Product productUpdateRequestToProduct(@MappingTarget Product product, ProductEditRequest productEditRequest);
}