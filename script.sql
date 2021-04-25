CREATE SCHEMA `hibernate` DEFAULT CHARACTER SET utf8 ;

use hibernate;

DROP TABLE IF EXISTS teams;

CREATE TABLE teams (
                       team_id			int NOT NULL AUTO_INCREMENT,
                       team_name		varchar(50) NOT NULL,
                       PRIMARY KEY (team_id)
)ENGINE=InnoDB  DEFAULT CHARSET= utf8mb4;

DROP TABLE IF EXISTS users;

CREATE TABLE users (
                       user_id			int NOT NULL AUTO_INCREMENT,
                       first_name		varchar(50) NOT NULL,
                       last_name		varchar(50) NOT NULL,
                       age				int	NOT NULL,
                       team_id			int NOT NULL,
                       PRIMARY KEY (user_id),
                       CONSTRAINT FK_USER_TEAM FOREIGN KEY (team_id)
                           REFERENCES teams (team_id)
)ENGINE=InnoDB  DEFAULT CHARSET= utf8mb4;

DROP TABLE IF EXISTS roles;

CREATE TABLE roles (
                       role_id			int NOT NULL AUTO_INCREMENT,
                       role_name		varchar(30) NOT NULL,
                       PRIMARY KEY (role_id)
)ENGINE=InnoDB  DEFAULT CHARSET= utf8mb4;

DROP TABLE IF EXISTS users_roles;

CREATE TABLE users_roles (
                             user_id			int NOT NULL,
                             role_id			int NOT NULL,
                             PRIMARY KEY (user_id, role_id),

                             CONSTRAINT FK_USER_ROLE FOREIGN KEY (user_id)
                                 REFERENCES roles (role_id)
                                 ON DELETE NO ACTION ON UPDATE NO ACTION,

                             CONSTRAINT FK_ROLE_USER FOREIGN KEY (role_id)
                                 REFERENCES users (user_id)
                                 ON DELETE NO ACTION ON UPDATE NO ACTION
)ENGINE=InnoDB  DEFAULT CHARSET= utf8mb4;



INSERT INTO `hibernate`.`teams`(`team_id`,`team_name`) VALUES (1,'cola');
INSERT INTO `hibernate`.`teams`(`team_id`,`team_name`) VALUES (2,'pepsi');
INSERT INTO `hibernate`.`roles` (`role_id`,`role_name`) values(1,'admin');
INSERT INTO `hibernate`.`roles` (`role_id`,`role_name`) values(2,'user');

SET FOREIGN_KEY_CHECKS = 0;

INSERT INTO `hibernate`.`users` (`user_id`,`first_name`,`last_name`, `age`, `team_id`) values(1,'Grzegorz', 'Pietrzyk', 28, 1);
INSERT INTO `hibernate`.`users_roles`(`user_id`,`role_id`) VALUES (1,1);

INSERT INTO `hibernate`.`users` (`user_id`,`first_name`,`last_name`, `age`, `team_id`) values(2,'Andrzej', 'Pałka', 28, 2);
INSERT INTO `hibernate`.`users_roles`(`user_id`,`role_id`) VALUES (2,2);

INSERT INTO `hibernate`.`users` (`user_id`,`first_name`,`last_name`, `age`, `team_id`) values(3,'Jozek', 'Bozek', 48, 2);
INSERT INTO `hibernate`.`users_roles`(`user_id`,`role_id`) VALUES (3,2);

INSERT INTO `hibernate`.`users` (`user_id`,`first_name`,`last_name`, `age`, `team_id`) values(4,'Andrzej', 'Niedzielan', 40, 1);
INSERT INTO `hibernate`.`users_roles`(`user_id`,`role_id`) VALUES (4,2);

INSERT INTO `hibernate`.`users` (`user_id`,`first_name`,`last_name`, `age`, `team_id`) values(5,'Kasia', 'Smutniak', 35, 1);
INSERT INTO `hibernate`.`users_roles`(`user_id`,`role_id`) VALUES (5,2);

SET FOREIGN_KEY_CHECKS = 1;
