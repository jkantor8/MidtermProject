-- MySQL Workbench Forward Engineering

SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0;
SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0;
SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='ONLY_FULL_GROUP_BY,STRICT_TRANS_TABLES,NO_ZERO_IN_DATE,NO_ZERO_DATE,ERROR_FOR_DIVISION_BY_ZERO,NO_ENGINE_SUBSTITUTION';

-- -----------------------------------------------------
-- Schema sportswapdb
-- -----------------------------------------------------
DROP SCHEMA IF EXISTS `sportswapdb` ;

-- -----------------------------------------------------
-- Schema sportswapdb
-- -----------------------------------------------------
CREATE SCHEMA IF NOT EXISTS `sportswapdb` DEFAULT CHARACTER SET utf8 ;
USE `sportswapdb` ;

-- -----------------------------------------------------
-- Table `address`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `address` ;

CREATE TABLE IF NOT EXISTS `address` (
  `id` INT NOT NULL AUTO_INCREMENT,
  `address` VARCHAR(45) NULL,
  `address2` VARCHAR(45) NULL,
  `city` VARCHAR(45) NULL,
  `state_province` CHAR(2) NULL,
  `postal_code` VARCHAR(45) NULL,
  `country_code` CHAR(3) NULL,
  PRIMARY KEY (`id`))
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `user`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `user` ;

