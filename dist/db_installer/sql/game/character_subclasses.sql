CREATE TABLE IF NOT EXISTS `character_subclasses` (
  `charId` INT UNSIGNED NOT NULL DEFAULT 0,
  `class_id` int(2) NOT NULL DEFAULT 0,
  `exp` bigint(20) NOT NULL DEFAULT 0,
  `sp` bigint(10) NOT NULL DEFAULT 0,
  `level` int(2) NOT NULL DEFAULT 40,
  `class_index` int(1) NOT NULL DEFAULT 0,
  PRIMARY KEY (`charId`,`class_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;