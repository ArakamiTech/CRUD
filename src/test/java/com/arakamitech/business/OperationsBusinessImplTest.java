package com.arakamitech.business;

import com.arakamitech.dtos.ResponseDto;
import com.arakamitech.dtos.UsuariosDto;
import com.arakamitech.entities.UsuariosEntity;
import com.arakamitech.services.IOperationsService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.modelmapper.ModelMapper;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class OperationsBusinessImplTest {

    @Mock
    private IOperationsService iOperationsService;

    @Mock
    private ModelMapper mapper;

    @InjectMocks
    private OperationsBusinessImpl operationsBusiness;

    private UsuariosEntity usuariosEntity;
    private UsuariosDto usuariosDto;

    @BeforeEach
    public void setUp() {
        usuariosEntity = new UsuariosEntity(1L, "test", "test", "test", "test@test.com", "test");
        usuariosDto = new UsuariosDto("test", "test", "test", "test@test.com", "test");
    }

    // ... existing tests ...

    @Test
    void testFindByTelefonoUsuarioWhenPhoneNumberIsValidThenReturnResponseDto() {
        when(iOperationsService.findByTelefonoUsuario("test")).thenReturn(usuariosEntity);
        when(mapper.map(usuariosEntity, UsuariosDto.class)).thenReturn(usuariosDto);

        ResponseDto responseDto = operationsBusiness.findByTelefonoUsuario("test");

        assertNotNull(responseDto);
        assertEquals(usuariosDto, responseDto.getUsuario());
    }

    @Test
    void testFindByTelefonoUsuarioWhenPhoneNumberDoesNotExistThenReturnNull() {
        when(iOperationsService.findByTelefonoUsuario("test")).thenReturn(null);

        ResponseDto responseDto = operationsBusiness.findByTelefonoUsuario("test");

        assertNotNull(responseDto);
        assertNull(responseDto.getUsuario());
    }

    @Test
    void testFindByCorreoAndIdentificacionWhenModeIsOneThenReturnExpectedDto() {
        when(iOperationsService.findByCorreoUsuarioAndIdentificacionUsuario("test@test.com", "test")).thenReturn(usuariosEntity);
        when(mapper.map(usuariosEntity, UsuariosDto.class)).thenReturn(usuariosDto);

        ResponseDto responseDto = operationsBusiness.findByCorreoAndIdentificacion("1", "test@test.com", "test");

        assertNotNull(responseDto);
        assertEquals(usuariosDto, responseDto.getUsuario());
    }

    @Test
    void testFindByCorreoAndIdentificacionWhenModeIsTwoThenReturnExpectedDto() {
        when(iOperationsService.findByCorreoAndIdentificacion("test@test.com", "test")).thenReturn(usuariosEntity);
        when(mapper.map(usuariosEntity, UsuariosDto.class)).thenReturn(usuariosDto);

        ResponseDto responseDto = operationsBusiness.findByCorreoAndIdentificacion("2", "test@test.com", "test");

        assertNotNull(responseDto);
        assertEquals(usuariosDto, responseDto.getUsuario());
    }

    @Test
    void testFindByCorreoAndIdentificacionWhenModeIsThreeThenReturnExpectedDto() {
        when(iOperationsService.findByCorreoAndIdentificacionQuery("test@test.com", "test")).thenReturn(usuariosEntity);
        when(mapper.map(usuariosEntity, UsuariosDto.class)).thenReturn(usuariosDto);

        ResponseDto responseDto = operationsBusiness.findByCorreoAndIdentificacion("3", "test@test.com", "test");

        assertNotNull(responseDto);
        assertEquals(usuariosDto, responseDto.getUsuario());
    }

    @Test
    void testFindByCorreoAndIdentificacionWhenModeIsInvalidThenReturnErrorMessage() {
        ResponseDto responseDto = operationsBusiness.findByCorreoAndIdentificacion("invalid", "test@test.com", "test");

        assertNotNull(responseDto);
        assertEquals("Operacion de header invalida", responseDto.getMensaje());
    }

    @Test
    void testFindByCorreoUsuarioWhenUserExistsThenReturnResponseDto() {
        when(iOperationsService.findByCorreoUsuario("test@test.com")).thenReturn(usuariosEntity);
        when(mapper.map(usuariosEntity, UsuariosDto.class)).thenReturn(usuariosDto);

        ResponseDto responseDto = operationsBusiness.findByCorreoUsuario("test@test.com");

        assertNotNull(responseDto);
        assertEquals(usuariosDto, responseDto.getUsuario());
    }

    @Test
    void testFindByCorreoUsuarioWhenUserDoesNotExistThenReturnResponseDto() {
        when(iOperationsService.findByCorreoUsuario("test@test.com")).thenReturn(null);

        ResponseDto responseDto = operationsBusiness.findByCorreoUsuario("test@test.com");

        assertNotNull(responseDto);
        assertNull(responseDto.getUsuario());
    }

    @Test
    void testFindByCorreoUsuarioCallsFindByCorreoUsuarioWithCorrectParameters() {
        operationsBusiness.findByCorreoUsuario("test@test.com");

        verify(iOperationsService).findByCorreoUsuario("test@test.com");
    }

    @Test
    void testFindByIdWhenIdIsValidThenReturnResponseDto() {
        when(iOperationsService.findById(1L)).thenReturn(usuariosEntity);
        when(mapper.map(usuariosEntity, UsuariosDto.class)).thenReturn(usuariosDto);

        ResponseDto responseDto = operationsBusiness.findById("1");

        assertNotNull(responseDto);
        assertEquals(usuariosDto, responseDto.getUsuario());
    }

    @Test
    void testFindByIdWhenNoUserFoundThenReturnResponseDtoWithNull() {
        when(iOperationsService.findById(1L)).thenReturn(null);

        ResponseDto responseDto = operationsBusiness.findById("1");

        assertNotNull(responseDto);
        assertNull(responseDto.getUsuario());
    }

    @Test
    void testFindByIdWhenIdIsNotValidLongThenThrowNumberFormatException() {
        assertThrows(NumberFormatException.class, () -> operationsBusiness.findById("invalid"));
    }

    // ... other tests ...
}