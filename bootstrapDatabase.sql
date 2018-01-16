DROP TABLE IF EXISTS `social_events`;
DROP TABLE IF EXISTS `users`;

CREATE TABLE `users` (
  `username` varchar(20) NOT NULL PRIMARY KEY,
  `fullname` varchar(128) NOT NULL,
  `password` varchar(128) NOT NULL
);

CREATE TABLE `social_events` (
  `id` int NOT NULL AUTO_INCREMENT PRIMARY KEY,
  `user` varchar(20) NOT NULL,
  `author` varchar(20) NOT NULL,
  `content` text NOT NULL,
  FOREIGN KEY (`user`) REFERENCES `users`(`username`),
  FOREIGN KEY (`author`) REFERENCES `users`(`username`)
);

INSERT INTO `users` VALUES
    ('Joe', 'Joe Fooble', 'password'),
    ('Gareth', 'Gareth Barble', 'password');

INSERT INTO `social_events` (`author`, `user`, `content`) VALUES
  ('Joe','Gareth','Hey Gareth it\'s Joe'),
  ('Joe','Gareth','What\'s up?'),
  ('Gareth','Joe','Help!');
