ALTER TABLE `opt_sssq`
ADD COLUMN `bszy` varchar(500) NULL COMMENT '病史摘要' AFTER `jzkh`,
ADD COLUMN `hgjcs` varchar(500) NULL COMMENT '后果及措施' AFTER `bszy`;