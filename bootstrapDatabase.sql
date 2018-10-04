DROP TABLE IF EXISTS `social_events`;
Drop TABLE IF EXISTS `users`;

CREATE TABLE `users` (
  `id` int NOT NULL AUTO_INCREMENT PRIMARY KEY,
  `username` varchar(20) NOT NULL,
  `fullName` varchar(20) NOT NULL,
  `password` varchar(20) NOT NULL,
);

CREATE TABLE `social_events` (
  `id` int NOT NULL AUTO_INCREMENT PRIMARY KEY,
  `user` int(11) NOT NULL FOREIGN KEY REFERENCES users(id),
  `author` int(11) NOT NULL FOREIGN KEY REFERENCES users(id),
  `content` text NOT NULL
);


INSERT INTO `users` (`username`, `fullName`,`password`) VALUES
  ('Joe','titus','efef'),
  ('Gareth','titus','efef'),
  ;

INSERT INTO `social_events` (`author`, `user`, `content`) VALUES
  ((SELECT id FROM users where username = `Joe`),(SELECT id FROM users where username = `Gareth`),'Hey Gareth it\'s Joe')),
  ('Joe','Gareth','What\'s up?'),
  ('Gareth','Joe','Help!');

