package com.paulk.demo.client;

import feign.FeignException;
import org.json.JSONObject;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import javax.servlet.http.HttpServletResponse;
import java.util.Map;

/**
 * Override the {@link FeignException} handling to convert the exception content into the actual response.
 */
@RestControllerAdvice
public class FeignExceptionHandler {

    /**
     * Override the {@link ExceptionHandler} for {@link FeignException.BadRequest} to extract the {@link ResponseEntity} body from the {@link HttpServletResponse}.
     *
     * @param exception - The {@link FeignException} thrown.
     * @param response  - The {@link HttpServletResponse} returned.
     * @return A {@link Map} containings the {@link ResponseEntity} body.
     */
    @ExceptionHandler(FeignException.BadRequest.class)
    public Map<String, Object> handleFeignStatusException(FeignException exception, HttpServletResponse response) {
        response.setStatus(exception.status());
        return new JSONObject(exception.contentUTF8()).toMap();
    }

}