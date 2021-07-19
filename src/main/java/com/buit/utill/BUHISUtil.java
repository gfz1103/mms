package com.buit.utill;

import java.lang.reflect.InvocationTargetException;
import java.math.BigDecimal;
import java.text.DateFormat;
import java.text.DecimalFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.UUID;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import com.buit.system.utill.AgeUtil;
import com.buit.system.utill.ObjectToTypes;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import com.buit.commons.BaseException;

import cn.hutool.core.bean.BeanUtil;
import cn.hutool.core.map.CaseInsensitiveMap;
import cn.hutool.core.util.StrUtil;

@Service
public class BUHISUtil {

	static final Logger logger = LoggerFactory.getLogger(BUHISUtil.class);


	/**
	 * 根据sbxh取消收费项目所对应的物资
	 *
	 * @param hospitalId 机构ID
	 * @param sbxh       识别序号
	 */
	public void deleteSupplies(Integer hospitalId, Integer sbxh) {
		//deleteSuppliesJG(sbxh, "1", hospitalId);
	} // 根据处置开的项目去取对应的价格本计算金额

	/**
	 * 根据sbxh取消收费项目所对应的物资
	 *
	 * @param hospitalId 机构序号
	 * @param jlxh       记录序号
	 */
	public void deleteSuppliesZY(Integer hospitalId, Integer jlxh) {
		//deleteSuppliesJG(jlxh, "2", hospitalId);
	} // 根据处置开的项目去取对应的价格本计算金额
	/**
	 * 结果集List<Map,String> 转List<T>
	 *
	 * @param <T>
	 * @param mapList
	 * @param bean
	 * @return
	 * @author wy
	 */
//	@SuppressWarnings("hiding")
	public static <T> List<T> ListToResultSet(List<Map<String, Object>> mapList, T bean) {
		List<T> resultList = new ArrayList<T>();
		try {
			for (Map<String, Object> map : mapList) {
				try {
					bean =  (T) bean.getClass().getDeclaredConstructor().newInstance();
				} catch (IllegalArgumentException | InvocationTargetException | NoSuchMethodException
						| SecurityException e) {
					e.printStackTrace();
				}
				T t = BeanUtil.fillBeanWithMapIgnoreCase(map, bean, true);
				resultList.add(t);
			}
		} catch (InstantiationException e) {
			logger.error(e.getMessage(), e);
		} catch (IllegalAccessException e) {
			logger.error(e.getMessage(), e);
		}
		return resultList;
	}

	/**
	 * @param birthday
	 * @param nowDate
	 * @return Map<String, Object> 返回类型
	 * @throws @Title: getPersonAge
	 * @Description: TODO 年龄大于等于3*12个月的，用岁表示； 小于3*12个月而又大于等于1*12个月的，用岁月表示；
	 *               小于12个月而又大于等于6个月的，用月表示； 小于6个月而大于等于29天的，用月天表示； 大于72小时小于29天的，用天表示；
	 *               小于72小时的，用小时表示。
	 * @author 龚方舟
	 */
	public static Map<String, Object> getPersonAge(Date birthday, Date nowDate) {
		if (birthday == null) {
			throw BaseException.create("ERROR_DCTWORK_OP_0040");
		}
		Calendar now = Calendar.getInstance();
		Calendar birth = Calendar.getInstance();
		birth.setTime(birthday);
		if (nowDate != null) {
			now.setTime(nowDate);
		}

		int age = AgeUtil.calculateAge(birthday, nowDate);
		String reAge = age + "岁";
		if (age < 3 && age >= 1) {
			int month = getMonths(birthday, now.getTime());
			reAge = age + "岁";
			if ((month - 12 * age) > 0) {
				reAge = age + "岁" + (month - 12 * age) + "月";
			}
		} else if (age < 1) {
			int month = getMonths(birthday, now.getTime());
			if (month < 12 && month >= 6) {
				reAge = month + "月";
			} else {
				int day = getPeriod(birthday, null);
				if (day >= 29 && month > 0) {
					if (now.get(Calendar.DAY_OF_MONTH) >= birth.get(Calendar.DAY_OF_MONTH)) {
						day = now.get(Calendar.DAY_OF_MONTH) - birth.get(Calendar.DAY_OF_MONTH);
					} else {
						now.set(Calendar.MONTH, birth.get(Calendar.MONTH) + 1);
						day = now.get(Calendar.DAY_OF_YEAR) - birth.get(Calendar.DAY_OF_YEAR);
					}
					reAge = month + "月";
					if (day > 0) {
						reAge = month + "月" + day + "天";
					}
				} else {
					if (day >= 4) {
						if ((now.get(Calendar.DAY_OF_YEAR) - birth.get(Calendar.DAY_OF_YEAR)) > 0) {
							day = now.get(Calendar.DAY_OF_YEAR) - birth.get(Calendar.DAY_OF_YEAR);
						}
						reAge = day - 1 + "天";
					} else {
						int hour = now.get(Calendar.HOUR_OF_DAY) - birth.get(Calendar.HOUR_OF_DAY);
						reAge = hour + 24 * (day) + "小时";
					}
				}
			}
		}
		HashMap<String, Object> resBody = new HashMap<String, Object>(16);
		resBody.put("age", age);
		resBody.put("ages", reAge);
		return resBody;
	}

