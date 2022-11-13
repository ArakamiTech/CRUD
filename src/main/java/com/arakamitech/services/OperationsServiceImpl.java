package com.arakamitech.services;

import java.util.Objects;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.arakamitech.entities.UsuariosEntity;
import com.arakamitech.exceptions.NotFoundException;
import com.arakamitech.repositories.IOperationsRepositoryUsuarios;

@Service
public class OperationsServiceImpl implements IOperationsService {

	private IOperationsRepositoryUsuarios repository;

	@Autowired
	public OperationsServiceImpl(IOperationsRepositoryUsuarios repository) {
		this.repository = repository;
	}

	@Override
	public UsuariosEntity findById(Long id) {
		Optional<UsuariosEntity> usuarioEntity = repository.findById(id);
		if (usuarioEntity.isEmpty()) {
			throw new NotFoundException("No se encontraron resultados");
		}
		return usuarioEntity.get();
	}

	@Override
	public UsuariosEntity findByTelefonoUsuario(String telefono) {
		UsuariosEntity usuarioEntity = repository.findByTelefonoUsuario(telefono);
		if (Objects.isNull(usuarioEntity)) {
			throw new NotFoundException("No se encontraron resultados");
		}
		return usuarioEntity;
	}

	@Override
	public UsuariosEntity findByCorreoUsuario(String correo) {
		UsuariosEntity usuarioEntity = repository.findByCorreo(correo);
		if (Objects.isNull(usuarioEntity)) {
			throw new NotFoundException("No se encontraron resultados");
		}
		return usuarioEntity;
	}

	@Override
	public UsuariosEntity findByCorreoUsuarioAndIdentificacionUsuario(String correo, String identificacion) {
		UsuariosEntity usuarioEntity = repository.findByCorreoUsuarioAndIdentificacionUsuario(correo, identificacion);
		if (Objects.isNull(usuarioEntity)) {
			throw new NotFoundException("No se encontraron resultados");
		}
		return usuarioEntity;
	}

	@Override
	public UsuariosEntity findByCorreoAndIdentificacion(String correo, String identificacion) {
		UsuariosEntity usuarioEntity = repository.findByCorreoAndIdentificacion(correo, identificacion);
		if (Objects.isNull(usuarioEntity)) {
			throw new NotFoundException("No se encontraron resultados");
		}
		return usuarioEntity;
	}

	@Override
	public UsuariosEntity findByCorreoAndIdentificacionQuery(String correo, String identificacion) {
		UsuariosEntity usuarioEntity = repository.findByCorreoAndIdentificacionQuery(correo, identificacion);
		if (Objects.isNull(usuarioEntity)) {
			throw new NotFoundException("No se encontraron resultados");
		}
		return usuarioEntity;
	}

}
