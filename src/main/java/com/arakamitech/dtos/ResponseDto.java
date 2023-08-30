package com.arakamitech.dtos;

import lombok.NoArgsConstructor;
import org.springframework.http.HttpStatus;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder(toBuilder = true)
public class ResponseDto {

	private UsuariosDto usuario;
	private String mensaje;
	private Integer code;
	private HttpStatus status;
	
}
