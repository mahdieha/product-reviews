package com.product.review.model.dto.base;

import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@Builder
public class BaseDTO<T> {

    private MetaDTO meta;

    private T data;

    public BaseDTO(MetaDTO meta, T data) {
        this.meta = meta;
        this.data = data;
    }

    public BaseDTO(MetaDTO meta) {
        this.meta = meta;
    }

}
