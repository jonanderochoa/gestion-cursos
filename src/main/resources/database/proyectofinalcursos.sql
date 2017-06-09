-- phpMyAdmin SQL Dump
-- version 4.1.14
-- http://www.phpmyadmin.net
--
-- Servidor: 127.0.0.1
-- Tiempo de generación: 09-06-2017 a las 13:39:24
-- Versión del servidor: 5.6.17
-- Versión de PHP: 5.5.12

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8 */;

--
-- Base de datos: `proyectofinalcursos`
--
DROP DATABASE `proyectofinalcursos`;
CREATE DATABASE IF NOT EXISTS `proyectofinalcursos` DEFAULT CHARACTER SET utf8 COLLATE utf8_unicode_ci;
USE `proyectofinalcursos`;

DELIMITER $$
--
-- Procedimientos
--
DROP PROCEDURE IF EXISTS `cursoCreate`$$
CREATE DEFINER=`root`@`localhost` PROCEDURE `cursoCreate`(IN `pcodcurso` VARCHAR(40), IN `pnomcurso` VARCHAR(150), OUT `pcodigo` INT)
BEGIN
	INSERT INTO curso(
	codcurso,
    nomcurso)
    
    VALUES
    (lower(pcodcurso),
    lower(pnomcurso));
    
    SET pcodigo = LAST_INSERT_ID();
    
END$$

DROP PROCEDURE IF EXISTS `cursoDelete`$$
CREATE DEFINER=`root`@`localhost` PROCEDURE `cursoDelete`(IN pcodigo INT)
BEGIN
	DELETE FROM curso WHERE codigo = pcodigo;
END$$

DROP PROCEDURE IF EXISTS `cursogetAll`$$
CREATE DEFINER=`root`@`localhost` PROCEDURE `cursogetAll`()
BEGIN
	SELECT 
    c.codigo as codigo,
    c.codcurso as codcurso,
    c.nomcurso as nomcurso
    
    FROM curso as c;
END$$

DROP PROCEDURE IF EXISTS `cursogetById`$$
CREATE DEFINER=`root`@`localhost` PROCEDURE `cursogetById`(IN pcodigo int)
BEGIN
	SELECT 
		c.codigo as codigo,
        c.codcurso as codcurso,
        c.nomcurso as nomcurso
        
    FROM curso as c
    
    WHERE c.codigo = pcodigo;
END$$

DROP PROCEDURE IF EXISTS `cursoUpdate`$$
CREATE DEFINER=`root`@`localhost` PROCEDURE `cursoUpdate`(IN `pcodigo` INT, IN `pcodcurso` VARCHAR(40), IN `pnomcurso` VARCHAR(150))
BEGIN
	UPDATE curso
    SET
    codigo=pcodigo,
    codcurso=lower(pcodcurso),
    nomcurso=lower(pnomcurso)
    
    WHERE codigo = pcodigo;
END$$

DELIMITER ;

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `curso`
--

DROP TABLE IF EXISTS `curso`;
CREATE TABLE IF NOT EXISTS `curso` (
  `codigo` int(11) NOT NULL AUTO_INCREMENT,
  `codcurso` varchar(40) COLLATE utf8_unicode_ci NOT NULL,
  `nomcurso` varchar(150) COLLATE utf8_unicode_ci NOT NULL,
  PRIMARY KEY (`codigo`),
  UNIQUE KEY `codcurso_UNIQUE` (`codcurso`)
) ENGINE=InnoDB  DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci COMMENT='Tabla que guarda los cursos de ipartek' AUTO_INCREMENT=8 ;

--
-- Volcado de datos para la tabla `curso`
--

INSERT INTO `curso` (`codigo`, `codcurso`, `nomcurso`) VALUES
(2, 'ert', 'php'),
(3, 'etww', 'spring'),
(5, 'qqqaaa', 'culo'),
(6, 'mocoso', 'culo'),
(7, 'moco', 'culo');

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
