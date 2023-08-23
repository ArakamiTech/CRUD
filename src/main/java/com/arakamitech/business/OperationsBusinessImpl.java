package com.arakamitech.business;

import org.modelmapper.ModelMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import com.arakamitech.dtos.ResponseDto;
import com.arakamitech.dtos.UsuariosDto;
import com.arakamitech.entities.UsuariosEntity;
import com.arakamitech.services.IOperationsService;
import com.arakamitech.util.Util;

@Service
public class OperationsBusinessImpl implements IOperationsBusiness {

	private static final Logger LOGGER = LoggerFactory.getLogger(OperationsBusinessImpl.class);

	private final IOperationsService iOperationsService;
	private final ModelMapper mapper;

	public OperationsBusinessImpl(IOperationsService iOperationsService, ModelMapper mapper) {
		this.iOperationsService = iOperationsService;
		this.mapper = mapper;
	}

	@Override
	public ResponseDto findById(String id) {
		var usuarioEntity = iOperationsService.findById(Long.parseLong(id));
		var usariosDto = mapper.map(usuarioEntity, UsuariosDto.class);
		return Util.buildResponseDto(usariosDto);
	}

	@Override
	public ResponseDto findByTelefonoUsuario(String telefono) {
		var usuarioEntity = iOperationsService.findByTelefonoUsuario(telefono);
		var usariosDto = mapper.map(usuarioEntity, UsuariosDto.class);
		return Util.buildResponseDto(usariosDto);
	}

	@Override
	public ResponseDto findByCorreoUsuario(String correo) {
		var usuarioEntity = iOperationsService.findByCorreoUsuario(correo);
		var usariosDto = mapper.map(usuarioEntity, UsuariosDto.class);
		return Util.buildResponseDto(usariosDto);
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
		var usariosDto = mapper.map(usuarioEntity, UsuariosDto.class);
		return Util.buildResponseDto(usariosDto);
	}

}
