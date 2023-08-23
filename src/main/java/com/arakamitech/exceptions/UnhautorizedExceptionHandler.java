package com.arakamitech.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import com.arakamitech.dtos.TokenDto;

@ControllerAdvice
public class UnhautorizedExceptionHandler {

	@ExceptionHandler
	public ResponseEntity<TokenDto> exceptionHandler(UnhautorizedException ex) {
		return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(new TokenDto(ex.getMessage()));
	}

}
