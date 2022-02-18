-- phpMyAdmin SQL Dump
-- version 4.8.2
-- https://www.phpmyadmin.net/
--
-- Servidor: 127.0.0.1
-- Tiempo de generación: 11-02-2019 a las 09:37:44
-- Versión del servidor: 10.1.34-MariaDB
-- Versión de PHP: 5.6.37

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
SET AUTOCOMMIT = 0;
START TRANSACTION;
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- Base de datos: `usuarios`
--

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `clientes`
--

CREATE TABLE `clientes` (
  `login` varchar(20) NOT NULL,
  `password` varchar(20) NOT NULL,
  `admin` tinyint(1) NOT NULL,
  `nombre` varchar(20) NOT NULL,
  `apellidos` varchar(30) NOT NULL,
  `edad` int(3) NOT NULL,
  `telefono` varchar(13) NOT NULL,
  `email` varchar(30) NOT NULL,
  `totalcomprado` float NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Volcado de datos para la tabla `clientes`
--

INSERT INTO `clientes` (`login`, `password`, `admin`, `nombre`, `apellidos`, `edad`, `telefono`, `email`, `totalcomprado`) VALUES
('admin', 'admin', 1, 'admin', 'admin', 99, '123456789', 'admin@games2daw.net', 0),
('ana', 'anan', 0, 'ana', 'garcia', 11, '2234234', ' mariagonzalez@hotmail.com', 0),
('miguel', 'miguel', 0, 'miguel', 'perez', 22, '3353566456', 'miguel@hotmail.com', 0);

--
-- Índices para tablas volcadas
--

--
-- Indices de la tabla `clientes`
--
ALTER TABLE `clientes`
  ADD PRIMARY KEY (`login`),
  ADD UNIQUE KEY `usuario` (`login`);
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
