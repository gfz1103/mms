ALTER TABLE `opt_sssq`
MODIFY COLUMN `ssbw` int NULL DEFAULT NULL COMMENT '手术部位,字典' AFTER `hzzqtyqk`,
MODIFY COLUMN `sstw` int NULL DEFAULT NULL COMMENT '手术体位,字典' AFTER `ssbw`,
MODIFY COLUMN `zrw` int NULL DEFAULT NULL COMMENT '植入物,0：无，1：有' AFTER `cfss`;

ALTER TABLE `ss_jyjg`
MODIFY COLUMN `ygjg` int(0) NULL DEFAULT NULL COMMENT '乙肝结果,-1:阴性，1：阳性,0：未报告' AFTER `sqdh`,
MODIFY COLUMN `bgjg` int(0) NULL DEFAULT NULL COMMENT '丙肝结果,-1:阴性，1：阳性,0：未报告' AFTER `ygjg`,
MODIFY COLUMN `mdjg` int(0) NULL DEFAULT NULL COMMENT ' 梅毒结果,-1:阴性，1：阳性,0：未报告' AFTER `bgjg`,
MODIFY COLUMN `hivjg` int(0) NULL DEFAULT NULL COMMENT 'hiv结果,-1:阴性，1：阳性,0：未报告' AFTER `mdjg`,
MODIFY COLUMN `gyxgky` int(0) NULL DEFAULT NULL COMMENT '肝炎相关抗原,-1:阴性，1：阳性,0：未报告' AFTER `hivjg`;