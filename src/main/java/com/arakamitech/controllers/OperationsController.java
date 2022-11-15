package com.arakamitech.controllers;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.arakamitech.business.IOperationsBusiness;
import com.arakamitech.dtos.ResponseDto;

@RestController
@RequestMapping("usuarios/operations")
public class OperationsController {

	private static final Logger LOGGER = LoggerFactory.getLogger(OperationsController.class);

	private IOperationsBusiness operationsBusiness;

	@Autowired
	public OperationsController(IOperationsBusiness operationsBusiness) {
		this.operationsBusiness = operationsBusiness;
	}

	@GetMapping("/findById")
	public ResponseEntity<ResponseDto> findById(@RequestParam(name = "id") String id) {
		LOGGER.info("Inicio servicio findById, id: {}", id);
		return new ResponseEntity<ResponseDto>(operationsBusiness.findById(id), HttpStatus.OK);
	}

	@GetMapping("/findByTelefonoUsuario/{telefono}")
	public ResponseEntity<ResponseDto> findByTelefonoUsuario(@PathVariable String telefono) {
		LOGGER.info("Inicio servicio findByTelefonoUsuario, telefono: {}", telefono);
		return new ResponseEntity<ResponseDto>(operationsBusiness.findByTelefonoUsuario(telefono), HttpStatus.OK);
	}

	@GetMapping("/findByCorreoUsuario")
	public ResponseEntity<ResponseDto> findByCorreoUsuario(@RequestParam String correo) {
		LOGGER.info("Inicio servicio findByCorreoUsuario: correo: {}", correo);
		return new ResponseEntity<ResponseDto>(operationsBusiness.findByCorreoUsuario(correo), HttpStatus.OK);
	}

	@GetMapping("/findByCorreoAndIdentificacion")
	public ResponseEntity<ResponseDto> findByCorreoAndIdentificacion(@RequestHeader(value = "operacion") String mode,
			@RequestParam String correo, @RequestParam String identificacion) {
		LOGGER.info("Inicio servicio findByCorreoAndIdentificacion: correo: {}, identificacion: {}", correo,
				identificacion);
		return new ResponseEntity<ResponseDto>(operationsBusiness.findByCorreoAndIdentificacion(mode, correo, identificacion),
				HttpStatus.OK);
	}

}