	/**
	 * @param date1 较早的一个日期
	 * @param date2 较晚的一个日期
	 * @return int 返回类型
	 * @Title: getMonths
	 * @Description: TODO 如果前面的日期小于后面的日期将返回-1。
	 * @author 龚方舟
	 */
	public static int getMonths(Date date1, Date date2) {
		Calendar beginDate = Calendar.getInstance();
		beginDate.setTime(date1);
		Calendar now = Calendar.getInstance();
		now.setTime(date2);
		if (beginDate.after(now)) {
			return -1;
		}
		int mon = now.get(Calendar.MONTH) - beginDate.get(Calendar.MONTH);
		if (now.get(Calendar.DAY_OF_MONTH) < beginDate.get(Calendar.DAY_OF_MONTH)) {
			if (now.getActualMaximum(Calendar.DAY_OF_MONTH) != now.get(Calendar.DAY_OF_MONTH)) {
				mon -= 1;
			}
		}
		if (now.get(Calendar.YEAR) == beginDate.get(Calendar.YEAR)) {
			return mon;
		}
		return 12 * (now.get(Calendar.YEAR) - beginDate.get(Calendar.YEAR)) + mon;
	}

	/**
	 * 计算两个日期之间的天数，参数null表示当前日期。
	 *
	 * @param date1
	 * @param date2
	 * @return
	 */
	public static int getPeriod(Date date1, Date date2) {
		if (date1 == null && date2 == null) {
			return 0;
		}
		if (date1 != null && date2 != null && date1.compareTo(date2) == 0) {
			return 0;
		}
		Calendar begin = Calendar.getInstance();
		if (date1 != null) {
			begin.setTime(date1);
		}
		Calendar end = Calendar.getInstance();
		if (date2 != null) {
			end.setTime(date2);
		}
		if (begin.after(end)) {
			Calendar temp = end;
			end = begin;
			begin = temp;
		}
		if (end.get(Calendar.YEAR) == begin.get(Calendar.YEAR)) {
			return end.get(Calendar.DAY_OF_YEAR) - begin.get(Calendar.DAY_OF_YEAR);
		}
		int years = end.get(Calendar.YEAR) - begin.get(Calendar.YEAR);
		int days = begin.getActualMaximum(Calendar.DAY_OF_YEAR) - begin.get(Calendar.DAY_OF_YEAR);
		for (int i = 0; i < years - 1; i++) {
			begin.add(Calendar.YEAR, 1);
			days += begin.getActualMaximum(Calendar.DAY_OF_YEAR);
		}
		days += end.get(Calendar.DAY_OF_YEAR);
		return days;
	}

	/**
	 * 得到几天前的时间
	 *
	 * @param d
	 * @param day
	 * @return
	 */
	public static Date getDateBefore(Date d, int day) {
		Calendar now = Calendar.getInstance();
		now.setTime(d);
		now.set(Calendar.DATE, now.get(Calendar.DATE) - day);
		return now.getTime();
	}

	/**
	 * 日期转换为字符串，如果pattern为null，使用“yyyy-MM-dd”的格式转换。
	 *
	 * @param date
	 * @param pattern
	 * @return
	 */
	public static String toString(Date date, String pattern) {
		DateFormat sdf = pattern == null ? new SimpleDateFormat("yyyy-MM-dd") : new SimpleDateFormat(pattern);
		return sdf.format(date);
	}

	/**
	 * tochar函数转化
	 *
	 * @param prop
	 * @param format
	 * @return
	 */
	public static String toChar(String prop, String format) {
		// 由于使用的是sql查询，无法使用hql中的str自动转化，增加判断收工更改，只适用oracle与DB2
		String tochar = "";
		tochar += "to_char(" + prop + ",'" + format + "')";
		return tochar;
	}

	/**
	 * 结果集List<Object> 转 List<Map<String,String>>
	 *
	 * @param objList
	 * @return
	 */
	@SuppressWarnings("hiding")
	public static <T> List<Map<String, Object>> ListObjToMap(List<T> objList) {
		List<Map<String, Object>> resultList = new ArrayList<>();
		for (T obj : objList) {
			Map<String, Object> map = BeanUtil.beanToMap(obj);
			Map<String, Object> result = new CaseInsensitiveMap<String, Object>(map);
			resultList.add(result);
		}
		return resultList;
	}

	/**
	 * 将日期转换成Date对象，支持的格式为yyyy-MM-dd HH:mm:ss，日期分隔符为（-,/,\）中的任意一个，
	 * 时间分隔符为（:,-,/）中的任意一个。 如果传入的日期格式不正确将返回null。
	 *
	 * @param str
	 * @return 如果传入的日期格式正确将返回一个Date对象，否则返回null。
	 */
	public static Date toDate(String str) {
		if (str == null || str.length() < 10) {
			return null;
		}
		String date = str.substring(0, 10);
		String pattern = "/^((((19|20)\\d{2})-(0?[13-9]|1[012])-(0?[1-9]|[12]\\d|30))|(((19|20)\\d{2})-(0?[13578]|1[02])-31)|(((19|20)\\d{2})-0?2-(0?[1-9]|1\\d|2[0-8]))|((((19|20)([13579][26]|[2468][048]|0[48]))|(2000))-0?2-29))$/";
		// 年月日正则表达式有错误
		// String pattern =
		// "\\d{4}[-,/,\\\\]{1}(0[1-9]{1}|1[0-2]{1})[-,/,\\\\]{1}(([0-2]{1}[1-9]{1})|10|20|30|31)";
		if (Pattern.matches(pattern, date)) {
			return null;
		}
		int year = Integer.parseInt(date.substring(0, 4));
		int month = Integer.parseInt(date.substring(5, 7)) - 1;
		int day = Integer.parseInt(date.substring(8, 10));
		Calendar c = Calendar.getInstance();
		c.set(year, month, day, 0, 0, 0);
		if (str.length() < 19) {
			return c.getTime();
		}
		String time = str.substring(11, 19);
		/* 将ptn中的[0-1]改称[0-2]，原因：20点之后会转化成0点 */
		String ptn = "[0-2]{1}([0-9]{1}|2[03]{1})[:,-,/]{1}[0-5]{1}[0-9]{1}[:,-,/]{1}[0-5]{1}[0-9]{1}";
		if (Pattern.matches(ptn, time)) {
			int hour = Integer.parseInt(time.substring(0, 2));
			int minute = Integer.parseInt(time.substring(3, 5));
			int second = Integer.parseInt(time.substring(6, 8));
			c.set(year, month, day, hour, minute, second);
		}
		return c.getTime();
	}

