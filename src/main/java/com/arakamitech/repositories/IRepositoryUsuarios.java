package com.arakamitech.repositories;

import java.util.Optional;

import org.springframework.data.repository.CrudRepository;

import com.arakamitech.entities.UsuariosEntity;

public interface IRepositoryUsuarios extends CrudRepository<UsuariosEntity, Long> {

	Optional<UsuariosEntity> findByIdentificacionUsuario(String identificacion);

	Optional<UsuariosEntity> findByIdentificacionUsuarioAndPassword(String identificacion, String password);

}
