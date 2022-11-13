package com.arakamitech.dtos;

import lombok.Data;

@Data
public class UsuariosDto {
	
	private Long id;
	private String nombreUsuario;
	private String identificacionUsuario;
	private String telefonoUsuario;
	private String correoUsuario;

}
