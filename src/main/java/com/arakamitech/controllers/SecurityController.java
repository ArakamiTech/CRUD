package com.arakamitech.controllers;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.arakamitech.business.ISecurityBusiness;
import com.arakamitech.business.IUsuariosBusiness;
import com.arakamitech.config.User;
import com.arakamitech.dtos.ResponseDto;
import com.arakamitech.dtos.UsuariosDto;

@RestController
public class SecurityController {

	private static final Logger LOGGER = LoggerFactory.getLogger(SecurityController.class);

	private final IUsuariosBusiness iUsuariosBusiness;
	private final ISecurityBusiness iSecurityBusiness;

	public SecurityController(IUsuariosBusiness iUsuariosBusiness, ISecurityBusiness iSecurityBusiness) {
		this.iUsuariosBusiness = iUsuariosBusiness;
		this.iSecurityBusiness = iSecurityBusiness;
	}

	@PostMapping("/login")
	public User login(@RequestParam("user") String username, @RequestParam("password") String password) {
		LOGGER.info("Inicio de servicio de Login, username: {}, password: {}", username, password);
		return new User(iSecurityBusiness.getJWTToken(username, password));
	}

	@PostMapping("/create")
	public ResponseEntity<ResponseDto> create(@RequestBody UsuariosDto usuariosDto) {
		LOGGER.info("Inicio de servicio create, usuariosDto: {}", usuariosDto);
		return new ResponseEntity<>(iUsuariosBusiness.createUsuario(usuariosDto), HttpStatus.OK);
	}

}