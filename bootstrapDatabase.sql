DROP TABLE IF EXISTS `social_events`;
CREATE TABLE `social_events` (
  `id` int NOT NULL AUTO_INCREMENT PRIMARY KEY,
  `user` varchar(20) NOT NULL,
  `author` varchar(20) NOT NULL,
  `content` text NOT NULL
);

INSERT INTO `social_events` (`author`, `user`, `content`) VALUES
  ('Joe','Gareth','Hey Gareth it\'s Joe'),
  ('Joe','Gareth','What\'s up?'),
  ('Gareth','Joe','Help!');
