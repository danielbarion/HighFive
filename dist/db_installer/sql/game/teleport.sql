DROP TABLE IF EXISTS `teleport`;
CREATE TABLE `teleport` (
  `Description` varchar(75) DEFAULT NULL,
  `id` mediumint(7) unsigned NOT NULL DEFAULT '0',
  `loc_x` mediumint(6) DEFAULT NULL,
  `loc_y` mediumint(6) DEFAULT NULL,
  `loc_z` mediumint(6) DEFAULT NULL,
  `price` int(10) unsigned DEFAULT NULL,
  `fornoble` tinyint(1) NOT NULL DEFAULT '0',
  `itemId` smallint(5) unsigned NOT NULL DEFAULT '57',
  PRIMARY KEY (`id`),
  KEY `itemId` (`itemId`)
) ENGINE=MyISAM DEFAULT CHARSET=utf8;

INSERT INTO `teleport` VALUES
('DE Village -> Town of Gludio',1,-12787,122779,-3112,10000,0,57),
('Elven Village -> Town of Gludio',2,-12787,122779,-3112,9200,0,57),
('Elven Village -> Elven Forest',468,21362,51122,-3688,710,0,57),
('Elven Village -> Elven Fortress',470,29294,74968,-3776,820,0,57),
('Gludio -> Elven Village',3,46951,51550,-2976,9200,0,57),
('Gludio -> DE Village',4,9709,15566,-4568,10000,0,57),
('Gludio -> Gludin',5,-80684,149770,-3040,7300,0,57),
('Gludio -> Dion',6,15472,142880,-2688,3400,0,57),
('Gludin -> Orc village',7,-45158,-112583,-240,26000,0,57),
('Gludin -> Dwarven village',8,115120,-178224,-880,38000,0,57),
('Gludin -> TI Village',9,-84141,244623,-3728,9400,0,57),
('Gludin -> Elven Village',10,46951,51550,-2976,16000,0,57),
('Gludin -> DE Village',11,9709,15566,-4568,16000,0,57),
('Gludin -> Town of Gludio',12,-12787,122779,-3112,7300,0,57),
('Gludin -> Wastelands',13,-16526,208032,-3664,3400,0,57),
('DE Village -> Dark Forest',464,-22224,14168,-3232,890,0,57),
('DE Village -> Spider Nest',465,-61095,75104,-3352,3600,0,57),
('DE Village -> Swampland',466,-21966,40544,-3192,1100,0,57),
('DE Village -> Neutral Zone',467,-10612,75881,-3592,1700,0,57),
('TI -> Village of Gludin',15,-80684,149770,-3040,18000,0,57),
('TI -> Obelisk of Victory',1001,-99586,237637,-3568,470,0,57),
('TI -> Western Territory',1002,-106696,214691,-3424,1000,0,57),
('TI -> Elven Ruins',1003,-112367,234703,-3688,830,0,57),
('TI -> Singing Waterfall',1004,-111728,244330,-3448,770,0,57),
('Dwarven Village -> Town of Gludio',16,-12787,122779,-3112,32000,0,57),
('Dwarven Village -> Eastern Mining Zone (Northeastern Shore)',17,169008,-208272,-3504,2400,0,57),
('Dwarven Village -> Abandoned Coal Mines',418,139714,-177456,-1536,690,0,57),
('Dwarven Village -> Mithril Mines Western Entrance',419,171946,-173352,3440,11000,0,57),
('Dwarven Village -> Mithril Mines Eastern Entrance',137,178591,-184615,-360,12000,0,57),
('Dion -> Town of Gludio',18,-12787,122779,-3112,3400,0,57),
('Dion -> Town of Giran',19,83551,147945,-3400,6800,0,57),
('Cruma Tower 1st floor -> Cruma Tower Entrance',22,17253,114232,-3440,0,0,57),
('Cruma Tower 1st floor -> Cruma Tower 2nd floor',23,17776,108288,-9056,0,0,57),
('Cruma Tower 2nd floor -> Cruma Tower 1st floor',24,17781,113999,-11672,0,0,57),
('Giran -> Dion',25,15472,142880,-2688,6800,0,57),
('Giran -> Oren',26,82956,53162,-1495,9400,0,57),
('Giran -> Hardin\'s Private Academy',28,105918,109759,-3192,4400,0,57),
('TI Dungeon inside -> outside',29,-112899,234942,-3688,0,0,57),
('TI Dungeon outside -> inside',30,48586,248459,-6160,0,0,57),
('Ivory Tower Basement',31,84915,15969,-4294,0,0,57),
('Ivory Tower Ground Floor',32,85399,16197,-3679,0,0,57),
('Ivory Tower 1st Floor',33,85399,16197,-2809,0,0,57),
('Ivory Tower 2nd Floor',34,85399,16197,-2293,0,0,57),
('Ivory Tower 3th Floor',35,85399,16197,-1776,0,0,57),
('Ivory Tower Ground Floor -> Oren Castle Town',36,82956,53162,-1495,3700,0,57),
('Ivory Tower Ground Floor -> Hunter\'s Village',37,116819,76994,-2714,6800,0,57),
('Ivory Tower Ground Floor -> Aden Castle Town',38,146331,25762,-2018,6200,0,57),
('Aden -> Oren Town',40,82971,53207,-1470,6900,0,57), -- retail
('Aden -> Hunter\'s Village',41,117088,76931,-2688,5900,0,57),
('Hunters -> Oren Town',43,82971,53207,-1488,4100,0,57),
('Hunters -> Hardin\'s Private Academy',45,105918,109759,-3192,3400,0,57),
('Hunters -> Aden Town',46,146783,25808,-2008,5900,0,57),
('Oren -> Giran Town',47,83551,147945,-3400,9400,0,57),
('Oren -> Ivory Tower',48,85391,16228,-3672,3700,0,57),
('Oren -> Hunter\'s Village',49,117088,76931,-2688,4100,0,57),
('Oren -> Hardin\'s Private Academy',50,105918,109759,-3192,6100,0,57),
('Oren -> Aden Town',51,146783,25808,-2008,6900,0,57),
('Hardin\'s Private Academy -> Giran Town',52,83551,147945,-3400,4400,0,57),
('Hardin\'s Private Academy -> Oren Town',53,82971,53207,-1488,6100,0,57),
('Hardin\'s Private Academy -> Hunter\'s Village',54,117088,76931,-2688,3400,0,57),
('Cruma level 2 -> Cruma level 3',55,17719,115590,-6584,0,0,57),
('Cruma level 3 -> Cruma Core',56,17691,111726,-6583,0,0,57),
('Cruma core -> Cruma level 3',57,17719,115590,-6584,0,0,57),
('Cruma Tower 3rd floor -> Cruma Tower 2nd Floor',58,17731,119465,-9067,0,0,57),
('Heine -> The Town of Giran',59,83551,147945,-3400,7600,0,57),
('Heine -> Giran Harbor',60,47938,186864,-3480,7100,0,57),
('Giran Harbor -> Giran Town',63,83551,147942,-3400,6300,0,57),
('Giran Harbor -> Heine',107,111455,219400,-3544,8500,0,57),
('Heine -> The Town of Dion',65,15472,142880,-2688,12000,0,57),
('Heine -> Field of Silence',66,87691,162835,-3563,12000,0,57), -- retail
('Heine -> Field of Whispers',67,82192,226128,-3664,5900,0,57), -- retail
('Heine -> Entrance to Alligator Islands',68,115583,192261,-3488,2100,0,57),
('Giran -> Heine',70,111455,219400,-3544,7600,0,57),
('Aden -> Coliseum',76,146440,46723,-3432,2000,0,57),
('Aden -> Blazing Swamp',81,155310,-16339,-3320,6800,0,57),
('Aden -> Forsaken Plains',84,168217,37990,-4072,1900,0,57),
('Dion -> Heine',85,111455,219400,-3544,12000,0,57),
('Dion -> Fortress of Resistance',86,47382,111278,-2104,1700,0,57),
('Dion -> Bee Hive',87,34475,188095,-2976,2900,0,57),
('Gludio -> Windawood Manor',88,-28327,155125,-3496,1400,0,57),
('Gludin -> Abandoned Camp',90,-49853,147089,-2784,1200,0,57),
('Gludin -> Fellmere Harvest Grounds',91,-63736,101522,-3552,1400,0,57),
('Gludin -> Langk Lizardman Dwelling',92,-44763,203497,-3592,1800,0,57),
('Orc Village -> The Immortal Plateau',93,-10983,-117484,-2464,960,0,57),
('Orc Village -> Immortal Plateau,Southern Region',94,-4190,-80040,-2696,2000,0,57),
('Orc Village -> Frozen Waterfall',96,8652,-139941,-1144,1600,0,57),
('Orc Village -> Cave of Trials',471,9340,-112509,-2536,1500,0,57),
('Oren -> Plains of Lizardmen',97,87252,85514,-3056,3900,0,57),
('Oren -> Sea of Spores',98,64328,26803,-3768,2500,0,57),
('Oren -> Outlaw Forest',1077,91539,-12204,-2440,5200,0,57),
('Hunters -> Northern Pathway of Enchanted Valley',99,104426,33746,-3800,3600,0,57),
('Hunters -> Southern Pathway of Enchanted Valley',100,124904,61992,-3920,1300,0,57),
('Hunters -> Entrance to the Forest of Mirrors',101,142065,81300,-3000,2000,0,57),
('Goddard -> Aden Castle Town',132,146783,25808,-2008,8100,0,57),
('Goddard -> Rune Township',108,43835,-47749,-792,10000,0,57),
('Goddard -> Varka Silenos Stronghold',109,125740,-40864,-3736,4200,0,57),
('Goddard -> Ketra Orc Outpost',110,146990,-67128,-3640,1800,0,57),
('Rune Township -> Town of Goddard',119,148024,-55281,-2728,10000,0,57), -- retail
('Rune Township -> The Town of Giran',120,83551,147945,-3400,59000,0,57), -- retail
('Rune -> Aden Castle Town',121,146783,25808,-2008,37000,0,57),
('Rune -> Rune Castle Town Guild',122,38316,-48216,-1152,150,0,57),
('Rune -> Rune Castle Town Temple',123,38303,-48040,896,150,0,57),
('Rune -> Forest of the Dead',124,52107,-54328,-3152,1200,0,57),
('Rune -> Swamp of Screams',125,69340,-50203,-3288,3000,0,57),
('Aden -> Rune',134,43835,-47749,-792,37000,0,57),
('Aden -> Goddard',135,148024,-55281,-2728,8100,0,57), -- retail
('Giran -> Giran Harbor',136,47938,186864,-3480,5200,0,57),

('TOI - 1st Floor',201,115168,16022,-5100,100000,0,57),
('TOI - 2nd Floor',202,114649,18587,-3609,150000,0,57),
('TOI - 3rd Floor',203,117918,16039,-2127,200000,0,57),
('TOI - 4th Floor',204,114622,12946,-645,250000,0,57),
('TOI - 5th Floor',205,112209,16078,928,300000,0,57),
('TOI - 6th Floor',206,112376,16099,1947,350000,0,57),
('TOI - 7th Floor',207,114448,16175,2994,400000,0,57),
('TOI - 8th Floor',208,111063,16118,3967,450000,0,57),
('TOI - 9th Floor',209,117147,18415,4977,500000,0,57),
('TOI - 10th Floor',210,118374,15973,5987,550000,0,57),
('TOI - 11th Floor',211,112209,16078,7028,600000,0,57),
('TOI - 12th Floor',212,114809,18711,7996,650000,0,57),
('TOI - 13th Floor',213,115178,16989,9007,700000,0,57),
('TOI - 14th Floor Outside Door',214,112714,14111,10077,800000,0,57),
('TOI - 14th Floor Inside On Roof',215,113098,14532,10077,900000,0,57),

