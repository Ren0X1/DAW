-- phpMyAdmin SQL Dump
-- version 4.5.1
-- http://www.phpmyadmin.net
--
-- Servidor: 127.0.0.1
-- Tiempo de generación: 09-11-2017 a las 22:25:10
-- Versión del servidor: 10.1.19-MariaDB
-- Versión de PHP: 5.6.28

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- Base de datos: `pedidoscd`
--

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `detallepedidos`
--

CREATE TABLE `detallepedidos` (
  `titulo` varchar(100) NOT NULL,
  `autor` varchar(100) NOT NULL,
  `precio` float NOT NULL,
  `cantidad` int(11) NOT NULL,
  `pedido` int(11) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Volcado de datos para la tabla `detallepedidos`
--

INSERT INTO `detallepedidos` (`titulo`, `autor`, `precio`, `cantidad`, `pedido`) VALUES
('Greatest Hits ', ' Dolly Parton', 9.9, 2, 2),
('Tupelo Honey ', ' Van Morrison', 8.2, 1, 3),
('One night only ', ' Bee Gees', 10.9, 1, 3),
('1999 Grammy Nominees ', ' Many', 10.2, 4, 4),
('The very best of ', ' Cat Stevens', 8.9, 4, 4),
('Private Dancer ', ' Tina Turner', 8.9, 4, 4),
('Hide your heart', 'Bonnie Tyler', 9.9, 1, 5),
('Sylvias Mother', 'Dr.Hook', 8.1, 1, 5),
('Sylvias Mother', 'Dr.Hook', 8.1, 1, 5),
('Tupelo Honey', 'Van Morrison', 8.2, 1, 5),
('Private Dancer', 'Tina Turner', 8.9, 1, 5),
('Private Dancer', 'Tina Turner', 8.9, 1, 5),
('Eros', 'Eros Ramazzotti', 9.9, 1, 5),
('Greatest Hits', 'Dolly Parton', 9.9, 1, 6),
('Maggie May', 'Rod Stewart', 8.5, 1, 6),
('When a man loves a woman', 'Percy Sledge', 8.7, 1, 6),
('One night only ', ' Bee Gees', 10.9, 2, 7),
('Empire Burlesque', 'Bob Dylan', 10.9, 1, 8),
('Greatest Hits', 'Dolly Parton', 9.9, 1, 8),
('Maggie May ', ' Rod Stewart', 8.5, 2, 9),
('Greatest Hits ', ' Dolly Parton', 9.9, 2, 10),
('One night only ', ' Bee Gees', 10.9, 2, 11),
('Eros', 'Eros Ramazzotti', 9.9, 1, 12),
('1999 Grammy Nominees', 'Many', 10.2, 1, 12),
('Empire Burlesque ', ' Bob Dylan', 10.9, 1, 13),
('Hide your heart', 'Bonnie Tyler', 9.9, 1, 14),
('Greatest Hits', 'Dolly Parton', 9.9, 1, 14),
('Empire Burlesque ', ' Bob Dylan', 10.9, 12, 15),
('Empire Burlesque ', ' Bob Dylan', 10.9, 1, 16),
('Still got the blues', 'Gary Moore', 10.2, 1, 16),
('Eros', 'Eros Ramazzotti', 9.9, 1, 16),
('Empire Burlesque ', ' Bob Dylan', 10.9, 2, 17),
('Hide your heart ', ' Bonnie Tyler', 9.9, 1, 18),
('Empire Burlesque ', ' Bob Dylan', 10.9, 1, 19),
('Empire Burlesque ', ' Bob Dylan', 10.9, 1, 20),
('Greatest Hits ', ' Dolly Parton', 9.9, 2, 21),
('Empire Burlesque ', ' Bob Dylan', 10.9, 1, 22),
('Romanza', 'Andrea Bocelli', 10.8, 1, 22),
('Hide your heart', 'Bonnie Tyler', 9.9, 1, 23),
('Greatest Hits ', ' Dolly Parton', 9.9, 1, 24),
('Still got the blues ', ' Gary Moore', 10.2, 1, 25),
('Greatest Hits ', ' Dolly Parton', 9.9, 1, 26),
('Empire Burlesque', 'Bob Dylan', 10.9, 1, 27),
('Hide your heart', 'Bonnie Tyler', 9.9, 1, 27),
('Empire Burlesque', 'Bob Dylan', 10.9, 1, 28),
('Greatest Hits ', ' Dolly Parton', 9.9, 1, 29),
('Empire Burlesque ', ' Bob Dylan', 10.9, 2, 30),
('One night only ', ' Bee Gees', 10.9, 2, 31),
('Greatest Hits', 'Dolly Parton', 9.9, 1, 31),
('Still got the blues', 'Gary Moore', 10.2, 1, 31),
('Romanza ', ' Andrea Bocelli', 10.8, 2, 32),
('Maggie May', 'Rod Stewart', 8.5, 1, 32),
('Soulsville', 'Jorn Hoel', 7.9, 1, 32),
('Hide your heart ', ' Bonnie Tyler', 9.9, 2, 33),
('Greatest Hits', 'Dolly Parton', 9.9, 1, 33),
('Pavarotti Gala Concert', 'Luciano Pavarotti', 9.9, 1, 33),
('Empire Burlesque', 'Bob Dylan', 10.9, 1, 33),
('Greatest Hits', 'Dolly Parton', 9.9, 1, 34),
('One night only', 'Bee Gees', 10.9, 1, 34),
('Black angel', 'Savage Rose', 10.9, 1, 34),
('Eros ', ' Eros Ramazzotti', 9.9, 2, 35),
('Hide your heart', 'Bonnie Tyler', 9.9, 1, 35),
('Sylvias Mother', 'Dr.Hook', 8.1, 1, 35),
('Tupelo Honey', 'Van Morrison', 8.2, 1, 35),
('Greatest Hits', 'Dolly Parton', 9.9, 1, 36);

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `pedidos`
--

CREATE TABLE `pedidos` (
  `codigo` int(11) NOT NULL,
  `fecha` varchar(50) NOT NULL,
  `cliente` varchar(50) NOT NULL COMMENT 'nif'
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Volcado de datos para la tabla `pedidos`
--

INSERT INTO `pedidos` (`codigo`, `fecha`, `cliente`) VALUES
(2, '16/1/2017', '123456789H'),
(3, '16/1/2017', '123456789H'),
(4, '16/1/2017', '3333333H'),
(5, '16/1/2017', '3333333H'),
(6, '16/1/2017', '123456789H'),
(7, '16/1/2017', '3333333H'),
(8, '17/1/2017', '3333333H'),
(9, '17/1/2017', '3333333H'),
(10, '17/1/2017', '123456789H'),
(11, '19/1/2017', '3333333H'),
(12, '19/1/2017', '123456789H'),
(13, '21/1/2017', '123456789H'),
(14, '21/1/2017', '123456789H'),
(15, '22/1/2017', '123456789H'),
(16, '24/1/2017', '123456789H'),
(17, '5/2/2017', '123456789H'),
(18, '5/2/2017', '123456789H'),
(19, '5/2/2017', '123456789H'),
(20, '5/2/2017', '123456789H'),
(21, '11/2/2017', '123456789H'),
(22, '12/2/2017', '123456789H'),
(23, '12/2/2017', '123456789H'),
(24, '12/2/2017', '123456789H'),
(25, '12/2/2017', '123456789H'),
(26, '12/2/2017', '123456789H'),
(27, '12/2/2017', '123456789H'),
(28, '12/2/2017', '123456789H'),
(29, '12/2/2017', '123456789H'),
(30, '13/2/2017', '123456789H'),
(31, '1/6/2017', '3333333H'),
(32, '2/11/2017', '123456789H'),
(33, '2/11/2017', '123456789H'),
(34, '2/11/2017', '123456789H'),
(35, '9/11/2017', '3333333H'),
(36, '9/11/2017', '123456789H');

--
-- Índices para tablas volcadas
--

--
-- Indices de la tabla `pedidos`
--
ALTER TABLE `pedidos`
  ADD PRIMARY KEY (`codigo`);

--
-- AUTO_INCREMENT de las tablas volcadas
--

--
-- AUTO_INCREMENT de la tabla `pedidos`
--
ALTER TABLE `pedidos`
  MODIFY `codigo` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=37;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
