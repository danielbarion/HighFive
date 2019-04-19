CREATE TABLE IF NOT EXISTS `character_ui_actions` (
  `charId` int(10) unsigned NOT NULL DEFAULT '0',
  `cat` tinyint(4) NOT NULL,
  `order` tinyint(4) NOT NULL,
  `cmd` int(8) NOT NULL DEFAULT '0',
  `key` int(8) NOT NULL,
  `tgKey1` int(8) DEFAULT NULL,
  `tgKey2` int(8) DEFAULT NULL,
  `show` tinyint(4) NOT NULL,
  PRIMARY KEY (`charId`,`cat`,`cmd`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;