('Cat Heretics Entrance',250,-53174,-250275,-7911,0,0,57), -- Interlude (undersea)
('Cat Heretics Exit',251,42514,143917,-5385,0,0,57),
('Cat Branded Entrance',252,46217,170290,-4983,0,0,57),
('Cat Branded Exit',253,45770,170299,-4985,0,0,57),
('Cat Apostate Entrance',254,-20230,-250780,-8168,0,0,57), -- Interlude (undersea)
('Cat Apostate Exit',255,77225,78362,-5119,0,0,57),
('Cat Witch Entrance',256,140404,79678,-5431,0,0,57),
('Cat Witch Exit',257,139965,79678,-5433,0,0,57),
('Cat DarkOmen Entrance',258,-19500,13508,-4905,0,0,57),
('Cat DarkOmen Exit',259,-19931,13502,-4905,0,0,57),
('Cat ForbiddenPath Entrance',260,12521,-248481,-9585,0,0,57), -- Interlude (undersea)
('Cat ForbiddenPath Exit',261,113429,84540,-6545,0,0,57),
('Necro Sacrifice Entrance',262,-41570,209785,-5089,0,0,57),
('Necro Sacrifice Exit',263,-41567,209292,-5091,0,0,57),
('Necro Pilgrims Entrance',264,45251,123890,-5415,0,0,57),
('Necro Pilgrims Exit',265,45250,124366,-5417,0,0,57),
('Necro Worshippers Entrance',266,111273,174015,-5417,0,0,57),
('Necro Worshippers Exit',267,110818,174010,-5443,0,0,57),
('Necro Patriots Entrance',268,-21726,77385,-5177,0,0,57),
('Necro Patriots Exit',269,-22197,77369,-5177,0,0,57),
('Necro Ascetics Entrance',270,-52254,79103,-4743,0,0,57),
('Necro Ascetics Exit',271,-52716,79106,-4745,0,0,57),
('Necro Martyrs Entrance',272,118308,132800,-4833,0,0,57),
('Necro Martyrs Exit',273,117793,132810,-4835,0,0,57),
('Necro Saints Entrance',274,83000,209213,-5443,0,0,57),
('Necro Saints Exit',275,82608,209225,-5443,0,0,57),
('Necro Disciples Entrance',276,172251,-17605,-4903,0,0,57),
('Necro Disciples Exit',277,171902,-17595,-4905,0,0,57),

('Dion(artifact -> out)',350,22967,157715,-2954,0,0,57),
('Dion(artifact -> hall)',351,22090,159871,-2711,0,0,57),
('Dion(artifact -> outofcastle)',352,22448,155798,-2958,0,0,57),
('Dion(in -> artifact)',353,22592,161530,-2775,0,0,57),
('Dion(in -> out)',354,22967,157715,-2954,0,0,57),
('Dion(in -> outofcastle)',355,22448,155798,-2958,0,0,57),
('Dion(out -> artifact)',356,22592,161530,-2775,0,0,57),
('Dion(out -> hall)',357,22090,159871,-2711,0,0,57),
('Dion(out -> outofcastle)',358,22448,155798,-2958,0,0,57),
('Dion(outofcastle -> artifact)',359,22592,161530,-2775,0,0,57),
('Dion(outofcastle -> out)',360,22967,157715,-2954,0,0,57),
('Dion(outofcastle -> hall)',361,22090,159871,-2711,0,0,57),
('Giran(artifact -> out)',362,113892,144175,-2714,0,0,57),
('Giran(artifact -> hall)',363,115984,145073,-2584,0,0,57),
('Giran(artifact -> outofcastle)',364,112016,144682,-2833,0,0,57),
('Giran(in -> artifact)',365,117619,144564,-2648,0,0,57),
('Giran(in -> out)',366,113892,144175,-2714,0,0,57),
('Giran(in -> outofcastle)',367,112016,144682,-2833,0,0,57),
('Giran(out -> artifact)',368,117619,144564,-2648,0,0,57),
('Giran(out -> hall)',369,115984,145073,-2584,0,0,57),
('Giran(out -> outofcastle)',370,112016,144682,-2833,0,0,57),
('Giran(outofcastle -> artifact)',371,117619,144564,-2648,0,0,57),
('Giran(outofcastle -> out)',372,113892,144175,-2714,0,0,57),
('Giran(outofcastle -> hall)',373,115984,145073,-2584,0,0,57),
('Oren(artifact -> out)',374,79956,36351,-2532,0,0,57),
('Oren(artifact -> hall)',375,82113,37217,-2311,0,0,57),
('Oren(artifact -> outofcastle)',376,78079,36809,-2566,0,0,57),
('Oren(in -> artifact)',377,83710,36713,-2375,0,0,57),
('Oren(in -> out)',378,79956,36351,-2532,0,0,57),
('Oren(in -> outofcastle)',379,78079,36809,-2566,0,0,57),
('Oren(out -> artifact)',380,83710,36713,-2375,0,0,57),
('Oren(out -> hall)',381,82113,37217,-2311,0,0,57),
('Oren(out -> outofcastle)',382,78079,36809,-2566,0,0,57),
('Oren(outofcastle -> artifact)',383,83710,36713,-2375,0,0,57),
('Oren(outofcastle -> out)',384,79956,36351,-2532,0,0,57),
('Oren(outofcastle -> hall)',385,82113,37217,-2311,0,0,57),
('Gludio(artifact -> out)',386,-18941,112085,-2762,0,0,57),
('Gludio(artifact -> hall)',387,-18129,109898,-2517,0,0,57),
('Gludio(artifact -> outofcastle)',388,-18484,113897,-2772,0,0,57),
('Gludio(in -> artifact)',389,-18592,108271,-2581,0,0,57),
('Gludio(in -> out)',390,-18941,112085,-2762,0,0,57),
('Gludio(in -> outofcastle)',391,-18484,113897,-2772,0,0,57),
('Gludio(out -> artifact)',392,-18592,108271,-2581,0,0,57),
('Gludio(out -> hall)',393,-18129,109898,-2517,0,0,57),
('Gludio(out -> outofcastle)',394,-18484,113897,-2772,0,0,57),
('Gludio(outofcastle -> artifact)',395,-18592,108271,-2581,0,0,57),
('Gludio(outofcastle -> out)',396,-18941,112085,-2762,0,0,57),
('Gludio(outofcastle -> hall)',397,-18129,109898,-2517,0,0,57),
('Aden(artifact -> out)',398,147723,7916,-475,0,0,57),
('Aden(artifact -> in)',399,148580,4578,-408,0,0,57),
('Aden(artifact -> outofcastle)',400,147582,8753,-496,0,0,57),
('Aden(artifact -> hall)',401,147520,6107,-409,0,0,57),
('Aden(in -> artifact)',402,1147499,2544,-473,0,0,57),
('Aden(in -> out)',403,147723,7916,-475,0,0,57),
('Aden(in -> outofcastle)',404,147582,8753,-496,0,0,57),
('Aden(in -> hall)',405,147520,6107,-409,0,0,57),
('Aden(out -> artifact)',406,147499,2544,-473,0,0,57),
('Aden(out -> in)',407,148580,4578,-408,0,0,57),
('Aden(out -> outofcastle)',408,147582,8753,-496,0,0,57),
('Aden(out -> hall)',409,147520,6107,-409,0,0,57),
('Aden(outofcastle -> artifact)',410,147499,2544,-473,0,0,57),
('Aden(outofcastle -> out)',411,147723,7916,-475,0,0,57),
('Aden(outofcastle -> in)',412,148580,4578,-408,0,0,57),
('Aden(outofcastle -> hall)',413,147520,6107,-409,0,0,57),
('Aden(hall) -> artifact)',414,147499,2544,-473,0,0,57),
('Aden(hall) -> out)',415,147723,7916,-475,0,0,57),
('Aden(hall) -> in)',416,148580,4578,-408,0,0,57),
('Aden(hall) -> outofcastle)',417,147582,8753,-496,0,0,57),
('Anakim/Lilith -> Disciples Necropolis',452,183225,-11911,-4897,0,0,57),
('TI -> DE Village',1005,9709,15566,-4568,24000,0,57),
('TI -> Dwarven Village',1006,115120,-178224,-880,46000,0,57),
('TI -> Elven Village',1007,46951,51550,-2976,23000,0,57),
('TI -> Orc Village',1008,-45158,-112583,-240,35000,0,57),
('DE Village -> Dwarven Village',1009,115120,-178224,-880,22000,0,57),
('DE Village -> TI Village',1010,-84141,244623,-3728,24000,0,57),
('Gludin -> Windy Hill',1121,-88539,83389,-2864,2600,0,57),
('DE Village -> Orc Village',1012,-45158,-112583,-240,13000,0,57),
('Elven Village -> Neutral Zone',1013,-10612,75881,-3592,1700,0,57),
('Elven Village -> Dwarven Village',1014,115120,-178224,-880,23000,0,57),
('Elven Village -> TI Village',1015,-84141,244623,-3728,23000,0,57),
('Elven Village -> Orc Village',1016,-45158,-112583,-240,18000,0,57),
('Dwarven Village -> DE Village',1017,9709,15566,-4568,22000,0,57),
('Dwarven Village -> Elven Village',1018,46951,51550,-2976,23000,0,57),
('Dwarven Village -> TI Village',1019,-84141,244623,-3728,46000,0,57),
('Dwarven Village -> Schuttgart',1020,87126,-143520,-1288,4400,0,57),
('Dwarven Village -> Orc Village',1021,-45158,-112583,-240,17000,0,57),
('Dwarven Village -> Western Mining Zone (Central Shore)',1022,136910,-205082,-3664,970,0,57),
('Orc Village -> Town of Gludio',1023,-12787,122779,-3112,23000,0,57),
('Orc Village -> Schuttgart',1024,87126,-143520,-1288,13000,0,57),
('Orc Village -> DE Village',1025,9709,15566,-4568,13000,0,57),
('Orc Village -> Dwarven Village',1026,115120,-178224,-880,17000,0,57),
('Orc Village -> TI Village',1027,-84141,244623,-3728,35000,0,57),
('Orc Village -> Elven Village',1028,46951,51550,-2976,18000,0,57),
('Gludio -> Orc Village',1033,-45158,-112583,-240,23000,0,57),
('Gludin -> Windmill Hill',1029,-75437,168800,-3632,550,0,57),
('Gludin -> Forgotten Temple',1030,-53001,191425,-3568,2000,0,57),
('Gludio -> Ant Cave',1031,-9959,176184,-4160,2100,0,57),
('Gludin -> Red Rock Ridge',1032,-42256,198333,-2800,3700,0,57),
('Gludio -> Dwarven Village',1034,115120,-178224,-880,32000,0,57),
('Gludio -> Schuttgart',1035,87126,-143520,-1288,85000,0,57),
('Gludio -> Heine',1036,111455,219400,-3544,47000,0,57),
('Gludio -> Aden',1037,146783,25808,-2008,56000,0,57),
('Gludio -> Oren',1038,82971,53207,-1488,35000,0,57),
('Gludio -> Goddard',1039,148024,-55281,-2728,71000,0,57),
('Gludio -> Giran',1040,83551,147945,-3400,29000,0,57),
('Gludio -> Rune',1041,43835,-47749,-792,53000,0,57),
('Gludio -> Ruins of Agony',1042,-41248,122848,-2904,790,0,57),
('Gludio -> Ruins of Despair',1043,-19120,136816,-3752,610,0,57),
('Gludin -> Orc Barracks',1044,-89763,105359,-3576,1800,0,57),
('Dion -> Goddard',1046,148024,-55281,-2728,71000,0,57),
('Dion -> Rune',1047,43835,-47749,-792,57000,0,57),
('Dion -> Schuttgart',1048,87126,-143520,-1288,88000,0,57),
('Dion -> Aden',1049,146783,25808,-2008,52000,0,57),
('Dion -> Oren',1050,82971,53207,-1488,33000,0,57),
('Dion -> Cruma Marshlands',1051,5106,126916,-3664,760,0,57),
('Dion -> Cruma Tower',1052,17225,114173,-3440,2300,0,57),
('Dion -> Plains of Dion',1053,630,179184,-3720,1500,0,57),
('Dion -> Tanor Canyon',1054,60374,164301,-2856,3900,0,57),
('Giran -> Gludio',1055,-12787,122779,-3112,29000,0,57),
('Giran -> Goddard',1056,148024,-55281,-2728,63000,0,57),
('Giran -> Rune',1057,43835,-47749,-792,59000,0,57),
('Giran -> Schuttgart',1058,87126,-143520,-1288,87000,0,57),
('Giran -> Aden',1059,146783,25808,-2008,13000,0,57),
('Giran -> Devil Isle',1061,43408,206881,-3752,5700,0,57),
('Giran -> Brekas Stronghold',1062,85546,131328,-3672,1000,0,57),
('Heine -> Oren',1063,82971,53207,-1488,50000,0,57),
('Heine -> Aden',1064,146783,25808,-2008,59000,0,57),
('Heine -> Goddard',1065,148024,-55281,-2728,83000,0,57),
('Heine -> Rune',1066,43835,-47749,-792,82000,0,57),
('Heine -> Schuttgart',1067,87126,-143520,-1288,100000,0,57),
('Heine -> Gludio',1068,-12787,122779,-3112,47000,0,57),
('Heine -> Garden of Eva',1069,84413,234334,-3656,2400,0,57),
('Oren -> Rune',1070,43835,-47749,-792,10000,0,57),
('Oren -> Goddard',1071,148024,-55281,-2728,37000,0,57),
('Oren -> Heine',1072,111455,219400,-3544,50000,0,57),
('Oren -> Dion',1073,15472,142880,-2688,33000,0,57),
('Oren -> Schuttgart',1074,87126,-143520,-1288,59000,0,57),
('Oren -> Gludio',1075,-12787,122779,-3112,35000,0,57),
('Aden -> Giran',1078,83551,147945,-3400,13000,0,57), -- retail
('Aden -> Heine',1079,111455,219400,-3546,59000,0,57), -- retail
('Aden -> Schuttgart',1080,87126,-143520,-1288,53000,0,57), -- retail
('Aden -> Dion',1081,15472,142880,-2688,52000,0,57),
('Aden -> Gludio',1082,-12787,122779,-3112,56000,0,57),
('Aden -> Seal of Shilen',1083,184742,19745,-3168,3000,0,57),
('Aden -> Forest of Mirrors',1084,142065,81300,-3000,4400,0,57),
('Aden -> Fields of Massacre',1085,183543,-14974,-2776,6500,0,57),
('Aden -> Ancient Battleground',1086,106517,-2871,-3416,5900,0,57),
('Aden -> Silent Valley',1087,170838,55776,-5280,6100,0,57), -- retail
('Aden -> ToI',1088,114649,11115,-5120,4200,0,57),
('Goddard -> Gludio',1089,-12787,122779,-3112,71000,0,57),
('Goddard -> Giran',1090,83551,147945,-3400,63000,0,57),
('Goddard -> Dion',1091,15472,142880,-2688,71000,0,57),
('Goddard -> Heine',1092,111455,219400,-3544,83000,0,57),
('Goddard -> Schuttgart',1093,87126,-143520,-1288,10000,0,57),
('Goddard -> Oren',1094,82971,53207,-1488,37000,0,57),
('Goddard -> Hot Springs',1095,144880,-113468,-2560,9300,0,57),
('Goddard -> Wall of Argos',1096,165054,-47861,-3560,2200,0,57),
('Goddard -> Monastery of silence',1097,106414,-87799,-2920,10000,0,57),
('Rune -> Dion',1098,15472,142880,-2688,57000,0,57),
('Rune Township -> The Town of Gludio',1099,-12787,122779,-3114,53000,0,57), -- retail
('Rune -> Heine',1100,111455,219400,-3544,82000,0,57),
('Rune -> Schuttgart',1101,87126,-143520,-1288,10000,0,57),
('Rune -> Oren',1102,82971,53207,-1488,10000,0,57),
('Rune -> Wild Beast Pastures',1103,53516,-82831,-2700,7200,0,57),
('Rune -> Valley of Saints',1104,65307,-71445,-3688,3800,0,57),
('Rune -> Monastery of Silence',1105,106414,-87799,-2920,14000,0,57),
('Schuttgart -> Rune',1106,43835,-47749,-792,10000,0,57),
('Schuttgart -> Goddard',1107,148024,-55281,-2728,10000,0,57),
('Schuttgart -> Aden',1108,146783,25808,-2008,53000,0,57),
('Schuttgart -> Oren',1109,82971,53207,-1488,59000,0,57),
('Schuttgart -> Heine',1110,111455,219400,-3544,100000,0,57),
('Schuttgart -> Giran',1111,83551,147945,-3400,87000,0,57),
('Schuttgart -> Dion',1112,15472,142880,-2688,88000,0,57),
('Schuttgart -> Gludio',1113,-12787,122779,-3112,85000,0,57),
('Schuttgart -> Orc Village',1114,-45158,-112583,-240,13000,0,57),
('Schuttgart -> Dwarven Village',1115,115120,-178224,-880,4400,0,57),
('Schuttgart -> Den of Evil',1116,68693,-110438,-1904,7500,0,57),
('Schuttgart -> Plunderous Plains',1117,111965,-154172,-1528,1600,0,57),
('Schuttgart -> Icemans Hut',1118,113903,-108752,-856,3500,0,57),
('Schuttgart -> Crypt of Disgrace',1119,47692,-115745,-3744,9600,0,57),
('Schuttgart -> Pavel Ruins',1120,91280,-117152,-3928,5300,0,57),
('Kamael Vilage -> The Town of Gludio',1139,-12672,122776,-3116,12000,0,57),
('Kamael Vilage -> Town of Aden',1122,146331,25762,-2018,26000,0,57),
('Kamael Vilage -> Talking Island Village',1123,-84318,244579,-3730,20000,0,57),
('Kamael Vilage -> Dark Elf Village',1124,9745,15606,-4574,13000,0,57),
('Kamael Vilage -> Elven Village',1125,46934,51467,-2977,16000,0,57),
('Kamael Vilage -> Dwarven Village',1126,115113,-178212,-901,32000,0,57),
('Kamael Vilage -> Orc Village',1127,-44836,-112524,-235,17000,0,57),
('Kamael Vilage -> Stronghold I',1128,-122410,73205,-2872,2600,0,57),
('Kamael Vilage -> Stronghold II',1129,-95540,52150,-2024,2200,0,57),
('Kamael Vilage -> Stronghold III',1130,-85928,37095,-2048,3200,0,57),
('Kamael Vilage -> Isle of Souls Harbor',1131,-74016,51932,-3680,4300,0,57),
('Kamael Vilage -> Stronghold I free',1132,-122410,73205,-2872,0,0,57),
('Kamael Vilage -> Stronghold II free',1133,-95540,52150,-2024,0,0,57),
('Kamael Vilage -> Stronghold III free',1134,-85928,37095,-2048,0,0,57),
('Strongolds -> Kamael Village free',1135,-117251,46771,360,0,0,57),
('Soul Isle Harbor -> The Town of Gludio',1136,-12672,122776,-3116,9300,0,57),
('Soul Isle Harbor -> Town of Aden',1137,146331,25762,-2018,22000,0,57),
('Soul Isle Harbor -> The Town of Gludio',1138,-117251,46771,360,4300,0,57),