	/**
	 * 计算两个日期的差，参数null表示当前日期。
	 *
	 * @param date1
	 * @param date2
	 * @return
	 */
	public static int getDifferDays(Date date1, Date date2) {
		if (date1 != null && date2 != null) {
			return (int) ((date1.getTime() - date2.getTime()) / (24 * 60 * 60 * 1000));
		} else {
			return 0;
		}
	}

	public static double loadDischargeDays(String ryrq_s, String cyrq_s) {
		Calendar c = Calendar.getInstance();
		Date ryrq_d = toDate(ryrq_s.substring(0, 10));
		Date cyrq_d = toDate(cyrq_s.substring(0, 10));
		Date ryrq = toDate(ryrq_s);
		Date cyrq = toDate(cyrq_s);
		double d = getDifferDays(cyrq_d, ryrq_d);
		System.out.println("buhis计算出的时间差" + d);
		c.setTime(ryrq);
		int ryrq_h = c.get(Calendar.HOUR_OF_DAY);
		double rysd = ryrq_h < 12 ? 0.5 : 1.0;
		c.setTime(cyrq);
		int cyrq_h = c.get(Calendar.HOUR_OF_DAY);
		double cysd = cyrq_h < 12 ? 0.5 : 1.0;
		return d + cysd - rysd;
	}
	/**
	 * 比较两个日期的年月日，忽略时分秒。
	 *
	 * @param d1
	 * @param d2
	 * @return 如果d1晚于d2返回大于零的值，如果d1等于d2返回0，否则返回一个负值。
	 */
	public static int dateCompare(Date d1, Date d2) {
		Calendar c = Calendar.getInstance();
		c.setTime(d1);
		Calendar c2 = Calendar.getInstance();
		c2.set(Calendar.YEAR, c.get(Calendar.YEAR));
		c2.set(Calendar.MONTH, c.get(Calendar.MONTH));
		c2.set(Calendar.DAY_OF_YEAR, c.get(Calendar.DAY_OF_YEAR));
		Date date0 = c2.getTime();

		c.setTime(d2);
		c2.set(Calendar.YEAR, c.get(Calendar.YEAR));
		c2.set(Calendar.MONTH, c.get(Calendar.MONTH));
		c2.set(Calendar.DAY_OF_YEAR, c.get(Calendar.DAY_OF_YEAR));
		Date date1 = c2.getTime();

		return date0.compareTo(date1);
	}
	/***
	 * 格式化Double类型并保留scale位小数 四舍五入
	 * 
	 * @param doubleVal
	 * @param scale     scale必须为大于0的正整数，不能等于0
	 * @return
	 */
	public static String formatBy2Scale(Double doubleVal, int scale) {
		if (null == doubleVal) {
			doubleVal =Double.valueOf(0);
		}
		StringBuffer sbStr = new StringBuffer("0.");
		for (int i = 0; i < scale; i++) {
			sbStr.append("0");
		}
		DecimalFormat myformat = new DecimalFormat(sbStr.toString());
		return myformat.format(doubleVal);
	}
	/**
	 * @Title: uf_cacl_lsbz @Description: TODO 计算历史标志 入参： parameters里的参数 datetime
	 * adt_kssj 开嘱时间，adt_qrsj 确认时间，adt_tzsj 停嘱时间，string as_sypc
	 * 频次编码，as_yzzxsj_str执行时间字符串，long al_lsyz 临时医嘱标志 出参：int lsbz 历史标志 1:历史医嘱
	 * 0:正常医嘱 @param @param dicsypcList @param @param parameters @param @return
	 * 设定文件 @return int 返回类型 @author 龚方舟 @throws
	 */
	public static int uf_cacl_lsbz(List<Map<String, Object>> dicsypcList, Map<String, Object> parameters) {
		SimpleDateFormat sdfdatetime = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		SimpleDateFormat sdfdate = new SimpleDateFormat("yyyy-MM-dd");
		try {
			Date adt_kssj = null;
			if (parameters.get("ldt_kssj") != null) {
				adt_kssj = sdfdatetime.parse(parameters.get("ldt_kssj") + "");
			}
			Date adt_qrsj = null;
			if (parameters.get("ldt_qrsj") != null) {
				adt_qrsj = sdfdatetime.parse(parameters.get("ldt_qrsj") + "");
			}
			Date adt_tzsj = null;
			if (parameters.get("ldt_tzsj") != null) {
				adt_tzsj = sdfdatetime.parse(parameters.get("ldt_tzsj") + "");
			}
			String as_sypc = parameters.get("ls_sypc") + "";
			String as_yzzxsj_str = parameters.get("ls_yzzxsj_str") + "";
			Long al_lsyz = null;
			if (parameters.get("ll_lsyz") != null) {
				al_lsyz = Long.parseLong(parameters.get("ll_lsyz") + "");
			}
			if (al_lsyz == 1) {
				if (adt_qrsj == null) {
					return 0;
				} else {
					return 1;
				}
			}
			if (adt_tzsj == null) {
				return 0;
			}
			if (adt_qrsj == null) {
				adt_qrsj = adt_kssj;
			}
			if (adt_qrsj.getTime() >= adt_tzsj.getTime()) {
				return 1;
			}
			for (int i = 0; i < dicsypcList.size(); i++) {
				if ((dicsypcList.get(i).get("PCBM") + "").equals(as_sypc)) {
					if (StrUtil.isBlank(as_yzzxsj_str) || "0".equals(as_yzzxsj_str)) {
						as_yzzxsj_str = dicsypcList.get(i).get("ZXSJ") + "";
					}
					List<String> ls_zxsjlist = new ArrayList<String>();
					// 将执行时间字符串转换成执行时间列表
					parameters.put("as_yzzxsj_str", as_yzzxsj_str);
					uf_get_zxsj_list(parameters, ls_zxsjlist);
					// 获取每日次数
					int ll_mrcs = ObjectToTypes.parseInt(dicsypcList.get(i).get("MRCS"));
					// 计算总的需要计算的天数
					int ll_total_ts = getPeriod(adt_qrsj, adt_tzsj) + 1;
					// 计算每一天的执行次数 for表示遍历
					for (int ll_ts = 0; ll_ts < ll_total_ts; ll_ts++) {
						// 获取日期
						Date ldt_current = getDateAfter(adt_qrsj, ll_ts);
						parameters.put("as_sypc", as_sypc);
						parameters.put("adt_kssj", adt_kssj);
						parameters.put("ldt_current", ldt_current);
						int bol = uf_check_zx(dicsypcList, parameters);
						if (bol <= 0) {
							continue;
						}
						for (int ll_zxcs = 0; ll_zxcs < ll_mrcs; ll_zxcs++) {
							Date ldt_zxsj = sdfdatetime
									.parse(sdfdate.format(ldt_current) + " " + ls_zxsjlist.get(ll_zxcs));
							if (ldt_zxsj.getTime() > adt_qrsj.getTime() && ldt_zxsj.getTime() < adt_tzsj.getTime()) {
								return 0;
							}
						}
					}
				}
			}
		} catch (ParseException e) {
			logger.error(e.getMessage(), e);
			throw BaseException.create("ERROR_DCTWORK_ZYZKJL_0003");
		}
		return 1;
	}

