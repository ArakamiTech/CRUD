package com.arakamitech.services;

import com.arakamitech.entities.UsuariosEntity;

public interface IOperationsService {

	UsuariosEntity findById(Long id);

	UsuariosEntity findByTelefonoUsuario(String telefono);

	UsuariosEntity findByCorreoUsuario(String correo);

	UsuariosEntity findByCorreoUsuarioAndIdentificacionUsuario(String correo, String identificacion);
	
	UsuariosEntity findByCorreoAndIdentificacion(String correo, String identificacion);

	UsuariosEntity findByCorreoAndIdentificacionQuery(String correo, String identificacion);

}
