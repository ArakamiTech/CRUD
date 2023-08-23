package com.arakamitech.util;

import org.springframework.http.HttpStatus;

import com.arakamitech.dtos.ResponseDto;
import com.arakamitech.dtos.UsuariosDto;
import com.arakamitech.entities.UsuariosEntity;

public class Util {

	private Util() {
	}

	public static ResponseDto buildResponseDto(UsuariosDto usuario) {
		return ResponseDto.builder().usuario(usuario).mensaje("OK").code(200).status(HttpStatus.OK).build();
	}

	public static void buildUsuarioEntity(UsuariosEntity usuarioEntity, UsuariosDto usuario) {
		usuarioEntity.setNombreUsuario(usuario.getNombreUsuario());
		usuarioEntity.setCorreoUsuario(usuario.getCorreoUsuario());
		usuarioEntity.setTelefonoUsuario(usuario.getTelefonoUsuario());
	}

	public static ResponseDto buildResponseDto() {
		return ResponseDto.builder().mensaje("Usuario eliminado con exito").code(200).status(HttpStatus.OK).build();
	}

}
