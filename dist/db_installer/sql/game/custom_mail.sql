CREATE TABLE IF NOT EXISTS `custom_mail` (
  `date` TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP,
  `receiver` INT UNSIGNED NOT NULL DEFAULT 0,
  `subject` TINYTEXT NOT NULL,
  `message` TEXT NOT NULL,
  `items` TEXT NOT NULL -- format: itemId1 count1;itemId2 count2;itemId3 count3...
) ENGINE=InnoDB DEFAULT CHARSET=utf8;