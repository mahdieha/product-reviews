package com.product.review.model.dto.base;


import com.product.review.configuration.ApplicationProperties;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class MetaDTO {
    private Integer code;
    private String message;

    public MetaDTO(int value, String name, String format) {

    }

    public static MetaDTO getInstance(ApplicationProperties applicationProperties) {
        return new MetaDTO(
                applicationProperties.getCode("application.message.success.code"),
                applicationProperties.getProperty("application.message.success.text")
        );
    }
}