	/**
	 * @Title: getDateAfter @Description: TODO 得到几天后的时间 @param @param
	 *         d @param @param day @param @return 设定文件 @return Date 返回类型 @author
	 *         龚方舟 @throws
	 */
	public static Date getDateAfter(Date d, int day) {
		Calendar now = Calendar.getInstance();
		now.setTime(d);
		now.set(Calendar.DATE, now.get(Calendar.DATE) + day);
		return now.getTime();
	}

	/**
	 * 
	 * @Title: uf_get_zxsj_list @Description: TODO 入参： parameters里的参数 string
	 * as_yzzxsj_str执行时间字符串，ref string ls_zxsjlist[]用于返回的执行时间列表数组（返回） @param @param
	 * parameters @param @param ls_zxsjlist 设定文件 @return void 返回类型 @author
	 * 龚方舟 @throws
	 */
	public static void uf_get_zxsj_list(Map<String, Object> parameters, List<String> ls_zxsjlist) {
		String as_zxsj_str = parameters.get("as_yzzxsj_str") + "";
		String ls_zxsj = as_zxsj_str.trim();
		if (ls_zxsj != "" || ls_zxsj != null) {
			String[] ll_pos = ls_zxsj.split("-");
			for (int i = 0; i < ll_pos.length; i++) {
				if (ll_pos[i].indexOf(":") < 0) {
					ls_zxsjlist.add(ll_pos[i] + ":00:00");
				} else {
					ls_zxsjlist.add(ll_pos[i] + ":00");
				}
			}
		}
	}

	/**
	 * @Title: uf_check_zx @Description: TODO 判断当前日期是否执行 入参：parameters里的参数 string
	 * as_sypc 使用频次datetime adt_kssj 开始时间(开嘱时间) datetime adt_dqrq 当前日期 出参： int
	 * ll_zxbz 1:需执行 0:不需执行 -1:有错误发生 @param @param dicsypcList @param @param
	 * parameters @param @return 设定文件 @return int 返回类型 @author 龚方舟 @throws
	 */
	public static int uf_check_zx(List<Map<String, Object>> dicsypcList, Map<String, Object> parameters) {
		SimpleDateFormat sdfdate = new SimpleDateFormat("yyyy-MM-dd");
		SimpleDateFormat sdfdatetime = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		int ll_zxbz = 0;
		for (int i = 0; i < dicsypcList.size(); i++) {
			if (dicsypcList.get(i).get("PCBM").toString().equals(parameters.get("as_sypc").toString())) {
				if (Integer.parseInt(dicsypcList.get(i).get("ZXZQ") + "") == 1) {
					ll_zxbz = 1;
					break;
				} else {
					// 取日执行周期
					int ll_zxzq = Integer.parseInt(dicsypcList.get(i).get("ZXZQ") + "");
					String ls_rzxzq = dicsypcList.get(i).get("RZXZQ") + "";
					if (ll_zxzq != ls_rzxzq.length()) {
						ll_zxbz = -1;
						break;
					} else {
						try {
							// 取执行标志
							if (Integer.parseInt(dicsypcList.get(i).get("RLZ") + "") == 1) {
								int weknum = getWeekOfDate(
										sdfdatetime.parse(sdfdatetime.format(parameters.get("ldt_current"))));
								if (ls_rzxzq.length() != weknum) {
									ll_zxbz = Integer.parseInt(ls_rzxzq.substring(weknum - 1, weknum));
								} else {
									ll_zxbz = Integer.parseInt(ls_rzxzq.substring(weknum - 1));
								}
							} else {
								int ll_days = getPeriod(sdfdate.parse(sdfdatetime.format(parameters.get("adt_kssj"))),
										sdfdate.parse(sdfdatetime.format(parameters.get("ldt_current")))) + 1;
								ll_days = ll_days % ll_zxzq;
								if (ll_days == 0) {
									ll_days = ls_rzxzq.length() - 1;
								} else {
									ll_days = ll_days - 1;
								}
								if (ls_rzxzq.length() != ll_days) {
									ll_zxbz = Integer.parseInt(ls_rzxzq.substring(ll_days, ll_days + 1)); // 获取该天的执行标志
								} else {
									ll_zxbz = Integer.parseInt(ls_rzxzq.substring(ll_days)); // 获取该天的执行标志
								}
							}
						} catch (ParseException e) {
							throw BaseException.create("ERROR_DCTWORK_ZYZKJL_0004");
						}
						break;
					}
				}
			} else {
				ll_zxbz = -1;
			}
		}
		return ll_zxbz;
	}