('Pagan Temple - > exit',11999,-16307,-36591,-10725,0,0,57),
('Pagan Chapel - > exit',11998,-16359,-43803,-10725,0,0,57);

INSERT INTO `teleport` VALUES
-- Gludio
('Clan Hall -> Village Square',500,-14393,123671,-3144,0,0,57),
('Clan Hall -> East Gate Entrance',501,-11817,123652,-3079,0,0,57),
('Clan Hall -> West Gate Entrance',502,-16796,124108,-3127,0,0,57),
('Clan Hall -> South Gate Entrance',503,-14207,126547,-3151,0,0,57),
('Clan Hall -> North Gate Entrance',504,-14591,121024,-2990,0,0,57),
('Clan Hall -> Ruins of Agony',505,-41248,122848,-2912,500,0,57),
('Clan Hall -> Ruins of Despair',506,-19120,136816,-3762,500,0,57),
('Clan Hall -> The Ant Nest',507,-9959,176184,-4160,500,0,57),
('Clan Hall -> Windawood Manor',508,-28327,155125,-3496,500,0,57),
-- Gludin
('Clan Hall -> Village Square',509,-82445,150788,-3129,0,0,57),
('Clan Hall -> North Gate Entrance',510,-83331,148563,-3148,0,0,57),
('Clan Hall -> East Gate Entrance',511,-78405,152723,-3181,0,0,57),
('Clan Hall -> South Gate Entrance',512,-77460,155995,-3194,0,0,57),
('Clan Hall -> West Gate Entrance',513,-85138,152749,-3160,0,0,57),
('Clan Hall -> Windmill Hill',514,-75437,168800,-3632,500,0,57),
('Clan Hall -> Fellmere Harvesting Grounds',515,-63736,101522,-3552,500,0,57),
('Clan Hall -> Windy Hill',516,-88539,83389,-2864,500,0,57),
('Clan Hall -> Abandoned Camp',517,-49853,147089,-2784,500,0,57),
('Clan Hall -> Wastelands',518,-16526,208032,-3664,500,0,57),
-- Dion
('Clan Hall -> Village Square',519,19025,145245,-3107,0,0,57),
('Clan Hall -> North Gate Entrance',520,21511,145866,-3153,0,0,57),
('Clan Hall -> East Gate Entrance',521,18891,142365,-3051,0,0,57),
('Clan Hall -> South Gate Entrance',522,17394,147593,-3129,0,0,57),
('Clan Hall -> West Gate Entrance',523,16582,144130,-2960,0,0,57),
('Clan Hall -> Cruma Marshlands',524,5106,126916,-3664,500,0,57),
('Clan Hall -> Fortress of Resistance',525,47382,111278,-2104,500,0,57),
('Clan Hall -> Plains of Dion',526,630,179184,-3720,500,0,57),
('Clan Hall -> Tanor Canyon',527,60374,164301,-2856,500,0,57),
('Clan Hall -> Entrance to Floran Village',528,17430,170103,-3506,500,0,57),
-- Aden
('Clan Hall -> Village Square',529,147450,28081,-2294,0,0,57),
('Clan Hall -> East Gate Entrance',530,151950,25094,-2172,0,0,57),
('Clan Hall -> West Gate Entrance',531,142593,26344,-2425,0,0,57),
('Clan Hall -> South Gate Entrance',532,147503,32299,-2501,0,0,57),
('Clan Hall -> North Gate Entrance',533,147465,20737,-2130,0,0,57),
('Clan Hall -> Ancient Battleground',534,106517,-2871,-3454,500,0,57),
('Clan Hall -> Enchanted Valley, Southern Region',535,124904,61992,-3973,500,0,57),
('Clan Hall -> Enchanted Valley, Northern Region',536,104426,33746,-3825,500,0,57),
('Clan Hall -> Blazing Swamp',537,155310,-16339,-3320,500,0,57),
('Clan Hall -> Forest of Mirrors',538,142065,81300,-3000,500,0,57),
('Clan Hall -> Anghel Waterfall',539,166182,91560,-3168,500,0,57),
('Clan Hall -> South Entrance of Devastated Castle',540,181726,-7524,-3464,500,0,57),
('Clan Hall -> West Area of the Devastated Castle',541,168779,-18790,-3184,500,0,57),
('Clan Hall -> Seal of Shilen',542,184742,19745,-3168,500,0,57),
('Clan Hall -> Forsaken Plains',543,168217,37990,-4072,500,0,57),
('Clan Hall -> Tower of Insolence',544,114649,11115,-5120,500,0,57),
('Clan Hall -> The Giants Cave Upper Layer',545,183985,61424,-3992,500,0,57),
('Clan Hall -> The Giants Cave Lower Layer',546,191754,56760,-7624,500,0,57),
-- Giran
('Clan Hall -> Village Square',547,81749,149171,-3464,0,0,57),
('Clan Hall -> North Gate Entrance',548,81525,143821,-3528,0,0,57),
('Clan Hall -> East Gate Entrance',549,88342,147329,-3400,0,0,57),
('Clan Hall -> South Gate Entrance',550,81548,152633,-3528,0,0,57),
('Clan Hall -> West Gate Entrance',551,77305,148636,-3592,0,0,57),
('Clan Hall -> Brekas Stronghold',552,85546,131328,-3672,500,0,57),
('Clan Hall -> Devils Isle',553,43408,206881,-3752,500,0,57),
('Clan Hall -> Dragon Valley',554,73024,118485,-3720,500,0,57),
('Clan Hall -> Tanor Canyon',555,60374,164301,-2856,500,0,57),
-- Goddard
('Clan Hall -> Village Square',556,147728,-56331,-2776,0,0,57),
('Clan Hall -> North Gate Entrance',557,147731,-58930,-2976,0,0,57),
('Clan Hall -> East Gate Entrance',558,150561,-57489,-2976,0,0,57),
('Clan Hall -> West Gate Entrance',559,144866,-57464,-2976,0,0,57),
('Clan Hall -> Varka Silenos Stronghold',560,125740,-40864,-3736,500,0,57),
('Clan Hall -> Ketra Orc Outpost',561,146990,-67128,-3640,500,0,57),
('Clan Hall -> Entrance to the Forge of the Gods',562,169018,-116303,-2432,500,0,57),
('Clan Hall -> Wall of Argos',563,165054,-47861,-3560,500,0,57),
('Clan Hall -> Hot Springs',564,144880,-113468,-2560,500,0,57),
-- Rune
('Clan Hall -> Village Square',565,43889,-49101,-792,0,0,57),
('Clan Hall -> South Gate Entrance',566,43307,-46563,-816,0,0,57),
('Clan Hall -> North Gate Entrance',567,40909,-52670,-896,0,0,57),
('Clan Hall -> Forest of the Dea',568,52107,-54328,-3158,500,0,57),
('Clan Hall -> Wild Beast Pastures',569,43805,-88010,-2780,500,0,57),
('Clan Hall -> Swamp of Screams',570,69340,-50203,-3314,500,0,57),
('Clan Hall -> Valley of Saints',571,65307,-71445,-3696,500,0,57),
('Clan Hall -> Monastery of Silence',572,106414,-87799,-2949,500,0,57),
('Clan Hall -> Shyeeds Cavern',573,76911,-55295,-5824,500,0,57),
-- Schuttgart
('Clan Hall -> Village Square',574,87379,-142322,-1336,0,0,57),
('Clan Hall -> West Gate Entrance',575,84753,-141051,-1536,0,0,57),
('Clan Hall -> South Gate Entrance',576,87347,-139802,-1536,0,0,57),
('Clan Hall -> East Gate Entrance',577,89959,-141034,-1536,0,0,57),
('Clan Hall -> Crypts of Disgrace',578,47692,-115745,-3744,500,0,57),
('Clan Hall -> Plunderous Plains',579,111965,-154172,-1528,500,0,57),
('Clan Hall -> Den of Evil',580,68693,-110438,-1946,500,0,57),
('Clan Hall -> Ice Merchant Cabin',581,113903,-108752,-860,500,0,57);

