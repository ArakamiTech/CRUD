package com.arakamitech.services;

import com.arakamitech.entities.UsuariosEntity;

public interface ICRUDService {

	UsuariosEntity getUsuario(String identificacion);

	UsuariosEntity createUsuario(UsuariosEntity usuarioEntity);

	UsuariosEntity updateUsuario(UsuariosEntity usuarioEntity);

	void deleteUsuario(String identificacion);

}