	/**
	 * @Title: getWeekOfDate @Description: TODO
	 * 获取当前日期是星期几将日期转化成数字，星期日表示1，星期六表示2.. @param @param dt @param @return
	 * 设定文件 @return int 返回类型 @author 龚方舟 @throws
	 */
	public static int getWeekOfDate(Date dt) {
		Calendar cal = Calendar.getInstance();
		cal.setTime(dt);
		int w = cal.get(Calendar.DAY_OF_WEEK);
		return w;
	}

	/**
	 * 对象接收参数转MAP,对象为空则新创建一个，Map取值忽略key值大小写
	 * 
	 * @author wy
	 * @param bean
	 * @return
	 */
	public static Map<String, Object> caseInsensitiveMap(Object bean) {
		if (bean == null) {
			bean = new Object();
		}
		Map<String, Object> map = BeanUtil.beanToMap(bean);
		Map<String, Object> result = new CaseInsensitiveMap<String, Object>(map);
		return result;
	}

	/**
	 * map 所有key转换成小写
	 * 
	 * @param orgMap
	 * @return
	 */
	public static Map<String, Object> transformLowerCase(Map<String, Object> orgMap) {
		Map<String, Object> resultMap = new HashMap<>();
		if (orgMap == null || orgMap.isEmpty()) {
			return resultMap;
		}
		Set<String> keySet = orgMap.keySet();
		for (String key : keySet) {
			String newKey = key.toLowerCase();
//			newKey = newKey.replace("_", "");
			resultMap.put(newKey, orgMap.get(key));
		}
		return resultMap;
	}
	/**
	 * 基本的乘法
	 * 
	 * @param n
	 * @param num1
	 * @param num2
	 * @return
	 */
	public static double simpleMultiply(int n, Object num1, Object num2) {
		return formatDouble(n, parseDouble(num1) * parseDouble(num2));
	}

	/**
	 * double型数据转换
	 * 
	 * @param number
	 * @param data
	 * @return
	 */
	public static double formatDouble(int number, double data) {
		return BUHISUtil.getDouble(data, number);
	}

	/**
	 * double四舍五入保留小数
	 * 
	 * @param d
	 * @param i
	 * @return
	 */
	public static double getDouble(Object d, int i) {
		if (d == null) {
			d = 0d;
		}
		String rStr = String.format("%." + i + "f", Double.parseDouble(d + ""));
		double rd = Double.parseDouble(rStr);
		return rd;
	}

