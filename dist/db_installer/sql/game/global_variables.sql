CREATE TABLE IF NOT EXISTS `global_variables` (
  `var`  VARCHAR(255) NOT NULL DEFAULT '',
  `value` VARCHAR(255) ,
  PRIMARY KEY (`var`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

REPLACE INTO `global_variables` VALUES ('HBLevel', '11');
REPLACE INTO `global_variables` VALUES ('HBTrust', '4000000');
