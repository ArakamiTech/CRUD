package com.arakamitech.business;

import com.arakamitech.dtos.ResponseDto;
import com.arakamitech.dtos.UsuariosDto;
import com.arakamitech.entities.UsuariosEntity;
import com.arakamitech.services.IUsuariosService;
import com.arakamitech.util.Util;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.modelmapper.ModelMapper;

import java.util.Base64;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class UsuariosBusinessImplTest {

    @Mock
    private IUsuariosService iUsuariosService;

    @Mock
    private ModelMapper mapper;

    @InjectMocks
    private UsuariosBusinessImpl usuariosBusiness;

    private UsuariosEntity usuarioEntity;
    private UsuariosDto usuarioDto;
    private ResponseDto responseDto;

    @BeforeEach
    public void setUp() {
        usuarioEntity = new UsuariosEntity(1L, "Test", "123", "1234567890", "test@test.com", "password");
        usuarioDto = new UsuariosDto("Test", "123", "1234567890", "test@test.com", "password");
        responseDto = Util.buildResponseDto(usuarioDto);
    }

    @Test
    void testGetUsuarioWhenValidIdentificationThenReturnResponseDto() {
        when(iUsuariosService.getUsuario("123")).thenReturn(usuarioEntity);
        when(mapper.map(usuarioEntity, UsuariosDto.class)).thenReturn(usuarioDto);

        ResponseDto result = usuariosBusiness.getUsuario("123");

        assertEquals(responseDto, result);
    }

    @Test
    void testGetUsuarioWhenNoUserFoundThenReturnResponseDtoWithNull() {
        when(iUsuariosService.getUsuario("123")).thenReturn(null);

        ResponseDto result = usuariosBusiness.getUsuario("123");

        assertNull(result.getUsuario());
    }

    @Test
    void testDeleteWhenInvokedThenDeleteUsuarioIsCalled() {
        String identificacion = "1234567890";

        usuariosBusiness.delete(identificacion);

        verify(iUsuariosService, times(1)).deleteUsuario(identificacion);
    }

    @Test
    void testDeleteWhenInvokedThenResponseDtoIsReturned() {
        String identificacion = "1234567890";

        ResponseDto result = usuariosBusiness.delete(identificacion);

        verify(iUsuariosService, times(1)).deleteUsuario(identificacion);
        assert (result instanceof ResponseDto);
    }

    @Test
    void testCreateUsuarioWhenUserDetailsProvidedThenReturnsCorrectResponseDto() {
        when(mapper.map(usuarioDto, UsuariosEntity.class)).thenReturn(usuarioEntity);
        when(iUsuariosService.createUsuario(any(UsuariosEntity.class))).thenReturn(usuarioEntity);
        when(mapper.map(usuarioEntity, UsuariosDto.class)).thenReturn(usuarioDto);

        ResponseDto responseDto = usuariosBusiness.createUsuario(usuarioDto);

        assertEquals(usuarioDto, responseDto.getUsuario());
        assertEquals("OK", responseDto.getMensaje());
        assertEquals(200, responseDto.getCode());
    }

    @Test
    void testCreateUsuarioWhenUserDetailsProvidedThenEncodesPassword() {
        usuarioDto.setPassword("password");
        usuarioEntity.setPassword(Base64.getEncoder().encodeToString("password".getBytes()));

        when(mapper.map(usuarioDto, UsuariosEntity.class)).thenReturn(usuarioEntity);
        when(iUsuariosService.createUsuario(any(UsuariosEntity.class))).thenReturn(usuarioEntity);
        when(mapper.map(usuarioEntity, UsuariosDto.class)).thenReturn(usuarioDto);

        ResponseDto responseDto = usuariosBusiness.createUsuario(usuarioDto);

        assertEquals(Base64.getEncoder().encodeToString("password".getBytes()), responseDto.getUsuario().getPassword());
    }

    @Test
    void testCreateUsuarioWhenUserDetailsProvidedThenCallsIUsuariosServiceCreateUsuario() {
        when(mapper.map(usuarioDto, UsuariosEntity.class)).thenReturn(usuarioEntity);
        when(iUsuariosService.createUsuario(any(UsuariosEntity.class))).thenReturn(usuarioEntity);
        when(mapper.map(usuarioEntity, UsuariosDto.class)).thenReturn(usuarioDto);

        usuariosBusiness.createUsuario(usuarioDto);

        verify(iUsuariosService).createUsuario(any(UsuariosEntity.class));
    }

    @Test
    void testUpdateUsuarioWhenUserInformationIsUpdatedThenReturnUpdatedUserInformation() {
        UsuariosDto updatedUsuarioDto = new UsuariosDto("John Doe", "123", "0987654321", "johndoe@example.com", "newpassword");
        UsuariosEntity updatedUsuarioEntity = new UsuariosEntity(1L, "John Doe", "123", "0987654321", "johndoe@example.com", "newpassword");

        when(iUsuariosService.getUsuario(usuarioDto.getIdentificacionUsuario())).thenReturn(usuarioEntity);
        when(iUsuariosService.updateUsuario(any(UsuariosEntity.class))).thenReturn(updatedUsuarioEntity);
        when(mapper.map(updatedUsuarioEntity, UsuariosDto.class)).thenReturn(updatedUsuarioDto);

        ResponseDto responseDto = usuariosBusiness.updateUsuario(updatedUsuarioDto);

        assertEquals(updatedUsuarioDto, responseDto.getUsuario());
        verify(iUsuariosService).getUsuario(usuarioDto.getIdentificacionUsuario());
        verify(iUsuariosService).updateUsuario(any(UsuariosEntity.class));
        verify(mapper).map(updatedUsuarioEntity, UsuariosDto.class);
    }

    @Test
    void testUpdateUsuarioWhenCalledThenCallNecessaryMethods() {
        when(iUsuariosService.getUsuario(usuarioDto.getIdentificacionUsuario())).thenReturn(usuarioEntity);
        when(iUsuariosService.updateUsuario(any(UsuariosEntity.class))).thenReturn(usuarioEntity);
        when(mapper.map(usuarioEntity, UsuariosDto.class)).thenReturn(usuarioDto);

        usuariosBusiness.updateUsuario(usuarioDto);

        verify(iUsuariosService).getUsuario(usuarioDto.getIdentificacionUsuario());
        verify(iUsuariosService).updateUsuario(any(UsuariosEntity.class));
        verify(mapper).map(usuarioEntity, UsuariosDto.class);
    }
}