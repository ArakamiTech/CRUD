package com.arakamitech.business;

import org.modelmapper.ModelMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.arakamitech.dtos.ResponseDto;
import com.arakamitech.dtos.UsuariosDto;
import com.arakamitech.entities.UsuariosEntity;
import com.arakamitech.services.IOperationsService;

@Service
public class OperationsBusinessImpl implements IOperationsBusiness {

	private static final Logger LOGGER = LoggerFactory.getLogger(OperationsBusinessImpl.class);

	private IOperationsService iOperationsService;
	private ModelMapper mapper;

	@Autowired
	public OperationsBusinessImpl(IOperationsService iOperationsService, ModelMapper mapper) {
		this.iOperationsService = iOperationsService;
		this.mapper = mapper;
	}

	@Override
	public ResponseDto findById(String id) {
		var usuarioEntity = iOperationsService.findById(Long.parseLong(id));
		return buildResponseEntity(usuarioEntity);
	}

	@Override
	public ResponseDto findByTelefonoUsuario(String telefono) {
		var usuarioEntity = iOperationsService.findByTelefonoUsuario(telefono);
		return buildResponseEntity(usuarioEntity);
	}

	@Override
	public ResponseDto findByCorreoUsuario(String correo) {
		var usuarioEntity = iOperationsService.findByCorreoUsuario(correo);
		return buildResponseEntity(usuarioEntity);
	}

	@Override
	public ResponseDto findByCorreoAndIdentificacion(String mode, String correo, String identificacion) {
		UsuariosEntity usuarioEntity = null;
		switch (mode) {
		case "1":
			LOGGER.info("Inicio findByCorreoAndIdentificacion mediante spring boot");
			usuarioEntity = iOperationsService.findByCorreoUsuarioAndIdentificacionUsuario(correo, identificacion);
			break;
		case "2":
			LOGGER.info("Inicio findByCorreoAndIdentificacion mediante JPQL");
			usuarioEntity = iOperationsService.findByCorreoAndIdentificacion(correo, identificacion);
			break;
		case "3":
			LOGGER.info("Inicio findByCorreoAndIdentificacion mediante namedQuery");
			usuarioEntity = iOperationsService.findByCorreoAndIdentificacionQuery(correo, identificacion);
			break;
		default:
			return ResponseDto.builder().mensaje("Operacion de header invalida").build();
		}
		return buildResponseEntity(usuarioEntity);
	}

	private ResponseDto buildResponseEntity(UsuariosEntity usuarioEntity) {
		return ResponseDto.builder().usuario(mapper.map(usuarioEntity, UsuariosDto.class)).mensaje("OK").build();
	}

}
