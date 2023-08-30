package com.arakamitech.services;

import com.arakamitech.entities.UsuariosEntity;
import com.arakamitech.exceptions.NotFoundException;
import com.arakamitech.repositories.IOperationsRepositoryUsuarios;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

class OperationsServiceImplTest {

    private IOperationsRepositoryUsuarios repository;
    private OperationsServiceImpl service;

    @BeforeEach
    public void setUp() {
        repository = Mockito.mock(IOperationsRepositoryUsuarios.class);
        service = new OperationsServiceImpl(repository);
    }

    @Test
    void testFindByCorreoAndIdentificacionWhenRepositoryReturnsEntityThenReturnEntity() {
        UsuariosEntity expectedEntity = new UsuariosEntity();
        Mockito.when(repository.findByCorreoAndIdentificacion(anyString(), anyString())).thenReturn(expectedEntity);

        UsuariosEntity actualEntity = service.findByCorreoAndIdentificacion("test@test.com", "1234567890");

        assertEquals(expectedEntity, actualEntity);
    }

    @Test
    void testFindByCorreoAndIdentificacionWhenRepositoryReturnsNullThenThrowNotFoundException() {
        Mockito.when(repository.findByCorreoAndIdentificacion(anyString(), anyString())).thenReturn(null);

        NotFoundException exception = assertThrows(NotFoundException.class, () -> {
            service.findByCorreoAndIdentificacion("test@test.com", "1234567890");
        });

        assertEquals("No se encontraron resultados", exception.getMessage());
    }

    @Test
    void testFindByCorreoUsuarioAndIdentificacionUsuarioWhenValidEmailAndIdentificationThenReturnUserEntity() {
        UsuariosEntity expectedUser = new UsuariosEntity(1L, "Test User", "123456", "1234567890", "test@test.com", "password");
        when(repository.findByCorreoUsuarioAndIdentificacionUsuario("test@test.com", "123456")).thenReturn(expectedUser);

        UsuariosEntity actualUser = service.findByCorreoUsuarioAndIdentificacionUsuario("test@test.com", "123456");

        assertEquals(expectedUser, actualUser);
    }

    @Test
    void testFindByCorreoUsuarioAndIdentificacionUsuarioWhenNoMatchThenThrowNotFoundException() {
        when(repository.findByCorreoUsuarioAndIdentificacionUsuario("test@test.com", "123456")).thenReturn(null);

        assertThrows(NotFoundException.class, () -> service.findByCorreoUsuarioAndIdentificacionUsuario("test@test.com", "123456"));
    }

    @Test
    void testFindByCorreoUsuarioAndIdentificacionUsuarioWhenNullInputsThenThrowNotFoundException() {
        when(repository.findByCorreoUsuarioAndIdentificacionUsuario(null, null)).thenReturn(null);

        assertThrows(NotFoundException.class, () -> service.findByCorreoUsuarioAndIdentificacionUsuario(null, null));
    }

    @Test
    void testFindByIdWhenIdExistsThenReturnsCorrectUser() {
        UsuariosEntity expectedUsuarioEntity = new UsuariosEntity();
        when(repository.findById(1L)).thenReturn(Optional.of(expectedUsuarioEntity));

        UsuariosEntity result = service.findById(1L);

        assertEquals(result, expectedUsuarioEntity);
    }

    @Test
    void testFindByIdWhenIdDoesNotExistThenThrowsNotFoundException() {
        when(repository.findById(1L)).thenReturn(Optional.empty());

        assertThrows(NotFoundException.class, () -> service.findById(1L));
    }

    @Test
    void testFindByTelefonoUsuarioWhenValidTelefonoThenReturnUsuarioEntity() {
        // Arrange
        String testTelefono = "1234567890";
        UsuariosEntity expectedUsuarioEntity = new UsuariosEntity();
        when(repository.findByTelefonoUsuario(testTelefono)).thenReturn(expectedUsuarioEntity);

        // Act
        UsuariosEntity actualUsuarioEntity = service.findByTelefonoUsuario(testTelefono);

        // Assert
        assertEquals(expectedUsuarioEntity, actualUsuarioEntity);
        verify(repository).findByTelefonoUsuario(testTelefono);
    }

    @Test
    void testFindByTelefonoUsuarioWhenInvalidTelefonoThenThrowNotFoundException() {
        // Arrange
        String testTelefono = "1234567890";
        when(repository.findByTelefonoUsuario(testTelefono)).thenReturn(null);

        // Act and Assert
        assertThrows(NotFoundException.class, () -> service.findByTelefonoUsuario(testTelefono));
        verify(repository).findByTelefonoUsuario(testTelefono);
    }

    @Test
    void testFindByCorreoUsuarioWhenCorreoExistsThenReturnsUsuarioEntity() {
        // Arrange
        String correo = "test@test.com";
        UsuariosEntity expectedUsuarioEntity = new UsuariosEntity(1L, "Test", "123456", "1234567890", correo, "password");
        when(repository.findByCorreo(correo)).thenReturn(expectedUsuarioEntity);

        // Act
        UsuariosEntity actualUsuarioEntity = service.findByCorreoUsuario(correo);

        // Assert
        assertEquals(expectedUsuarioEntity, actualUsuarioEntity);
    }

    @Test
    void testFindByCorreoUsuarioWhenCorreoDoesNotExistThenThrowsNotFoundException() {
        // Arrange
        String correo = "test@test.com";
        when(repository.findByCorreo(correo)).thenReturn(null);

        // Act & Assert
        assertThrows(NotFoundException.class, () -> service.findByCorreoUsuario(correo));
    }
}