package com.arakamitech.services;

import com.arakamitech.entities.UsuariosEntity;
import com.arakamitech.exceptions.DataIntegrityException;
import com.arakamitech.exceptions.NotFoundException;
import com.arakamitech.repositories.IRepositoryUsuarios;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.dao.DataIntegrityViolationException;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.*;

class UsuariosServiceImplTest {

    private IRepositoryUsuarios iRepositoryUsuarios;
    private UsuariosServiceImpl usuariosService;

    @BeforeEach
    public void setUp() {
        iRepositoryUsuarios = Mockito.mock(IRepositoryUsuarios.class);
        usuariosService = new UsuariosServiceImpl(iRepositoryUsuarios);
    }

    // ... existing tests ...

    @Test
    void testUpdateUsuarioWhenUserExistsThenUpdatesCorrectly() {
        // Arrange
        UsuariosEntity existingUser = new UsuariosEntity(4L, "Test4", "validIdentification4", "5566778899", "test4@test.com", "password4");
        UsuariosEntity updatedUser = new UsuariosEntity(4L, "Test4Updated", "validIdentification4", "5566778899", "test4updated@test.com", "password4updated");
        when(iRepositoryUsuarios.save(existingUser)).thenReturn(existingUser);
        when(iRepositoryUsuarios.save(updatedUser)).thenReturn(updatedUser);
        when(iRepositoryUsuarios.findByIdentificacionUsuario("validIdentification4")).thenReturn(Optional.of(updatedUser));

        // Act
        usuariosService.createUsuario(existingUser);
        usuariosService.updateUsuario(updatedUser);
        UsuariosEntity actualUser = usuariosService.getUsuario("validIdentification4");

        // Assert
        assertEquals(updatedUser, actualUser);
    }

    @Test
    void testDeleteUsuarioWhenValidIdentificationThenUserIsDeleted() {
        // Arrange
        String validIdentification = "validIdentification";
        UsuariosEntity userToDelete = new UsuariosEntity(6L, "Test6", validIdentification, "1234567890", "test6@test.com", "password6");
        when(iRepositoryUsuarios.findByIdentificacionUsuario(validIdentification)).thenReturn(Optional.of(userToDelete));

        // Act
        usuariosService.deleteUsuario(validIdentification);

        // Assert
        verify(iRepositoryUsuarios, times(1)).delete(userToDelete);
    }

    @Test
    void testDeleteUsuarioWhenInvalidIdentificationThenNotFoundException() {
        // Arrange
        String invalidIdentification = "invalidIdentification";
        when(iRepositoryUsuarios.findByIdentificacionUsuario(invalidIdentification)).thenReturn(Optional.empty());

        // Act & Assert
        assertThrows(NotFoundException.class, () -> usuariosService.deleteUsuario(invalidIdentification));
    }

    @Test
    void testGetUsuarioWhenIdentificationIsValidThenReturnUser() {
        // Arrange
        String validIdentification = "validIdentification";
        UsuariosEntity expectedUser = new UsuariosEntity(1L, "Test", validIdentification, "1234567890", "test@test.com", "password");
        when(iRepositoryUsuarios.findByIdentificacionUsuario(validIdentification)).thenReturn(Optional.of(expectedUser));

        // Act
        UsuariosEntity actualUser = usuariosService.getUsuario(validIdentification);

        // Assert
        assertEquals(expectedUser, actualUser);
    }

    @Test
    void testGetUsuarioWhenIdentificationIsInvalidThenThrowNotFoundException() {
        // Arrange
        String invalidIdentification = "invalidIdentification";
        when(iRepositoryUsuarios.findByIdentificacionUsuario(invalidIdentification)).thenReturn(Optional.empty());

        // Act & Assert
        assertThrows(NotFoundException.class, () -> usuariosService.getUsuario(invalidIdentification));
    }

    @Test
    void testCreateUsuarioWhenNewUserThenReturnSameUser() {
        // Arrange
        UsuariosEntity newUser = new UsuariosEntity(2L, "Test2", "validIdentification2", "0987654321", "test2@test.com", "password2");
        when(iRepositoryUsuarios.save(newUser)).thenReturn(newUser);

        // Act
        UsuariosEntity actualUser = usuariosService.createUsuario(newUser);

        // Assert
        assertEquals(newUser, actualUser);
    }

    @Test
    void testCreateUsuarioWhenDataIntegrityViolationExceptionThenThrowDataIntegrityException() {
        // Arrange
        UsuariosEntity newUser = new UsuariosEntity(3L, "Test3", "validIdentification3", "1122334455", "test3@test.com", "password3");
        when(iRepositoryUsuarios.save(newUser)).thenThrow(DataIntegrityViolationException.class);

        // Act & Assert
        assertThrows(DataIntegrityException.class, () -> usuariosService.createUsuario(newUser));
    }
}