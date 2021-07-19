CREATE TABLE `ss_jyjg` (
  `sqdh` int COMMENT 'opt_sssq表外键',
  `ygjg` int DEFAULT NULL COMMENT '乙肝结果,-1:阴性，1：阳性',
  `bgjg` int DEFAULT NULL COMMENT '丙肝结果,-1:阴性，1：阳性',
  `mdjg` int DEFAULT NULL COMMENT ' 梅毒结果,-1:阴性，1：阳性',
  `hivjg` int DEFAULT NULL COMMENT 'hiv结果,-1:阴性，1：阳性',
  `gyxgky` int DEFAULT NULL COMMENT '肝炎相关抗原,-1:阴性，1：阳性',
  PRIMARY KEY (`sqdh`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci COMMENT='手术检验结果表';