package com.arakamitech.business;

import com.arakamitech.dtos.ResponseDto;

public interface IOperationsBusiness {

	ResponseDto findById(String id);

	ResponseDto findByTelefonoUsuario(String telefono);

	ResponseDto findByCorreoUsuario(String correo);

	ResponseDto findByCorreoAndIdentificacion(String mode, String correo, String identificacion);

}
