package com.arakamitech.business;

import java.util.Base64;

import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import com.arakamitech.dtos.ResponseDto;
import com.arakamitech.dtos.UsuariosDto;
import com.arakamitech.entities.UsuariosEntity;
import com.arakamitech.services.IUsuariosService;
import com.arakamitech.util.Util;

@Service
public class UsuariosBusinessImpl implements IUsuariosBusiness {

	private final IUsuariosService iUsuariosService;
	private final ModelMapper mapper;

	public UsuariosBusinessImpl(IUsuariosService iUsuariosService, ModelMapper mapper) {
		this.iUsuariosService = iUsuariosService;
		this.mapper = mapper;
	}

	@Override
	public ResponseDto getUsuario(String identificacion) {
		var usuario = iUsuariosService.getUsuario(identificacion);
		var usariosDto = mapper.map(usuario, UsuariosDto.class);
		return Util.buildResponseDto(usariosDto);
	}

	@Override
	public ResponseDto createUsuario(UsuariosDto usuario) {
		usuario.setPassword(Base64.getEncoder().encodeToString(usuario.getPassword().getBytes()));
		var usuarioEntity = mapper.map(usuario, UsuariosEntity.class);
		usuarioEntity = iUsuariosService.createUsuario(usuarioEntity);
		var usariosDto = mapper.map(usuarioEntity, UsuariosDto.class);
		return Util.buildResponseDto(usariosDto);
	}

	@Override
	public ResponseDto updateUsuario(UsuariosDto usuario) {
		var usuarioEntity = iUsuariosService.getUsuario(usuario.getIdentificacionUsuario());
		Util.buildUsuarioEntity(usuarioEntity, usuario);
		usuarioEntity = iUsuariosService.updateUsuario(usuarioEntity);
		var usariosDto = mapper.map(usuarioEntity, UsuariosDto.class);
		return Util.buildResponseDto(usariosDto);
	}

	@Override
	public ResponseDto delete(String identificacion) {
		iUsuariosService.deleteUsuario(identificacion);
		return Util.buildResponseDto();
	}

}
