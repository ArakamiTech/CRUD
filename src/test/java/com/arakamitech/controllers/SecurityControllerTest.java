package com.arakamitech.controllers;

import com.arakamitech.business.ISecurityBusiness;
import com.arakamitech.business.IUsuariosBusiness;
import com.arakamitech.config.User;
import com.arakamitech.dtos.ResponseDto;
import com.arakamitech.dtos.UsuariosDto;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

class SecurityControllerTest {

    private ISecurityBusiness iSecurityBusiness;
    private IUsuariosBusiness iUsuariosBusiness;
    private SecurityController securityController;

    @BeforeEach
    public void setUp() {
        iSecurityBusiness = Mockito.mock(ISecurityBusiness.class);
        iUsuariosBusiness = Mockito.mock(IUsuariosBusiness.class);
        securityController = new SecurityController(iUsuariosBusiness, iSecurityBusiness);
    }

    @Test
    void testLoginWhenValidCredentialsThenReturnUserWithJwt() {
        String username = "testUser";
        String password = "testPassword";
        String jwtToken = "testJwtToken";
        when(iSecurityBusiness.getJWTToken(username, password)).thenReturn(jwtToken);

        User user = securityController.login(username, password);

        assertEquals(jwtToken, user.getToken());
    }

    @Test
    void testLoginWhenInvokedThenCallGetJwtTokenWithCorrectParameters() {
        String username = "testUser";
        String password = "testPassword";
        when(iSecurityBusiness.getJWTToken(anyString(), anyString())).thenReturn("testJwtToken");

        securityController.login(username, password);

        verify(iSecurityBusiness).getJWTToken(username, password);
    }

    @Test
    void testCreateWhenValidUsuariosDtoThenReturnExpectedResponseEntity() {
        UsuariosDto usuariosDto = new UsuariosDto();
        ResponseDto responseDto = ResponseDto.builder().build();
        when(iUsuariosBusiness.createUsuario(usuariosDto)).thenReturn(responseDto);

        ResponseEntity<ResponseDto> responseEntity = securityController.create(usuariosDto);

        assertEquals(HttpStatus.OK, responseEntity.getStatusCode());
        assertEquals(responseDto, responseEntity.getBody());
    }

    @Test
    void testCreateWhenValidUsuariosDtoThenCallCreateUsuario() {
        UsuariosDto usuariosDto = new UsuariosDto();
        when(iUsuariosBusiness.createUsuario(usuariosDto)).thenReturn(ResponseDto.builder().build());

        securityController.create(usuariosDto);

        verify(iUsuariosBusiness).createUsuario(usuariosDto);
    }

    @Test
    void testCreateWhenValidUsuariosDtoThenReturnResponseEntity() {
        UsuariosDto usuariosDto = new UsuariosDto();
        ResponseDto responseDto = ResponseDto.builder().build();
        when(iUsuariosBusiness.createUsuario(usuariosDto)).thenReturn(responseDto);

        ResponseEntity<ResponseDto> responseEntity = securityController.create(usuariosDto);

        assertEquals(HttpStatus.OK, responseEntity.getStatusCode());
        assertEquals(responseDto, responseEntity.getBody());
    }

    @Test
    void testCreateWhenCalledThenCallCreateUsuario() {
        UsuariosDto usuariosDto = new UsuariosDto();
        when(iUsuariosBusiness.createUsuario(any(UsuariosDto.class))).thenReturn(ResponseDto.builder().build());

        securityController.create(usuariosDto);

        verify(iUsuariosBusiness).createUsuario(usuariosDto);
    }
}