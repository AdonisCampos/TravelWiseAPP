-- phpMyAdmin SQL Dump
-- version 5.2.0
-- https://www.phpmyadmin.net/
--
-- Servidor: 127.0.0.1:3306
-- Tiempo de generación: 06-12-2023 a las 23:51:11
-- Versión del servidor: 8.0.31
-- Versión de PHP: 8.0.26

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
START TRANSACTION;
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- Base de datos: `travelwisedb`
--

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `ciudades`
--

DROP TABLE IF EXISTS `ciudades`;
CREATE TABLE IF NOT EXISTS `ciudades` (
  `id` int NOT NULL AUTO_INCREMENT,
  `nombre_ciudad` varchar(100) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL,
  `id_pais` int DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `fk_ciudades_paises` (`id_pais`)
) ENGINE=InnoDB AUTO_INCREMENT=85 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Volcado de datos para la tabla `ciudades`
--

INSERT INTO `ciudades` (`id`, `nombre_ciudad`, `id_pais`) VALUES
(1, 'Ciudad de Guatemala', 1),
(2, 'Quetzaltenango', 1),
(3, 'San Pedro Sula', 2),
(4, 'Tegucigalpa', 2),
(5, 'San Salvador', 3),
(6, 'Santa Ana', 3),
(7, 'Managua', 4),
(8, 'León', 4),
(9, 'San José', 5),
(10, 'Liberia', 5),
(11, 'Ciudad de Panamá', 6),
(12, 'Colón', 6),
(13, 'Cobán', 1),
(14, 'Tactic', 1),
(15, 'Fray Bartolomé de las Casas', 1),
(16, 'Salamá', 1),
(17, 'Cubulco', 1),
(18, 'Purulhá', 1),
(19, 'Chimaltenango', 1),
(20, 'San José Poaquil', 1),
(21, 'San Martín Jilotepeque', 1),
(22, 'Chiquimula', 1),
(23, 'Esquipulas', 1),
(24, 'Jocotán', 1),
(25, 'Tegucigalpa', 2),
(26, 'San Pedro Sula', 2),
(27, 'Choloma', 2),
(28, 'La Ceiba', 2),
(29, 'El Progreso', 2),
(30, 'Choluteca', 2),
(31, 'Comayagua', 2),
(32, 'Siguatepeque', 2),
(33, 'Puerto Cortés', 2),
(34, 'Danlí', 2),
(35, 'Santa Rosa de Copán', 2),
(36, 'Juticalpa', 2),
(37, 'San Salvador', 3),
(38, 'Santa Ana', 3),
(39, 'Soyapango', 3),
(40, 'San Miguel', 3),
(41, 'Mejicanos', 3),
(42, 'Santa Tecla', 3),
(43, 'Apopa', 3),
(44, 'Delgado', 3),
(45, 'Usulután', 3),
(46, 'Ilopango', 3),
(47, 'Sonsonate', 3),
(48, 'San Marcos', 3),
(49, 'Managua', 4),
(50, 'León', 4),
(51, 'Matagalpa', 4),
(52, 'Chinandega', 4),
(53, 'Masaya', 4),
(54, 'Granada', 4),
(55, 'Estelí', 4),
(56, 'Juigalpa', 4),
(57, 'Boaco', 4),
(58, 'Rivas', 4),
(59, 'Jinotega', 4),
(60, 'Bluefields', 4),
(61, 'San José', 5),
(62, 'Alajuela', 5),
(63, 'Cartago', 5),
(64, 'Heredia', 5),
(65, 'Puntarenas', 5),
(66, 'Guanacaste', 5),
(67, 'Limón', 5),
(68, 'Los Santos', 5),
(69, 'San Carlos', 5),
(70, 'Upala', 5),
(71, 'Desamparados', 5),
(72, 'Tibás', 5),
(73, 'Panamá', 6),
(74, 'San Miguelito', 6),
(75, 'Tocumen', 6),
(76, 'David', 6),
(77, 'Arraiján', 6),
(78, 'Colon', 6),
(79, 'Las Cumbres', 6),
(80, 'La Chorrera', 6),
(81, 'Pedregal', 6),
(82, 'Santiago de Veraguas', 6),
(83, 'Chitré', 6),
(84, 'Penonomé', 6);

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `destinos`
--

DROP TABLE IF EXISTS `destinos`;
CREATE TABLE IF NOT EXISTS `destinos` (
  `id` int NOT NULL AUTO_INCREMENT,
  `titulo` varchar(100) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL,
  `id_pais` int DEFAULT NULL,
  `creacion` datetime DEFAULT NULL,
  `ultima_modifiacion` datetime DEFAULT NULL,
  `id_usuario` int DEFAULT NULL,
  `token` text CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL,
  PRIMARY KEY (`id`),
  KEY `fk_destinos_usuarios` (`id_usuario`)
) ENGINE=InnoDB AUTO_INCREMENT=16 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Volcado de datos para la tabla `destinos`
--

INSERT INTO `destinos` (`id`, `titulo`, `id_pais`, `creacion`, `ultima_modifiacion`, `id_usuario`, `token`) VALUES
(1, 'Título del Destino', 1, '2023-11-23 13:48:55', '2023-11-23 13:48:55', 1, ''),
(2, 'sopas', 1, '2023-12-01 20:01:15', '2023-12-01 20:01:15', 1, '43c064d24bc63df504de7b7969d9fbb0');

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `destinos_ciudades`
--

