-- phpMyAdmin SQL Dump
-- version 5.2.1
-- https://www.phpmyadmin.net/
--
-- Servidor: 127.0.0.1
-- Tiempo de generación: 19-02-2025 a las 09:57:06
-- Versión del servidor: 10.4.32-MariaDB
-- Versión de PHP: 8.0.30

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
START TRANSACTION;
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- Base de datos: `proyecto`
--

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `alumnos`
--

CREATE TABLE `alumnos` (
  `dni_alumno` varchar(100) NOT NULL,
  `nombre` varchar(100) NOT NULL,
  `apellidos` varchar(100) NOT NULL,
  `direccion` varchar(100) NOT NULL,
  `pass` varchar(100) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Volcado de datos para la tabla `alumnos`
--

INSERT INTO `alumnos` (`dni_alumno`, `nombre`, `apellidos`, `direccion`, `pass`) VALUES
('11111111A', 'Jose Luis', 'Pérez', 'Calle a 3, Ciudad', 'uem'),
('22222222B', 'Ana', 'Gómez', 'Calle r 4, Ciudad', 'uem'),
('33333333C', 'Juan', 'Martínez', 'Calle g 5, Ciudad', 'uem'),
('44444444D', 'María', 'López', 'Calle v 6, Ciudad', 'uem'),
('55555555E', 'Luis', 'García', 'Calle r,7, Ciudad', 'uem'),
('66666666A', 'Lourdes', 'Ciudad', 'Calle B, Z', 'uem'),
('77777777A', 'Carla', 'Santiago', 'Mi calle, 32, 7', 'uem'),
('88888888C', 'Pedro', 'Fernández', 'Avenida Central 45', 'uem');

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `asignaturas`
--

CREATE TABLE `asignaturas` (
  `id_asignatura` varchar(100) NOT NULL,
  `denominacion` varchar(100) NOT NULL,
  `horas` varchar(100) NOT NULL,
  `descripcion` varchar(100) NOT NULL,
  `dni_profesor` varchar(100) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Volcado de datos para la tabla `asignaturas`
--

INSERT INTO `asignaturas` (`id_asignatura`, `denominacion`, `horas`, `descripcion`, `dni_profesor`) VALUES
('A001', 'Programación', '192', 'Introducción a la programación en Java', '12345678A'),
('A002', 'Bases de Datos', '160', 'Fundamentos de bases de datos y SQL', '87654321B'),
('A003', 'Entornos de Desarrollo', '96', 'Herramientas y metodologías de desarrollo', '12345678A'),
('A004', 'Lenguajes de Marcas y Sistemas de Gestión', '128', 'XML, HTML, CSS y ERP', '87654321B'),
('A005', 'Sistemas Informáticos', '160', 'Hardware, redes y sistemas operativos', '12345678A'),
('A006', 'FOL', '96', 'Formación y orientación laboral', '87654321B'),
('A007', 'Acceso a Datos', '120', 'Persistencia y acceso a bases de datos en Java', '12345678A'),
('A008', 'Desarrollo de Interfaces', '120', 'Diseño y desarrollo de interfaces gráficas', '87654321B'),
('A009', 'Programación de Servicios y Procesos', '100', 'Programación multihilo y servicios en red', '12345678A'),
('A010', 'Empresa e Iniciativa Emprendedora', '96', 'Creación y gestión de empresas', '87654321B'),
('A011', 'Programación Multimedia y Dispositivos Móviles', '120', 'Desarrollo de apps para móviles', '12345678A'),
('A012', 'Proyecto DAM', '40', 'Proyecto final de desarrollo de aplicaciones', '87654321B'),
('A013', 'Formación en Centros de Trabajo', '370', 'Prácticas en empresa', '87654321B');

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `matriculas`
--

CREATE TABLE `matriculas` (
  `id_matricula` varchar(100) NOT NULL,
  `dni_alumno` varchar(100) DEFAULT NULL,
  `id_asignatura` varchar(100) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Volcado de datos para la tabla `matriculas`
--

INSERT INTO `matriculas` (`id_matricula`, `dni_alumno`, `id_asignatura`) VALUES
('10', '11111111A', 'A005'),
('11', '11111111A', 'A006'),
('12', '22222222B', 'A001'),
('13', '22222222B', 'A002'),
('14', '22222222B', 'A003'),
('15', '22222222B', 'A004'),
('16', '22222222B', 'A005'),
('17', '22222222B', 'A006'),
('18', '33333333C', 'A007'),
('19', '33333333C', 'A008'),
('20', '33333333C', 'A009'),
('21', '33333333C', 'A010'),
('22', '33333333C', 'A011'),
('23', '33333333C', 'A012'),
('24', '33333333C', 'A013'),
('25', '44444444D', 'A007'),
('26', '44444444D', 'A008'),
('27', '44444444D', 'A009'),
('28', '44444444D', 'A010'),
('29', '44444444D', 'A011'),
('30', '44444444D', 'A012'),
('31', '44444444D', 'A013'),
('32', '55555555E', 'A001'),
('33', '55555555E', 'A002'),
('34', '55555555E', 'A003'),
('35', '55555555E', 'A004'),
('36', '55555555E', 'A005'),
('37', '55555555E', 'A006'),
('38', '77777777A', 'A001'),
('39', '77777777A', 'A002'),
('40', '77777777A', 'A003'),
('41', '77777777A', 'A004'),
('42', '77777777A', 'A005'),
('6', '11111111A', 'A001'),
('7', '11111111A', 'A002'),
('8', '11111111A', 'A003'),
('9', '11111111A', 'A004');

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `notas`
--

CREATE TABLE `notas` (
  `id_nota` varchar(100) NOT NULL,
  `id_asignatura` varchar(100) NOT NULL,
  `dni` varchar(100) NOT NULL,
  `calificacion` varchar(100) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Volcado de datos para la tabla `notas`
--

INSERT INTO `notas` (`id_nota`, `id_asignatura`, `dni`, `calificacion`) VALUES
('N001', 'A001', '11111111A', '8'),
('N002', 'A002', '11111111A', '8'),
('N003', 'A003', '22222222B', '5'),
('N004', 'A004', '22222222B', '5'),
('N005', 'A005', '55555555E', '5'),
('N006', 'A006', '55555555E', '8'),
('N007', 'A007', '33333333C', '9'),
('N008', 'A008', '33333333C', '7'),
('N009', 'A009', '44444444D', '6'),
('N010', 'A010', '44444444D', '9'),
('N011', 'A011', '33333333C', '7'),
('N012', 'A012', '44444444D', '10'),
('N013', 'A013', '33333333C', '9'),
('N014', 'A001', '77777777A', '8'),
('N015', 'A002', '77777777A', '6'),
('N016', 'A003', '77777777A', '9'),
('N017', 'A004', '77777777A', '9'),
('N018', 'A005', '77777777A', '7'),
('N22222222BA0051739807655256', 'A005', '22222222B', '9');

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `profesores`
--

CREATE TABLE `profesores` (
  `dni_profesor` varchar(100) NOT NULL,
  `nombre` varchar(100) NOT NULL,
  `apellidos` varchar(100) NOT NULL,
  `direccion` varchar(100) NOT NULL,
  `pass` varchar(100) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Volcado de datos para la tabla `profesores`
--

INSERT INTO `profesores` (`dni_profesor`, `nombre`, `apellidos`, `direccion`, `pass`) VALUES
('12345678A', 'Carla', 'Abad', 'Calle x, 1, Ciudad', 'uem'),
('87654321B', 'Juan', 'López', 'Calle y ,2, Ciudad', 'uem');

--
-- Índices para tablas volcadas
--

--
-- Indices de la tabla `alumnos`
--
ALTER TABLE `alumnos`
  ADD PRIMARY KEY (`dni_alumno`);

--
-- Indices de la tabla `asignaturas`
--
ALTER TABLE `asignaturas`
  ADD KEY `FK` (`id_asignatura`),
  ADD KEY `FK_profesor` (`dni_profesor`);

--
-- Indices de la tabla `matriculas`
--
ALTER TABLE `matriculas`
  ADD PRIMARY KEY (`id_matricula`),
  ADD KEY `dni_alumno` (`dni_alumno`),
  ADD KEY `id_asignatura` (`id_asignatura`);

--
-- Indices de la tabla `notas`
--
ALTER TABLE `notas`
  ADD PRIMARY KEY (`id_nota`),
  ADD KEY `dni` (`dni`),
  ADD KEY `id_asignatura` (`id_asignatura`);

--
-- Indices de la tabla `profesores`
--
ALTER TABLE `profesores`
  ADD PRIMARY KEY (`dni_profesor`);

--
-- Restricciones para tablas volcadas
--

--
-- Filtros para la tabla `asignaturas`
--
ALTER TABLE `asignaturas`
  ADD CONSTRAINT `asignaturas_ibfk_1` FOREIGN KEY (`dni_profesor`) REFERENCES `profesores` (`dni_profesor`);

--
-- Filtros para la tabla `matriculas`
--
ALTER TABLE `matriculas`
  ADD CONSTRAINT `matriculas_ibfk_1` FOREIGN KEY (`dni_alumno`) REFERENCES `alumnos` (`dni_alumno`),
  ADD CONSTRAINT `matriculas_ibfk_2` FOREIGN KEY (`id_asignatura`) REFERENCES `asignaturas` (`id_asignatura`);

--
-- Filtros para la tabla `notas`
--
ALTER TABLE `notas`
  ADD CONSTRAINT `notas_ibfk_1` FOREIGN KEY (`dni`) REFERENCES `alumnos` (`dni_alumno`),
  ADD CONSTRAINT `notas_ibfk_2` FOREIGN KEY (`id_asignatura`) REFERENCES `asignaturas` (`id_asignatura`);
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
