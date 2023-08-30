package com.arakamitech.dtos;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class UsuariosDto {
	
	private Long id;
	private String nombreUsuario;
	private String identificacionUsuario;
	private String telefonoUsuario;
	private String correoUsuario;
	private String password;

	public UsuariosDto(String nombreUsuario, String identificacionUsuario, String telefonoUsuario, String correoUsuario, String password) {
		this.nombreUsuario = nombreUsuario;
		this.identificacionUsuario = identificacionUsuario;
		this.telefonoUsuario = telefonoUsuario;
		this.correoUsuario = correoUsuario;
		this.password = password;
	}
}
