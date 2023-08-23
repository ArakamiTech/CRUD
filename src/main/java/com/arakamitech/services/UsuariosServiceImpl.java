package com.arakamitech.services;

import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.arakamitech.entities.UsuariosEntity;
import com.arakamitech.exceptions.DataIntegrityException;
import com.arakamitech.exceptions.NotFoundException;
import com.arakamitech.repositories.IRepositoryUsuarios;

@Service
@Transactional
public class UsuariosServiceImpl implements IUsuariosService {

	private final IRepositoryUsuarios iRepositoryUsuarios;

	public UsuariosServiceImpl(IRepositoryUsuarios iRepositoryUsuarios) {
		this.iRepositoryUsuarios = iRepositoryUsuarios;
	}

	@Override
	public UsuariosEntity getUsuario(String identificacion) {
		var usuarioEntity = iRepositoryUsuarios.findByIdentificacionUsuario(identificacion);
		if (usuarioEntity.isEmpty()) {
			throw new NotFoundException("No se encontraron resultados");
		}
		return usuarioEntity.get();
	}

	@Override
	public UsuariosEntity createUsuario(UsuariosEntity usuario) {
		try {
			return iRepositoryUsuarios.save(usuario);
		} catch (DataIntegrityViolationException e) {
			throw new DataIntegrityException("La identificación ya se encuentra registrada");
		}
	}

	@Override
	public UsuariosEntity updateUsuario(UsuariosEntity usuario) {
		return iRepositoryUsuarios.save(usuario);
	}

	@Override
	public void deleteUsuario(String identificacion) {
		var usuarioEntity = iRepositoryUsuarios.findByIdentificacionUsuario(identificacion);
		if (usuarioEntity.isEmpty()) {
			throw new NotFoundException("No se encontraron resultados");
		}
		iRepositoryUsuarios.delete(usuarioEntity.get());
	}

}
