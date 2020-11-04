package com.product.review.exceptions;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.product.review.configuration.ApplicationContextHolder;
import com.product.review.model.dto.base.BaseDTO;
import com.product.review.model.dto.base.MetaDTO;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.client.HttpServerErrorException;

import javax.servlet.http.HttpServletRequest;

@ControllerAdvice
public class ServiceExceptionHandler extends ApplicationContextHolder {

    @ExceptionHandler(ServiceException.class)
    public final ResponseEntity<BaseDTO> handleAllExceptions(ServiceException ex, HttpServletRequest request) {
        BaseDTO baseDTO = new BaseDTO(new MetaDTO(ex.getCode(), ex.getMessage()));
        return new ResponseEntity<>(baseDTO, ex.getHttpStatus() == null ? HttpStatus.INTERNAL_SERVER_ERROR : ex.getHttpStatus()
        );
    }

    @ExceptionHandler(Exception.class)
    public final ResponseEntity<BaseDTO> handleAllExceptions(Exception ex, HttpServletRequest request) throws Exception {
        BaseDTO baseDTO = new ObjectMapper().readValue((((HttpServerErrorException) ex).getResponseBodyAsString()), BaseDTO.class);
        return new ResponseEntity<>(baseDTO, ((HttpServerErrorException) ex).getStatusCode()
        );

    }
}
