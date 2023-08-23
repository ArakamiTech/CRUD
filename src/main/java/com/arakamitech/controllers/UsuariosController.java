package com.arakamitech.controllers;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.arakamitech.business.IUsuariosBusiness;
import com.arakamitech.dtos.ResponseDto;
import com.arakamitech.dtos.UsuariosDto;

@RestController
@RequestMapping("/usuarios/crud")
public class UsuariosController {

	private static final Logger LOGGER = LoggerFactory.getLogger(UsuariosController.class);

	private final IUsuariosBusiness iUsuariosBusiness;

	public UsuariosController(IUsuariosBusiness iUsuariosBusiness) {
		this.iUsuariosBusiness = iUsuariosBusiness;
	}

	@GetMapping("/get")
	public ResponseEntity<ResponseDto> getUsuario(@RequestParam String identificacion) {
		LOGGER.info("Inicio Servicio get, Identificacion: {}", identificacion);
		return new ResponseEntity<>(iUsuariosBusiness.getUsuario(identificacion), HttpStatus.OK);
	}

	@PostMapping("/create")
	public ResponseEntity<ResponseDto> createUsuario(@RequestBody UsuariosDto usuario) {
		LOGGER.info("Inicio Servicio create, Request: {}", usuario);
		return new ResponseEntity<>(iUsuariosBusiness.createUsuario(usuario), HttpStatus.OK);
	}

	@PutMapping("/update")
	public ResponseEntity<ResponseDto> updateUsuario(@RequestBody UsuariosDto usuario) {
		LOGGER.info("Inicio Servicio update, Request: {}", usuario);
		return new ResponseEntity<>(iUsuariosBusiness.updateUsuario(usuario), HttpStatus.OK);
	}

	@DeleteMapping("/delete")
	public ResponseEntity<ResponseDto> deleteUsuario(@RequestParam String identificacion) {
		LOGGER.info("Inicio Servicio get, Identificacion: {}", identificacion);
		return new ResponseEntity<>(iUsuariosBusiness.delete(identificacion), HttpStatus.OK);
	}

}
