package com.buit.constans;

/**
 * @Description SYS_XTCS表csmc常量类
 * @Author yueyu
 * @Date 2020/5/11 16:25
 */
public class SysXtcsCsmcCts {

    public final static String QYFYCK = "QYFYCK";
    public final static String MZFYZDSXMS = "MZFYZDSXMS";
    //药房月结时间
    public final static String YJSJ_YF = "YJSJ_YF";
    /**
     * 病人性质_商业保险
     * */
    public static final String BRXZ_SYBX = "BRXZ_SYBX";
    /**
     * 是否启用药房预发药,0是不启用,1启用
     * */
    public static final String SFQYYFYFY = "SFQYYFYFY";
    /**
     * 库存冻结天数,单位是天
     * */
    public static final String KCDJTS = "KCDJTS";
    public static final String JCKCDJTS = "JCKCDJTS";
    public static final String CKSX_YPXQ_YF = "CKSX_YPXQ_YF";
    public static final String CKSX_YPXQ_ORDER_YF = "CKSX_YPXQ_ORDER_YF";

    public static final String CKSX_KCSL_YF = "CKSX_KCSL_YF";
    public static final String CKSX_KCSL_ORDER_YF = "CKSX_KCSL_ORDER_YF";
    public static final String ZDFYYFSB = "ZDFYYFSB";
    //住院病案号码
    public static final String BAHM="BAHM";
    //住院号码
    public static final String ZYHM="ZYHM";

    public static final String CFXQ = "CFXQ";
    //VIP 病人性质
    public static final String VIPBRXZ = "VIPBRXZ";
    public static final String YF_PCPD = "YF_PCPD";
    public static final String PDFYKZ = "PDFYKZ";
    public static final String SFQYPDYPFP = "SFQYPDYPFP";//是否启用盘点药品分配,1是启用,0是不启用 默认是1
    public static final String FYJJNSL = "FYJJNSL";// 发药机机内数量参数 0 关闭 1开启
    public static final String ZYFPSFZCGY = "ZYFPSFZCGY";
    public static final String JKSJSFZCGY = "JKSJSFZCGY";
    public static final String BAHMDYZYHM = "BAHMDYZYHM";
    public static final String YJSJ_YK = "YJSJ_YK";
    public static final String ZYQFTXYZ = "ZYQFTXYZ";
    
  //病区床号显示位数，默认为0时显示全部，其余按值从右截取显示
    public final static String BQCHXSWS = "BQCHXSWS";
    
    //物品计费标志，0表示不启用，1表示启用，默认是0
    public final static String WPJFBZ = "WPJFBZ";
    
    //住院物资收费项目价格，0表示不启用，1表示启用，默认是0
    public static final String WZSFXMJGZY = "WZSFXMJGZY";
    
    //启用消息系统 0:不启用 1:启用,默认不启用
    public static final String QYXXXT = "QYXXXT";
    
    //是否启用抗菌药物管理:0表示不启用，1表示启用，默认为0
    public static final String QYKJYWGL = "QYKJYWGL";
    
    //是否启用住院抗菌药物审批：0表示不开启，1表示开启，默认为0
    public static final String QYZYKJYWSP = "QYZYKJYWSP";
    
    //住院医生站启用 0.不启用1.启用
    public static final String ZYYSQY = "ZYYSQY";
    
    //病区药品预领时间 1:领药日期第二天00:00:00  2:领药日期第二天09:30:00 3:领药日期第二天12:30:00,默认1
    public static final String BQYPYLSJ = "BQYPYLSJ";
    
    //摆药病区药品预领时间 1:领药日期第二天23:59:59  2:领药日期第二天09:30:00, 3:领药日期第二天12:30:00
    public static final String BQYPYLTS = "BQYPYLTS";
    
    //复核医嘱后计费
    public static final String FHYZHJF = "FHYZHJF";
    
    //临时医嘱使用频次参数 ：0：默认乘以SYPC。1：不乘以SYPC(如：bid 发药的时候不乘以2)	
    public static final String LSYZSYPCCS = "LSYZSYPCCS";	
    
    //病区提交天数启用 0.不启用1.启用,默认0
    public static final String BQTJTS = "BQTJTS";	
    
    //医嘱提交最大天数控制,默认0
    public static final String YZTS = "YZTS";	
    
    //住院欠费控制 1表示控制 0表示不控制默认为0
    public static final String ZYQFKZ = "ZYQFKZ";	 
    
    //医嘱录入复核可为同一工号   0:不可为同一个人 1:可为同一个人
    public static final String YZLRFHTYGH = "YZLRFHTYGH";	  
    
    //是否通知出院释放床位,0:否,1:是
    public static final String SFTZCYSFCW = "SFTZCYSFCW";
    
    //包装类型,1:医嘱录入使用包装类型 2使用药品信息中的病房包装
    public static final String YZLR_BZLX = "YZLR_BZLX";
    
    //门诊物品计费标志,0表示不启用,1表示启用,默认是0
    public static final String MZWPJFBZ = "MZWPJFBZ";

    //启用静配中心，0：否，1：是
    public static final String QYJPZX = "QYJPZX";
    
    //启是否显示停嘱医嘱(1是0否)默认0
    public static final String SFXSTZYZ = "SFXSTZYZ";
    //药库库存盘点按批次盘点参数，true：按批次，false：不按批次
    public static final String KCPD_PC = "KCPD_PC";
    public static final String PYFS_YK = "PYFS_YK";
    public static final String PKFS_YK = "PKFS_YK";
    //医保性质
    public static final String YBXZ = "YBXZ";
    //住院结算打印机名称
    public static final String ZYJSDYJMC = "ZYJSDYJMC";
    
    //床位费自动累加,1表示自动累加
    public static final String CWFZDLJ = "CWFZDLJ";
    
    //治疗费用单价
    public static final String ZLFYDJ = "ZLFYDJ";
    
    //医嘱套打每页条数,默认20,设置好后请勿随便修改 以防打印不正确
    public static final String YZTDMYTS = "YZTDMYTS";
    
    //医嘱本打印,转科重整后换页打印,0否,1是
    public static final String ZKCZHHYDY = "ZKCZHHYDY";
    
    //医嘱本打印时间,1是提交后打印,2是复核后打印,默认为2
    public static final String YZBDYSJ = "YZBDYSJ";
    
    //医嘱本打印是否需要套打,0是不需要,1是需要,默认为0
    public static final String YZBDYSFTD = "YZBDYSFTD";

    //处方打印，1：套打，0：白纸打
    public static final String CFTD = "CFTD";
    public static final String JAJGID = "JAJGID";
    
    //是否启用药品补记账0.不启用1.启用
    public static final String SFQYYPJZ = "SFQYYPJZ";

    //是否启用药品补记账0.不启用1.启用
    public static final String SFQYYPJZZF = "SFQYYPJZZF";

    public static final String EKKSDM = "EKKSDM";
    public static final String EKCFDYBZ = "EKCFDYBZ";
    
    //1:按实际时间显示 2:改变时间显示在格子内
    public static final String TWDXSFS = "TWDXSFS";
    
    //危急值定时任务(执行一次/分钟)
    public static final String WJZSJ = "WJZSJ";

    
}