DROP TABLE IF EXISTS `destinos_ciudades`;
CREATE TABLE IF NOT EXISTS `destinos_ciudades` (
  `id` int NOT NULL AUTO_INCREMENT,
  `id_ciudad` int NOT NULL,
  `id_destino` int DEFAULT NULL,
  `creacion` datetime DEFAULT NULL,
  `ultima_modifiacion` datetime DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `fk_destinos_ciudades_ciudades` (`id_ciudad`),
  KEY `fk_destinos_ciudades_destinos` (`id_destino`)
) ENGINE=InnoDB AUTO_INCREMENT=27 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Volcado de datos para la tabla `destinos_ciudades`
--

INSERT INTO `destinos_ciudades` (`id`, `id_ciudad`, `id_destino`, `creacion`, `ultima_modifiacion`) VALUES
(1, 1, 1, '2023-11-23 13:51:02', '2023-11-23 13:51:02'),
(2, 2, 2, '2023-12-01 20:23:56', '2023-12-01 20:23:56'),
(3, 2, 2, '2023-12-01 20:25:25', '2023-12-01 20:25:25');

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `destinos_recomendaciones`
--

DROP TABLE IF EXISTS `destinos_recomendaciones`;
CREATE TABLE IF NOT EXISTS `destinos_recomendaciones` (
  `id` int NOT NULL AUTO_INCREMENT,
  `recomendacion` text CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL,
  `id_destino` int DEFAULT NULL,
  `creacion` datetime DEFAULT NULL,
  `ultima_modifiacion` datetime DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `fk_destinos_recomendaciones_destinos` (`id_destino`)
) ENGINE=InnoDB AUTO_INCREMENT=13 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Volcado de datos para la tabla `destinos_recomendaciones`
--

INSERT INTO `destinos_recomendaciones` (`id`, `recomendacion`, `id_destino`, `creacion`, `ultima_modifiacion`) VALUES
(2, 'ninguna', 2, '2023-12-01 20:25:25', '2023-12-01 20:25:25');

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `paises`
--

DROP TABLE IF EXISTS `paises`;
CREATE TABLE IF NOT EXISTS `paises` (
  `id` int NOT NULL AUTO_INCREMENT,
  `nombre_pais` varchar(100) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=7 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Volcado de datos para la tabla `paises`
--

INSERT INTO `paises` (`id`, `nombre_pais`) VALUES
(1, 'Guatemala'),
(2, 'Honduras'),
(3, 'El Salvador'),
(4, 'Nicaragua'),
(5, 'Costa Rica'),
(6, 'Panamá');

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `requisitos_entrada`
--

DROP TABLE IF EXISTS `requisitos_entrada`;
CREATE TABLE IF NOT EXISTS `requisitos_entrada` (
  `id` int NOT NULL AUTO_INCREMENT,
  `id_pais` int DEFAULT NULL,
  `requisitos` text CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL,
  PRIMARY KEY (`id`),
  KEY `id_pais` (`id_pais`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `usuarios`
--

DROP TABLE IF EXISTS `usuarios`;
CREATE TABLE IF NOT EXISTS `usuarios` (
  `id` int NOT NULL AUTO_INCREMENT,
  `correo` varchar(150) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL,
  `usuario` varchar(100) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL,
  `password` varchar(100) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `unique_usuario` (`usuario`)
) ENGINE=InnoDB AUTO_INCREMENT=7 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Volcado de datos para la tabla `usuarios`
--

INSERT INTO `usuarios` (`id`, `correo`, `usuario`, `password`) VALUES
(1, 'admin@outlook.es', 'admin', '12345'),
(2, 'asd@gmail.com', 'asd', '12'),
(3, 'asdad@gmail.com', 'asdasdas', '123'),
(5, 'val@gmail.com', 'valery', '123'),
(6, 'jo@gmail.com', 'administrador', '123');

--
-- Restricciones para tablas volcadas
--

--
-- Filtros para la tabla `ciudades`
--
ALTER TABLE `ciudades`
  ADD CONSTRAINT `fk_ciudades_paises` FOREIGN KEY (`id_pais`) REFERENCES `paises` (`id`);

--
-- Filtros para la tabla `destinos`
--
ALTER TABLE `destinos`
  ADD CONSTRAINT `fk_destinos_usuarios` FOREIGN KEY (`id_usuario`) REFERENCES `usuarios` (`id`) ON DELETE CASCADE ON UPDATE CASCADE;

--
-- Filtros para la tabla `destinos_ciudades`
--
ALTER TABLE `destinos_ciudades`
  ADD CONSTRAINT `fk_destinos_ciudades_ciudades` FOREIGN KEY (`id_ciudad`) REFERENCES `ciudades` (`id`),
  ADD CONSTRAINT `fk_destinos_ciudades_destinos` FOREIGN KEY (`id_destino`) REFERENCES `destinos` (`id`) ON DELETE CASCADE ON UPDATE CASCADE;

--
-- Filtros para la tabla `destinos_recomendaciones`
--
ALTER TABLE `destinos_recomendaciones`
  ADD CONSTRAINT `fk_destinos_recomendaciones_destinos` FOREIGN KEY (`id_destino`) REFERENCES `destinos` (`id`) ON DELETE CASCADE ON UPDATE CASCADE;

--
-- Filtros para la tabla `requisitos_entrada`
--
ALTER TABLE `requisitos_entrada`
  ADD CONSTRAINT `requisitos_entrada_ibfk_1` FOREIGN KEY (`id_pais`) REFERENCES `paises` (`id`);
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