	/**
	 * 数据转换成double
	 * 
	 * @param o
	 * @return
	 */
	public static double parseDouble(Object o) {
		if (o == null || "".equals(o)) {
			return  Double.valueOf(0);
		}
		return Double.parseDouble(o + "");
	}
	public static String getDate() {
		Date date = new Date();
		SimpleDateFormat matter = new SimpleDateFormat("yyyy-MM-dd");
		return matter.format(date);
	}
	/**
	 * @Title: uf_cacl_fycs_cq @Description: TODO 入参：parameters里的参数 datetime
	 * adt_kssj 开嘱时间，adt_qrsj 确认时间，adt_tzsj 停嘱时间，adt_ylrq 预领日期，long 首日次数，string
	 * as_sypc 频次编码，as_yzzxsj_str执行时间字符串，long 发药方式， ref datetime
	 * adt_qrsj_list[]确认时间列表, long al_fybz (1:发药 2:退药) 出参：long ll_count
	 * 总的需执行的次数 @param @param dicsypcList @param @param parameters @param @param
	 * adt_qrsj_list @param @return 设定文件 @return int 返回类型 @author 龚方舟 @throws
	 */
	public static int uf_cacl_fycs_cq(List<Map<String, Object>> dicsypcList, Map<String, Object> parameters,
			List<Date> adt_qrsj_list) {
		SimpleDateFormat sdfdatetime = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		SimpleDateFormat sdfdate = new SimpleDateFormat("yyyy-MM-dd");
		int ll_count = 0;
		try {
			Date adt_kssj = null;
			if (parameters.get("ldt_kssj") != null) {
				adt_kssj = sdfdatetime.parse(parameters.get("ldt_kssj") + "");
			}
			Date adt_qrsj = null;
			if (parameters.get("ldt_qrsj") != null) {
				adt_qrsj = sdfdatetime.parse(parameters.get("ldt_qrsj") + "");
			}
			Date adt_tzsj = null;
			if (parameters.get("ldt_tzsj") != null) {
				adt_tzsj = sdfdatetime.parse(parameters.get("ldt_tzsj") + "");
			}
			Date adt_ylrq = null;
			if (parameters.get("adt_ylrq") != null) {
				adt_ylrq = (Date) parameters.get("adt_ylrq");
			}
			int adt_srcs = 0;
			if (parameters.get("ll_srcs") != null) {
				adt_srcs = Integer.parseInt(parameters.get("ll_srcs") + "");
			}
			String as_sypc = parameters.get("ls_sypc") + "";
			String as_yzzxsj_str = parameters.get("ls_yzzxsj_str") + "";
			int al_fybz = 0;
			if (parameters.get("al_fybz") != null) {
				al_fybz = Integer.parseInt(parameters.get("al_fybz") + "");
			}
			for (int i = 0; i < dicsypcList.size(); i++) {
				List<String> ls_zxsjlist = new ArrayList<String>();
				if (dicsypcList.get(i).get("PCBM").toString().equals(as_sypc)) {
					if (as_yzzxsj_str == null || as_yzzxsj_str == "" || "0".equals(as_yzzxsj_str)) {
						as_yzzxsj_str = dicsypcList.get(i).get("ZXSJ") + "";
					}
					// 将执行时间字符串转换成执行时间列表
					parameters.put("as_yzzxsj_str", as_yzzxsj_str);
					uf_get_zxsj_list(parameters, ls_zxsjlist);
					// 获取每日次数
					int ll_mrcs = Integer.parseInt(dicsypcList.get(i).get("MRCS") + "");
					// 如果确认时间为空，则起始时间 = 开嘱时间，否则起始时间 = 确认时间
					Date ldt_qssj = new Date();
					if (adt_qrsj != null) {
						ldt_qssj = adt_qrsj;
					} else if (adt_kssj != null) {
						ldt_qssj = adt_kssj;
					}
					// 计算预领截止时间
					Date ldt_yljzsj = adt_ylrq; // 预领药时间
					// 取预领截止时间和停嘱时间的较小者作为本次提交的终止时间
					Date ldt_zzsj = new Date();
					if (adt_tzsj == null || ldt_yljzsj.getTime() < adt_tzsj.getTime()) {
						ldt_zzsj = ldt_yljzsj;
					} else {
						ldt_zzsj = adt_tzsj;
					}
					// 计算总的需要计算的天数 daysafter获取两个日期之前的天数
					int ll_total_ts = getPeriod(sdfdate.parse(sdfdatetime.format(ldt_qssj)),
							sdfdate.parse(sdfdatetime.format(ldt_zzsj))) + 1;
					Date ldt_zxsj = new Date();
					for (int ll_ts = 0; ll_ts < ll_total_ts; ll_ts++) {
						// 获取计算日期
						Date ldt_current = getDateAfter(ldt_qssj, ll_ts);
						// 该天不需要执行,有错误发生,也认为是不能执行
						parameters.put("as_sypc", as_sypc);
						parameters.put("adt_kssj", adt_kssj);
						parameters.put("ldt_current", ldt_current);
						int bol = uf_check_zx(dicsypcList, parameters);
						if (bol <= 0) {
							continue;
						}
						// 首日（确认时间为空情况下的第一天）
						if (ll_ts == 0 && adt_qrsj == null) {
							// for表示遍历
							for (int ll_zxcs = (ll_mrcs - adt_srcs); ll_zxcs < ll_mrcs; ll_zxcs++) {
								ldt_zxsj = sdfdatetime
										.parse(sdfdate.format(ldt_current) + " " + ls_zxsjlist.get(ll_zxcs));
								adt_qrsj_list.add(ldt_zxsj);
								ll_count++;
							}
						} else {
							// 非首日
							// for表示遍历
							for (int ll_zxcs = 0; ll_zxcs < ll_mrcs; ll_zxcs++) {
								ldt_zxsj = sdfdatetime
										.parse(sdfdate.format(ldt_current) + " " + ls_zxsjlist.get(ll_zxcs));
								// 发药时是否执行的判断
								if (al_fybz == 1 && ldt_zxsj.getTime() > ldt_qssj.getTime()
										&& ldt_zxsj.getTime() < ldt_zzsj.getTime()) {
									adt_qrsj_list.add(ldt_zxsj);
									ll_count++;
								}
								// 退药时是否执行的判断
								if (al_fybz == 2 && ldt_zxsj.getTime() > ldt_qssj.getTime()
										&& ldt_zxsj.getTime() <= ldt_zzsj.getTime()) {
									adt_qrsj_list.add(ldt_zxsj);
									ll_count++;
								}
							}
						}
					}
				}
			}
		} catch (ParseException e) {
			throw BaseException.create("ERROR_DCTWORK_ZYBQYZ_00023");
		}
		return ll_count;
	}

	/**
	 * @Title: uf_cacl_fycs_ls @Description: TODO * 入参：parameters里的参数 datetime
	 * adt_kssj 开嘱时间，string as_sypc 频次编码，as_yzzxsj_str执行时间字符串，ref datetime
	 * adt_qrsj[]确认时间列表 出参：long ll_mrcs 总的需执行的次数 @param @param
	 * dicsypcList @param @param parameters @param @param
	 * adt_qrsj_list @param @return 设定文件 @return int 返回类型 @author 龚方舟 @throws
	 */
	public static int uf_cacl_fycs_ls(List<Map<String, Object>> dicsypcList, Map<String, Object> parameters,
			List<Date> adt_qrsj_list) {
		SimpleDateFormat sdfdatetime = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		SimpleDateFormat sdfdate = new SimpleDateFormat("yyyy-MM-dd");
		int ll_mrcs = 0;
		try {
			Date adt_kssj = null;
			if (parameters.get("ldt_kssj") != null) {
				adt_kssj = sdfdatetime.parse(parameters.get("ldt_kssj") + "");
			}
			String as_sypc = parameters.get("ls_sypc") + "";
			String as_yzzxsj_str = parameters.get("ls_yzzxsj_str") + "";
			for (int i = 0; i < dicsypcList.size(); i++) {
				List<String> ls_zxsjlist = new ArrayList<String>();
				if (dicsypcList.get(i).get("PCBM").toString().equals(as_sypc)) {
					if (as_yzzxsj_str == null || as_yzzxsj_str == "" || "0".equals(as_yzzxsj_str)) {
						as_yzzxsj_str = dicsypcList.get(i).get("ZXSJ") + "";
					}
					// 将执行时间字符串转换成执行时间列表
					parameters.put("as_yzzxsj_str", as_yzzxsj_str);
					uf_get_zxsj_list(parameters, ls_zxsjlist);
					// 获取每日次数
					ll_mrcs = Integer.parseInt(dicsypcList.get(i).get("MRCS") + "");
					// 遍历每日次数，将开嘱时间和执行时间字符数组中的执行时间组成一个时间加入adt_qrsj确认时间中
					// for表示遍历
					Date ldt_zxsj = new Date();
					for (int ll_zxcs = 0; ll_zxcs < ll_mrcs; ll_zxcs++) {
						ldt_zxsj = sdfdatetime.parse(sdfdate.format(adt_kssj) + " " + ls_zxsjlist.get(ll_zxcs));
						adt_qrsj_list.add(ldt_zxsj);
					}
				}
			}
		} catch (ParseException e) {
			throw BaseException.create("ERROR_DCTWORK_ZYBQYZ_00024");
		}
		return ll_mrcs;
	}
	/**
	 * 长期医嘱项目执行次数
	 * 
	 * @param parameters
	 * @param adt_qrsj_list
	 * @param dao
	 * @param ctx
	 * @return
	 * @throws ModelDataOperationException
	 */