-- Noblesse Teleport
INSERT INTO `teleport` VALUES
-- 1000 adena
('Gludin Arena - 1000 adena',9000,-87328,142266,-3640,1000,1,57), -- retail
('Coliseum - 1000 adena',9001,146440,46723,-3400,1000,1,57), -- retail
('Giran Arena - 1000 adena',9002,73579,142709,-3768,1000,1,57), -- retail
('Center of the Forgotten Temple - 1000 adena',9010,-54026,179504,-4650,1000,1,57), -- retail
('Wastelands, Western Region  - 1000 adena',9011,-47506,179572,-3669,1000,1,57), -- retail
('The Giant\'s Cave Upper Layer - 1000 adena',9020,183985,61424,-3992,1000,1,57), -- retail
('Plains of Glory - 1000 adena',9021,135580,19467,-3424,1000,1,57), -- retail
('War-Torn Plains - 1000 adena',9022,156898,11217,-4032,1000,1,57), -- retail
('Tower of Insolence, 3rd Floor - 1000 adena',9023,110848,16154,-2120,1000,1,57), -- retail
('Tower of Insolence, 5th Floor - 1000 adena',9024,118404,15988,832,1000,1,57), -- retail
('Tower of Insolence, 7th Floor - 1000 adena',9025,115064,12181,2960,1000,1,57), -- retail
('Tower of Insolence. 10th Floor - 1000 adena',9026,118525,16455,5984,1000,1,57), -- retail
('Tower of Insolence. 13th Floor - 1000 adena',9027,115384,16820,9000,1000,1,57), -- retail
('Hunters Valley - 1000 adena',9028,114306,86573,-3112,1000,1,57), -- retail
('Anghel Waterfall - 1000 adena',9029,166182,91560,-3168,1000,1,57), -- retail
('The Center of the Forest of Mirrors - 1000 adena',9030,166080,75574,-2992,1000,1,57), -- retail
('The Center of the Enchanted Valley - 1000 adena',9031,114674,44150,-3376,1000,1,57), -- retail
('Hunters Valley - 1000 adena',9032,114306,86573,-3112,1000,1,57), -- retail
('The Giant\'s Cave Lower Layer - 1000 adena',9033,191754,56760,-7624,1000,1,57), -- retail
('Gorgon Flower Garden - 1000 adena',9040,113553,134813,-3540,1000,1,57), -- retail
('Hardin\'s Private Academy - 1000 adena',9046,105918,109759,-3170,1000,1,57), -- retail
('The Center of the Forest of the Dead - 1000 adena',9050,54425,-41692,-3072,1000,1,57), -- retail
('The Center of the Valley of Saints - 1000 adena',9051,84092,-80084,-3504,1000,1,57), -- retail
('Cursed Village - 1000 adena',9052,62084,-40935,-2802,1000,1,57), -- retail
('Shyeed\'s Cavern - 1000 adena',9053,76911,-55295,-5824,1000,1,57), -- retail
('Monastery of Silence - 1000 adena',9054,106414,-87799,-2949,1000,1,57), -- retail
('Varka Silenos Village - 1000 adena',9060,107929,-52248,-2408,1000,1,57), -- retail
('Ketra Orc Village - 1000 adena',9061,149817,-80053,-5576,1000,1,57), -- retail
('Devil\'s Pass - 1000 adena',9062,106349,-61870,-2904,1000,1,57), -- retail
('Garden of Wild Beasts - 1000 adena',9063,132997,-60608,-2960,1000,1,57), -- retail
('The Center of the Hot Springs - 1000 adena',9064,144625,-101291,-3384,1000,1,57), -- retail
('The Center of the Wall of Argos - 1000 adena',9065,183140,-53307,-1896,1000,1,57), -- retail
('Shrine of Loyalty - 1000 adena',9066,191257,-59388,-2898,1000,1,57), -- retail
('Four Sepulchers - 1000 adena',9067,178127,-84435,-7215,1000,1,57), -- retail
('Imperial Tomb - 1000 adena',9068,186699,-75915,-2826,1000,1,57), -- retail
('Forge of the Gods - Top Level - 1000 adena',9070,173436,-112725,-3680,1000,1,57), -- retail
('Forge of the Gods - Lower Level - 1000 adena',9071,180260,-111913,-5851,1000,1,57), -- retail
('Execution Grounds - 1000 adena',9080,50568,152408,-2656,1000,1,57), -- retail
('Floran Agricultural Area - 1000 adena',9081,26810,172787,-3376,1000,1,57), -- retail
('The Center of the Cruma Marshlands - 1000 adena',9082,5941,125455,-3400,1000,1,57), -- retail
('Frost Lake - 1000 adena',9090,107577,-122392,-3632,1000,1,57), -- retail
('Grave Robber Hideout - 1000 adena',9091,44221,-114232,-2784,1000,1,57), -- retail
('Sky Wagon Relic - 1000 adena',9092,121618,-141554,-1496,1000,1,57), -- retail
('Evil Hunting Grounds - 1000 adena',9100,-6989,109503,-3040,1000,1,57), -- retail
('The Center of the Neutral Zone - 1000 adena',9101,-18415,85624,-3680,1000,1,57), -- retail
('The Center of the Dark Forest - 1000 adena',9102,-14129,27094,-3680,1000,1,57), -- retail
('Center of the School of Dark Arts - 1000 adena',9103,-49185,49441,-5912,1000,1,57), -- retail
('Center of the Elven Ruins - 1000 adena',9104,49315,248452,-5960,1000,1,57), -- retail
('Immortal Plateau, Northern Region - 1000 adena',9105,-25309,-131569,-680,1000,1,57), -- retail
('The Center of the Mithril Mines - 1000 adena',9106,175499,-181586,-904,1000,1,57), -- retail
('The Center of the Abandoned Coal Mines - 1000 adena',9107,144706,-173223,-1520,1000,1,57), -- retail
('Forest of Evil - 1000 adena',9110,93218,16969,-3904,1000,1,57), -- retail
('Timak Outpost - 1000 adena',9111,67097,68815,-3648,1000,1,57), -- retail
('Altar of Rites - 1000 adena',9112,-44566,77508,-3736,1000,1,57), -- retail
('Field of Silence (Western Section) - 1000 adena',9120,75387,195263,-3000,1000,1,57), -- retail
('Field of Whispers (Eastern Section) - 1000 adena',9121,97786,209303,-3040,1000,1,57), -- retail
('The Center of Alligator Island - 1000 adena',9122,113708,178387,-3232,1000,1,57), -- retail
('Inside the Garden of Eva - 1000 adena',9123,82693,242220,-6712,1000,1,57), -- retail
('Garden of Eva - 2nd Level - 1000 adena',9124,79248,247390,-8816,1000,1,57), -- retail
('Garden of Eva - 3rd Level - 1000 adena',9125,77868,250400,-9328,1000,1,57), -- retail
('Garden of Eva - 4th Level - 1000 adena',9126,78721,253309,-9840,1000,1,57), -- retail
('Garden of Eva - 5th Level - 1000 adena',9127,82951,252354,-10592,1000,1,57), -- retail
('Field of Silence Center - 1000 adena',9128,80987,182423,-3504,1000,1,57), -- retail
('Field of Whispers Center - 1000 adena',9129,86147,218268,-3592,1000,1,57), -- retail
-- Olympiad Token
('Gludin Arena - 1 Olympiad Token',9900,-87328,142266,-3640,1,1,13722), -- retail
('Coliseum - 1 Olympiad Token',9901,146440,46723,-3400,1,1,13722), -- retail
('Giran Arena - 1 Olympiad Token',9902,73579,142709,-3768,1,1,13722), -- retail
('Center of the Forgotten Temple - 1 Olympiad Token',9910,-54026,179504,-4650,1,1,13722), -- retail
('Wastelands, Western Region  - 1 Olympiad Token',9911,-47506,179572,-3669,1,1,13722), -- retail
('The Giant\'s Cave Upper Layer - 1 Olympiad Token',9920,183985,61424,-3992,1,1,13722), -- retail
('Plains of Glory - 1 Olympiad Token',9921,135580,19467,-3424,1,1,13722), -- retail
('War-Torn Plains - 1 Olympiad Token',9922,156898,11217,-4032,1,1,13722), -- retail
('Tower of Insolence, 3rd Floor - 1 Olympiad Token',9923,110848,16154,-2120,1,1,13722), -- retail
('Tower of Insolence, 5th Floor - 1 Olympiad Token',9924,118404,15988,832,1,1,13722), -- retail
('Tower of Insolence, 7th Floor - 1 Olympiad Token',9925,115064,12181,2960,1,1,13722), -- retail
('Tower of Insolence. 10th Floor - 1 Olympiad Token',9926,118525,16455,5984,1,1,13722), -- retail
('Tower of Insolence. 13th Floor - 1 Olympiad Token',9927,115384,16820,9000,1,1,13722), -- retail
('Hunters Valley - 1 Olympiad Token',9928,114306,86573,-3112,1,1,13722), -- retail
('Anghel Waterfall - 1 Olympiad Token',9929,166182,91560,-3168,1,1,13722), -- retail
('The Center of the Forest of Mirrors - 1 Olympiad Token',9930,166080,75574,-2992,1,1,13722), -- retail
('The Center of the Enchanted Valley - 1 Olympiad Token',9931,114674,44150,-3376,1,1,13722), -- retail
('Hunters Valley - 1 Olympiad Token',9932,114306,86573,-3112,1,1,13722), -- retail
('The Giant\'s Cave Lower Layer - 1 Olympiad Token',9933,191754,56760,-7624,1,1,13722), -- retail
('Gorgon Flower Garden - 1 Olympiad Token',9940,113553,134813,-3540,1,1,13722), -- retail
('Hardin\'s Private Academy - 1 Olympiad Token',9946,105918,109759,-3170,1,1,13722), -- retail
('The Center of the Forest of the Dead - 1 Olympiad Token',9950,54425,-41692,-3072,1,1,13722), -- retail
('The Center of the Valley of Saints - 1 Olympiad Token',9951,84092,-80084,-3504,1,1,13722), -- retail
('Cursed Village - 1 Olympiad Token',9952,62084,-40935,-2802,1,1,13722), -- retail
('Shyeed\'s Cavern - 1 Olympiad Token',9953,76911,-55295,-5824,1,1,13722), -- retail
('Monastery of Silence - 1 Olympiad Token',9954,106414,-87799,-2949,1,1,13722), -- retail
('Varka Silenos Village - 1000 adena',9960,107929,-52248,-2408,1,1,13722), -- retail
('Ketra Orc Village - 1000 adena',9961,149817,-80053,-5576,1,1,13722), -- retail
('Devil\'s Pass - 1000 adena',9962,106349,-61870,-2904,1,1,13722), -- retail
('Garden of Wild Beasts - 1000 adena',9963,132997,-60608,-2960,1,1,13722), -- retail
('The Center of the Hot Springs - 1000 adena',9964,144625,-101291,-3384,1,1,13722), -- retail
('The Center of the Wall of Argos - 1000 adena',9965,183140,-53307,-1896,1,1,13722), -- retail
('Shrine of Loyalty - 1000 adena',9966,191257,-59388,-2898,1,1,13722), -- retail
('Four Sepulchers - 1000 adena',9967,178127,-84435,-7215,1,1,13722), -- retail
('Imperial Tomb - 1000 adena',9968,186699,-75915,-2826,1,1,13722), -- retail
('Forge of the Gods - Top Level - 1000 adena',9970,173436,-112725,-3680,1,1,13722), -- retail
('Forge of the Gods - Lower Level - 1000 adena',9971,180260,-111913,-5851,1,1,13722), -- retail
('Execution Grounds - 1 Olympiad Token',9980,50568,152408,-2656,1,1,13722), -- retail
('Floran Agricultural Area - 1 Olympiad Token',9981,26810,172787,-3376,1,1,13722), -- retail
('The Center of the Cruma Marshlands - 1 Olympiad Token',9982,5941,125455,-3400,1,1,13722), -- retail
('Frost Lake - 1 Olympiad Token',9990,107577,-122392,-3632,1,1,13722), -- retail
('Grave Robber Hideout - 1 Olympiad Token',9991,44221,-114232,-2784,1,1,13722), -- retail
('Sky Wagon Relic - 1 Olympiad Token',9992,121618,-141554,-1496,1,1,13722), -- retail
('Evil Hunting Grounds - 1 Olympiad Token',10000,-6989,109503,-3040,1,1,13722), -- retail
('The Center of the Neutral Zone - 1 Olympiad Token',10001,-18415,85624,-3680,1,1,13722), -- retail
('The Center of the Dark Forest - 1 Olympiad Token',10002,-14129,27094,-3680,1,1,13722), -- retail
('Center of the School of Dark Arts - 1 Olympiad Token',10003,-49185,49441,-5912,1,1,13722), -- retail
('Center of the Elven Ruins - 1 Olympiad Token',10004,49315,248452,-5960,1,1,13722), -- retail
('Immortal Plateau, Northern Region - 1 Olympiad Token',10005,-25309,-131569,-680,1,1,13722), -- retail
('The Center of the Mithril Mines - 1 Olympiad Token',10006,175499,-181586,-904,1,1,13722), -- retail
('The Center of the Abandoned Coal Mines - 1 Olympiad Token',10007,144706,-173223,-1520,1,1,13722), -- retail
('Forest of Evil - 1 Olympiad Token',10010,93218,16969,-3904,1,1,13722), -- retail
('Timak Outpost - 1 Olympiad Token',10011,67097,68815,-3648,1,1,13722), -- retail
('Altar of Rites - 1 Olympiad Token',10012,-44566,77508,-3736,1,1,13722), -- retail
('Field of Silence (Western Section) - 1 Olympiad Token',10020,75387,195263,-3000,1,1,13722), -- retail
('Field of Whispers (Eastern Section) - 1 Olympiad Token',10021,97786,209303,-3040,1,1,13722), -- retail
('The Center of Alligator Island - 1 Olympiad Token',10022,113708,178387,-3232,1,1,13722), -- retail
('Inside the Garden of Eva - 1 Olympiad Token',10023,82693,242220,-6712,1,1,13722), -- retail
('Garden of Eva - 2nd Level - 1 Olympiad Token',10024,79248,247390,-8816,1,1,13722), -- retail
('Garden of Eva - 3rd Level - 1 Olympiad Token',10025,77868,250400,-9328,1,1,13722), -- retail
('Garden of Eva - 4th Level - 1 Olympiad Token',10026,78721,253309,-9840,1,1,13722), -- retail
('Garden of Eva - 5th Level - 1 Olympiad Token',10027,82951,252354,-10592,1,1,13722), -- retail
('Ketra teleport - Town of Goddard',10028,148024,-55281,-2728,2400,0,57),
('Ketra teleport - Rune Township',10029,43835,-47749,-792,11000,0,57),
('Ketra teleport - Town of Aden',10030,146783,25808,-2000,10000,0,57),
('Ketra teleport - Wall of Argos',10031,165054,-47861,-3560,4200,0,57),
('Ketra teleport - Hot Springs',10032,144880,-113468,-2560,5400,0,57),
('Varka teleport - Town of Goddard',10033,148024,-47749,-2728,4000,0,57),
('Varka teleport - Rune Township',10034,43835,-47877,-792,6400,0,57),
('Varka teleport - Town of Aden',10035,146783,25808,-2000,8700,0,57),
('Varka teleport - Wall of Argos',10036,165054,-47861,-3560,6800,0,57),
('Varka teleport - Hot Springs',10037,144880,-113468,-2560,11000,0,57),
('Field of Silence Center - 1000 adena',10038,80987,182423,-3504,1,1,13722), -- retail
('Field of Whispers Center - 1000 adena',10039,86147,218268,-3592,1,1,13722), -- retail
('Ketra teleport - Town of Schuttgart',10040,87126,-143520,-1288,8900,0,57),
('Varka teleport - Town of Schuttgart',10041,87126,-143520,-1288,9300,0,57);

