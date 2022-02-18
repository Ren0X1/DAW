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


-- Volcando estructura de base de datos para peliculasonlinealta
CREATE DATABASE IF NOT EXISTS `peliculasonlinealta` /*!40100 DEFAULT CHARACTER SET utf8mb4 */;
USE `peliculasonlinealta`;

-- Volcando estructura para tabla peliculasonlinealta.actor
CREATE TABLE IF NOT EXISTS `actor` (
  `codActor` int(5) NOT NULL,
  `nombreActor` varchar(20) NOT NULL,
  `nacionalidad` varchar(20) NOT NULL,
  `sexo` varchar(10) NOT NULL,
  PRIMARY KEY (`codActor`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

-- Volcando datos para la tabla peliculasonlinealta.actor: ~12 rows (aproximadamente)
/*!40000 ALTER TABLE `actor` DISABLE KEYS */;
INSERT INTO `actor` (`codActor`, `nombreActor`, `nacionalidad`, `sexo`) VALUES
	(1, 'Bruce Willis', 'EEUU', 'Varon'),
	(2, 'Tom Hanks', 'EEUU', 'Varon'),
	(3, 'Sally FIeld', 'EEUU', 'Mujer'),
	(4, 'Leonardo DiCaprio', 'EEUU', 'Varon'),
	(5, 'Kate Winslet', 'EEUU', 'Mujer'),
	(6, 'John Travolta', 'EEUU', 'Varon'),
	(7, 'Uma Thurman', 'EEUU', 'Mujer'),
	(8, 'Jodie Foster', 'EEUU', 'Mujer'),
	(9, 'Anthony Hopkins', 'EEUU', 'Varon'),
	(10, 'Emma Thompson', 'UK', 'Mujer'),
	(11, 'Hugh Grant', 'UK', 'Varon'),
	(12, 'Kenneth Branagh', 'UK', 'Varon');
/*!40000 ALTER TABLE `actor` ENABLE KEYS */;

-- Volcando estructura para tabla peliculasonlinealta.director
CREATE TABLE IF NOT EXISTS `director` (
  `codDIrector` int(5) NOT NULL AUTO_INCREMENT,
  `nombreDirector` varchar(20) NOT NULL,
  `nacionalidad` varchar(15) NOT NULL,
  PRIMARY KEY (`codDIrector`)
) ENGINE=InnoDB AUTO_INCREMENT=10 DEFAULT CHARSET=latin1;

-- Volcando datos para la tabla peliculasonlinealta.director: ~9 rows (aproximadamente)
/*!40000 ALTER TABLE `director` DISABLE KEYS */;
INSERT INTO `director` (`codDIrector`, `nombreDirector`, `nacionalidad`) VALUES
	(1, 'Robert Zemeckis', 'Americana'),
	(2, 'James Cameron', 'EEUU'),
	(3, 'Jonathan Demme', 'EEUU'),
	(4, 'M. Night Shyamalan', 'EEUU'),
	(5, 'Quentin Tarantino', 'EEUU'),
	(6, 'James Ivory', 'EEUU'),
	(7, 'Kenneth Branagh', 'EEUU'),
	(8, 'Ang Lee', 'EEUU'),
	(9, 'Roger Michell', 'EEUU');
/*!40000 ALTER TABLE `director` ENABLE KEYS */;

-- Volcando estructura para tabla peliculasonlinealta.genero
CREATE TABLE IF NOT EXISTS `genero` (
  `codGenero` int(5) NOT NULL AUTO_INCREMENT,
  `genero` varchar(15) NOT NULL,
  PRIMARY KEY (`codGenero`)
) ENGINE=InnoDB AUTO_INCREMENT=9 DEFAULT CHARSET=latin1;

-- Volcando datos para la tabla peliculasonlinealta.genero: ~8 rows (aproximadamente)
/*!40000 ALTER TABLE `genero` DISABLE KEYS */;
INSERT INTO `genero` (`codGenero`, `genero`) VALUES
	(1, 'comedia'),
	(2, 'drama'),
	(3, 'epoca'),
	(4, 'accion'),
	(5, 'ciencia ficcion'),
	(6, 'terror'),
	(7, 'romantica'),
	(8, 'suspense');
/*!40000 ALTER TABLE `genero` ENABLE KEYS */;

-- Volcando estructura para tabla peliculasonlinealta.opinion
CREATE TABLE IF NOT EXISTS `opinion` (
  `codComen` int(5) NOT NULL AUTO_INCREMENT,
  `codPelicula` int(5) NOT NULL,
  `comentario` varchar(100) NOT NULL,
  `puntuacion` int(11) NOT NULL,
  PRIMARY KEY (`codComen`),
  KEY `codPelicula` (`codPelicula`),
  CONSTRAINT `opinion_ibfk_1` FOREIGN KEY (`codPelicula`) REFERENCES `pelicula` (`codPelicula`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB AUTO_INCREMENT=26 DEFAULT CHARSET=latin1;

-- Volcando datos para la tabla peliculasonlinealta.opinion: ~23 rows (aproximadamente)
/*!40000 ALTER TABLE `opinion` DISABLE KEYS */;
INSERT INTO `opinion` (`codComen`, `codPelicula`, `comentario`, `puntuacion`) VALUES
	(1, 2, 'Pelicula excepcional', 5),
	(2, 2, 'Esperaba algo mas del director y los actores', 2),
	(3, 2, 'La mejor de todas hasta ahora', 4),
	(4, 3, 'Un clasico moderno', 4),
	(5, 3, 'Excepcional', 5),
	(6, 3, 'Insuperable', 5),
	(7, 3, 'No me me gusto', 5),
	(8, 3, 'Terror en estado puro', 5),
	(9, 3, 'De lo mejor del cine moderno', 0),
	(11, 10, 'Un clasico llevado a la pantalla', 0),
	(12, 3, 'Abusa del efectismo', 3),
	(13, 10, 'EL mejor shakespeare', 4),
	(14, 10, 'Emma insuperable', 3),
	(15, 10, 'Simplemente Perfecta', 4),
	(16, 8, 'me encanta', 5),
	(17, 8, 'Lenta pero maravillosa', 5),
	(18, 8, 'Lenta pero maravillosa', 5),
	(20, 10, 'Refleja a la perfeccion la sociedad inglesa', 5),
	(21, 3, 'ME encanta', 4),
	(22, 9, 'Comedia deliciosa', 5),
	(23, 10, 'Hartaaaaa', 4),
	(24, 9, 'Divertida', 4),
	(25, 13, 'Me encanto', 5);
/*!40000 ALTER TABLE `opinion` ENABLE KEYS */;

-- Volcando estructura para tabla peliculasonlinealta.pelicula
CREATE TABLE IF NOT EXISTS `pelicula` (
  `codPelicula` int(5) NOT NULL AUTO_INCREMENT,
  `titulo` varchar(40) NOT NULL,
  `codDirector` int(5) NOT NULL,
  `codGenero` int(5) NOT NULL,
  `estreno` int(11) NOT NULL,
  `imagen` varchar(15) NOT NULL,
  `puntuacion` int(5) NOT NULL,
  PRIMARY KEY (`codPelicula`),
  KEY `codDirector` (`codDirector`,`codGenero`),
  KEY `codGenero` (`codGenero`),
  CONSTRAINT `pelicula_ibfk_1` FOREIGN KEY (`codDirector`) REFERENCES `director` (`codDIrector`) ON DELETE CASCADE ON UPDATE CASCADE,
  CONSTRAINT `pelicula_ibfk_2` FOREIGN KEY (`codGenero`) REFERENCES `genero` (`codGenero`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB AUTO_INCREMENT=22 DEFAULT CHARSET=latin1;

-- Volcando datos para la tabla peliculasonlinealta.pelicula: ~20 rows (aproximadamente)
/*!40000 ALTER TABLE `pelicula` DISABLE KEYS */;
INSERT INTO `pelicula` (`codPelicula`, `titulo`, `codDirector`, `codGenero`, `estreno`, `imagen`, `puntuacion`) VALUES
	(1, 'Forrest Gump', 1, 2, 1994, 'forrest', 3),
	(2, 'Titanic', 2, 2, 1997, 'titanic', 3),
	(3, 'El silencio de los corderos', 3, 8, 1991, 'corderos', 4),
	(4, 'El sexto Sentido', 4, 5, 1999, 'sexto', 3),
	(5, 'Avatar', 2, 5, 2009, 'avatar', 5),
	(6, 'Terminator', 2, 4, 1984, 'terminator', 2),
	(7, 'Pulp Fiction', 5, 4, 1994, 'pulp', 4),
	(8, 'Lo que queda del dia', 6, 3, 1993, 'dia', 5),
	(9, 'Mucho Ruido y pocas Nueces', 7, 3, 1993, 'nueces', 4),
	(10, 'Sentido y Sensibilidad', 8, 3, 1995, 'sentido', 5),
	(11, 'Nothing Hill', 9, 1, 1999, 'nothing', 3),
	(12, 'El intruso', 9, 2, 2004, 'intruso', 3),
	(13, 'The Mother', 9, 2, 2003, 'mother', 3),
	(14, 'El Halcon Maltes', 1, 4, 1, 'hojaldritos3', 0),
	(15, 'sdfsfs', 3, 3, 2000, 'Alien8.jpg', 0),
	(16, 'la desesperacion de serv', 1, 6, 0, 'cara2', 0),
	(17, 'PruebaPeli1', 3, 4, 2000, 'corderos.jpg', 0),
	(19, 'Prueba2', 1, 1, 2000, 'intruso.jpg', 0),
	(20, 'a1', 1, 1, 2000, 'avatar.jpg', 0),
	(21, 'a1', 1, 1, 2000, 'lista.jpg', 0);
/*!40000 ALTER TABLE `pelicula` ENABLE KEYS */;

-- Volcando estructura para vista peliculasonlinealta.peliculasdetalle
-- Creando tabla temporal para superar errores de dependencia de VIEW
CREATE TABLE `peliculasdetalle` (
	`id` VARCHAR(36) NOT NULL COLLATE 'utf8_general_ci',
	`titulo` VARCHAR(40) NOT NULL COLLATE 'latin1_swedish_ci',
	`nombreDirector` VARCHAR(20) NOT NULL COLLATE 'latin1_swedish_ci',
	`genero` VARCHAR(15) NOT NULL COLLATE 'latin1_swedish_ci'
) ENGINE=MyISAM;

-- Volcando estructura para tabla peliculasonlinealta.reparto
CREATE TABLE IF NOT EXISTS `reparto` (
  `codPelicula` int(5) NOT NULL,
  `codActor` int(5) NOT NULL,
  `minutos` int(11) NOT NULL,
  PRIMARY KEY (`codPelicula`,`codActor`),
  KEY `codActor` (`codActor`),
  CONSTRAINT `reparto_ibfk_1` FOREIGN KEY (`codPelicula`) REFERENCES `pelicula` (`codPelicula`) ON DELETE CASCADE ON UPDATE CASCADE,
  CONSTRAINT `reparto_ibfk_2` FOREIGN KEY (`codActor`) REFERENCES `actor` (`codActor`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

-- Volcando datos para la tabla peliculasonlinealta.reparto: ~36 rows (aproximadamente)
/*!40000 ALTER TABLE `reparto` DISABLE KEYS */;
INSERT INTO `reparto` (`codPelicula`, `codActor`, `minutos`) VALUES
	(1, 2, 67),
	(1, 3, 50),
	(2, 4, 89),
	(2, 5, 100),
	(3, 8, 70),
	(3, 9, 99),
	(4, 1, 120),
	(4, 4, 50),
	(4, 8, 50),
	(7, 4, 50),
	(7, 6, 79),
	(7, 7, 70),
	(8, 3, 50),
	(8, 9, 113),
	(8, 10, 98),
	(9, 1, 50),
	(9, 5, 50),
	(9, 6, 50),
	(9, 7, 50),
	(9, 10, 115),
	(9, 12, 56),
	(10, 5, 60),
	(10, 10, 70),
	(10, 11, 66),
	(10, 12, 89),
	(14, 10, 0),
	(14, 11, 0),
	(15, 1, 0),
	(15, 2, 0),
	(15, 3, 0),
	(16, 1, 0),
	(16, 10, 0),
	(16, 11, 0),
	(19, 3, 0),
	(20, 1, 0),
	(21, 1, 0);
/*!40000 ALTER TABLE `reparto` ENABLE KEYS */;

-- Volcando estructura para tabla peliculasonlinealta.usuario
CREATE TABLE IF NOT EXISTS `usuario` (
  `usu` varchar(25) NOT NULL,
  `pass` int(7) NOT NULL,
  `admin` int(1) NOT NULL,
  PRIMARY KEY (`usu`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

-- Volcando datos para la tabla peliculasonlinealta.usuario: ~2 rows (aproximadamente)
/*!40000 ALTER TABLE `usuario` DISABLE KEYS */;
INSERT INTO `usuario` (`usu`, `pass`, `admin`) VALUES
	('carmen', 2222, 0),
	('lolavv', 1111, 1);
/*!40000 ALTER TABLE `usuario` ENABLE KEYS */;

-- Volcando estructura para vista peliculasonlinealta.peliculasdetalle
-- Eliminando tabla temporal y crear estructura final de VIEW
DROP TABLE IF EXISTS `peliculasdetalle`;
CREATE ALGORITHM=UNDEFINED SQL SECURITY DEFINER VIEW `peliculasdetalle` AS select uuid() AS `id`,`p`.`titulo` AS `titulo`,`d`.`nombreDirector` AS `nombreDirector`,`g`.`genero` AS `genero` from ((`pelicula` `p` join `director` `d`) join `genero` `g`) where `p`.`codDirector` = `d`.`codDIrector` and `p`.`codGenero` = `g`.`codGenero` ;

/*!40101 SET SQL_MODE=IFNULL(@OLD_SQL_MODE, '') */;
/*!40014 SET FOREIGN_KEY_CHECKS=IFNULL(@OLD_FOREIGN_KEY_CHECKS, 1) */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40111 SET SQL_NOTES=IFNULL(@OLD_SQL_NOTES, 1) */;
