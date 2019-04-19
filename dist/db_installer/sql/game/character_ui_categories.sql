CREATE TABLE IF NOT EXISTS `character_ui_categories` (
  `charId` int(10) unsigned NOT NULL DEFAULT '0',
  `catId` tinyint(4) NOT NULL,
  `order` tinyint(4) NOT NULL,
  `cmdId` int(8) NOT NULL DEFAULT '0',
  PRIMARY KEY (`charId`,`catId`,`order`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;