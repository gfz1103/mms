ALTER TABLE `opt_sssq`
ADD COLUMN `ssbw` varchar(255) NULL COMMENT '手术部位' AFTER `hzzqtyqk`,
ADD COLUMN `sstw` varchar(255) NULL COMMENT '手术体位' AFTER `ssbw`,
ADD COLUMN `ssbx` varchar(255) NULL COMMENT '手术备血' AFTER `sstw`,
ADD COLUMN `sfjhzss` int NULL COMMENT '是否计划再手术,0：否，1：是' AFTER `ssbx`,
ADD COLUMN `cfss` int NULL COMMENT '重返手术，0：否，1：是' AFTER `sfjhzss`,
ADD COLUMN `zrw` varchar(255) NULL COMMENT '植入物' AFTER `cfss`,
ADD COLUMN `qkdj` int NULL COMMENT '切口等级' AFTER `zrw`;