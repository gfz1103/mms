ALTER TABLE `follow_up_sz`
ADD COLUMN `KSTSDW` varchar(10) NULL COMMENT '开始天数单位(天，周，月，年)' AFTER `SFLX`;

ALTER TABLE `follow_up_sz`
ADD COLUMN `JSTSDW` varchar(10) NULL COMMENT '结束天数单位(天，周，月，年)' AFTER `CYSFKSTS`;