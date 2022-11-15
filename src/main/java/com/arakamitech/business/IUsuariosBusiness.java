package com.arakamitech.business;

import com.arakamitech.dtos.ResponseDto;
import com.arakamitech.dtos.UsuariosDto;

public interface IUsuariosBusiness {
	
	ResponseDto getUsuario(String identificacion);

	ResponseDto createUsuario(UsuariosDto usuario);

	ResponseDto updateUsuario(UsuariosDto usuario);

	ResponseDto delete(String identificacion);

}
