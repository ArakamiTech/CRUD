package com.arakamitech.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.arakamitech.entities.UsuariosEntity;

public interface IOperationsRepositoryUsuarios extends JpaRepository<UsuariosEntity, Long> {

	UsuariosEntity findByTelefonoUsuario(String telefono);

	@Query(value = "SELECT u FROM usuarios u WHERE u.correoUsuario=?1")
	UsuariosEntity findByCorreo(String correo);

	UsuariosEntity findByCorreoUsuarioAndIdentificacionUsuario(String correo, String identificacion);

	@Query("SELECT u FROM usuarios u WHERE u.correoUsuario=:correo AND u.identificacionUsuario=:identificacion")
	UsuariosEntity findByCorreoAndIdentificacion(@Param("correo") String correo,
			@Param("identificacion") String identificacion);

	UsuariosEntity findByCorreoAndIdentificacionQuery(@Param("correo") String correo,
			@Param("identificacion") String identificacion);

}
