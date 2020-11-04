package com.product.review.exceptions;

import lombok.Builder;
import lombok.Data;
import lombok.EqualsAndHashCode;
import org.springframework.http.HttpStatus;

@Data
@Builder
@EqualsAndHashCode(callSuper = true)
public class ServiceException extends RuntimeException {

    private final Integer code;
    private final String message;
    private HttpStatus httpStatus;

    public ServiceException(Integer code, String message, HttpStatus httpStatus) {
        this.code = code;
        this.message = message;
        this.httpStatus = httpStatus;
    }

}