	public static int uf_cacl_zxcs_cq(List<Map<String, Object>> dicsypcList, Map<String, Object> parameters,
			Map<String, Object> parameters_cq) {
		SimpleDateFormat sdfdatetime = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		SimpleDateFormat sdfdate = new SimpleDateFormat("yyyy-MM-dd");
		int ll_count = 0;
		try {
			Date adt_kssj = null;
			if (parameters.get("ldt_kssj") != null) {
				adt_kssj = sdfdatetime.parse(parameters.get("ldt_kssj") + "");
			}
			Date adt_qrsj = null;
			if (parameters.get("ldt_qrsj") != null) {
				adt_qrsj = sdfdatetime.parse(parameters.get("ldt_qrsj") + "");
			}
			Date adt_tzsj = null;
			if (parameters.get("ldt_tzsj") != null) {
				adt_tzsj = sdfdatetime.parse(parameters.get("ldt_tzsj") + "");
			}
			int SRCS = 0; // 首日次数
			if (parameters.get("SRCS") != null) {
				SRCS = Integer.parseInt(parameters.get("SRCS") + "");
			}

			String as_sypc = parameters.get("ls_sypc") + "";
			String as_yzzxsj_str = parameters.get("ls_yzzxsj_str") + "";

			for (int i = 0; i < dicsypcList.size(); i++) {
				List<String> ls_zxsjlist = new ArrayList<String>();
				int mrcs = Integer.parseInt(dicsypcList.get(i).get("MRCS") + "");

				if (dicsypcList.get(i).get("PCBM").toString().equals(as_sypc)) {
					if (as_yzzxsj_str == null || as_yzzxsj_str == "" || "0".equals(as_yzzxsj_str)) {
						as_yzzxsj_str = dicsypcList.get(i).get("ZXSJ") + "";
					}
					// 将执行时间字符串转换成执行时间列表
					parameters.put("as_yzzxsj_str", as_yzzxsj_str);
					uf_get_zxsj_list(parameters, ls_zxsjlist);

					// 如果确认时间为空，则起始时间 = 开嘱时间，否则起始时间 = 确认时间
					Date ldt_qssj = new Date();
					if (adt_qrsj != null) {
						ldt_qssj = adt_qrsj;
					} else if (adt_kssj != null) {
						ldt_qssj = adt_kssj;
					}
					// 截止时间为当前时间
					Date ldt_yljzsj = sdfdatetime.parse((sdfdate.format(getDateAfter(new Date(), 1)) + " 00:00:00"));
					// 截止时间和停嘱时间的较小者作为本次提交的终止时间
					Date ldt_zzsj = new Date();
					if (adt_tzsj == null || ldt_yljzsj.getTime() < adt_tzsj.getTime()) {
						ldt_zzsj = ldt_yljzsj;
					} else {
						ldt_zzsj = adt_tzsj;
					}
					// 计算总的需要计算的天数 daysafter获取两个日期之前的天数
					int ll_total_ts = getPeriod(sdfdate.parse(sdfdatetime.format(ldt_qssj)),
							sdfdate.parse(sdfdatetime.format(ldt_zzsj))) + 1;

					// 计算每一天的执行次数
					int ll_zxcs_day = 0;
					Date ldt_zxsj = new Date();
					Date zxsj = new Date();

					// 计算每一天的执行次数
					for (int ll_ts = 0; ll_ts < ll_total_ts; ll_ts++) {
						// 获取计算日期
						Date ldt_current = getDateAfter(ldt_qssj, ll_ts);
						// 该天不需要执行,有错误发生,也认为是不能执行
						parameters.put("as_sypc", as_sypc);
						parameters.put("adt_kssj", adt_kssj);
						parameters.put("ldt_current", ldt_current);
						int bol = uf_check_zx(dicsypcList, parameters);
						if (bol <= 0) {
							continue;
						}
						// 计算该天的执行次数
						if (ll_ts == 0 && adt_qrsj == null && SRCS != 0) {
							for (int j = mrcs - SRCS; j < mrcs; j++) {
								ll_zxcs_day++;
								ldt_zxsj = sdfdatetime
										.parse(sdfdate.format(ldt_current) + " " + ls_zxsjlist.get(j) + ":00");
								Date current = getDateAfter(ldt_zxsj, 1);
								zxsj = sdfdatetime.parse(sdfdate.format(current) + " " + "00:00:00");
							}
						} else {
							for (int j = 0; j < mrcs; j++) {
								ls_zxsjlist.get(j);
								ldt_zxsj = sdfdatetime
										.parse(sdfdate.format(ldt_current) + " " + ls_zxsjlist.get(j) + ":00");
								if (ldt_zxsj.getTime() > ldt_qssj.getTime()) {
									if (ldt_zxsj.getTime() <= ldt_zzsj.getTime()) {
										ll_zxcs_day++;
										// zxsj = ldt_zxsj;
										Date current = getDateAfter(ldt_zxsj, 1);
										zxsj = sdfdatetime.parse(sdfdate.format(current) + " " + "00:00:00");
									}
								}
							}
						}
						if (ll_zxcs_day > 0) {
							ll_count++;
							parameters_cq.put("al_zxcs", ll_zxcs_day);
							parameters_cq.put("currentTime", zxsj);// 确认时间取医嘱的最后执行时间。
						}
					}
				}
			}
		} catch (ParseException e) {
			logger.error(e.getMessage(), e);
			throw BaseException.create("ERROR_DCTWORK_ZYBQYZ_00036");
		}
		return ll_count;
	}
	/**
	 * 判断是否包含中文 @Title: isContainChinese @Description:
	 * TODO(这里用一句话描述这个方法的作用) @param @param str @param @return 设定文件 @return boolean
	 * 返回类型 @author 龚方舟 @throws
	 */
	public static boolean isContainChinese(String str) {
		Pattern p = Pattern.compile("[\u4e00-\u9fa5]");
		Matcher m = p.matcher(str);
		if (m.find()) {
			return true;
		}
		return false;
	}
	/**
	 * 人民币转大写
	 * 
	 * @param amount
	 * @return
	 */
	public static String changeMoneyUpper(Object amount) {
		if (amount == null || amount.toString().length() == 0)
			return null;
		String sNumber = amount.toString();
		String[] oneUnit = { "元", "拾", "佰", "仟", "万", "拾", "佰", "仟", "亿", "拾", "佰", "仟", "兆", "拾", "佰", "仟" };
		String[] twoUnit = { "分", "角" };
		String[] sChinese = { "零", "壹", "贰", "叁", "肆", "伍", "陆", "柒", "捌", "玖" };
		String sign = ""; // 符号位 add by Zhangxw
		if (sNumber.startsWith("-")) {
			sNumber = sNumber.substring(1);
			sign = "负";
		}
		int pointPos = sNumber.indexOf("."); // 小数点的位置
		String sInteger;// 记录整数部分
		String sDecimal;// 记录小数部分
		String value = "";// 记录返回结果
		if (pointPos != -1) {
			// 分解并记录整数部分，注意substring()的用法
			sInteger = sNumber.substring(0, pointPos); // 分解并记录小数部分
			sDecimal = sNumber.substring(pointPos + 1,
					pointPos + 3 < sNumber.length() ? pointPos + 3 : sNumber.length());
		} else { // 没有小数部分的情况
			sInteger = sNumber;
			sDecimal = "";
		} // 格式化整数部分，并记录到返回结果
		for (int i = 0; i < sInteger.length(); i++) {
			int temp = Integer.parseInt(sInteger.substring(i, i + 1));
			value += sChinese[temp] + oneUnit[sInteger.length() - i - 1];
		} // 格式化小数部分，并记录到返回结果
		for (int i = 0; i < sDecimal.length(); i++) {
			int temp = Integer.parseInt(sDecimal.substring(i, i + 1));
			// value += sChinese[temp] + twoUnit[sDecimal.length() - i - 1];
			value += sChinese[temp] + twoUnit[2 - i - 1];
		}
		return sign + value;
	}

