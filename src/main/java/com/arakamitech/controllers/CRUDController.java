package com.arakamitech.controllers;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
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

import com.arakamitech.business.ICRUDBusiness;
import com.arakamitech.dtos.ResponseDto;
import com.arakamitech.dtos.UsuariosDto;

@RestController
@RequestMapping("/tutorial/crud")
public class CRUDController {

	private static final Logger LOGGER = LoggerFactory.getLogger(CRUDController.class);

	private final ICRUDBusiness iCRUDBusiness;

	@Autowired
	public CRUDController(ICRUDBusiness iCRUDBusiness) {
		this.iCRUDBusiness = iCRUDBusiness;
	}

	@GetMapping("/get")
	private ResponseEntity<ResponseDto> getUsuario(@RequestParam String identificacion) {
		LOGGER.info("Inicio Servicio get, Identificacion: {}", identificacion);
		return new ResponseEntity<ResponseDto>(iCRUDBusiness.getUsuario(identificacion), HttpStatus.OK);
	}

	@PostMapping("/create")
	private ResponseEntity<ResponseDto> createUsuario(@RequestBody UsuariosDto usuario) {
		LOGGER.info("Inicio Servicio create, Request: {}", usuario);
		return new ResponseEntity<ResponseDto>(iCRUDBusiness.createUsuario(usuario), HttpStatus.OK);
	}

	@PutMapping("/update")
	private ResponseEntity<ResponseDto> updateUsuario(@RequestBody UsuariosDto usuario) {
		LOGGER.info("Inicio Servicio update, Request: {}", usuario);
		return new ResponseEntity<ResponseDto>(iCRUDBusiness.updateUsuario(usuario), HttpStatus.OK);
	}

	@DeleteMapping("/delete")
	private ResponseEntity<ResponseDto> deleteUsuario(@RequestParam String identificacion) {
		LOGGER.info("Inicio Servicio get, Identificacion: {}", identificacion);
		return new ResponseEntity<ResponseDto>(iCRUDBusiness.delete(identificacion), HttpStatus.OK);
	}

}
