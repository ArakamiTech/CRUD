package com.arakamitech.business;

import java.util.Base64;
import java.util.Objects;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import com.arakamitech.config.JWTAuthorizationFilter;
import com.arakamitech.exceptions.UnhautorizedException;
import com.arakamitech.repositories.IRepositoryUsuarios;

@Service
public class ISecurityBusinessImpl implements ISecurityBusiness {

	private static final Logger LOGGER = LoggerFactory.getLogger(ISecurityBusinessImpl.class);

	@Value("${jwt.secret}")
	private String secret;

	private final IRepositoryUsuarios repository;
	private final JWTAuthorizationFilter jwtAuthorizationFilter;

	public ISecurityBusinessImpl(IRepositoryUsuarios repository, JWTAuthorizationFilter jwtAuthorizationFilter) {
		super();
		this.repository = repository;
		this.jwtAuthorizationFilter = jwtAuthorizationFilter;
	}

	@Override
	public String getJWTToken(String username, String password) {
		try {
			LOGGER.info("Inicio funcion getJWTToken");
			password = Base64.getEncoder().encodeToString(password.getBytes());
			var entity = repository.findByIdentificacionUsuarioAndPassword(username, password);
			if (entity.isPresent() && Objects.nonNull(entity.get())) {
				LOGGER.info("Usuario existe, continua proceso getJWTToken");
				return jwtAuthorizationFilter.getJWTToken(username, secret);
			} else {
				throw new UnhautorizedException("NO AUTORIZADO");
			}
		} catch (Exception e) {
			throw new UnhautorizedException(e.getMessage());
		}
	}

}
