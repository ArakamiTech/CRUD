package com.arakamitech.entities;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.NamedQuery;

import lombok.Builder;
import lombok.Data;

@Builder
@Data
@Entity(name = "usuarios")
@NamedQuery(name = "UsuariosEntity.findByCorreoAndIdentificacionQuery", query = "SELECT u FROM usuarios u WHERE u.correoUsuario=:correo AND u.identificacionUsuario=:identificacion")
public class UsuariosEntity {

	public UsuariosEntity() {
		super();
	}

	public UsuariosEntity(Long id, String nombreUsuario, String identificacionUsuario, String telefonoUsuario,
			String correoUsuario, String password) {
		super();
		this.id = id;
		this.nombreUsuario = nombreUsuario;
		this.identificacionUsuario = identificacionUsuario;
		this.telefonoUsuario = telefonoUsuario;
		this.correoUsuario = correoUsuario;
		this.password = password;
	}

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id_usuario")
	private Long id;
	@Column(name = "nombre_usuario")
	private String nombreUsuario;
	@Column(name = "identificacion_usuario")
	private String identificacionUsuario;
	@Column(name = "telefono_usuario")
	private String telefonoUsuario;
	@Column(name = "correo_usuarios")
	private String correoUsuario;
	@Column(name = "password")
	private String password;

}