package com.arakamitech.business;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.arakamitech.dtos.ResponseDto;
import com.arakamitech.dtos.UsuariosDto;
import com.arakamitech.entities.UsuariosEntity;
import com.arakamitech.exceptions.NotFoundException;
import com.arakamitech.services.ICRUDService;

@Service
public class CRUDBusinessImpl implements ICRUDBusiness {

	private final ICRUDService iCRUDService;
	private final ModelMapper mapper;

	@Autowired
	public CRUDBusinessImpl(ICRUDService iCRUDService, ModelMapper mapper) {
		this.iCRUDService = iCRUDService;
		this.mapper = mapper;
	}

	@Override
	public ResponseDto getUsuario(String identificacion) {
		UsuariosEntity usuario = iCRUDService.getUsuario(identificacion);
		return buildResponseDto(usuario);
	}

	@Override
	public ResponseDto createUsuario(UsuariosDto usuario) {
		UsuariosEntity usuarioEntity = mapper.map(usuario, UsuariosEntity.class);
		usuarioEntity = iCRUDService.createUsuario(usuarioEntity);
		return buildResponseDto(usuarioEntity);
	}

	@Override
	public ResponseDto updateUsuario(UsuariosDto usuario) {
		UsuariosEntity usuarioEntity = iCRUDService.getUsuario(usuario.getIdentificacionUsuario());
		buildUsuarioEntity(usuarioEntity, usuario);
		usuarioEntity = iCRUDService.updateUsuario(usuarioEntity);
		return buildResponseDto(usuarioEntity);
	}

	@Override
	public ResponseDto delete(String identificacion) {
		iCRUDService.deleteUsuario(identificacion);
		return buildResponseDto();
	}

	private ResponseDto buildResponseDto(UsuariosEntity usuario) throws NotFoundException {
		return ResponseDto.builder().usuario(mapper.map(usuario, UsuariosDto.class)).mensaje("OK").build();
	}

	private void buildUsuarioEntity(UsuariosEntity usuarioEntity, UsuariosDto usuario) {
		usuarioEntity.setNombreUsuario(usuario.getNombreUsuario());
		usuarioEntity.setCorreoUsuario(usuario.getCorreoUsuario());
		usuarioEntity.setTelefonoUsuario(usuario.getTelefonoUsuario());
	}

	private ResponseDto buildResponseDto() {
		return ResponseDto.builder().mensaje("Usuario eliminado con exito").build();
	}

}
