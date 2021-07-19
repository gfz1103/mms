
CREATE TABLE `cis_wjzjl`  (
  `WJZDH` int(0) NOT NULL COMMENT '危急值单号',
  `BRLX` int(0) NULL COMMENT '病人类型',
  `MZH` int(0) NULL COMMENT '门诊号',
  `ZYH` int(0) NULL COMMENT '住院号',
  `BQDM` int(0) NULL COMMENT '病区代码',
  `KSDM` int(0) NULL COMMENT '科室代码',
  `CH` varchar(50) NULL COMMENT '床号',
  `FSSJ` datetime(0) NULL COMMENT '发送时间',
  `FSRDM` int(0) NULL COMMENT '发送人代码',
  `WJZXMDM` int(0) NULL COMMENT '危急值项目代码',
  `WJZXMMC` varchar(500) NULL COMMENT '危急值项目名称',
  `WJZJG` varchar(255) NULL COMMENT '危急值结果',
  `ZCCKZ` varchar(255) NULL COMMENT '正常参考值',
  `BGH` varchar(100) NULL COMMENT '检查/检验报告号',
  `JSSJ` datetime(0) NULL COMMENT '接收时间',
  `JSRDM` int(0) NULL COMMENT '接收人代码',
  `TZSJ` datetime(0) NULL COMMENT '通知时间',
  `TZYSDM` int(0) NULL COMMENT '通知医生代码',
  `CZSJ` datetime(0) NULL COMMENT '处置时间',
  `CZRDM` int(0) NULL COMMENT '处置人代码',
  `CZCS` varchar(500) NULL COMMENT '处置措施',
  `ZT` int(0) NULL COMMENT '状态 0 未接收/1 已接收/2已通知/3 已处置',
  `YLJGD` int(0) NULL COMMENT '机构id',
  PRIMARY KEY (`WJZDH`)
) COMMENT = '危急值记录表';

CREATE TABLE `cmo_ssxmgx` (
  `fyxh` int NOT NULL COMMENT '费用序号',
  `jbxh` int DEFAULT NULL COMMENT '疾病序号',
  PRIMARY KEY (`fyxh`) USING BTREE
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci COMMENT='手术与收费项目关系表';

CREATE TABLE `cmo_zdss` (
  `ssbm` int DEFAULT NULL COMMENT '手术编码，对应dic_jbbm表jbxh',
  `ssmc` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci DEFAULT NULL COMMENT '手术名称',
  `icd` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci DEFAULT NULL COMMENT 'icd码',
  `ssdj` int DEFAULT NULL COMMENT '手术等级',
  `pydm` varchar(100) DEFAULT NULL COMMENT '拼音代码'
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci COMMENT='重大手术';

ALTER TABLE `his`.`hr_personnel`
ADD COLUMN `ssdj` int NULL COMMENT '手术等级权限' AFTER `LAST_LOGIN_IP`,
ADD COLUMN `shssdj` int NULL COMMENT '审核手术等级' AFTER `ssdj`;


ALTER TABLE `his`.`opt_sssq`
ADD COLUMN `TZZT` int(0) NULL DEFAULT NULL COMMENT '通知状态(1:已通知,0:未通知)' AFTER `sqjg`,
ADD COLUMN `TZTIME` datetime(0) NULL DEFAULT NULL COMMENT '通知时间' AFTER `TZZT`,
ADD COLUMN `TZGH` int(10) UNSIGNED ZEROFILL NULL DEFAULT NULL COMMENT '通知工号' AFTER `TZTIME`;

ALTER TABLE `his`.`dic_jbbm`
MODIFY COLUMN `ZFBZ` int(0) UNSIGNED NULL DEFAULT 0 AFTER `JBLX`;

ALTER TABLE `his`.`opt_sssq`
ADD COLUMN `jzkh` varchar(255) NULL COMMENT '就诊卡号' AFTER `TZGH`;