INSERT INTO `teleport` VALUES
('Gludio -> Kamael Village',12050,-117251,46771,360,12000,0,57),
('Dark Elf Village -> Kamael Village',12051,-117251,46771,360,13000,0,57),
('Orc Village -> Kamael Village',12052,-117251,46771,360,17000,0,57),
('Elven Village -> Kamael Village',12053,-117251,46771,360,16000,0,57),
('Town of Aden -> Kamael Village',12054,-117251,46771,360,26000,0,57),
('Talking Village -> Kamael Village',12055,-117251,46771,360,10000,0,57),
('Dwarven Village -> Kamael Village',12056,-117251,46771,360,32000,0,57),
('Soul Harbor -> Nornil\'s Garden',12057,-119534,87176,-12593,0,0,57),
('Nornil\'s Garden -> Soul Harbor',12058,-73494,53507,-3680,0,0,57),
('Fantasy Isle -> Krateis Cube',12108,-70293,-71029,-1416,0,0,57),
('Krateis Cube -> Fantasy Isle',12109,-59224,-56837,-2032,0,0,57),
('Gludio -> Airship dock',12118,-149406,255247,-80,0,0,57),
('Airship dock -> Talking Island',12119,-84752,243122,-3728,0,0,57),
('Talking Island -> Airship dock',12120,-149406,255247,-80,0,0,57),
('Fantasy Isle -> Handys Block',12121,-57328,-60566,-2360,0,0,57),
('Keucereus -> Heart of Infinity',12122,-204288,242026,1744,0,0,57),
('Handys Block -> Fantasy Isle',12139,-59157,-56906,-2032,0,0,57),
('Guide of Immortality -> Seed of Infinity',14000,-183292,206063,-12888,0,0,57),
('Gatekeeper of the Abyss -> Seed of Infinity',14001,-212836,209824,4288,0,0,57),
('Gludio Castle -> Front of the Gludio Castle',13000,-15043,116596,-3208,0,0,57),
('Gludio Castle -> Gludio Town Square',13001,-14393,123671,-3144,0,0,57),
('Gludio Castle -> Front of the Shanty Fortress',13002,-58761,154663,-2701,0,0,57),
('Gludio Castle -> Front of the Southern Fortress',13003,-29856,214275,-3680,0,0,57),
('Gludio Castle -> Ruins of Agony',13004,-41248,122848,-2912,500,0,57),
('Gludio Castle -> Ruins of Despair',13005,-19120,136816,-3762,500,0,57),
('Gludio Castle -> The Ant Nest',13006,-9959,176184,-4160,500,0,57),
('Gludio Castle -> Windawood Manor',13007,-28327,155125,-3496,500,0,57),
('Dion Castle -> Front of Dion Castle',13008,19888,153395,-3144,0,0,57),
('Dion Castle -> Dion Town Square',13009,19025,145245,-3107,0,0,57),
('Dion Castle -> Front of the Hive Fortress',13010,20126,188254,-3392,0,0,57),
('Dion Castle -> Entrance to Floran Village',13011,17430,170103,-3506,0,0,57),
('Dion Castle -> Cruma Marshlands',13012,5106,126916,-3664,500,0,57),
('Dion Castle -> Fortress of Resistance',13013,47382,111278,-2104,500,0,57),
('Dion Castle -> Plains of Dion',13014,630,179184,-3720,500,0,57),
('Dion Castle -> Tanor Canyon',13015,60374,164301,-2856,500,0,57),
('Giran Castle -> Front of Giran Castle',13016,107954,145841,-3224,0,0,57),
('Giran Castle -> Giran Town Square',13017,81749,149171,-3464,0,0,57),
('Giran Castle -> Front of the Valley Fortress',13018,125934,118277,-3080,0,0,57),
('Giran Castle -> Giran Harbor',13019,47938,186864,-3420,0,0,57),
('Giran Castle -> Brekas Stronghold',13020,85546,131328,-3672,500,0,57),
('Giran Castle -> Devils Isle',13021,43408,206881,-3752,500,0,57),
('Giran Castle -> Dragon Valley',13022,73024,118485,-3720,500,0,57),
('Giran Castle -> Tanor Canyon',13023,60374,164301,-2856,500,0,57),
('Oren Castle -> Front of Oren Castle',13024,75648,39380,-2952,0,0,57),
('Oren Castle -> Oren Town Square',13025,82323,55466,-1480,0,0,57),
('Oren Castle -> Front of the Ivory Fortress',13026,77023,1591,-3608,0,0,57),
('Oren Castle -> Ivory Tower',13027,85391,16228,-3640,500,0,57),
('Oren Castle -> Near the frontier post',13028,109721,7394,-2800,500,0,57),
('Oren Castle -> Sea of Spores',13029,64328,26803,-3768,500,0,57),
('Oren Castle -> Enchanted Valley, Southern Region',13030,124904,61992,-3973,500,0,57),
('Oren Castle -> Ancient Battleground',13031,118509,4779,-4000,500,0,57),
('Aden Castle -> Front of Aden Castle',13032,147428,20161,-2008,0,0,57),
('Aden Castle -> Aden Town Square',13033,147450,28081,-2294,0,0,57),
('Aden Castle -> Front of the Narsell Fortress',13034,160702,51661,-3512,0,0,57),
('Aden Castle -> Front of the Basin Fortress',13035,189664,46042,-4264,0,0,57),
('Aden Castle -> Enchanted Valley, Northen Region',13036,104426,33746,-3825,500,0,57),
('Aden Castle -> Blazing Swamp',13071,155310,-16339,-3320,500,0,57),
('Aden Castle -> Forest of Mirrors',13038,142065,81300,-3000,500,0,57),
('Aden Castle -> Anghel Waterfall',13039,166182,91560,-3168,500,0,57),
('Aden Castle -> The Giants Cave Upper Layer',13072,183985,61424,-3992,500,0,57),
('Aden Castle -> The Giants Cave Lower Layer',13073,191754,56760,-7624,500,0,57),
('Innadril Castle -> Front of Innadril Castle',13040,117608,241660,-1408,0,0,57),
('Innadril Castle -> Innadril Town Square',13041,111455,219400,-3546,0,0,57),
('Innadril Castle -> Front of the White Sands Fortress',13042,124651,207877,-3184,0,0,57),
('Innadril Castle -> The Center of Alligator Island',13043,113708,178387,-3232,500,0,57),
('Innadril Castle -> Field of Silence',13044,80987,182423,-3504,500,0,57),
('Innadril Castle -> Field of Whispers',13045,86147,218268,-3592,500,0,57),
('Innadril Castle -> Inside the Garden of Eva',13046,82693,242220,-6712,500,0,57),
('Goddard Castle -> Front of Goddard Castle',13047,153996,-50182,-2992,0,0,57),
('Goddard Castle -> Goddard Town Square',13048,147728,-56331,-2776,0,0,57),
('Goddard Castle -> Front of the Borderland Fortress',13049,153460,-70055,-3312,0,0,57),
('Goddard Castle -> Hot Springs',13050,144880,-113468,-2560,500,0,57),
('Goddard Castle -> Varka Silenos Stronghold',13051,125740,-40864,-3736,500,0,57),
('Goddard Castle -> Ketra Orc Outpost',13052,146990,-67128,-3640,500,0,57),
('Goddard Castle -> Entrance to the Forge of the Gods',13053,169018,-116303,-2432,500,0,57),
('Goddard Castle -> Wall of Argos',13054,165054,-47861,-3560,500,0,57),
('Rune Castle -> Front of Rune Castle',13055,27400,-49180,-1320,0,0,57),
('Rune Castle -> Rune Town Square',13056,43889,-49101,-792,0,0,57),
('Rune Castle -> Front of the Swamp Fortress',13057,71814,-57054,-3088,0,0,57),
('Rune Castle -> Forest of the Dead',13058,52107,-54328,-3158,500,0,57),
('Rune Castle -> Wild Beast Pastures',13059,43805,-88010,-2780,500,0,57),
('Rune Castle -> Swamp of Screams',13060,69340,-50203,-3314,500,0,57),
('Rune Castle -> Valley of Saints',13061,65307,-71445,-3696,500,0,57),
('Rune Castle -> Monastery of Silence',13062,106414,-87799,-2949,500,0,57),
('Rune Castle -> Shyeeds Cavern',14063,76911,-55295,-5824,500,0,57),
('Schuttgart Castle -> Front of Schuttgart Castle',13063,76358,-145548,-1176,0,0,57),
('Schuttgart Castle -> Schuttgart Town Square',13064,87379,-142322,-1336,0,0,57),
('Schuttgart Castle -> Front of the Archaic Fortress',13065,105007,-140874,-3360,0,0,57),
('Schuttgart Castle -> The Center of the Abandoned Coal Mines',13066,144706,-173223,-1520,500,0,57),
('Schuttgart Castle -> Plunderous Plains',13067,111965,-154172,-1528,500,0,57),
('Schuttgart Castle -> Den of Evil',13068,68693,-110438,-1946,500,0,57),
('Schuttgart Castle -> Ice Merchant Cabin',13069,113903,-108752,-860,500,0,57),
('Schuttgart Castle -> Crypts of Disgrace',13070,47692,-115745,-3744,500,0,57);

