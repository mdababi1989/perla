-- phpMyAdmin SQL Dump
-- version 4.8.4
-- https://www.phpmyadmin.net/
--
-- Hôte : 127.0.0.1:3306
-- Généré le :  ven. 05 avr. 2019 à 21:58
-- Version du serveur :  10.3.12-MariaDB
-- Version de PHP :  7.2.14

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
SET AUTOCOMMIT = 0;
START TRANSACTION;
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- Base de données :  `perla`
--

-- --------------------------------------------------------

--
-- Structure de la table `hibernate_sequence`
--

DROP TABLE IF EXISTS `hibernate_sequence`;
CREATE TABLE IF NOT EXISTS `hibernate_sequence` (
  `next_val` bigint(20) DEFAULT NULL
) ENGINE=MyISAM DEFAULT CHARSET=latin1;

--
-- Déchargement des données de la table `hibernate_sequence`
--

INSERT INTO `hibernate_sequence` (`next_val`) VALUES
(108);

-- --------------------------------------------------------

--
-- Structure de la table `image`
--

DROP TABLE IF EXISTS `image`;
CREATE TABLE IF NOT EXISTS `image` (
  `id` int(11) NOT NULL,
  `description` varchar(255) DEFAULT NULL,
  `image_type` int(11) DEFAULT NULL,
  `path` varchar(255) DEFAULT NULL,
  `title` varchar(255) DEFAULT NULL,
  `principal` int(11) NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=MyISAM DEFAULT CHARSET=latin1;

--
-- Déchargement des données de la table `image`
--

INSERT INTO `image` (`id`, `description`, `image_type`, `path`, `title`, `principal`) VALUES
(1, '', 0, 's1.jpg', '', 0),
(2, '', 0, 's3.jpg', '', 0),
(3, '', 0, 's5.jpg', '', 0),
(4, '', 0, 's2.jpg', '', 0),
(9, NULL, 1, 'francais.jpg', NULL, 0),
(7, NULL, 1, 'arabe.png', NULL, 0),
(8, NULL, 1, 'facebook.jpg', NULL, 0),
(11, NULL, 1, 's5.jpg', NULL, 0),
(12, NULL, 1, 'spa.png', NULL, 0),
(13, NULL, 1, 'youtube.jpg', NULL, 0),
(14, NULL, 1, 'r1arabe.png', NULL, 0),
(16, NULL, 1, 'bg-nav-arrow-active.png', NULL, 0),
(18, NULL, 2, 'arabe.png', NULL, 0),
(81, NULL, 2, '55744527_425907951548166_6160568018410668032_n.jpg', NULL, 1),
(22, NULL, 2, 'r1arabe.png', NULL, 1),
(27, NULL, 2, 'adresse.png', NULL, 0),
(28, NULL, 2, 'arabe.jpg', NULL, 0),
(29, NULL, 2, 'r2arabe.png', NULL, 0),
(30, NULL, 2, 'bg-nav-arrow-active.png', NULL, 0),
(31, NULL, 2, 'bg-nav-arrow-inactive.png', NULL, 0),
(32, NULL, 2, 'bg-sidebar-box-csc-header.png', NULL, 0),
(33, NULL, 2, 'corbeille.jpg', NULL, 0),
(34, NULL, 2, 'facebook.jpg', NULL, 0),
(35, NULL, 2, 'r1francais.jpg', NULL, 0),
(36, NULL, 2, 's1.jpg', NULL, 0),
(37, NULL, 2, 's2.jpg', NULL, 0),
(38, NULL, 2, 's3.jpg', NULL, 0),
(39, NULL, 2, 's4.jpg', NULL, 0),
(40, NULL, 2, 's5.jpg', NULL, 0),
(41, NULL, 2, 'search.png', NULL, 0),
(42, NULL, 2, 'spa.jpg', NULL, 0),
(84, NULL, 2, 'Chrysanthemum.jpg', NULL, 0),
(82, NULL, 3, '55744527_425907951548166_6160568018410668032_n.jpg', NULL, 1),
(46, NULL, 3, 'r1corbeille.jpg', NULL, 0),
(47, NULL, 3, 'facebook.jpg', NULL, 0),
(48, NULL, 3, 'r2corbeille.jpg', NULL, 1),
(83, NULL, 4, '55744527_425907951548166_6160568018410668032_n.jpg', NULL, 1),
(51, NULL, 4, 's2.jpg', NULL, 1),
(57, NULL, 5, '22196208_872504936251311_8457151048002145380_n.jpg', NULL, 1),
(53, NULL, 4, 's1.jpg', NULL, 0),
(54, NULL, 4, 'r1s2.jpg', NULL, 0),
(55, NULL, 4, 's3.jpg', NULL, 0),
(56, NULL, 4, 's5.jpg', NULL, 0),
(58, NULL, 5, 'VelocityAnd-PositionError.png', NULL, 1),
(59, NULL, 5, 'Chrysanthemum.jpg', NULL, 0),
(60, NULL, 5, 'Desert.jpg', NULL, 0),
(61, NULL, 5, 'Hydrangeas.jpg', NULL, 0),
(62, NULL, 5, 'Jellyfish.jpg', NULL, 0),
(63, NULL, 5, 'Koala.jpg', NULL, 0),
(64, NULL, 5, 'Lighthouse.jpg', NULL, 0),
(65, NULL, 5, 'Penguins.jpg', NULL, 0),
(66, NULL, 5, 'Tulips.jpg', NULL, 0),
(67, NULL, 6, 'Chrysanthemum.jpg', NULL, 1),
(68, NULL, 6, 'Jellyfish.jpg', NULL, 1),
(69, NULL, 6, 'r1Chrysanthemum.jpg', NULL, 0),
(70, NULL, 6, 'Desert.jpg', NULL, 0),
(71, NULL, 6, 'Hydrangeas.jpg', NULL, 0),
(72, NULL, 6, 'r1Jellyfish.jpg', NULL, 0),
(79, NULL, 7, '55744527_425907951548166_6160568018410668032_n.jpg', NULL, 1),
(80, NULL, 8, 'VelocityAnd-PositionError.png', NULL, 1),
(76, NULL, 8, 'Koala.jpg', NULL, 1),
(85, NULL, 2, 'Desert.jpg', NULL, 0),
(86, NULL, 2, 'Hydrangeas.jpg', NULL, 0),
(87, NULL, 2, 'Jellyfish.jpg', NULL, 0),
(88, NULL, 2, 'Koala.jpg', NULL, 0),
(89, NULL, 2, 'Lighthouse.jpg', NULL, 0),
(90, NULL, 2, 'Penguins.jpg', NULL, 0),
(91, NULL, 2, 'Tulips.jpg', NULL, 0),
(92, NULL, 2, 'r1Chrysanthemum.jpg', NULL, 0),
(93, NULL, 2, 'r1Desert.jpg', NULL, 0),
(94, NULL, 2, 'r1Hydrangeas.jpg', NULL, 0),
(95, NULL, 2, 'r1Jellyfish.jpg', NULL, 0),
(96, NULL, 2, 'r1Koala.jpg', NULL, 0),
(97, NULL, 2, 'r1Lighthouse.jpg', NULL, 0),
(98, NULL, 2, 'r1Penguins.jpg', NULL, 0),
(99, NULL, 2, 'r1Tulips.jpg', NULL, 0),
(100, NULL, 2, 'r2Chrysanthemum.jpg', NULL, 0),
(101, NULL, 2, 'r2Desert.jpg', NULL, 0),
(102, NULL, 2, 'r2Hydrangeas.jpg', NULL, 0),
(103, NULL, 2, 'r2Jellyfish.jpg', NULL, 0),
(104, NULL, 2, 'r2Koala.jpg', NULL, 0),
(105, NULL, 2, 'r2Lighthouse.jpg', NULL, 0),
(106, NULL, 2, 'r2Penguins.jpg', NULL, 0),
(107, NULL, 2, 'r2Tulips.jpg', NULL, 0);

-- --------------------------------------------------------

--
-- Structure de la table `user`
--

DROP TABLE IF EXISTS `user`;
CREATE TABLE IF NOT EXISTS `user` (
  `user_id` int(11) NOT NULL,
  `active` int(11) DEFAULT NULL,
  `password` varchar(255) DEFAULT NULL,
  `role` varchar(255) DEFAULT NULL,
  `username` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`user_id`)
) ENGINE=MyISAM DEFAULT CHARSET=latin1;

--
-- Déchargement des données de la table `user`
--

INSERT INTO `user` (`user_id`, `active`, `password`, `role`, `username`) VALUES
(77, 1, '$2a$10$JVdh0CphRCPIJUxtCx8WVOkbLLkpzsDPl.OifQjqjtoWkCMP5Hk/a', 'ADMIN', 'mdababi'),
(78, 1, '$2a$10$mh1g0wxiE6N/zCqmZkyxbeap0PAHRsXLMp0gg3Uc4LqFUklIhl3m6', 'ADMIN', 'a');
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
