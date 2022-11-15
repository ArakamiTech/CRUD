package com.arakamitech.business;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.arakamitech.dtos.ResponseDto;
import com.arakamitech.dtos.UsuariosDto;
import com.arakamitech.entities.UsuariosEntity;
import com.arakamitech.exceptions.NotFoundException;
import com.arakamitech.services.IUsuariosService;

@Service
public class UsuariosBusinessImpl implements IUsuariosBusiness {

	private final IUsuariosService iUsuariosService;
	private final ModelMapper mapper;

	@Autowired
	public UsuariosBusinessImpl(IUsuariosService iUsuariosService, ModelMapper mapper) {
		this.iUsuariosService = iUsuariosService;
		this.mapper = mapper;
	}

	@Override
	public ResponseDto getUsuario(String identificacion) {
		UsuariosEntity usuario = iUsuariosService.getUsuario(identificacion);
		return buildResponseDto(usuario);
	}

	@Override
	public ResponseDto createUsuario(UsuariosDto usuario) {
		UsuariosEntity usuarioEntity = mapper.map(usuario, UsuariosEntity.class);
		usuarioEntity = iUsuariosService.createUsuario(usuarioEntity);
		return buildResponseDto(usuarioEntity);
	}

	@Override
	public ResponseDto updateUsuario(UsuariosDto usuario) {
		UsuariosEntity usuarioEntity = iUsuariosService.getUsuario(usuario.getIdentificacionUsuario());
		buildUsuarioEntity(usuarioEntity, usuario);
		usuarioEntity = iUsuariosService.updateUsuario(usuarioEntity);
		return buildResponseDto(usuarioEntity);
	}

	@Override
	public ResponseDto delete(String identificacion) {
		iUsuariosService.deleteUsuario(identificacion);
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