-- Castle teleports
INSERT INTO `teleport` VALUES
('Gludio Outer -> Out',2001,-18372,113315,-2760,0,0,57),
('Gludio Outer -> In',2002,-18395,112831,-2768,0,0,57),
('Gludio Inner -> Out',2003,-18115,110679,-2528,0,0,57),
('Gludio Inner -> In',2004,-18123,110210,-2512,0,0,57),
('Dion Outer -> Out',2005,22315,156447,-2962,0,0,57),
('Dion Outer -> In',2006,22315,156909,-2962,0,0,57),
('Dion Inner -> Out',2007,22073,159057,-2725,0,0,57),
('Dion Inner -> In',2008,22073,159516,-2706,0,0,57),
('Giran Outer -> Out',2009,112568,144869,-2835,0,0,57),
('Giran Outer -> In',2010,113123,144869,-2835,0,0,57),
('Giran Inner -> Out',2011,115209,145089,-2604,0,0,57),
('Giran Inner -> In',2012,115727,145096,-2579,0,0,57),
('Oren Outer -> Out',2013,78618,36955,-2562,0,0,57),
('Oren Outer -> In',2014,79211,36955,-2562,0,0,57),
('Oren Inner -> Out',2015,81285,37190,-2337,0,0,57),
('Oren Inner -> In',2016,81789,37190,-2306,0,0,57),
('Aden Outer -> Out',2017,147455,8808,-495,0,0,57),
('Aden Outer -> In',2018,147455,7966,-470,0,0,57),
('Aden Inner -> Out',2019,145616,4614,-436,0,0,57),
('Aden Inner -> In',2020,146374,4599,-403,0,0,57),
('Aden Inner -> Out',2021,149283,4610,-432,0,0,57),
('Aden Inner -> In',2022,148575,4607,-403,0,0,57),
('Aden Hall -> Out',2023,147456,6357,-407,0,0,57),
('Aden Hall -> In',2024,147456,6058,-407,0,0,57),
('Aden Terrace -> Out',2025,147458,2010,217,0,0,57),
('Aden Terrace -> In',2026,147458,2408,219,0,0,57),
('Innadril Outer -> Out',2027,116263,245174,-1058,0,0,57),
('Innadril Outer -> In',2028,116263,245750,-1058,0,0,57),
('Innadril Inner -> Out',2029,116021,247821,-826,0,0,57),
('Innadril Inner -> In',2030,116021,248272,-805,0,0,57),
('Goddard Outer -> Out',2031,147459,-45221,-2084,0,0,57),
('Goddard Outer -> In',2032,147459,-45789,-2084,0,0,57),
('Goddard Inner -> Out',2033,145514,-48241,-2383,0,0,57),
('Goddard Inner -> In',2034,146026,-48241,-2383,0,0,57),
('Goddard Inner -> Out',2035,149430,-48245,-2383,0,0,57),
('Goddard Inner -> In',2036,148928,-48245,-2383,0,0,57),
('Goddard Terrace -> Out',2037,148748,-48951,-687,0,0,57),
('Goddard Terrace -> In',2038,148736,-49201,-688,0,0,57),
('Goddard Terrace -> Out',2039,146177,-48942,-687,0,0,57),
('Goddard Terrace -> In',2040,146181,-49209,-686,0,0,57),
('Rune Outer -> Out',2041,18788,-49149,-1240,0,0,57),
('Rune Outer -> In',2042,17716,-49149,-1190,0,0,57),
('Rune Inner -> Out',2043,16037,-49149,-1060,0,0,57),
('Rune Inner -> In',2044,15573,-49162,-1058,0,0,57),
('Rune Inner -> Out',2045,12858,-51332,-1089,0,0,57),
('Rune Inner -> In',2046,12858,-50860,-1089,0,0,57),
('Schuttgart Outer -> Out',2047,77544,-149250,-355,0,0,57),
('Schuttgart Outer -> In',2048,77544,-149869,-355,0,0,57),
('Schuttgart Inner -> Out',2049,75577,-152303,-651,0,0,57),
('Schuttgart Inner -> In',2050,76091,-152303,-651,0,0,57),
('Schuttgart Inner -> Out',2051,79519,-152303,-651,0,0,57),
('Schuttgart Inner -> In',2052,79009,-152303,-651,0,0,57),
('Schuttgart Terrace -> Out',2053,76260,-152991,1044,0,0,57),
('Schuttgart Terrace -> In',2054,76267,-153275,1044,0,0,57),
('Schuttgart Terrace -> Out',2055,78843,-153008,1044,0,0,57),
('Schuttgart Terrace -> In',2056,78832,-153271,1044,0,0,57);

-- Gracia Epilogue
INSERT INTO `teleport` VALUES
('News Informer -> Forge of the Gods',200901,169018,-116303,-2432,10000,0,57),
('News Informer -> Primeval Isle Wharf',200902,11235,-24026,-3640,10000,0,57),
('News Informer -> The Giant\'s Cave',200903,174491,50942,-4360,10000,0,57),
('News Informer -> Den of Evil',200904,68693,-110438,-1904,10000,0,57),
('News Informer -> Mithril Mines Western Entrance',200905,171946,-173352,3440,10000,0,57),
('News Informer -> Mithril Mines Eastern Entrance',200906,178591,-184615,360,10000,0,57),
('News Informer -> Field of Silence',200907,87691,162835,-3520,10000,0,57),
('News Informer -> Field of Whispers',200908,82192,226128,-3656,10000,0,57),
('News Informer -> Crypts of Disgrace',200909,47692,-115745,-3744,10000,0,57),
('News Informer -> Stakato Nest',200910,89513,-44800,-2136,10000,0,57),
('News Informer -> Town of Giran',200911,83551,147945,-3400,100000,0,57),
('News Informer -> Town of Aden',200912,146783,25808,-2008,100000,0,57),
('News Informer -> Town of Oren',200913,82971,53207,-1488,100000,0,57),
('News Informer -> Rune Township',200914,43835,-47749,-792,100000,0,57),
('News Informer -> Town of Goddard',200915,148024,-55281,-2728,100000,0,57),
('Primeval Isle -> Rune Township',200916,43835,-47749,-792,50000,0,57),
('Rune Township -> Primeval Isle',200917,11235,-24026,-3640,6400,0,57),
('Heine -> Isle of Prayer',200918,149518,195280,-3736,7200,0,57),
('Giant\'s Cave -> Lower',200919,191754,56760,-7624,0,0,57),
('Giant\'s Cave -> Upper',200920,183985,61424,-3992,0,0,57),
('Giran Harbor -> Pirate\'s Palace',200921,52241,218775,-3224,0,0,57),
('Aden GK -> The Giant\'s Cave',200922,174491,50942,-4360,7400,0,57), -- retail
('FoG Entrance -> FoG Upper Level',200923,173492,-112272,-5192,0,0,57),
('Krun -> Enter Mines ',200924,173462,-174011,3480,0,0,57),
('Tarum -> Enter Mines ',200925,179299,-182831,-224,0,0,57),
('Goddard -> Forge of the Gods',200926,169018,-116303,-2432,10000,0,57),
('Rune -> Stakato Nest',126,89513,-44800,-2136,9100,0,57);

