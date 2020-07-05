-- phpMyAdmin SQL Dump
-- version 4.9.0.1
-- https://www.phpmyadmin.net/
--
-- Hôte : 127.0.0.1
-- Généré le :  ven. 03 juil. 2020 à 09:45
-- Version du serveur :  10.3.16-MariaDB
-- Version de PHP :  7.3.7

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
SET AUTOCOMMIT = 0;
START TRANSACTION;
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- Base de données :  `testgenerator`
--

-- --------------------------------------------------------

--
-- Structure de la table `carte_mentale`
--

CREATE TABLE `carte_mentale` (
  `question_id` bigint(20) NOT NULL,
  `media` longblob DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

-- --------------------------------------------------------

--
-- Structure de la table `categorie`
--

CREATE TABLE `categorie` (
  `id_categorie` bigint(20) NOT NULL,
  `date_creation` datetime DEFAULT NULL,
  `date_modification` datetime DEFAULT NULL,
  `description` varchar(255) DEFAULT NULL,
  `libelle` varchar(255) NOT NULL,
  `theme` bigint(20) NOT NULL,
  `resp_cat` bigint(20) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Déchargement des données de la table `categorie`
--

INSERT INTO `categorie` (`id_categorie`, `date_creation`, `date_modification`, `description`, `libelle`, `theme`, `resp_cat`) VALUES
(1, '2020-06-10 11:15:03', '2020-06-10 11:15:03', NULL, 'python', 0, 0),
(2, '2020-06-10 11:15:42', '2020-06-10 11:15:42', NULL, 'installation', 0, 0),
(3, '2020-06-10 11:15:03', '2020-06-10 11:15:03', NULL, 'class python', 0, 0);

-- --------------------------------------------------------

--
-- Structure de la table `envoi_message`
--

CREATE TABLE `envoi_message` (
  `id` bigint(20) NOT NULL,
  `date_envoi` datetime DEFAULT NULL,
  `statut` varchar(255) DEFAULT NULL,
  `mail` bigint(20) DEFAULT NULL,
  `utilisateur` bigint(20) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

-- --------------------------------------------------------

--
-- Structure de la table `evaluation`
--

CREATE TABLE `evaluation` (
  `id_evaluation` bigint(20) NOT NULL,
  `date_creation` datetime DEFAULT NULL,
  `date_modification` datetime DEFAULT NULL,
  `statut` varchar(255) DEFAULT NULL,
  `total` int(11) DEFAULT NULL,
  `type_evaluation` bigint(20) DEFAULT NULL,
  `apprenant` bigint(20) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Déchargement des données de la table `evaluation`
--

INSERT INTO `evaluation` (`id_evaluation`, `date_creation`, `date_modification`, `statut`, `total`, `type_evaluation`, `apprenant`) VALUES
(5, '2020-06-10 11:15:03', '2020-06-10 11:15:03', NULL, 0, NULL, 6),
(19, '2020-06-27 21:35:39', '2020-06-27 21:35:39', NULL, 0, NULL, 6),
(20, '2020-06-27 21:37:09', '2020-06-27 21:37:09', NULL, 0, NULL, 6),
(23, '2020-06-27 21:38:35', '2020-06-27 21:38:35', NULL, 0, NULL, 6),
(26, '2020-06-27 21:39:43', '2020-06-27 21:39:43', NULL, 0, NULL, 6),
(29, '2020-06-27 21:43:33', '2020-06-27 21:43:33', NULL, 0, NULL, 6),
(33, '2020-06-27 21:44:05', '2020-06-27 21:44:05', NULL, 0, NULL, 6),
(42, '2020-06-27 23:10:05', '2020-06-27 23:10:05', NULL, 0, NULL, 6),
(45, '2020-06-27 23:14:57', '2020-06-27 23:14:57', NULL, 0, NULL, 6),
(48, '2020-06-27 23:21:07', '2020-06-27 23:21:07', NULL, 0, NULL, 6),
(51, '2020-06-27 23:22:40', '2020-06-27 23:22:40', NULL, 0, NULL, 6),
(54, '2020-06-27 23:25:50', '2020-06-27 23:25:50', NULL, 0, NULL, 6),
(57, '2020-06-27 23:27:44', '2020-06-27 23:27:44', NULL, 0, NULL, 6),
(60, '2020-06-27 23:29:37', '2020-06-27 23:29:37', NULL, 0, NULL, 6),
(63, '2020-06-27 23:55:24', '2020-06-27 23:55:24', NULL, 0, NULL, 6),
(66, '2020-06-27 23:58:13', '2020-06-27 23:58:13', NULL, 0, NULL, 6),
(69, '2020-06-28 00:00:19', '2020-06-28 00:00:19', NULL, 0, NULL, 6),
(72, '2020-06-28 00:01:03', '2020-06-28 00:01:03', NULL, 0, NULL, 6),
(75, '2020-06-28 00:03:28', '2020-06-28 00:03:28', NULL, 0, NULL, 6),
(78, '2020-06-28 00:03:47', '2020-06-28 00:03:47', NULL, 0, NULL, 6),
(81, '2020-06-28 00:04:14', '2020-06-28 00:04:14', NULL, 0, NULL, 6),
(86, '2020-06-28 00:04:22', '2020-06-28 00:04:22', NULL, 0, NULL, 6),
(91, '2020-07-02 23:02:49', '2020-07-02 23:02:49', NULL, 0, NULL, 6),
(96, '2020-07-02 23:08:19', '2020-07-02 23:08:19', NULL, 0, NULL, 6),
(108, '2020-07-02 23:10:00', '2020-07-02 23:10:00', NULL, 0, NULL, 6);

-- --------------------------------------------------------

--
-- Structure de la table `eval_quest_rep`
--

CREATE TABLE `eval_quest_rep` (
  `id` bigint(20) NOT NULL,
  `reponse` bigint(20) DEFAULT NULL,
  `etat` varchar(255) DEFAULT NULL,
  `evaluation` bigint(20) DEFAULT NULL,
  `question` bigint(20) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Déchargement des données de la table `eval_quest_rep`
--

INSERT INTO `eval_quest_rep` (`id`, `reponse`, `etat`, `evaluation`, `question`) VALUES
(22, NULL, 'Reussi', 20, 16),
(30, NULL, '', 29, 11),
(31, NULL, 'Reussi', 29, 13),
(36, NULL, 'Reussi', 33, 12),
(43, NULL, 'Reussi', 42, 11),
(44, NULL, 'Reussi', 42, 13),
(92, NULL, NULL, 91, 11),
(93, NULL, NULL, 91, 12),
(94, NULL, 'Reussi', 91, 9),
(95, NULL, 'Reussi', 91, 18);

-- --------------------------------------------------------

--
-- Structure de la table `fichier`
--

CREATE TABLE `fichier` (
  `id_fichier` bigint(20) NOT NULL,
  `media` longblob DEFAULT NULL,
  `question_id` bigint(20) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

-- --------------------------------------------------------

--
-- Structure de la table `forfait`
--

CREATE TABLE `forfait` (
  `id_forfait` bigint(20) NOT NULL,
  `description` varchar(255) DEFAULT NULL,
  `duree` int(11) DEFAULT NULL,
  `libelle` varchar(255) DEFAULT NULL,
  `montant` int(11) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

-- --------------------------------------------------------

--
-- Structure de la table `hibernate_sequence`
--

CREATE TABLE `hibernate_sequence` (
  `next_val` bigint(20) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Déchargement des données de la table `hibernate_sequence`
--

INSERT INTO `hibernate_sequence` (`next_val`) VALUES
(120),
(120),
(120),
(120),
(120),
(120),
(120),
(120),
(120),
(120),
(120),
(120);

-- --------------------------------------------------------

--
-- Structure de la table `mail`
--

CREATE TABLE `mail` (
  `id_mail` bigint(20) NOT NULL,
  `contenu` varchar(255) DEFAULT NULL,
  `destinataire` varchar(255) DEFAULT NULL,
  `emetteur` varchar(255) DEFAULT NULL,
  `objet` varchar(255) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

-- --------------------------------------------------------

--
-- Structure de la table `question`
--

CREATE TABLE `question` (
  `id_question` bigint(20) NOT NULL,
  `date_creation` datetime DEFAULT NULL,
  `date_modification` datetime DEFAULT NULL,
  `duree` int(11) DEFAULT NULL,
  `libelle` varchar(255) DEFAULT NULL,
  `score` int(11) DEFAULT NULL,
  `categorie` bigint(20) DEFAULT NULL,
  `type_question` bigint(20) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Déchargement des données de la table `question`
--

INSERT INTO `question` (`id_question`, `date_creation`, `date_modification`, `duree`, `libelle`, `score`, `categorie`, `type_question`) VALUES
(1, '2020-06-10 11:17:51', '2020-06-10 11:17:51', 5, 'Qu\'est ce que python?', 4, 1, 0),
(9, NULL, NULL, 1, 'Question 1', 1, 2, NULL),
(10, NULL, NULL, 1, 'Question 2', 1, 2, NULL),
(11, NULL, NULL, 1, 'question test', 1, 2, NULL),
(12, NULL, NULL, 1, 'quest', 1, 2, NULL),
(13, NULL, NULL, 1, 'hdghf', 1, 2, NULL),
(16, NULL, NULL, 1, 'fdghhjkkl', 1, 2, NULL),
(17, NULL, NULL, 1, 'gfgff', 1, 2, NULL),
(18, '2020-06-25 04:15:09', '2020-06-25 04:15:09', 1, 'Comment installer odoo?', 1, 2, 0),
(19, '2020-06-25 04:15:09', '2020-06-25 04:15:09', 1, 'Quelle est la syntaxe d\'une classe en python?', 2, 3, 0);

-- --------------------------------------------------------

--
-- Structure de la table `reponse`
--

CREATE TABLE `reponse` (
  `id_reponse` bigint(20) NOT NULL,
  `descriptiion` varchar(255) DEFAULT NULL,
  `libelle` varchar(255) NOT NULL,
  `media` longblob DEFAULT NULL,
  `valeur` bit(1) NOT NULL,
  `question_id` bigint(20) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Déchargement des données de la table `reponse`
--

INSERT INTO `reponse` (`id_reponse`, `descriptiion`, `libelle`, `media`, `valeur`, `question_id`) VALUES
(0, NULL, 'un sgbd', NULL, b'0', 1),
(1, NULL, 'un langage ', NULL, b'1', 1),
(4, NULL, 'un serpent', NULL, b'0', 1),
(14, NULL, 'une langue', NULL, b'0', 1),
(15, '', 'lib', NULL, b'1', 13),
(18, '', 'sdf', NULL, b'1', 17);

-- --------------------------------------------------------

--
-- Structure de la table `reponse_user`
--

CREATE TABLE `reponse_user` (
  `id` bigint(20) NOT NULL,
  `rep_user` tinyblob DEFAULT NULL,
  `eval_id` bigint(20) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

-- --------------------------------------------------------

--
-- Structure de la table `theme`
--

CREATE TABLE `theme` (
  `id_theme` bigint(20) NOT NULL,
  `date_creation` datetime DEFAULT NULL,
  `date_modification` datetime DEFAULT NULL,
  `description` varchar(255) DEFAULT NULL,
  `libelle` varchar(255) DEFAULT NULL,
  `utilisateur` bigint(20) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Déchargement des données de la table `theme`
--

INSERT INTO `theme` (`id_theme`, `date_creation`, `date_modification`, `description`, `libelle`, `utilisateur`) VALUES
(0, '2020-06-10 11:14:05', '2020-06-10 11:14:05', 'Voici Erp', 'ERP', 2);

-- --------------------------------------------------------

--
-- Structure de la table `type_evaluation`
--

CREATE TABLE `type_evaluation` (
  `id_type_eval` bigint(20) NOT NULL,
  `libelle` varchar(255) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

-- --------------------------------------------------------

--
-- Structure de la table `type_question`
--

CREATE TABLE `type_question` (
  `id_type_q` bigint(20) NOT NULL,
  `libelle` varchar(255) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Déchargement des données de la table `type_question`
--

INSERT INTO `type_question` (`id_type_q`, `libelle`) VALUES
(0, 'qcm');

-- --------------------------------------------------------

--
-- Structure de la table `utilisateur`
--

CREATE TABLE `utilisateur` (
  `id` bigint(20) NOT NULL,
  `description` varchar(255) DEFAULT NULL,
  `email` varchar(255) NOT NULL,
  `is_active` bit(1) NOT NULL,
  `login` varchar(255) DEFAULT NULL,
  `nom` varchar(255) NOT NULL,
  `password` varchar(255) DEFAULT NULL,
  `prenom` varchar(255) DEFAULT NULL,
  `role` varchar(255) DEFAULT NULL,
  `sexe` varchar(255) DEFAULT NULL,
  `forfait` bigint(20) DEFAULT NULL,
  `date_naissance` date DEFAULT NULL,
  `username` varchar(255) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Déchargement des données de la table `utilisateur`
--

INSERT INTO `utilisateur` (`id`, `description`, `email`, `is_active`, `login`, `nom`, `password`, `prenom`, `role`, `sexe`, `forfait`, `date_naissance`, `username`) VALUES
(2, NULL, 'fouda.anthony12@gmail.com', b'1', 'anthony1299', 'fouda', 'anthony12', 'anthony', NULL, NULL, NULL, NULL, NULL),
(3, NULL, 'fodjomaximejr@gmail.com', b'1', 'maxime', 'Fodjo', '12345', 'Maxime', NULL, NULL, NULL, NULL, NULL),
(4, NULL, 'odjomaximejr@gmail.com', b'1', 'axime', 'Fodj', '$2a$10$yfsY.3fVYonPudQOhGX7UumWOt8zoIo2wMyLPznEENhRHvkB.7bpO', 'axime', NULL, NULL, NULL, NULL, NULL),
(5, NULL, 'elie@gmail.com', b'1', 'elie', 'Dabbagh', '$2a$10$I41b8EJCtme64g1vivwYdOcNrQDSXTy4LUuC0oa.IwcLLTZ2oXxxK', 'Elie', 'RESPONSABLE', NULL, NULL, NULL, NULL),
(6, NULL, 'John@gmail.com', b'1', 'john', 'Doe', '$2a$10$jRwE40/MNOaQr4IUffafduyedqSSUKqJ5zw6LfvAdZ.NH7Z/DM76i', 'John', 'APPRENANT', NULL, NULL, NULL, NULL);

-- --------------------------------------------------------

--
-- Structure de la table `verification_token`
--

CREATE TABLE `verification_token` (
  `id` bigint(20) NOT NULL,
  `date_expiration` datetime DEFAULT NULL,
  `token` varchar(255) DEFAULT NULL,
  `user_id` bigint(20) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Déchargement des données de la table `verification_token`
--

INSERT INTO `verification_token` (`id`, `date_expiration`, `token`, `user_id`) VALUES
(2, '2020-06-04 09:57:53', '222ab3c5-f8e8-4b34-96f1-cabe9fa3efdb', 2);

--
-- Index pour les tables déchargées
--

--
-- Index pour la table `carte_mentale`
--
ALTER TABLE `carte_mentale`
  ADD PRIMARY KEY (`question_id`);

--
-- Index pour la table `categorie`
--
ALTER TABLE `categorie`
  ADD PRIMARY KEY (`id_categorie`),
  ADD KEY `FKpkogvmu8u986htma0ev8u5vol` (`theme`);

--
-- Index pour la table `envoi_message`
--
ALTER TABLE `envoi_message`
  ADD PRIMARY KEY (`id`),
  ADD KEY `FKi3bgpcypyvdrf2kddk306b93b` (`mail`),
  ADD KEY `FK1ek96h9p3x7caan2xsia4m0jk` (`utilisateur`);

--
-- Index pour la table `evaluation`
--
ALTER TABLE `evaluation`
  ADD PRIMARY KEY (`id_evaluation`),
  ADD KEY `FKhdgpb3w5ouu8ci32evrjvjn8n` (`type_evaluation`),
  ADD KEY `FKif841hn2nkmlj23iy3w8o6lto` (`apprenant`);

--
-- Index pour la table `eval_quest_rep`
--
ALTER TABLE `eval_quest_rep`
  ADD PRIMARY KEY (`id`),
  ADD KEY `FKogh4mqv7anl1s6oyd59n5hyt8` (`evaluation`),
  ADD KEY `FKatib126k14cej63wq1tutjbcn` (`question`);

--
-- Index pour la table `fichier`
--
ALTER TABLE `fichier`
  ADD PRIMARY KEY (`id_fichier`),
  ADD KEY `FKdd3m5gyl0aw46dcuc38p9bf6t` (`question_id`);

--
-- Index pour la table `forfait`
--
ALTER TABLE `forfait`
  ADD PRIMARY KEY (`id_forfait`);

--
-- Index pour la table `mail`
--
ALTER TABLE `mail`
  ADD PRIMARY KEY (`id_mail`);

--
-- Index pour la table `question`
--
ALTER TABLE `question`
  ADD PRIMARY KEY (`id_question`),
  ADD KEY `FK932c6hsdyrrb4ot9a3gy5engq` (`categorie`),
  ADD KEY `FKtb3ceqry12rjtkuq2vw0q40s7` (`type_question`);

--
-- Index pour la table `reponse`
--
ALTER TABLE `reponse`
  ADD PRIMARY KEY (`id_reponse`),
  ADD KEY `FKp0yq2hox7fyw8rpbhx9t514rt` (`question_id`);

--
-- Index pour la table `reponse_user`
--
ALTER TABLE `reponse_user`
  ADD PRIMARY KEY (`id`),
  ADD KEY `FKiaea98p00rw95tmalq82s31kw` (`eval_id`);

--
-- Index pour la table `theme`
--
ALTER TABLE `theme`
  ADD PRIMARY KEY (`id_theme`),
  ADD KEY `FKc0xupfd39m0vvbjbkiddylfmh` (`utilisateur`);

--
-- Index pour la table `type_evaluation`
--
ALTER TABLE `type_evaluation`
  ADD PRIMARY KEY (`id_type_eval`);

--
-- Index pour la table `type_question`
--
ALTER TABLE `type_question`
  ADD PRIMARY KEY (`id_type_q`);

--
-- Index pour la table `utilisateur`
--
ALTER TABLE `utilisateur`
  ADD PRIMARY KEY (`id`),
  ADD KEY `FKijruopv9d1cum0wdekq4bdjhi` (`forfait`);

--
-- Index pour la table `verification_token`
--
ALTER TABLE `verification_token`
  ADD PRIMARY KEY (`id`),
  ADD KEY `FKk86b43hyrsarlu0vi15wepxaa` (`user_id`);

--
-- AUTO_INCREMENT pour les tables déchargées
--

--
-- AUTO_INCREMENT pour la table `utilisateur`
--
ALTER TABLE `utilisateur`
  MODIFY `id` bigint(20) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=7;

--
-- AUTO_INCREMENT pour la table `verification_token`
--
ALTER TABLE `verification_token`
  MODIFY `id` bigint(20) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=3;

--
-- Contraintes pour les tables déchargées
--

--
-- Contraintes pour la table `carte_mentale`
--
ALTER TABLE `carte_mentale`
  ADD CONSTRAINT `FK3yjpqnmt0wf1e65277mrnslaw` FOREIGN KEY (`question_id`) REFERENCES `question` (`id_question`);

--
-- Contraintes pour la table `categorie`
--
ALTER TABLE `categorie`
  ADD CONSTRAINT `FKpkogvmu8u986htma0ev8u5vol` FOREIGN KEY (`theme`) REFERENCES `theme` (`id_theme`);

--
-- Contraintes pour la table `envoi_message`
--
ALTER TABLE `envoi_message`
  ADD CONSTRAINT `FK1ek96h9p3x7caan2xsia4m0jk` FOREIGN KEY (`utilisateur`) REFERENCES `utilisateur` (`id`),
  ADD CONSTRAINT `FKi3bgpcypyvdrf2kddk306b93b` FOREIGN KEY (`mail`) REFERENCES `mail` (`id_mail`);

--
-- Contraintes pour la table `evaluation`
--
ALTER TABLE `evaluation`
  ADD CONSTRAINT `FKhdgpb3w5ouu8ci32evrjvjn8n` FOREIGN KEY (`type_evaluation`) REFERENCES `type_evaluation` (`id_type_eval`),
  ADD CONSTRAINT `FKif841hn2nkmlj23iy3w8o6lto` FOREIGN KEY (`apprenant`) REFERENCES `utilisateur` (`id`);

--
-- Contraintes pour la table `eval_quest_rep`
--
ALTER TABLE `eval_quest_rep`
  ADD CONSTRAINT `FKatib126k14cej63wq1tutjbcn` FOREIGN KEY (`question`) REFERENCES `question` (`id_question`),
  ADD CONSTRAINT `FKogh4mqv7anl1s6oyd59n5hyt8` FOREIGN KEY (`evaluation`) REFERENCES `evaluation` (`id_evaluation`);

--
-- Contraintes pour la table `fichier`
--
ALTER TABLE `fichier`
  ADD CONSTRAINT `FKdd3m5gyl0aw46dcuc38p9bf6t` FOREIGN KEY (`question_id`) REFERENCES `question` (`id_question`);

--
-- Contraintes pour la table `question`
--
ALTER TABLE `question`
  ADD CONSTRAINT `FK932c6hsdyrrb4ot9a3gy5engq` FOREIGN KEY (`categorie`) REFERENCES `categorie` (`id_categorie`),
  ADD CONSTRAINT `FKtb3ceqry12rjtkuq2vw0q40s7` FOREIGN KEY (`type_question`) REFERENCES `type_question` (`id_type_q`);

--
-- Contraintes pour la table `reponse`
--
ALTER TABLE `reponse`
  ADD CONSTRAINT `FKp0yq2hox7fyw8rpbhx9t514rt` FOREIGN KEY (`question_id`) REFERENCES `question` (`id_question`);

--
-- Contraintes pour la table `reponse_user`
--
ALTER TABLE `reponse_user`
  ADD CONSTRAINT `FKiaea98p00rw95tmalq82s31kw` FOREIGN KEY (`eval_id`) REFERENCES `eval_quest_rep` (`id`);

--
-- Contraintes pour la table `theme`
--
ALTER TABLE `theme`
  ADD CONSTRAINT `FKc0xupfd39m0vvbjbkiddylfmh` FOREIGN KEY (`utilisateur`) REFERENCES `utilisateur` (`id`);

--
-- Contraintes pour la table `utilisateur`
--
ALTER TABLE `utilisateur`
  ADD CONSTRAINT `FKijruopv9d1cum0wdekq4bdjhi` FOREIGN KEY (`forfait`) REFERENCES `forfait` (`id_forfait`);

--
-- Contraintes pour la table `verification_token`
--
ALTER TABLE `verification_token`
  ADD CONSTRAINT `FKk86b43hyrsarlu0vi15wepxaa` FOREIGN KEY (`user_id`) REFERENCES `utilisateur` (`id`);
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
