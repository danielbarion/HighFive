CREATE TABLE IF NOT EXISTS `item_attributes` (
  `itemId` int(11) NOT NULL DEFAULT 0,
  `augAttributes` int(11) NOT NULL DEFAULT -1,
  PRIMARY KEY (`itemId`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;