CREATE TABLE IF NOT EXISTS `auction_watch` (
  `charObjId` INT NOT NULL DEFAULT 0,
  `auctionId` INT NOT NULL DEFAULT 0,
  PRIMARY KEY (`charObjId`,`auctionId`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;