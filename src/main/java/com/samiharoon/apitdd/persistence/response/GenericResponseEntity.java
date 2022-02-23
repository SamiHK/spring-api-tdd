package com.samiharoon.apitdd.persistence.response;

import org.springframework.http.HttpStatus;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;

public class GenericResponseEntity {


    public static <T> org.springframework.http.ResponseEntity create(String message, Integer status, T data, Long totalSize, Integer pageSize, Integer totalPages,
                                                                     MultiValueMap<String, String> headers, HttpStatus httpStatus) {
        return new org.springframework.http.ResponseEntity(new PageableResponseEntity<T>(status, message, data, totalSize, pageSize,totalPages), headers,
                httpStatus);
    }



    public static <T> org.springframework.http.ResponseEntity create(String message, Integer status, T data, HttpStatus httpStatus) {
        return create(null, status, data, new LinkedMultiValueMap<String, String>(), httpStatus);

    }



    public static <T> org.springframework.http.ResponseEntity create( PageableResponseEntity<T> pageableResponse, HttpStatus httpStatus) {
        return new org.springframework.http.ResponseEntity(pageableResponse, httpStatus);
    }

    public static <T> org.springframework.http.ResponseEntity create(Integer status, T data, HttpStatus httpStatus) {
        return create(null, status, data, new LinkedMultiValueMap<String, String>(), httpStatus);
    }

    public static <T> org.springframework.http.ResponseEntity create(String message, Integer status, T data,
                                                                     MultiValueMap<String, String> headers, HttpStatus httpStatus) {
        return new org.springframework.http.ResponseEntity(new ResponseEntity<>(status, message, data), headers,
                httpStatus);
    }
}
