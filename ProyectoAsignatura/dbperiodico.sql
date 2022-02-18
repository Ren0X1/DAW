-- --------------------------------------------------------
-- Host:                         127.0.0.1
-- Versión del servidor:         10.4.21-MariaDB - mariadb.org binary distribution
-- SO del servidor:              Win64
-- HeidiSQL Versión:             11.3.0.6295
-- --------------------------------------------------------

/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET NAMES utf8 */;
/*!50503 SET NAMES utf8mb4 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;
/*!40111 SET @OLD_SQL_NOTES=@@SQL_NOTES, SQL_NOTES=0 */;


-- Volcando estructura de base de datos para dbperiodico
CREATE DATABASE IF NOT EXISTS `dbperiodico` /*!40100 DEFAULT CHARACTER SET latin1 */;
USE `dbperiodico`;

-- Volcando estructura para tabla dbperiodico.articulo
CREATE TABLE IF NOT EXISTS `articulo` (
  `cod_art` int(11) NOT NULL AUTO_INCREMENT,
  `cod_usuario` int(11) NOT NULL,
  `cod_categoria` int(11) NOT NULL,
  `titular` varchar(255) NOT NULL,
  `cuerpoNoticia` varchar(255) NOT NULL,
  `fecha_publi` date NOT NULL,
  `n_visitas` int(11) DEFAULT NULL,
  `prioridadBase` int(11) NOT NULL,
  PRIMARY KEY (`cod_art`),
  KEY `cod_usuario` (`cod_usuario`),
  KEY `cod_categoria` (`cod_categoria`),
  CONSTRAINT `articulo_ibfk_1` FOREIGN KEY (`cod_categoria`) REFERENCES `categorias` (`cod_categoria`) ON DELETE CASCADE ON UPDATE CASCADE,
  CONSTRAINT `articulo_ibfk_2` FOREIGN KEY (`cod_usuario`) REFERENCES `usuario` (`cod_usuario`) ON DELETE CASCADE ON UPDATE CASCADE,
  CONSTRAINT `articulo_ibfk_3` FOREIGN KEY (`cod_art`) REFERENCES `opinion` (`cod_art`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=latin1;

-- Volcando datos para la tabla dbperiodico.articulo: ~1 rows (aproximadamente)
/*!40000 ALTER TABLE `articulo` DISABLE KEYS */;
INSERT INTO `articulo` (`cod_art`, `cod_usuario`, `cod_categoria`, `titular`, `cuerpoNoticia`, `fecha_publi`, `n_visitas`, `prioridadBase`) VALUES
	(1, 1, 1, 'Prueba Titular', 'Prueba cuerpo', '2022-02-01', 2, 0);
/*!40000 ALTER TABLE `articulo` ENABLE KEYS */;

-- Volcando estructura para tabla dbperiodico.categorias
CREATE TABLE IF NOT EXISTS `categorias` (
  `cod_categoria` int(11) NOT NULL AUTO_INCREMENT,
  `categoria` varchar(255) NOT NULL,
  PRIMARY KEY (`cod_categoria`)
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=latin1;

-- Volcando datos para la tabla dbperiodico.categorias: ~0 rows (aproximadamente)
/*!40000 ALTER TABLE `categorias` DISABLE KEYS */;
INSERT INTO `categorias` (`cod_categoria`, `categoria`) VALUES
	(1, 'Deportes');
/*!40000 ALTER TABLE `categorias` ENABLE KEYS */;

-- Volcando estructura para tabla dbperiodico.opinion
CREATE TABLE IF NOT EXISTS `opinion` (
  `cod_opinion` int(11) NOT NULL AUTO_INCREMENT,
  `email` varchar(255) NOT NULL,
  `cod_art` int(11) NOT NULL,
  `hora` time NOT NULL,
  `fecha_publi` date NOT NULL,
  `contenido` varchar(255) NOT NULL,
  PRIMARY KEY (`cod_opinion`),
  KEY `email` (`email`),
  KEY `cod_art` (`cod_art`),
  CONSTRAINT `opinion_ibfk_1` FOREIGN KEY (`email`) REFERENCES `usuario` (`email`) ON DELETE CASCADE ON UPDATE CASCADE,
  CONSTRAINT `opinion_ibfk_2` FOREIGN KEY (`cod_art`) REFERENCES `articulo` (`cod_art`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=latin1;

-- Volcando datos para la tabla dbperiodico.opinion: ~3 rows (aproximadamente)
/*!40000 ALTER TABLE `opinion` DISABLE KEYS */;
INSERT INTO `opinion` (`cod_opinion`, `email`, `cod_art`, `hora`, `fecha_publi`, `contenido`) VALUES
	(1, 'admin@admin.es', 1, '19:55:14', '2022-02-07', 'Prueba Respuesta'),
	(2, 'admin@admin.es', 1, '19:55:28', '2022-02-07', 'Prueba Respuesta2'),
	(3, 'admin@admin.es', 1, '19:55:28', '2022-02-07', 'Prueba Respuesta3');
/*!40000 ALTER TABLE `opinion` ENABLE KEYS */;

-- Volcando estructura para tabla dbperiodico.respuestas
CREATE TABLE IF NOT EXISTS `respuestas` (
  `cod_respuesta` int(11) NOT NULL AUTO_INCREMENT,
  `cod_opinion` int(11) NOT NULL DEFAULT 0,
  `cod_engancha` int(11) NOT NULL DEFAULT 0,
  PRIMARY KEY (`cod_respuesta`),
  KEY `cod_engancha` (`cod_engancha`),
  KEY `cod_opinion` (`cod_opinion`),
  CONSTRAINT `respuestas_ibfk_1` FOREIGN KEY (`cod_opinion`) REFERENCES `opinion` (`cod_opinion`) ON DELETE CASCADE ON UPDATE CASCADE,
  CONSTRAINT `respuestas_ibfk_2` FOREIGN KEY (`cod_engancha`) REFERENCES `opinion` (`cod_opinion`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=latin1;

-- Volcando datos para la tabla dbperiodico.respuestas: ~1 rows (aproximadamente)
/*!40000 ALTER TABLE `respuestas` DISABLE KEYS */;
INSERT INTO `respuestas` (`cod_respuesta`, `cod_opinion`, `cod_engancha`) VALUES
	(1, 3, 1);
/*!40000 ALTER TABLE `respuestas` ENABLE KEYS */;

-- Volcando estructura para tabla dbperiodico.subcategorias
CREATE TABLE IF NOT EXISTS `subcategorias` (
  `cod_subcategoria` int(11) NOT NULL AUTO_INCREMENT,
  `cod_categoria` int(11) NOT NULL DEFAULT 0,
  `nombre` varchar(255) NOT NULL DEFAULT '0',
  PRIMARY KEY (`cod_subcategoria`),
  KEY `cod_categoria` (`cod_categoria`),
  CONSTRAINT `subcategorias_ibfk_1` FOREIGN KEY (`cod_categoria`) REFERENCES `categorias` (`cod_categoria`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=latin1;

-- Volcando datos para la tabla dbperiodico.subcategorias: ~2 rows (aproximadamente)
/*!40000 ALTER TABLE `subcategorias` DISABLE KEYS */;
INSERT INTO `subcategorias` (`cod_subcategoria`, `cod_categoria`, `nombre`) VALUES
	(1, 1, 'Baloncesto'),
	(2, 1, 'Futbol');
/*!40000 ALTER TABLE `subcategorias` ENABLE KEYS */;

-- Volcando estructura para tabla dbperiodico.usuario
CREATE TABLE IF NOT EXISTS `usuario` (
  `email` varchar(255) NOT NULL DEFAULT 'admin@admin.es',
  `cod_usuario` int(11) NOT NULL AUTO_INCREMENT,
  `fecha_nacimiento` date NOT NULL,
  `pais` varchar(255) NOT NULL,
  `nombre` varchar(255) NOT NULL,
  `apellidos` varchar(255) NOT NULL,
  `password` varchar(255) NOT NULL,
  `permiso` int(11) DEFAULT NULL,
  PRIMARY KEY (`email`),
  UNIQUE KEY `cod_usuario` (`cod_usuario`),
  KEY `cod_usuario_2` (`cod_usuario`)
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=latin1;

-- Volcando datos para la tabla dbperiodico.usuario: ~0 rows (aproximadamente)
/*!40000 ALTER TABLE `usuario` DISABLE KEYS */;
INSERT INTO `usuario` (`email`, `cod_usuario`, `fecha_nacimiento`, `pais`, `nombre`, `apellidos`, `password`, `permiso`) VALUES
	('admin@admin.es', 1, '2000-01-07', 'España', 'Alejandro', 'Mendoza', '123', 2);
/*!40000 ALTER TABLE `usuario` ENABLE KEYS */;

/*!40101 SET SQL_MODE=IFNULL(@OLD_SQL_MODE, '') */;
/*!40014 SET FOREIGN_KEY_CHECKS=IFNULL(@OLD_FOREIGN_KEY_CHECKS, 1) */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40111 SET SQL_NOTES=IFNULL(@OLD_SQL_NOTES, 1) */;
