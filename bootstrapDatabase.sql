DROP TABLE IF EXISTS `walls`;
DROP TABLE IF EXISTS `users`;

CREATE TABLE `users` (
  `username` varchar(20) NOT NULL,
  `fullname` varchar(128) NOT NULL,
  `password` varchar(128) NOT NULL,
  PRIMARY KEY `username` (`username`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

CREATE TABLE `walls` (
  `username` varchar(20) NOT NULL,
  `author` varchar(20) NOT NULL,
  `content` text NOT NULL,
  FOREIGN KEY (`username`) REFERENCES `users`(`username`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

INSERT INTO `users` VALUES
    ('rick', 'Rick Sanchez', 'password'),
    ('morty', 'Morty Smith', 'password');

INSERT INTO `walls` (`author`, `username`, `content`) VALUES
    ('rick','morty','Hey Morty it\'s rick'),
    ('rick','morty','What\'s up?'),
    ('morty','rick','Help!');
