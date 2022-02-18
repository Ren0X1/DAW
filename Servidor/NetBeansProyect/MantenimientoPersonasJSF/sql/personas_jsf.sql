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


-- Volcando estructura de base de datos para personas_jsf
CREATE DATABASE IF NOT EXISTS `personas_jsf` /*!40100 DEFAULT CHARACTER SET utf8mb4 */;
USE `personas_jsf`;

-- Volcando estructura para tabla personas_jsf.personas
CREATE TABLE IF NOT EXISTS `personas` (
  `ID` int(10) unsigned NOT NULL AUTO_INCREMENT,
  `NOMBRE` varchar(45) NOT NULL,
  `APELLIDOS` varchar(55) DEFAULT NULL,
  `FNACIMIENTO` date DEFAULT NULL,
  `OBSERVACIONES` text DEFAULT NULL,
  PRIMARY KEY (`ID`)
) ENGINE=InnoDB AUTO_INCREMENT=6 DEFAULT CHARSET=latin1;

-- Volcando datos para la tabla personas_jsf.personas: ~5 rows (aproximadamente)
/*!40000 ALTER TABLE `personas` DISABLE KEYS */;
INSERT INTO `personas` (`ID`, `NOMBRE`, `APELLIDOS`, `FNACIMIENTO`, `OBSERVACIONES`) VALUES
	(1, 'Joshua', 'Nunca Visto', '1971-04-30', 'holaaaaaaaaaa'),
	(2, 'Filemón ', 'Pi', '2007-07-04', 'Es un dibujo, de los de antes.'),
	(3, 'Mortadelo', 'Sinape', '2006-06-05', ''),
	(4, 'Marta', 'Núñez Corregidor', '1995-11-25', 'Persona musical'),
	(5, 'Marco Elio', 'García Gomariz', '1992-11-11', 'que pregunta jjjj');
/*!40000 ALTER TABLE `personas` ENABLE KEYS */;

/*!40101 SET SQL_MODE=IFNULL(@OLD_SQL_MODE, '') */;
/*!40014 SET FOREIGN_KEY_CHECKS=IFNULL(@OLD_FOREIGN_KEY_CHECKS, 1) */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40111 SET SQL_NOTES=IFNULL(@OLD_SQL_NOTES, 1) */;
