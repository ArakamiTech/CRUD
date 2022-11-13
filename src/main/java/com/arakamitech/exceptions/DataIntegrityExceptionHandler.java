package com.arakamitech.exceptions;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import com.arakamitech.dtos.ResponseDto;

@ControllerAdvice
public class DataIntegrityExceptionHandler {

	@ExceptionHandler
	public ResponseEntity<ResponseDto> exceptionHandler(DataIntegrityException ex) {
		return ResponseEntity.ok(new ResponseDto(null, ex.getMessage()));
	}

}