-- Freya
INSERT INTO `teleport` VALUES
('Oren -> Sel Mahum Training Grounds (West Gate)',15000,76839,63851,-3648,2400,0,57),
('Oren -> Sel Mahum Training Grounds (South Gate)',15001,79414,71496,-3448,3700,0,57),
('Oren -> Sel Mahum Training Grounds (Center)',15002,87448,61460,-3664,1800,0,57),
('Schuttgart -> Mithril Mines',15003,171946,-173352,3440,5300,0,57),
('Enter the Seed of Annihilation -> Entrance SOA',15005,-178262,153430,2472,0,0,57),
('Seed of Infinity Dock',15006,-212843,209695,4280,150000,0,57), -- retail
('Seed of Destruction Dock',15007,-248535,250273,4336,150000,0,57), -- retail
('Seed of Annihilation Dock',15008,-175520,154505,2712,150000,0,57); -- retail

-- High Five
INSERT INTO `teleport` VALUES
('Starting Village -> The Village of Gludin',16000,-80684,149770,-3040,18000,0,57),
('Hunters -> Dragon Valley',16001,73024,118485,-3696,1800,0,57),
('Hunters -> Antharas Lair',16002,131557,114509,-3712,7000,0,57),
('Hunters -> Ivory Tower',16003,85391,16228,-3672,13000,0,57),
('Hunters (Noble) -> The Heart of Antharas Lair',16004,154396,121235,-3808,1000,1,57),
('Hunters (Noble) -> The Heart of Antharas Lair',16005,154396,121235,-3808,1,1,13722),
('Gludio -> Isle of Souls Harbor',16006,-73983,51956,-3680,9300,0,57),
('Aden -> Isle of Souls Harbor',16007,-73983,51956,-3680,22000,0,57),
('Aden -> Ivory Tower',16008,85391,16228,-3672,6200,0,57),
('Photo Snow - Book Castle',16009,-61926,-59504,-1728,0,0,57),
('Photo Snow - Clock Tower',16010,-61342,-57686,-1388,0,0,57),
('Photo Snow - House',16011,-57525,-54523,-1576,0,0,57),
('Photo Snow - Tuba 1',16012,-55355,-56305,-1112,0,0,57),
('Photo Snow - Tuba 2',16013,-55545,-56310,-1256,0,0,57),
('Photo Snow - Tuba 3',16014,-55646,-56314,-1296,0,0,57),
('Photo Snow - Tuba 4',16015,-55748,-56327,-1336,0,0,57),
('Photo Snow - Parade Tunnel a',16016,-58151,-53110,-1688,0,0,57),
('Photo Snow - Parade Tunnel b',16017,-55223,-58832,-1680,0,0,57),
('Photo Snow - Book House',16018,-59075,-59464,-1464,0,0,57);

