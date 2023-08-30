package com.arakamitech.business;

import com.arakamitech.config.JWTAuthorizationFilter;
import com.arakamitech.exceptions.UnhautorizedException;
import com.arakamitech.repositories.IRepositoryUsuarios;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Base64;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class SecurityBusinessImplTest {

    @Mock
    private IRepositoryUsuarios repository;

    @Mock
    private JWTAuthorizationFilter jwtAuthorizationFilter;

    @InjectMocks
    private SecurityBusinessImpl securityBusiness;

    private String username;
    private String password;
    private String encodedPassword;
    private String secret;

    @BeforeEach
    public void setUp() {
        username = "testUser";
        password = "testPassword";
        encodedPassword = Base64.getEncoder().encodeToString(password.getBytes());
        secret = "testSecret";
    }

    @Test
    void testGetJWTTokenWhenInvalidCredentialsThenThrowException() {
        when(repository.findByIdentificacionUsuarioAndPassword(username, encodedPassword)).thenReturn(Optional.empty());

        assertThrows(UnhautorizedException.class, () -> securityBusiness.getJWTToken(username, password));

        verify(repository, times(1)).findByIdentificacionUsuarioAndPassword(username, encodedPassword);
        verify(jwtAuthorizationFilter, times(0)).getJWTToken(username, secret);
    }

    @Test
    void testGetJWTTokenWhenExceptionOccursThenThrowException() {
        when(repository.findByIdentificacionUsuarioAndPassword(username, encodedPassword)).thenThrow(RuntimeException.class);

        assertThrows(UnhautorizedException.class, () -> securityBusiness.getJWTToken(username, password));

        verify(repository, times(1)).findByIdentificacionUsuarioAndPassword(username, encodedPassword);
        verify(jwtAuthorizationFilter, times(0)).getJWTToken(username, secret);
    }
}