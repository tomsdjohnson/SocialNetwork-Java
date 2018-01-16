DROP TABLE IF EXISTS `walls`;
CREATE TABLE `walls` (
  `user` varchar(20) NOT NULL,
  `author` varchar(20) NOT NULL,
  `content` text NOT NULL,
  KEY `user` (`user`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

INSERT INTO `walls` (`author`, `user`, `content`) VALUES
    ('rick','morty','Hey Morty it\'s rick'),
    ('rick','morty','What\'s up?'),
    ('morty','rick','Help!');
