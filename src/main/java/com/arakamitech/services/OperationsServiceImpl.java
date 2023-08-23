package com.arakamitech.services;

import java.util.Objects;

import org.springframework.stereotype.Service;

import com.arakamitech.entities.UsuariosEntity;
import com.arakamitech.exceptions.NotFoundException;
import com.arakamitech.repositories.IOperationsRepositoryUsuarios;

@Service
public class OperationsServiceImpl implements IOperationsService {
	
	private static final String NOT_FOUND = "No se encontraron resultados";

	private final IOperationsRepositoryUsuarios repository;

	public OperationsServiceImpl(IOperationsRepositoryUsuarios repository) {
		this.repository = repository;
	}

	@Override
	public UsuariosEntity findById(Long id) {
		var usuarioEntity = repository.findById(id);
		if (usuarioEntity.isEmpty()) {
			throw new NotFoundException(NOT_FOUND);
		}
		return usuarioEntity.get();
	}

	@Override
	public UsuariosEntity findByTelefonoUsuario(String telefono) {
		var usuarioEntity = repository.findByTelefonoUsuario(telefono);
		if (Objects.isNull(usuarioEntity)) {
			throw new NotFoundException(NOT_FOUND);
		}
		return usuarioEntity;
	}

	@Override
	public UsuariosEntity findByCorreoUsuario(String correo) {
		var usuarioEntity = repository.findByCorreo(correo);
		if (Objects.isNull(usuarioEntity)) {
			throw new NotFoundException(NOT_FOUND);
		}
		return usuarioEntity;
	}

	@Override
	public UsuariosEntity findByCorreoUsuarioAndIdentificacionUsuario(String correo, String identificacion) {
		var usuarioEntity = repository.findByCorreoUsuarioAndIdentificacionUsuario(correo, identificacion);
		if (Objects.isNull(usuarioEntity)) {
			throw new NotFoundException(NOT_FOUND);
		}
		return usuarioEntity;
	}

	@Override
	public UsuariosEntity findByCorreoAndIdentificacion(String correo, String identificacion) {
		var usuarioEntity = repository.findByCorreoAndIdentificacion(correo, identificacion);
		if (Objects.isNull(usuarioEntity)) {
			throw new NotFoundException(NOT_FOUND);
		}
		return usuarioEntity;
	}

	@Override
	public UsuariosEntity findByCorreoAndIdentificacionQuery(String correo, String identificacion) {
		var usuarioEntity = repository.findByCorreoAndIdentificacionQuery(correo, identificacion);
		if (Objects.isNull(usuarioEntity)) {
			throw new NotFoundException(NOT_FOUND);
		}
		return usuarioEntity;
	}

}