CREATE TABLE IF NOT EXISTS `user` (
  `id` INT NOT NULL AUTO_INCREMENT,
  `username` VARCHAR(45) NOT NULL,
  `password` VARCHAR(45) NOT NULL,
  `active` TINYINT NOT NULL DEFAULT 1,
  `role` VARCHAR(45) NULL,
  `email` VARCHAR(45) NULL,
  `created` DATETIME NULL,
  `updated` DATETIME NULL,
  `deactivated` DATETIME NULL,
  `address_id` INT NOT NULL,
  PRIMARY KEY (`id`),
  UNIQUE INDEX `username_UNIQUE` (`username` ASC),
  INDEX `fk_user_address1_idx` (`address_id` ASC),
  CONSTRAINT `fk_user_address1`
    FOREIGN KEY (`address_id`)
    REFERENCES `address` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `age_group`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `age_group` ;

CREATE TABLE IF NOT EXISTS `age_group` (
  `id` INT NOT NULL AUTO_INCREMENT,
  `age` VARCHAR(45) NULL,
  PRIMARY KEY (`id`))
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `sport`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `sport` ;

CREATE TABLE IF NOT EXISTS `sport` (
  `id` INT NOT NULL AUTO_INCREMENT,
  `name` VARCHAR(45) NOT NULL,
  PRIMARY KEY (`id`))
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `item_condition`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `item_condition` ;

CREATE TABLE IF NOT EXISTS `item_condition` (
  `id` INT NOT NULL AUTO_INCREMENT,
  `name` VARCHAR(45) NOT NULL,
  PRIMARY KEY (`id`))
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `item`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `item` ;

CREATE TABLE IF NOT EXISTS `item` (
  `id` INT NOT NULL AUTO_INCREMENT,
  `name` VARCHAR(100) NOT NULL,
  `description` VARCHAR(1000) NULL,
  `image_url` VARCHAR(2000) NULL,
  `gender` VARCHAR(45) NULL,
  `brand` VARCHAR(45) NULL,
  `active` TINYINT NOT NULL DEFAULT 1,
  `created` DATETIME NULL,
  `updated` DATETIME NULL,
  `deactivated` DATETIME NULL,
  `user_id` INT NOT NULL,
  `age_group_id` INT NOT NULL,
  `sport_id` INT NOT NULL,
  `item_condition_id` INT NOT NULL,
  PRIMARY KEY (`id`),
  INDEX `fk_item_user1_idx` (`user_id` ASC),
  INDEX `fk_item_age_group1_idx` (`age_group_id` ASC),
  INDEX `fk_item_sport1_idx` (`sport_id` ASC),
  INDEX `fk_item_item_condition1_idx` (`item_condition_id` ASC),
  CONSTRAINT `fk_item_user1`
    FOREIGN KEY (`user_id`)
    REFERENCES `user` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_item_age_group1`
    FOREIGN KEY (`age_group_id`)
    REFERENCES `age_group` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_item_sport1`
    FOREIGN KEY (`sport_id`)
    REFERENCES `sport` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_item_item_condition1`
    FOREIGN KEY (`item_condition_id`)
    REFERENCES `item_condition` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `sale_listing`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `sale_listing` ;

CREATE TABLE IF NOT EXISTS `sale_listing` (
  `id` INT NOT NULL AUTO_INCREMENT,
  `title` VARCHAR(100) NOT NULL,
  `description` VARCHAR(1000) NOT NULL,
  `price` VARCHAR(45) NOT NULL,
  `active` TINYINT NOT NULL DEFAULT 1,
  `created` DATETIME NULL,
  `updated` DATETIME NULL,
  `deactivated` DATETIME NULL,
  `user_id` INT NOT NULL,
  `item_id` INT NOT NULL,
  PRIMARY KEY (`id`),
  INDEX `fk_sale_listing_user1_idx` (`user_id` ASC),
  INDEX `fk_sale_listing_item1_idx` (`item_id` ASC),
  CONSTRAINT `fk_sale_listing_user1`
    FOREIGN KEY (`user_id`)
    REFERENCES `user` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_sale_listing_item1`
    FOREIGN KEY (`item_id`)
    REFERENCES `item` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `donation_listing`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `donation_listing` ;

CREATE TABLE IF NOT EXISTS `donation_listing` (
  `id` INT NOT NULL AUTO_INCREMENT,
  `title` VARCHAR(100) NOT NULL,
  `description` VARCHAR(1000) NOT NULL,
  `is_request` TINYINT NULL DEFAULT 0,
  `active` TINYINT NOT NULL DEFAULT 1,
  `created` DATETIME NULL,
  `updated` DATETIME NULL,
  `deactivated` DATETIME NULL,
  `event_start` DATETIME NULL,
  `event_end` DATETIME NULL,
  `user_id` INT NOT NULL,
  `address_id` INT NOT NULL,
  PRIMARY KEY (`id`),
  INDEX `fk_donation_listing_user1_idx` (`user_id` ASC),
  INDEX `fk_donation_listing_address1_idx` (`address_id` ASC),
  CONSTRAINT `fk_donation_listing_user1`
    FOREIGN KEY (`user_id`)
    REFERENCES `user` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_donation_listing_address1`
    FOREIGN KEY (`address_id`)
    REFERENCES `address` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `swap_listing`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `swap_listing` ;

CREATE TABLE IF NOT EXISTS `swap_listing` (
  `id` INT NOT NULL AUTO_INCREMENT,
  `title` VARCHAR(100) NOT NULL,
  `description` VARCHAR(1000) NOT NULL,
  `active` TINYINT NULL,
  `created` DATETIME NULL,
  `updated` DATETIME NULL,
  `deactivated` DATETIME NULL,
  `user_id` INT NOT NULL,
  `address_id` INT NOT NULL,
  PRIMARY KEY (`id`),
  INDEX `fk_swap_listing_user1_idx` (`user_id` ASC),
  INDEX `fk_swap_listing_address1_idx` (`address_id` ASC),
  CONSTRAINT `fk_swap_listing_user1`
    FOREIGN KEY (`user_id`)
    REFERENCES `user` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_swap_listing_address1`
    FOREIGN KEY (`address_id`)
    REFERENCES `address` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `post`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `post` ;

CREATE TABLE IF NOT EXISTS `post` (
  `id` INT NOT NULL AUTO_INCREMENT,
  `comment` VARCHAR(300) NULL,
  `created` DATETIME NULL,
  `updated` DATETIME NULL,
  `deactivated` DATETIME NULL,
  `donation_listing_id` INT NULL,
  `sale_listing_id` INT NULL,
  `swap_listing_id` INT NULL,
  `in_reply_to_id` INT NULL,
  `active` TINYINT NOT NULL DEFAULT 1,
  `user_id` INT NOT NULL,
  PRIMARY KEY (`id`),
  INDEX `fk_post_user1_idx` (`user_id` ASC),
  INDEX `fk_post_donation_listing1_idx` (`donation_listing_id` ASC),
  INDEX `fk_post_sale_listing1_idx` (`sale_listing_id` ASC),
  INDEX `fk_post_swap_listing1_idx` (`swap_listing_id` ASC),
  INDEX `fk_post_post1_idx` (`in_reply_to_id` ASC),
  CONSTRAINT `fk_post_user1`
    FOREIGN KEY (`user_id`)
    REFERENCES `user` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_post_donation_listing1`
    FOREIGN KEY (`donation_listing_id`)
    REFERENCES `donation_listing` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_post_sale_listing1`
    FOREIGN KEY (`sale_listing_id`)
    REFERENCES `sale_listing` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_post_swap_listing1`
    FOREIGN KEY (`swap_listing_id`)
    REFERENCES `swap_listing` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_post_post1`
    FOREIGN KEY (`in_reply_to_id`)
    REFERENCES `post` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `donation_listing_has_item`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `donation_listing_has_item` ;

CREATE TABLE IF NOT EXISTS `donation_listing_has_item` (
  `donation_listing_id` INT NOT NULL,
  `item_id` INT NOT NULL,
  PRIMARY KEY (`donation_listing_id`, `item_id`),
  INDEX `fk_donation_listing_has_item_item1_idx` (`item_id` ASC),
  INDEX `fk_donation_listing_has_item_donation_listing1_idx` (`donation_listing_id` ASC),
  CONSTRAINT `fk_donation_listing_has_item_donation_listing1`
    FOREIGN KEY (`donation_listing_id`)
    REFERENCES `donation_listing` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_donation_listing_has_item_item1`
    FOREIGN KEY (`item_id`)
    REFERENCES `item` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `swap_listing_has_sport`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `swap_listing_has_sport` ;

CREATE TABLE IF NOT EXISTS `swap_listing_has_sport` (
  `swap_listing_id` INT NOT NULL,
  `sport_id` INT NOT NULL,
  PRIMARY KEY (`swap_listing_id`, `sport_id`),
  INDEX `fk_swap_listing_has_sport_sport1_idx` (`sport_id` ASC),
  INDEX `fk_swap_listing_has_sport_swap_listing1_idx` (`swap_listing_id` ASC),
  CONSTRAINT `fk_swap_listing_has_sport_swap_listing1`
    FOREIGN KEY (`swap_listing_id`)
    REFERENCES `swap_listing` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_swap_listing_has_sport_sport1`
    FOREIGN KEY (`sport_id`)
    REFERENCES `sport` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `swap_listing_has_item`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `swap_listing_has_item` ;

CREATE TABLE IF NOT EXISTS `swap_listing_has_item` (
  `swap_listing_id` INT NOT NULL,
  `item_id` INT NOT NULL,
  PRIMARY KEY (`swap_listing_id`, `item_id`),
  INDEX `fk_swap_listing_has_item_item1_idx` (`item_id` ASC),
  INDEX `fk_swap_listing_has_item_swap_listing1_idx` (`swap_listing_id` ASC),
  CONSTRAINT `fk_swap_listing_has_item_swap_listing1`
    FOREIGN KEY (`swap_listing_id`)
    REFERENCES `swap_listing` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_swap_listing_has_item_item1`
    FOREIGN KEY (`item_id`)
    REFERENCES `item` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `donation_listing_has_sport`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `donation_listing_has_sport` ;

CREATE TABLE IF NOT EXISTS `donation_listing_has_sport` (
  `donation_listing_id` INT NOT NULL,
  `sport_id` INT NOT NULL,
  PRIMARY KEY (`donation_listing_id`, `sport_id`),
  INDEX `fk_donation_listing_has_sport_sport1_idx` (`sport_id` ASC),
  INDEX `fk_donation_listing_has_sport_donation_listing1_idx` (`donation_listing_id` ASC),
  CONSTRAINT `fk_donation_listing_has_sport_donation_listing1`
    FOREIGN KEY (`donation_listing_id`)
    REFERENCES `donation_listing` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_donation_listing_has_sport_sport1`
    FOREIGN KEY (`sport_id`)
    REFERENCES `sport` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `user_has_favorite_sport`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `user_has_favorite_sport` ;

CREATE TABLE IF NOT EXISTS `user_has_favorite_sport` (
  `user_id` INT NOT NULL,
  `sport_id` INT NOT NULL,
  PRIMARY KEY (`user_id`, `sport_id`),
  INDEX `fk_user_has_sport_sport1_idx` (`sport_id` ASC),
  INDEX `fk_user_has_sport_user1_idx` (`user_id` ASC),
  CONSTRAINT `fk_user_has_sport_user1`
    FOREIGN KEY (`user_id`)
    REFERENCES `user` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_user_has_sport_sport1`
    FOREIGN KEY (`sport_id`)
    REFERENCES `sport` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `message`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `message` ;

CREATE TABLE IF NOT EXISTS `message` (
  `id` INT NOT NULL AUTO_INCREMENT,
  `subject` VARCHAR(100) NULL,
  `content` VARCHAR(2000) NULL,
  `created` DATETIME NULL,
  `deactivated` DATETIME NULL,
  `sender_id` INT NOT NULL,
  `receiver_id` INT NOT NULL,
  PRIMARY KEY (`id`),
  INDEX `fk_message_user1_idx` (`sender_id` ASC),
  INDEX `fk_message_user2_idx` (`receiver_id` ASC),
  CONSTRAINT `fk_message_user1`
    FOREIGN KEY (`sender_id`)
    REFERENCES `user` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_message_user2`
    FOREIGN KEY (`receiver_id`)
    REFERENCES `user` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;

SET SQL_MODE = '';
DROP USER IF EXISTS topswap@localhost;
SET SQL_MODE='ONLY_FULL_GROUP_BY,STRICT_TRANS_TABLES,NO_ZERO_IN_DATE,NO_ZERO_DATE,ERROR_FOR_DIVISION_BY_ZERO,NO_ENGINE_SUBSTITUTION';
CREATE USER 'topswap'@'localhost' IDENTIFIED BY 'topswap1';

GRANT SELECT, INSERT, TRIGGER, UPDATE, DELETE ON TABLE * TO 'topswap'@'localhost';

SET SQL_MODE=@OLD_SQL_MODE;
SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS;
SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS;

-- -----------------------------------------------------
-- Data for table `address`
-- -----------------------------------------------------
START TRANSACTION;
USE `sportswapdb`;
INSERT INTO `address` (`id`, `address`, `address2`, `city`, `state_province`, `postal_code`, `country_code`) VALUES (1, '2929 Beach St', NULL, 'Mendota Heights', 'MN', '55555', 'US');
INSERT INTO `address` (`id`, `address`, `address2`, `city`, `state_province`, `postal_code`, `country_code`) VALUES (2, '111 48th Ave N', 'Apt 202', 'St. Paul', 'MN', '55551', 'US');
INSERT INTO `address` (`id`, `address`, `address2`, `city`, `state_province`, `postal_code`, `country_code`) VALUES (3, '5050 9th St', NULL, 'Des Moines', 'IA', '23425', 'US');
INSERT INTO `address` (`id`, `address`, `address2`, `city`, `state_province`, `postal_code`, `country_code`) VALUES (4, '321 Rockwell Ave', 'Unit 89', 'Springfield', 'IL', '43212', 'US');
INSERT INTO `address` (`id`, `address`, `address2`, `city`, `state_province`, `postal_code`, `country_code`) VALUES (5, '1212 Curry Street', NULL, 'Chicago', 'IL', '23411', 'US');
INSERT INTO `address` (`id`, `address`, `address2`, `city`, `state_province`, `postal_code`, `country_code`) VALUES (6, '1200 W. 78th St.', NULL, 'Denver', 'CO', '78787', 'US');
INSERT INTO `address` (`id`, `address`, `address2`, `city`, `state_province`, `postal_code`, `country_code`) VALUES (7, '900 Westwood Terrace', NULL, 'Colorado Springs', 'CO', '67676', 'US');
INSERT INTO `address` (`id`, `address`, `address2`, `city`, `state_province`, `postal_code`, `country_code`) VALUES (8, '1234 Imaginary Ave.', NULL, 'Cheyenne', 'WY', '90887', 'US');
INSERT INTO `address` (`id`, `address`, `address2`, `city`, `state_province`, `postal_code`, `country_code`) VALUES (9, '345 WackaWacka Ave.', NULL, 'Miami', 'FL', '78678', 'US');
INSERT INTO `address` (`id`, `address`, `address2`, `city`, `state_province`, `postal_code`, `country_code`) VALUES (10, '111 Pool', 'Apt 10', 'Richfield', 'NE', '23123', 'US');

COMMIT;


-- -----------------------------------------------------
-- Data for table `user`
-- -----------------------------------------------------
START TRANSACTION;
USE `sportswapdb`;
INSERT INTO `user` (`id`, `username`, `password`, `active`, `role`, `email`, `created`, `updated`, `deactivated`, `address_id`) VALUES (1, 'admin', 'admin1', 1, 'ADMIN', NULL, NULL, NULL, NULL, 1);
INSERT INTO `user` (`id`, `username`, `password`, `active`, `role`, `email`, `created`, `updated`, `deactivated`, `address_id`) VALUES (2, 'Bobafet1', 'starwars', 1, 'ACTIVE_USER', 'bobafet1@gmail.com', NULL, NULL, NULL, 2);
INSERT INTO `user` (`id`, `username`, `password`, `active`, `role`, `email`, `created`, `updated`, `deactivated`, `address_id`) VALUES (3, 'rugbyfan78', 'rugbyrules', 1, 'ACTIVE_USER', 'rugbyfan78@usarugby.com', NULL, NULL, NULL, 3);
INSERT INTO `user` (`id`, `username`, `password`, `active`, `role`, `email`, `created`, `updated`, `deactivated`, `address_id`) VALUES (4, 'blueJacketsyay', 'bluejackets', 1, 'ACTIVE_USER', 'bluejacketsyay@gmail.com', NULL, NULL, NULL, 4);
INSERT INTO `user` (`id`, `username`, `password`, `active`, `role`, `email`, `created`, `updated`, `deactivated`, `address_id`) VALUES (5, 'bobjohnson', 'greatdayforhockey', 1, 'ACTIVE_USER', 'bobjo@hockey.com', NULL, NULL, NULL, 5);
INSERT INTO `user` (`id`, `username`, `password`, `active`, `role`, `email`, `created`, `updated`, `deactivated`, `address_id`) VALUES (6, 'monstermash', 'monster', 1, 'ACTIVE_USER', 'monstersz@yahoo.com', NULL, NULL, NULL, 7);

COMMIT;


-- -----------------------------------------------------
-- Data for table `age_group`
-- -----------------------------------------------------
START TRANSACTION;
USE `sportswapdb`;
INSERT INTO `age_group` (`id`, `age`) VALUES (1, 'YOUTH');
INSERT INTO `age_group` (`id`, `age`) VALUES (2, 'INTERMEDIATE');
INSERT INTO `age_group` (`id`, `age`) VALUES (3, 'ADULT');
INSERT INTO `age_group` (`id`, `age`) VALUES (4, 'N/A');

COMMIT;


-- -----------------------------------------------------
-- Data for table `sport`
-- -----------------------------------------------------
START TRANSACTION;
USE `sportswapdb`;
INSERT INTO `sport` (`id`, `name`) VALUES (1, 'Boxing');
INSERT INTO `sport` (`id`, `name`) VALUES (2, 'Hockey');
INSERT INTO `sport` (`id`, `name`) VALUES (3, 'Golf');
INSERT INTO `sport` (`id`, `name`) VALUES (4, 'Football');
INSERT INTO `sport` (`id`, `name`) VALUES (5, 'Soccer');
INSERT INTO `sport` (`id`, `name`) VALUES (6, 'Curling');
INSERT INTO `sport` (`id`, `name`) VALUES (7, 'Baseball');
INSERT INTO `sport` (`id`, `name`) VALUES (8, 'Softball');
INSERT INTO `sport` (`id`, `name`) VALUES (9, 'Basketball');
INSERT INTO `sport` (`id`, `name`) VALUES (10, 'Weightlifting');
INSERT INTO `sport` (`id`, `name`) VALUES (11, 'Track and Field');
INSERT INTO `sport` (`id`, `name`) VALUES (12, 'Wrestling');
INSERT INTO `sport` (`id`, `name`) VALUES (13, 'Bicycling');
INSERT INTO `sport` (`id`, `name`) VALUES (14, 'Swimming');

COMMIT;


-- -----------------------------------------------------
-- Data for table `item_condition`
-- -----------------------------------------------------
START TRANSACTION;
USE `sportswapdb`;
INSERT INTO `item_condition` (`id`, `name`) VALUES (1, 'NEW');
INSERT INTO `item_condition` (`id`, `name`) VALUES (2, 'LIGHTLY USED');
INSERT INTO `item_condition` (`id`, `name`) VALUES (3, 'USED');
INSERT INTO `item_condition` (`id`, `name`) VALUES (4, 'HEAVILY USED');

COMMIT;


-- -----------------------------------------------------
-- Data for table `item`
-- -----------------------------------------------------
START TRANSACTION;
USE `sportswapdb`;
INSERT INTO `item` (`id`, `name`, `description`, `image_url`, `gender`, `brand`, `active`, `created`, `updated`, `deactivated`, `user_id`, `age_group_id`, `sport_id`, `item_condition_id`) VALUES (1, 'Golf Clubs', 'These clubs are practically new. Adult sized set with nice bag. Golf cart not included.', 'https://xanimal37.github.io/img/golfclubs.jpg', 'N/A', 'Titleist', 1, NULL, NULL, NULL, 2, 3, 3, 1);
INSERT INTO `item` (`id`, `name`, `description`, `image_url`, `gender`, `brand`, `active`, `created`, `updated`, `deactivated`, `user_id`, `age_group_id`, `sport_id`, `item_condition_id`) VALUES (2, 'Hockey Stick', 'almost new!', 'https://xanimal37.github.io/img/hockeystick.jpg', 'N/A', 'Bauer', 1, NULL, NULL, NULL, 2, 2, 2, 1);
INSERT INTO `item` (`id`, `name`, `description`, `image_url`, `gender`, `brand`, `active`, `created`, `updated`, `deactivated`, `user_id`, `age_group_id`, `sport_id`, `item_condition_id`) VALUES (3, 'Swim Goggles', 'pretty used but they work', 'https://xanimal37.github.io/img/goggles.jpg', 'N/A', 'Google', 1, NULL, NULL, NULL, 3, 1, 14, 3);
INSERT INTO `item` (`id`, `name`, `description`, `image_url`, `gender`, `brand`, `active`, `created`, `updated`, `deactivated`, `user_id`, `age_group_id`, `sport_id`, `item_condition_id`) VALUES (4, 'baseball bat', 'kind beat up but good for hitting things. ', 'https://xanimal37.github.io/img/bat.jpg', 'N/A', 'Wilson', 1, NULL, NULL, NULL, 3, 4, 7, 4);
INSERT INTO `item` (`id`, `name`, `description`, `image_url`, `gender`, `brand`, `active`, `created`, `updated`, `deactivated`, `user_id`, `age_group_id`, `sport_id`, `item_condition_id`) VALUES (5, 'shoulder pads', 'for playing football. would be good for a DB or CB', 'https://xanimal37.github.io/img/shoulderpads.jpg', 'Male', 'Warrior', 1, NULL, NULL, NULL, 4, 2, 4, 2);
INSERT INTO `item` (`id`, `name`, `description`, `image_url`, `gender`, `brand`, `active`, `created`, `updated`, `deactivated`, `user_id`, `age_group_id`, `sport_id`, `item_condition_id`) VALUES (6, 'golf ball', 'found this in a pond. pretty scuffed up', 'https://xanimal37.github.io/img/golfball.jpg', 'N/A', 'Titleist', 1, NULL, NULL, NULL, 5, 4, 3, 4);
INSERT INTO `item` (`id`, `name`, `description`, `image_url`, `gender`, `brand`, `active`, `created`, `updated`, `deactivated`, `user_id`, `age_group_id`, `sport_id`, `item_condition_id`) VALUES (7, 'blue breezers', 'These breezers are lightly used. They are royal blue and sized for females.', 'https://xanimal37.github.io/img/breezers.jpg', 'Female', 'CCM', 1, NULL, NULL, NULL, 6, 3, 2, 2);

COMMIT;


-- -----------------------------------------------------
-- Data for table `sale_listing`
-- -----------------------------------------------------
START TRANSACTION;
USE `sportswapdb`;
INSERT INTO `sale_listing` (`id`, `title`, `description`, `price`, `active`, `created`, `updated`, `deactivated`, `user_id`, `item_id`) VALUES (1, 'nice set of clubs!', 'this is a really nice set of clubs. They are practically new but I had to quit playing.', '189.00', 1, NULL, NULL, NULL, 2, 1);
INSERT INTO `sale_listing` (`id`, `title`, `description`, `price`, `active`, `created`, `updated`, `deactivated`, `user_id`, `item_id`) VALUES (2, 'football shoulder pads', 'My kid quit football. These pads are pretty nice and selling for half what I bought them for!', '56.00', 1, NULL, NULL, NULL, 4, 5);

COMMIT;


-- -----------------------------------------------------
-- Data for table `donation_listing`
-- -----------------------------------------------------
START TRANSACTION;
USE `sportswapdb`;
INSERT INTO `donation_listing` (`id`, `title`, `description`, `is_request`, `active`, `created`, `updated`, `deactivated`, `event_start`, `event_end`, `user_id`, `address_id`) VALUES (1, 'seeking youth hockey gear!', 'Hello! As you know, hockey equipment is very expensive. We are collecting gear for local kids Mite and Squirt age. We have a collection box at So and so Church.', 1, 1, NULL, NULL, NULL, NULL, NULL, 6, 10);
INSERT INTO `donation_listing` (`id`, `title`, `description`, `is_request`, `active`, `created`, `updated`, `deactivated`, `event_start`, `event_end`, `user_id`, `address_id`) VALUES (2, 'free golf ball!', 'Who wants it!?!?!', 0, 1, NULL, NULL, NULL, NULL, NULL, 5, 9);

COMMIT;


-- -----------------------------------------------------
-- Data for table `swap_listing`
-- -----------------------------------------------------
START TRANSACTION;
USE `sportswapdb`;
INSERT INTO `swap_listing` (`id`, `title`, `description`, `active`, `created`, `updated`, `deactivated`, `user_id`, `address_id`) VALUES (1, 'hockey stick trade?', 'I got this stick in a lefty but I need a righty. I can\'t return it. Anyone want to trade?', 1, NULL, NULL, NULL, 2, 6);
INSERT INTO `swap_listing` (`id`, `title`, `description`, `active`, `created`, `updated`, `deactivated`, `user_id`, `address_id`) VALUES (2, 'looking for swim trunks', 'anyone want these goggles? I will trade for a pair of swim trunks', 1, NULL, NULL, NULL, 3, 7);

COMMIT;


-- -----------------------------------------------------
-- Data for table `post`
-- -----------------------------------------------------
START TRANSACTION;
USE `sportswapdb`;
INSERT INTO `post` (`id`, `comment`, `created`, `updated`, `deactivated`, `donation_listing_id`, `sale_listing_id`, `swap_listing_id`, `in_reply_to_id`, `active`, `user_id`) VALUES (1, 'Hi, where can I pick up this nice golf ball?', NULL, NULL, NULL, 2, NULL, NULL, NULL, 1, 4);
INSERT INTO `post` (`id`, `comment`, `created`, `updated`, `deactivated`, `donation_listing_id`, `sale_listing_id`, `swap_listing_id`, `in_reply_to_id`, `active`, `user_id`) VALUES (2, 'Hi, would you take $40?', NULL, NULL, NULL, NULL, 2, NULL, NULL, 1, 6);
INSERT INTO `post` (`id`, `comment`, `created`, `updated`, `deactivated`, `donation_listing_id`, `sale_listing_id`, `swap_listing_id`, `in_reply_to_id`, `active`, `user_id`) VALUES (3, 'Hey i have some swim trunks. Do you like yellow?', NULL, NULL, NULL, NULL, NULL, 2, NULL, 1, 5);

COMMIT;


-- -----------------------------------------------------
-- Data for table `donation_listing_has_item`
-- -----------------------------------------------------
START TRANSACTION;
USE `sportswapdb`;
INSERT INTO `donation_listing_has_item` (`donation_listing_id`, `item_id`) VALUES (1, 2);
INSERT INTO `donation_listing_has_item` (`donation_listing_id`, `item_id`) VALUES (2, 6);

COMMIT;


-- -----------------------------------------------------
-- Data for table `swap_listing_has_sport`
-- -----------------------------------------------------
START TRANSACTION;
USE `sportswapdb`;
INSERT INTO `swap_listing_has_sport` (`swap_listing_id`, `sport_id`) VALUES (1, 2);
INSERT INTO `swap_listing_has_sport` (`swap_listing_id`, `sport_id`) VALUES (2, 14);

COMMIT;


-- -----------------------------------------------------
-- Data for table `swap_listing_has_item`
-- -----------------------------------------------------
START TRANSACTION;
USE `sportswapdb`;
INSERT INTO `swap_listing_has_item` (`swap_listing_id`, `item_id`) VALUES (1, 2);
INSERT INTO `swap_listing_has_item` (`swap_listing_id`, `item_id`) VALUES (2, 3);

COMMIT;


-- -----------------------------------------------------
-- Data for table `donation_listing_has_sport`
-- -----------------------------------------------------
START TRANSACTION;
USE `sportswapdb`;
INSERT INTO `donation_listing_has_sport` (`donation_listing_id`, `sport_id`) VALUES (1, 2);
INSERT INTO `donation_listing_has_sport` (`donation_listing_id`, `sport_id`) VALUES (2, 3);

COMMIT;


-- -----------------------------------------------------
-- Data for table `user_has_favorite_sport`
-- -----------------------------------------------------
START TRANSACTION;
USE `sportswapdb`;
INSERT INTO `user_has_favorite_sport` (`user_id`, `sport_id`) VALUES (1, 1);
INSERT INTO `user_has_favorite_sport` (`user_id`, `sport_id`) VALUES (2, 7);
INSERT INTO `user_has_favorite_sport` (`user_id`, `sport_id`) VALUES (3, 2);
INSERT INTO `user_has_favorite_sport` (`user_id`, `sport_id`) VALUES (4, 4);
INSERT INTO `user_has_favorite_sport` (`user_id`, `sport_id`) VALUES (5, 2);
INSERT INTO `user_has_favorite_sport` (`user_id`, `sport_id`) VALUES (6, 12);

COMMIT;


-- -----------------------------------------------------
-- Data for table `message`
-- -----------------------------------------------------
START TRANSACTION;
USE `sportswapdb`;
INSERT INTO `message` (`id`, `subject`, `content`, `created`, `deactivated`, `sender_id`, `receiver_id`) VALUES (1, 'Question', 'Hi, how do I add a post to my swap?', NULL, NULL, 3, 1);
INSERT INTO `message` (`id`, `subject`, `content`, `created`, `deactivated`, `sender_id`, `receiver_id`) VALUES (2, 'What do you think?', 'Do you think Bauer breezers are any good? Or do you prefer Warrior or CCM?', NULL, NULL, 5, 4);

COMMIT;