	/**
	 * 获得2个日期之间的天数之差 @Title: getWeeksForTem @Description:
	 * TODO(这里用一句话描述这个方法的作用) @param @param begin @param @param datum @param @return
	 * 设定文件 @return int 返回类型 @author 龚方舟 @throws
	 */
	public static int getWeeksForTem(Date begin, Date datum) {
		Calendar beginDate = Calendar.getInstance();
		beginDate.setTime(begin);
		Calendar now = Calendar.getInstance();
		if (datum != null) {
			now.setTime(datum);
		}
		if (dateCompare(beginDate.getTime(), now.getTime()) > 0) {
			return -1;
		}
		int days = getPeriod(begin, datum) + 1;

		return (int) (Math.ceil(days / 7f));
	}

	/**
	 * 获取32位UUID
	 * 
	 * @return
	 */
	public static String UUIDGenerator() {
		return UUID.randomUUID().toString().replaceAll("-", "");
	}
	
	// 用bigDecimal做浮点数加法计算
	public static double add(Object x, Object y) {
		if (x == null) {
			x = 0;
		}
		if (y == null) {
			y = 0;
		}
		double ret = new BigDecimal(x.toString()).add(new BigDecimal(y.toString())).doubleValue();
		return parseDouble(ret);
	}
	public static double doubletwo(double dou) {
		double re = new java.math.BigDecimal(Double.toString(dou)).setScale(2, java.math.BigDecimal.ROUND_HALF_UP)
				.doubleValue();
		return re;
	}

	/**
	 * @name: listToString
	 * @description: list集合转string
	 * @return: java.lang.String
	 * @date: 2020/8/28 9:41
	 * @auther: 老花生
	 *
	 */
	public static String listToString(List<Object> list){
		if(list == null) return "";

		StringBuilder b = new StringBuilder();
		int iMax = list.size() - 1;
		for (int i = 0; i<list.size(); i++) {
			b.append(list.get(i));
			if(i == iMax){
				break;
			}
			b.append(", ");
		}
		return b.toString();
	}
	
	public static String getDateTime() {
		Date date = new Date();
		SimpleDateFormat matter = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		return matter.format(date);
	}

	public static <T> LinkedList<Map<String, Object>> LinkedListObjToMap(LinkedList<T> objList) {
		LinkedList<Map<String, Object>> resultList = new LinkedList<>();
		for (T obj : objList) {
			Map<String, Object> map = BeanUtil.beanToMap(obj);
		//	Map<String, Object> result = new CaseInsensitiveMap<String, Object>(map);
			resultList.add(map);
		}
		return resultList;
	}
}
