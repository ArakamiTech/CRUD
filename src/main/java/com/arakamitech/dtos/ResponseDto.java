package com.arakamitech.dtos;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

@Data
@AllArgsConstructor
@Builder(toBuilder = true)
public class ResponseDto {

	private UsuariosDto usuario;
	private String mensaje;
	
}
