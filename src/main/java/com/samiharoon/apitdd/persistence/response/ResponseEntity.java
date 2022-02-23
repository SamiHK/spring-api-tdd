package com.samiharoon.apitdd.persistence.response;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class ResponseEntity<T> {

    protected Integer status;
	protected String message;
	protected T data;

}
