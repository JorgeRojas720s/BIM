-- phpMyAdmin SQL Dump
-- version 5.2.1
-- https://www.phpmyadmin.net/
--
-- Servidor: 127.0.0.1
-- Tiempo de generación: 22-11-2023 a las 12:48:51
-- Versión del servidor: 10.4.28-MariaDB
-- Versión de PHP: 8.2.4

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
START TRANSACTION;
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- Base de datos: `db_bim`
--

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `rel_objects_materials`
--

CREATE TABLE `rel_objects_materials` (
  `rel_fk_object` int(11) NOT NULL,
  `rel_fk_material` int(11) NOT NULL,
  `rel_quantity` int(11) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `tbl_construction_papers`
--

CREATE TABLE `tbl_construction_papers` (
  `con_id` int(11) NOT NULL,
  `con_name` varchar(75) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Volcado de datos para la tabla `tbl_construction_papers`
--

INSERT INTO `tbl_construction_papers` (`con_id`, `con_name`) VALUES
(53, 'PlanoSanVito');

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `tbl_materials`
--

CREATE TABLE `tbl_materials` (
  `mat_id` int(11) NOT NULL,
  `mat_name` varchar(50) DEFAULT NULL,
  `mat_price` double DEFAULT NULL,
  `mat_unitMeasure` varchar(25) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `tbl_objects`
--

CREATE TABLE `tbl_objects` (
  `obj_id` int(11) NOT NULL,
  `obj_posX` double DEFAULT NULL,
  `obj_posY` double DEFAULT NULL,
  `obj_objectType` varchar(50) DEFAULT NULL,
  `obj_rotation` double DEFAULT NULL,
  `obj_flip` double DEFAULT NULL,
  `obj_width` double DEFAULT NULL,
  `obj_height` double DEFAULT NULL,
  `obj_fk_constructionPapers` int(11) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Volcado de datos para la tabla `tbl_objects`
--

INSERT INTO `tbl_objects` (`obj_id`, `obj_posX`, `obj_posY`, `obj_objectType`, `obj_rotation`, `obj_flip`, `obj_width`, `obj_height`, `obj_fk_constructionPapers`) VALUES
(471, 428.1999755859375, 310.40000610351564, 'crown', 180, 1, 20, 20, 53),
(472, 420.1999755859375, 129.60000305175782, 'wall', 90, 0, 7, 100, 53),
(473, 279.40000610351564, 135.99999694824217, 'wall', 90, 0, 7, 100, 53),
(474, 280.20001220703125, 143.20001220703122, 'window', 0, 0, 100, 5, 53),
(475, 278.60000610351557, 243.20000610351565, 'wall', 90, 0, 7, 161.0510000000001, 53),
(476, 423.4000244140625, 239.2, 'wall', 0, 0, 82.64462809917354, 7, 53),
(477, 445.0000244140625, 244.80001220703127, 'door', 180, -1, 30, 30, 53),
(478, 515.3999877929687, 137.60000610351565, 'wall', 0, 0, 194.87171000000015, 7, 53),
(479, 439.4000000000001, 321.6000000000001, 'window', 90, 0, 5, 46.65073802097332, 53),
(480, 280.2000122070312, 316.0000122070313, 'wall', 0, 0, 7.5, 7, 53),
(481, 481.79997558593755, 245.60000000000002, 'wall', 90, 0, 7, 38.55432894295315, 53),
(482, 485, 320.8000061035156, 'wall', 90, 0, 7, 35.04938994813922, 53),
(483, 426.5999755859375, 136.8000030517578, 'col1', 90, 1, 20, 20, 53),
(484, 360.9999938964844, 128.79999389648435, 'col1', 90, 1, 20, 20, 53),
(485, 503.4, 237.60000610351562, 'col2', 90, 1, 20, 20, 53),
(486, 503.4000000000001, 312.79998779296875, 'col3', 90, 1, 20, 20, 53),
(487, 502.6000122070312, 133.60000000000002, 'col3', 0, 1, 20, 20, 53),
(488, 427.4000244140625, 256, 'col3', 0, 1, 20, 20, 53),
(489, 278.6000061035156, 236.80001220703122, 'crown', 90, 1, 20, 20, 53),
(490, 278.59999999999997, 131.1999969482422, 'crown', 90, 1, 20, 20, 53),
(491, 273.8000061035156, 310.40000000000003, 'col4', 0, 1, 20, 20, 53),
(492, 428.1999755859375, 310.40000610351564, 'crown', 180, 1, 20, 20, 53);

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `tbl_proyects`
--

CREATE TABLE `tbl_proyects` (
  `pro_code` int(11) NOT NULL,
  `pro_name` varchar(45) NOT NULL,
  `pro_starDate` date NOT NULL,
  `pro_endDate` date NOT NULL,
  `pro_engineer` int(11) NOT NULL,
  `pro_designer` int(11) NOT NULL,
  `pro_fk_constructionPapers` int(11) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Volcado de datos para la tabla `tbl_proyects`
--

INSERT INTO `tbl_proyects` (`pro_code`, `pro_name`, `pro_starDate`, `pro_endDate`, `pro_engineer`, `pro_designer`, `pro_fk_constructionPapers`) VALUES
(1234, 'Casa SanVito', '2023-11-01', '2023-11-08', 117990469, 124346575, 53);

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `tbl_users`
--

CREATE TABLE `tbl_users` (
  `usr_id` int(11) NOT NULL,
  `usr_name` varchar(30) NOT NULL,
  `usr_lastName` varchar(45) NOT NULL,
  `usr_status` varchar(30) NOT NULL,
  `usr_username` varchar(50) NOT NULL,
  `usr_email` varchar(100) NOT NULL,
  `usr_password` varchar(20) NOT NULL,
  `usr_role` varchar(20) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Volcado de datos para la tabla `tbl_users`
--

INSERT INTO `tbl_users` (`usr_id`, `usr_name`, `usr_lastName`, `usr_status`, `usr_username`, `usr_email`, `usr_password`, `usr_role`) VALUES
(113567864, 'Gabriel', 'Nuñez', 'Active ', 'gabo', 'gabriel.nuñez@gamil.com', '123', 'Administrator'),
(117990469, 'Jorge', 'Rojas', 'Active ', 'reds', 'jorge.rojas@gmail.com', 'aux', 'Engineer'),
(124346575, 'Fabian', 'Arguedas', 'Active', 'fabiux', 'Cesar.Arguedas.@gmail.com', '321', 'Designer');

--
-- Índices para tablas volcadas
--

--
-- Indices de la tabla `rel_objects_materials`
--
ALTER TABLE `rel_objects_materials`
  ADD KEY `rel_fk_object` (`rel_fk_object`),
  ADD KEY `rel_fk_materials` (`rel_fk_material`);

--
-- Indices de la tabla `tbl_construction_papers`
--
ALTER TABLE `tbl_construction_papers`
  ADD PRIMARY KEY (`con_id`);

--
-- Indices de la tabla `tbl_materials`
--
ALTER TABLE `tbl_materials`
  ADD PRIMARY KEY (`mat_id`);

--
-- Indices de la tabla `tbl_objects`
--
ALTER TABLE `tbl_objects`
  ADD PRIMARY KEY (`obj_id`),
  ADD KEY `obj_fk_constructionPlanes` (`obj_fk_constructionPapers`);

--
-- Indices de la tabla `tbl_proyects`
--
ALTER TABLE `tbl_proyects`
  ADD PRIMARY KEY (`pro_code`),
  ADD KEY `pro_fk_constructionPlanes` (`pro_fk_constructionPapers`),
  ADD KEY `fk_engineer` (`pro_engineer`),
  ADD KEY `fk_designer` (`pro_designer`);

--
-- Indices de la tabla `tbl_users`
--
ALTER TABLE `tbl_users`
  ADD PRIMARY KEY (`usr_id`);

--
-- AUTO_INCREMENT de las tablas volcadas
--

--
-- AUTO_INCREMENT de la tabla `tbl_construction_papers`
--
ALTER TABLE `tbl_construction_papers`
  MODIFY `con_id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=54;

--
-- AUTO_INCREMENT de la tabla `tbl_materials`
--
ALTER TABLE `tbl_materials`
  MODIFY `mat_id` int(11) NOT NULL AUTO_INCREMENT;

--
-- AUTO_INCREMENT de la tabla `tbl_objects`
--
ALTER TABLE `tbl_objects`
  MODIFY `obj_id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=493;

--
-- Restricciones para tablas volcadas
--

--
-- Filtros para la tabla `rel_objects_materials`
--
ALTER TABLE `rel_objects_materials`
  ADD CONSTRAINT `rel_fk_materials` FOREIGN KEY (`rel_fk_material`) REFERENCES `tbl_materials` (`mat_id`),
  ADD CONSTRAINT `rel_fk_object` FOREIGN KEY (`rel_fk_object`) REFERENCES `tbl_objects` (`obj_id`);

--
-- Filtros para la tabla `tbl_objects`
--
ALTER TABLE `tbl_objects`
  ADD CONSTRAINT `obj_fk_constructionPlanes` FOREIGN KEY (`obj_fk_constructionPapers`) REFERENCES `tbl_construction_papers` (`con_id`);

--
-- Filtros para la tabla `tbl_proyects`
--
ALTER TABLE `tbl_proyects`
  ADD CONSTRAINT `fk_designer` FOREIGN KEY (`pro_designer`) REFERENCES `tbl_users` (`usr_id`),
  ADD CONSTRAINT `fk_engineer` FOREIGN KEY (`pro_engineer`) REFERENCES `tbl_users` (`usr_id`),
  ADD CONSTRAINT `pro_fk_constructionPlanes` FOREIGN KEY (`pro_fk_constructionPapers`) REFERENCES `tbl_construction_papers` (`con_id`);
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
