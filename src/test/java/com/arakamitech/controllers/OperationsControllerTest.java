package com.arakamitech.controllers;

import com.arakamitech.business.IOperationsBusiness;
import com.arakamitech.dtos.ResponseDto;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.mockito.Mockito.*;

class OperationsControllerTest {

    private IOperationsBusiness operationsBusiness;
    private OperationsController operationsController;

    @BeforeEach
    public void setUp() {
        operationsBusiness = Mockito.mock(IOperationsBusiness.class);
        operationsController = new OperationsController(operationsBusiness);
    }

    // Existing tests...

    // New tests
    @Test
    void testFindByCorreoAndIdentificacionWhenCorreoAndIdentificacionValidThenReturnCorrectResponseDto() {
        String correo = "validCorreo";
        String identificacion = "validIdentificacion";
        String mode = "validMode";
        ResponseDto responseDto = new ResponseDto();
        when(operationsBusiness.findByCorreoAndIdentificacion(mode, correo, identificacion)).thenReturn(responseDto);

        ResponseEntity<ResponseDto> responseEntity = operationsController.findByCorreoAndIdentificacion(mode, correo, identificacion);

        assertEquals(HttpStatus.OK, responseEntity.getStatusCode());
        assertEquals(responseDto, responseEntity.getBody());
    }

    @Test
    void testFindByCorreoAndIdentificacionWhenCalledThenCallOperationsBusinessFindByCorreoAndIdentificacionWithCorrectParameters() {
        String correo = "testCorreo";
        String identificacion = "testIdentificacion";
        String mode = "testMode";
        IOperationsBusiness spyOperationsBusiness = spy(IOperationsBusiness.class);
        OperationsController spyController = new OperationsController(spyOperationsBusiness);

        spyController.findByCorreoAndIdentificacion(mode, correo, identificacion);

        verify(spyOperationsBusiness).findByCorreoAndIdentificacion(mode, correo, identificacion);
    }

    @Test
    void testFindByIdWhenIdIsValidThenReturnResponseDto() {
        String id = "validId";
        ResponseDto responseDto = new ResponseDto();
        when(operationsBusiness.findById(id)).thenReturn(responseDto);

        ResponseEntity<ResponseDto> responseEntity = operationsController.findById(id);

        assertEquals(HttpStatus.OK, responseEntity.getStatusCode());
        assertEquals(responseDto, responseEntity.getBody());
    }

    @Test
    void testFindByIdWhenIdIsInvalidThenReturnNull() {
        String id = "invalidId";
        when(operationsBusiness.findById(id)).thenReturn(null);

        ResponseEntity<ResponseDto> responseEntity = operationsController.findById(id);

        assertEquals(HttpStatus.OK, responseEntity.getStatusCode());
        assertNull(responseEntity.getBody());
    }

    @Test
    void testFindByIdWhenCalledThenCallOperationsBusinessFindById() {
        String id = "testId";
        operationsController.findById(id);

        verify(operationsBusiness).findById(id);
    }

    @Test
    void testFindByIdWhenCalledThenOperationsBusinessFindByIdIsCalledWithCorrectId() {
        String id = "testId";
        operationsController.findById(id);

        verify(operationsBusiness).findById(id);
    }

    @Test
    void testFindByCorreoAndIdentificacionWhenCorreoAndIdentificacionValidThenReturnResponseDto() {
        String correo = "validCorreo";
        String identificacion = "validIdentificacion";
        String mode = "validMode";
        ResponseDto responseDto = new ResponseDto();
        when(operationsBusiness.findByCorreoAndIdentificacion(mode, correo, identificacion)).thenReturn(responseDto);

        ResponseEntity<ResponseDto> responseEntity = operationsController.findByCorreoAndIdentificacion(mode, correo, identificacion);

        assertEquals(HttpStatus.OK, responseEntity.getStatusCode());
        assertEquals(responseDto, responseEntity.getBody());
    }

    @Test
    void testFindByCorreoAndIdentificacionWhenCalledThenCallOperationsBusinessMethod() {
        String correo = "testCorreo";
        String identificacion = "testIdentificacion";
        String mode = "testMode";
        operationsController.findByCorreoAndIdentificacion(mode, correo, identificacion);

        verify(operationsBusiness).findByCorreoAndIdentificacion(mode, correo, identificacion);
    }

    @Test
    void testFindByCorreoUsuarioWhenCorreoIsValidThenReturnCorrectResponseDto() {
        // Arrange
        String correo = "test@test.com";
        ResponseDto expectedResponseDto = new ResponseDto();
        when(operationsBusiness.findByCorreoUsuario(correo)).thenReturn(expectedResponseDto);

        // Act
        ResponseEntity<ResponseDto> responseEntity = operationsController.findByCorreoUsuario(correo);

        // Assert
        assertEquals(expectedResponseDto, responseEntity.getBody());
    }

    @Test
    void testFindByCorreoUsuarioWhenCorreoIsValidThenReturnHttpStatusOk() {
        // Arrange
        String correo = "test@test.com";
        ResponseDto expectedResponseDto = new ResponseDto();
        when(operationsBusiness.findByCorreoUsuario(correo)).thenReturn(expectedResponseDto);

        // Act
        ResponseEntity<ResponseDto> responseEntity = operationsController.findByCorreoUsuario(correo);

        // Assert
        assertEquals(HttpStatus.OK, responseEntity.getStatusCode());
    }

    @Test
    void testFindByCorreoUsuarioWhenCorreoIsValidThenCallBusinessMethodWithCorrectCorreo() {
        // Arrange
        String correo = "test@test.com";
        IOperationsBusiness spyOperationsBusiness = spy(IOperationsBusiness.class);
        OperationsController spyController = new OperationsController(spyOperationsBusiness);

        // Act
        spyController.findByCorreoUsuario(correo);

        // Assert
        verify(spyOperationsBusiness, times(1)).findByCorreoUsuario(correo);
    }

    @BeforeEach
    public void setup() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void testFindByTelefonoUsuarioWhenGivenTelefonoThenReturnExpectedResponse() {
        // Arrange
        String telefono = "1234567890";
        ResponseDto expectedResponseDto = ResponseDto.builder().build();
        when(operationsBusiness.findByTelefonoUsuario(telefono)).thenReturn(expectedResponseDto);

        // Act
        ResponseEntity<ResponseDto> responseEntity = operationsController.findByTelefonoUsuario(telefono);

        // Assert
        assertEquals(HttpStatus.OK, responseEntity.getStatusCode());
        assertEquals(expectedResponseDto, responseEntity.getBody());
    }

    @Test
    void testFindByTelefonoUsuarioWhenGivenNonExistingTelefonoThenReturnNull() {
        // Arrange
        String telefono = "1234567890";
        when(operationsBusiness.findByTelefonoUsuario(telefono)).thenReturn(null);

        // Act
        ResponseEntity<ResponseDto> responseEntity = operationsController.findByTelefonoUsuario(telefono);

        // Assert
        assertEquals(HttpStatus.OK, responseEntity.getStatusCode());
        assertNull(responseEntity.getBody());
    }
}