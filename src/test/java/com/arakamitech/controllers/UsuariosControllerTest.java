package com.arakamitech.controllers;

import com.arakamitech.business.IUsuariosBusiness;
import com.arakamitech.dtos.ResponseDto;
import com.arakamitech.dtos.UsuariosDto;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

class UsuariosControllerTest {

    private IUsuariosBusiness iUsuariosBusiness;
    private UsuariosController usuariosController;

    @BeforeEach
    public void setUp() {
        iUsuariosBusiness = Mockito.mock(IUsuariosBusiness.class);
        usuariosController = new UsuariosController(iUsuariosBusiness);
    }

    // ... existing tests ...

    @Test
    void testUpdateUsuarioWhenGivenValidUsuarioDtoThenReturnExpectedResponseEntity() {
        UsuariosDto usuario = new UsuariosDto("John", "123", "1234567890", "john@example.com", "password");
        ResponseDto expectedResponse = new ResponseDto(usuario, "Success", 200, HttpStatus.OK);

        when(iUsuariosBusiness.updateUsuario(usuario)).thenReturn(expectedResponse);

        ResponseEntity<ResponseDto> responseEntity = usuariosController.updateUsuario(usuario);

        assertEquals(expectedResponse, responseEntity.getBody());
        assertEquals(HttpStatus.OK, responseEntity.getStatusCode());
        verify(iUsuariosBusiness).updateUsuario(usuario);
    }

    @Test
    void testUpdateUsuarioWhenGivenNullUsuarioDtoThenReturnExpectedResponseEntity() {
        UsuariosDto usuario = null;
        ResponseDto expectedResponse = new ResponseDto(null, "Invalid user", 400, HttpStatus.BAD_REQUEST);

        when(iUsuariosBusiness.updateUsuario(usuario)).thenReturn(expectedResponse);

        ResponseEntity<ResponseDto> responseEntity = usuariosController.updateUsuario(usuario);

        assertEquals(expectedResponse, responseEntity.getBody());
        assertEquals(HttpStatus.OK, responseEntity.getStatusCode());
    }

    @Test
    void testGetUsuarioWhenIdentificationIsProvidedThenReturnCorrectUser() {
        String identificacion = "123";
        UsuariosDto expectedUser = new UsuariosDto("John", "123", "1234567890", "john@example.com", "password");
        ResponseDto expectedResponse = new ResponseDto(expectedUser, "Success", 200, HttpStatus.OK);

        when(iUsuariosBusiness.getUsuario(identificacion)).thenReturn(expectedResponse);

        ResponseEntity<ResponseDto> responseEntity = usuariosController.getUsuario(identificacion);

        assertEquals(expectedResponse, responseEntity.getBody());
    }

    @Test
    void testGetUsuarioWhenCalledThenInvokeBusinessGetUsuarioWithCorrectIdentification() {
        String identificacion = "123";
        UsuariosDto expectedUser = new UsuariosDto("John", "123", "1234567890", "john@example.com", "password");
        ResponseDto expectedResponse = new ResponseDto(expectedUser, "Success", 200, HttpStatus.OK);

        when(iUsuariosBusiness.getUsuario(identificacion)).thenReturn(expectedResponse);

        usuariosController.getUsuario(identificacion);

        verify(iUsuariosBusiness).getUsuario(identificacion);
    }

    @Test
    void testGetUsuarioWhenCalledThenReturnResponseEntityWithCorrectHttpStatus() {
        String identificacion = "123";
        UsuariosDto expectedUser = new UsuariosDto("John", "123", "1234567890", "john@example.com", "password");
        ResponseDto expectedResponse = new ResponseDto(expectedUser, "Success", 200, HttpStatus.OK);

        when(iUsuariosBusiness.getUsuario(identificacion)).thenReturn(expectedResponse);

        ResponseEntity<ResponseDto> responseEntity = usuariosController.getUsuario(identificacion);

        assertEquals(HttpStatus.OK, responseEntity.getStatusCode());
    }

    @Test
    void testCreateUsuarioWhenValidUsuarioThenReturnResponseEntity() {
        UsuariosDto usuario = new UsuariosDto("John", "123", "1234567890", "john@example.com", "password");
        ResponseDto expectedResponse = new ResponseDto(usuario, "Success", 200, HttpStatus.OK);

        when(iUsuariosBusiness.createUsuario(usuario)).thenReturn(expectedResponse);

        ResponseEntity<ResponseDto> responseEntity = usuariosController.createUsuario(usuario);

        assertEquals(expectedResponse, responseEntity.getBody());
        assertEquals(HttpStatus.OK, responseEntity.getStatusCode());
    }

    @Test
    void testCreateUsuarioWhenNullUsuarioThenReturnResponseEntity() {
        UsuariosDto usuario = null;
        ResponseDto expectedResponse = new ResponseDto(null, "Invalid user", 400, HttpStatus.BAD_REQUEST);

        when(iUsuariosBusiness.createUsuario(usuario)).thenReturn(expectedResponse);

        ResponseEntity<ResponseDto> responseEntity = usuariosController.createUsuario(usuario);

        assertEquals(expectedResponse, responseEntity.getBody());
        assertEquals(HttpStatus.OK, responseEntity.getStatusCode());
    }

    @Test
    void testCreateUsuarioWhenCalledThenCallBusinessCreateUsuario() {
        UsuariosDto usuario = new UsuariosDto("John", "123", "1234567890", "john@example.com", "password");

        usuariosController.createUsuario(usuario);

        verify(iUsuariosBusiness).createUsuario(usuario);
    }
}