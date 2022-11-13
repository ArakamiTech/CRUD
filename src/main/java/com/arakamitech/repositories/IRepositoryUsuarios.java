package com.arakamitech.repositories;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.arakamitech.entities.UsuariosEntity;

@Repository
public interface IRepositoryUsuarios extends CrudRepository<UsuariosEntity, Long> {

	UsuariosEntity findByIdentificacionUsuario(String identificacion);

}
