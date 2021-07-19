ALTER TABLE `follow_up_record`
ADD COLUMN `jgid` int NOT NULL COMMENT '机构ID' AFTER `id`;

ALTER TABLE `follow_up_sz`
ADD COLUMN `jgid` int NOT NULL COMMENT '机构ID' AFTER `id`;