-- Fortress Teleports
INSERT INTO `teleport` VALUES
-- Shanty Fortress
('Shanty Fortress -> Fortress West Gate',20000,-55240,157193,-2048,0,0,57), -- retail
('Shanty Fortress -> Fortress East Gate',20001,-50296,155793,-2056,0,0,57), -- retail
('Shanty Fortress -> Gludio Town Square',20002,-14393,123671,-3144,0,0,57), -- retail
('Shanty Fortress -> Front of the Gludio Castle Gate',20003,-15043,116596,-3208,0,0,57), -- retail
('Shanty Fortress -> Ruins of Agony',20004,-41248,122848,-2912,500,0,57), -- retail
('Shanty Fortress -> Ruins of Despair',20005,-19120,136816,-3762,500,0,57), -- retail
('Shanty Fortress -> The Ant Nest',20006,-9959,176184,-4160,500,0,57), -- retail
('Shanty Fortress -> Windawood Manor',20007,-28327,155125,-3496,500,0,57), -- retail
-- Southern Fortress
('Southern Fortress -> Fortress West Gate',20008,-25480,219848,-3248,0,0,57), -- retail
('Southern Fortress -> Fortress East Gate',20009,-19886,219821,-3256,0,0,57), -- retail
('Southern Fortress -> Gludio Town Square',20010,-14393,123671,-3144,0,0,57), -- retail
('Southern Fortress -> Front of the Gludio Castle Gate',20011,-15043,116596,-3208,0,0,57), -- retail
('Southern Fortress -> Ruins of Agony',20012,-41248,122848,-2912,500,0,57), -- retail
('Southern Fortress -> Ruins of Despair',20013,-19120,136816,-3762,500,0,57), -- retail
('Southern Fortress -> The Ant Nest',20014,-9959,176184,-4160,500,0,57), -- retail
('Southern Fortress -> Windawood Manor',20015,-28327,155125,-3496,500,0,57), -- retail
-- Hive Fortress
('Hive Fortress -> Fortress North Gate',20016,15383,186025,-2920,0,0,57), -- retail
('Hive Fortress -> Fortress South Gate',20017,18046,190017,-2920,0,0,57), -- retail
('Hive Fortress -> Dion Town Square',20018,19025,145245,-3107,0,0,57), -- retail
('Hive Fortress -> Front of the Dion Castle Gate',20019,19888,153395,-3144,0,0,57), -- retail
('Hive Fortress -> Cruma Marshlands',20020,5106,126916,-3664,500,0,57), -- retail
('Hive Fortress -> Fortress of Resistance',20021,47382,111278,-2104,500,0,57), -- retail
('Hive Fortress -> Plains of Dion',20022,630,179184,-3720,500,0,57), -- retail
('Hive Fortress -> Tanor Canyon',20023,60374,164301,-2856,500,0,57), -- retail
-- Valley Fortress
('Valley Fortress -> Fortress North Gate',20024,126066,120350,-2584,0,0,57), -- retail
('Valley Fortress -> Fortress South Gate',20025,126023,125853,-2584,0,0,57), -- retail
('Valley Fortress -> Giran Town Square',20026,81749,149171,-3464,0,0,57), -- retail
('Valley Fortress -> Front of the Giran Castle Gate',20027,107954,145841,-3224,0,0,57), -- retail
('Valley Fortress -> Breka\'s Stronghold',20028,85546,131328,-3672,500,0,57), -- retail
('Valley Fortress -> Devil\'s Isle',20029,43408,206881,-3752,500,0,57), -- retail
('Valley Fortress -> Dragon Valley',20030,73024,118485,-3720,500,0,57), -- retail
('Valley Fortress -> Tanor Canyon',20031,60374,164301,-2856,500,0,57), -- retail
-- Ivory Fortress
('Ivory Fortress -> Fortress North Gate',20032,74367,2533,-3040,0,0,57), -- retail
('Ivory Fortress -> Fortress South Gate',20033,71388,6235,-3032,0,0,57), -- retail
('Ivory Fortress -> Oren Town Square',20034,82323,55466,-1480,0,0,57), -- retail
('Ivory Fortress -> Front of the Oren Castle Gate',20035,75648,39380,-2952,0,0,57), -- retail
('Ivory Fortress -> Sea of Spores',20036,64328,26803,-3768,500,0,57), -- retail
('Ivory Fortress -> Enchanted Valley, Southern Region',20037,124904,61992,-3973,500,0,57), -- retail
('Ivory Fortress -> Enchanted Valley, Northern Region',20038,104426,33746,-3825,500,0,57), -- retail
('Ivory Fortress -> Ancient Battleground',20039,118509,-4779,-4000,500,0,57), -- retail
-- Narsell Fortress
('Narsell Fortress -> Fortress West Gate',20040,153112,56872,-3256,0,0,57), -- retail
('Narsell Fortress -> Fortress East Gate',20041,156730,53921,-3256,0,0,57), -- retail
('Narsell Fortress -> Aden Town Square',20042,147450,28081,-2294,0,0,57), -- retail
('Narsell Fortress -> Front of the Aden Castle Gate',20043,147428,20161,-2008,0,0,57), -- retail
('Narsell Fortress -> Ancient Battleground',20044,118509,-4779,-4000,500,0,57), -- retail
('Narsell Fortress -> Enchanted Valley, Southern Region',20045,124904,61992,-3973,500,0,57), -- retail
('Narsell Fortress -> Enchanted Valley, Northern Region',20046,104426,33746,-3825,500,0,57), -- retail
('Narsell Fortress -> Blazing Swamp',20047,155310,-16339,-3320,500,0,57), -- retail
('Narsell Fortress -> The Giant\'s Cave Upper Layer',20048,183985,61424,-3992,500,0,57), -- retail
('Narsell Fortress -> The Giant\'s Cave Lower Layer',20049,191754,56760,-7624,500,0,57), -- retail
-- Bayou Fortress
('Bayou Fortress -> Fortress North Gate',20050,189894,36746,-3408,0,0,57), -- retail
('Bayou Fortress -> Fortress South Gate',20051,189914,42242,-3408,0,0,57), -- retail
('Bayou Fortress -> Aden Town Square',20052,147450,28081,-2294,0,0,57), -- retail
('Bayou Fortress -> Front of the Aden Castle Gate',20053,147428,20161,-2008,0,0,57), -- retail
('Bayou Fortress -> Ancient Battleground',20054,118509,-4779,-4000,500,0,57), -- retail
('Bayou Fortress -> Enchanted Valley, Southern Region',20055,124904,61992,-3973,500,0,57), -- retail
('Bayou Fortress -> Enchanted Valley, Northern Region',20056,104426,33746,-3825,500,0,57), -- retail
('Bayou Fortress -> Blazing Swamp',20057,155310,-16339,-3320,500,0,57), -- retail
('Bayou Fortress -> The Giant\'s Cave Upper Layer',20058,183985,61424,-3992,500,0,57), -- retail
('Bayou Fortress -> The Giant\'s Cave Lower Layer',20059,191754,56760,-7624,500,0,57), -- retail
-- White Sands Fortress
('White Sands Fortress -> Fortress West Gate',20060,116336,203775,-3336,0,0,57), -- retail
('White Sands Fortress -> Fortress East Gate',20061,120487,206093,-3336,0,0,57), -- retail
('White Sands Fortress -> Heine Town Square',20062,111455,219400,-3546,0,0,57), -- retail
('White Sands Fortress -> Front of the Innadril Castle Gate',20063,117608,241660,-1408,0,0,57), -- retail
('White Sands Fortress -> Field of Silence Center',20064,80987,182423,-3504,500,0,57), -- retail
('White Sands Fortress -> Field of Whispers Center',20065,86147,218268,-3592,500,0,57), -- retail
('White Sands Fortress -> The Center of Alligator Island',20066,113708,178387,-3232,500,0,57), -- retail
('White Sands Fortress -> Inside the Garden of Eva',20067,82693,242220,-6712,500,0,57), -- retail
-- Borderland Fortress
('Borderland Fortress -> Fortress West Gate',20068,157017,-68977,-2864,0,0,57), -- retail
('Borderland Fortress -> Fortress East Gate',20069,161618,-72057,-2864,0,0,57), -- retail
('Borderland Fortress -> Goddard Town Square',20070,147728,-56331,-2776,0,0,57), -- retail
('Borderland Fortress -> Front of the Goddard Castle Gate',20071,153996,-50182,-2992,500,0,57), -- retail
('Borderland Fortress -> Varka Silenos Stronghold',20072,125740,-40864,-3736,500,0,57), -- retail
('Borderland Fortress -> Ketra Orc Outpost',20073,146990,-67128,-3640,500,0,57), -- retail
('Borderland Fortress -> Entrance to the Forge of the Gods',20074,169018,-116303,-2432,500,0,57), -- retail
('Borderland Fortress -> Wall of Argos',20075,165054,-47861,-3560,500,0,57), -- retail
-- Swamp Fortress
('Swamp Fortress -> Fortress North Gate',20076,68692,-63928,-2784,0,0,57), -- retail
('Swamp Fortress -> Fortress South Gate',20077,70820,-58862,-2784,0,0,57), -- retail
('Swamp Fortress -> Rune Town Square',20078,43889,-49101,-792,0,0,57), -- retail
('Swamp Fortress -> Front of the Rune Castle Gate',20079,27400,-49180,-1320,0,0,57), -- retail
('Swamp Fortress -> Forest of the Dead',20080,52107,-54328,-3158,500,0,57), -- retail
('Swamp Fortress -> Wild Beast Pastures',20081,43805,-88010,-2780,500,0,57), -- retail
('Swamp Fortress -> Swamp of Screams',20082,69340,-50203,-3314,500,0,57), -- retail
('Swamp Fortress -> Valley of Saints',20083,65307,-71445,-3696,500,0,57), -- retail
('Swamp Fortress -> Shyeed\'s Cavern',20084,76911,-55295,-5824,500,0,57), -- retail
-- Archaic Fortress
('Archaic Fortress -> Fortress West Gate',20085,107248,-140450,-2960,0,0,57), -- retail
('Archaic Fortress -> Fortress East Gate',20086,111769,-141775,-2920,0,0,57), -- retail
('Archaic Fortress -> Schuttgart Town Square',20087,87379,-142322,-1336,0,0,57), -- retail
('Archaic Fortress -> Front of the Schuttgart Castle Gate',20088,76358,-145548,-1176,0,0,57), -- retail
('Archaic Fortress -> Crypts of Disgrace',20089,47692,-115745,-3744,500,0,57), -- retail
('Archaic Fortress -> Plunderous Plains',20090,111965,-154172,-1528,500,0,57), -- retail
('Archaic Fortress -> Den of Evil',20091,68693,-110438,-1946,500,0,57), -- retail
('Archaic Fortress -> Ice Merchant Cabin',20092,113903,-108752,-860,500,0,57), -- retail
-- Floran Fortress
('Floran Fortress -> Fortress West Gate',20093,3798,148797,-2888,0,0,57), -- retail
('Floran Fortress -> Fortress East Gate',20094,7693,150682,-2888,0,0,57), -- retail
('Floran Fortress -> Gludio Town Square',20095,-14393,123671,-3144,0,0,57), -- retail
('Floran Fortress -> Dion Town Square',20096,19025,145245,-3107,0,0,57), -- retail
('Floran Fortress -> The Ant Nest',20097,-9959,176184,-4160,500,0,57), -- retail
('Floran Fortress -> Windawood Manor',20098,-28327,155125,-3496,500,0,57), -- retail
('Floran Fortress -> Plains of Dion',20099,630,179184,-3720,500,0,57), -- retail
('Floran Fortress -> Tanor Canyon',20100,60374,164301,-2856,500,0,57), -- retail
-- Cloud Mountain Fortress
('Cloud Mountain Fortress -> Fortress North Gate',20101,-54275,89255,-2824,0,0,57), -- retail
('Cloud Mountain Fortress -> Fortress South Gate',20102,-52274,93334,-2816,0,0,57), -- retail
('Cloud Mountain Fortress -> Gludio Town Square',20103,-14393,123671,-3144,0,0,57), -- retail
('Cloud Mountain Fortress -> Oren Town Square',20104,82323,55466,-1480,0,0,57), -- retail
('Cloud Mountain Fortress -> The Ant Nest',20105,-9959,176184,-4160,500,0,57), -- retail
('Cloud Mountain Fortress -> Windawood Manor',20106,-28327,155125,-3496,500,0,57), -- retail
('Cloud Mountain Fortress -> Sea of Spores',20107,64328,26803,-3768,500,0,57), -- retail
('Cloud Mountain Fortress -> Ancient Battleground',20108,118509,-4779,-4000,500,0,57), -- retail
-- Tanor Fortress
('Tanor Fortress -> Fortress North Gate',20109,58923,137789,-1752,0,0,57), -- retail
('Tanor Fortress -> Fortress South Gate',20110,61551,141036,-1752,0,0,57), -- retail
('Tanor Fortress -> Dion Town Square',20111,19025,145245,-3107,0,0,57), -- retail
('Tanor Fortress -> Giran Town Square',20112,81749,149171,-3464,0,0,57), -- retail
('Tanor Fortress -> The Ant Nest',20113,-9959,176184,-4160,500,0,57), -- retail
('Tanor Fortress -> Windawood Manor',20114,-28327,155125,-3496,500,0,57), -- retail
('Tanor Fortress -> Dragon Valley',20115,73024,118485,-3720,500,0,57), -- retail
('Tanor Fortress -> Tanor Canyon',20116,60374,164301,-2856,500,0,57), -- retail
-- Dragonspine Fortress
('Dragonspine Fortress -> Fortress North Gate',20117,12468,93196,-3424,0,0,57), -- retail
('Dragonspine Fortress -> Fortress South Gate',20118,10494,96881,-3424,0,0,57), -- retail
('Dragonspine Fortress -> Dion Town Square',20119,19025,145245,-3107,0,0,57), -- retail
('Dragonspine Fortress -> Oren Town Square',20120,82323,55466,-1480,0,0,57), -- retail
('Dragonspine Fortress -> The Ant Nest',20121,-9959,176184,-4160,500,0,57), -- retail
('Dragonspine Fortress -> Windawood Manor',20122,-28327,155125,-3496,500,0,57), -- retail
('Dragonspine Fortress -> Sea of Spores',20123,64328,26803,-3768,500,0,57), -- retail
('Dragonspine Fortress -> Ancient Battleground',20124,118509,-4779,-4000,500,0,57), -- retail
-- Antharas Fortress
('Antharas Fortress -> Fortress West Gate',20125,77834,89176,-2880,0,0,57), -- retail
('Antharas Fortress -> Fortress East Gate',20126,80441,92831,-2880,0,0,57), -- retail
('Antharas Fortress -> Oren Town Square',20127,82323,55466,-1480,0,0,57), -- retail
('Antharas Fortress -> Giran Town Square',20128,81749,149171,-3464,0,0,57), -- retail
('Antharas Fortress -> Sea of Spores',20129,64328,26803,-3768,500,0,57), -- retail
('Antharas Fortress -> Ancient Battleground',20130,118509,-4779,-4000,500,0,57), -- retail
('Antharas Fortress -> Dragon Valley',20131,73024,118485,-3720,500,0,57), -- retail
('Antharas Fortress -> Tanor Canyon',20132,60374,164301,-2856,500,0,57), -- retail
-- Western Fortress
('Western Fortress -> Fortress North Gate',20133,112356,-17243,-992,0,0,57), -- retail
('Western Fortress -> Fortress South Gate',20134,110349,-13288,-922,0,0,57), -- retail
('Western Fortress -> Aden Town Square',20135,147450,28081,-2294,0,0,57), -- retail
('Western Fortress -> Oren Town Square',20136,82323,55466,-1480,0,0,57), -- retail
('Western Fortress -> Goddard Town Square',20137,147728,-56331,-2776,0,0,57), -- retail
('Western Fortress -> Enchanted Valley, Northern Region',20138,104426,33746,-3825,500,0,57), -- retail
('Western Fortress -> Blazing Swamp',20139,155310,-16339,-3320,500,0,57), -- retail
('Western Fortress -> Ancient Battleground',20140,118509,-4779,-4000,500,0,57), -- retail
('Western Fortress -> Entrance to the Forge of the Gods',20141,169018,-116303,-2432,500,0,57), -- retail
('Western Fortress -> Wall of Argos',20142,165054,-47861,-3560,500,0,57), -- retail
('Western Fortress -> The Giant\'s Cave Upper Layer',20143,183985,61424,-3992,500,0,57), -- retail
('Western Fortress -> The Giant\'s Cave Lower Layer',20144,191754,56760,-7624,500,0,57), -- retail
-- Hunter's Fortress
('Hunters Fortress -> Fortress North Gate',20145,124180,93249,-2144,0,0,57), -- retail
('Hunters Fortress -> Fortress South Gate',20146,126155,97124,-2144,0,0,57), -- retail
('Hunters Fortress -> Aden Town Square',20147,147450,28081,-2294,0,0,57), -- retail
('Hunters Fortress -> Giran Town Square',20148,81749,149171,-3464,0,0,57), -- retail
('Hunters Fortress -> Enchanted Valley, Northern Region',20149,104426,33746,-3825,500,0,57), -- retail
('Hunters Fortress -> Blazing Swamp',20150,155310,-16339,-3320,500,0,57), -- retail
('Hunters Fortress -> Dragon Valley',20151,73024,118485,-3720,500,0,57), -- retail
('Hunters Fortress -> Tanor Canyon',20152,60374,164301,-2856,500,0,57), -- retail
('Hunters Fortress -> The Giant\'s Cave Upper Layer',20153,183985,61424,-3992,500,0,57), -- retail
('Hunters Fortress -> The Giant\'s Cave Lower Layer',20154,191754,56760,-7624,500,0,57), -- retail
-- Aaru Fortress
('Aaru Fortress -> Fortress North Gate',20155,73205,183893,-2584,0,0,57), -- retail
('Aaru Fortress -> Fortress South Gate',20156,72822,188128,-2584,0,0,57), -- retail
('Aaru Fortress -> Heine Town Square',20157,111455,219400,-3546,0,0,57), -- retail
('Aaru Fortress -> Giran Town Square',20158,81749,149171,-3464,0,0,57), -- retail
('Aaru Fortress -> Inside the Garden of Eva',20159,82693,242220,-6712,500,0,57), -- retail
('Aaru Fortress -> The Center of Alligator Island',20160,113708,178387,-3232,500,0,57), -- retail
('Aaru Fortress -> Dragon Valley',20161,73024,118485,-3720,500,0,57), -- retail
('Aaru Fortress -> Tanor Canyon',20162,60374,164301,-2856,500,0,57), -- retail
-- Demon Fortress
('Demon Fortress -> Fortress West Gate',20163,98920,-56423,-624,0,0,57), -- retail
('Demon Fortress -> Fortress East Gate',20164,102390,-54320,-632,0,0,57), -- retail
('Demon Fortress -> Rune Town Square',20165,43889,-49101,-792,0,0,57), -- retail
('Demon Fortress -> Goddard Town Square',20166,147728,-56331,-2776,0,0,57), -- retail
('Demon Fortress -> Swamp of Screams',20167,69340,-50203,-3314,500,0,57), -- retail
('Demon Fortress -> Valley of Saints',20168,65307,-71445,-3696,500,0,57), -- retail
('Demon Fortress -> Entrance to the Forge of the Gods',20169,169018,-116303,-2432,500,0,57), -- retail
('Demon Fortress -> Wall of Argos',20170,165054,-47861,-3560,500,0,57), -- retail
('Demon Fortress -> Shyeed\'s Cavern',20171,76911,-55295,-5824,500,0,57), -- retail
-- Monastic Fortress
('Monastic Fortress -> Fortress North Gate',20172,72388,-96770,-1424,0,0,57), -- retail
('Monastic Fortress -> Fortress South Gate',20173,71937,-92600,-1416,0,0,57), -- retail
('Monastic Fortress -> Rune Town Square',20174,43889,-49101,-792,0,0,57), -- retail
('Monastic Fortress -> Schuttgart Town Square',20175,87379,-142322,-1336,0,0,57), -- retail
('Monastic Fortress -> Swamp of Screams',20176,69340,-50203,-3314,500,0,57), -- retail
('Monastic Fortress -> Valley of Saints',20177,65307,-71445,-3696,500,0,57), -- retail
('Monastic Fortress -> Den of Evil',20178,68693,-110438,-1946,500,0,57), -- retail
('Monastic Fortress -> Ice Merchant Cabin',20179,113903,-108752,-860,500,0,57), -- retail
('Monastic Fortress -> Shyeed\'s Cavern',20180,76911,-55295,-5824,500,0,57); -- retail
