CREATE TABLE `usuarios` (
  `id_usuario` int NOT NULL AUTO_INCREMENT,
  `nombre_usuario` varchar(45) DEFAULT NULL,
  `identificacion_usuario` varchar(45) DEFAULT NULL,
  `telefono_usuario` varchar(45) DEFAULT NULL,
  `correo_usuarios` varchar(45) DEFAULT NULL,
  `password` varchar(200) NOT NULL,
  PRIMARY KEY (`id_usuario`),
  UNIQUE KEY `id_usuario_UNIQUE` (`id_usuario`),
  UNIQUE KEY `identificacion_usuario_UNIQUE` (`identificacion_usuario`)
) ENGINE=InnoDB AUTO_INCREMENT=16 DEFAULT CHARSET=utf8mb3