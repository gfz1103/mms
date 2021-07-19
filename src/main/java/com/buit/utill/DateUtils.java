package com.buit.utill;

import java.sql.Timestamp;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.TimeZone;

/**
 * 处理时间的常用工具类
 */
public  class DateUtils {

    public static  final String YEAR_MONTH_DAY="yyyy-MM-dd";
    public static final String YEAR_MONTH_DAY_HOUR_MINUTE_SECOND="yyyy-MM-dd HH:mm:ss";


    public static Timestamp getMonthStartTime(Timestamp timeStamp) {
        if(null == timeStamp){
            return null;
        }
        Calendar calendar = Calendar.getInstance();
        calendar.setTimeZone(TimeZone.getTimeZone("GMT+8:00"));
        calendar.setTimeInMillis(timeStamp.getTime());
        calendar.set(Calendar.DAY_OF_MONTH, 1);
        calendar.set(Calendar.HOUR_OF_DAY, 0);
        calendar.set(Calendar.SECOND, 0);
        calendar.set(Calendar.MINUTE, 0);
        calendar.set(Calendar.MILLISECOND, 0);
        return new Timestamp(calendar.getTimeInMillis());
    }

    public static Timestamp getMonthEndTime(Timestamp timeStamp) {
        if(null == timeStamp){
            return null;
        }
        Calendar calendar = Calendar.getInstance();
        calendar.setTimeZone(TimeZone.getTimeZone("GMT+8:00"));
        calendar.setTimeInMillis(timeStamp.getTime());
        calendar.set(Calendar.DAY_OF_MONTH, calendar.getActualMaximum(Calendar.DAY_OF_MONTH));
        calendar.set(Calendar.HOUR_OF_DAY, 23);
        calendar.set(Calendar.SECOND, 59);
        calendar.set(Calendar.MINUTE, 59);
        calendar.set(Calendar.MILLISECOND, 999);
        return new Timestamp(calendar.getTimeInMillis());
    }

    /**
     * 获取一天的最小时间
     * @param timeStamp
     * @param
     * @return
     */
    public static Timestamp getDailyStartTime(Long timeStamp) {
        Calendar calendar = Calendar.getInstance();
        calendar.setTimeZone(TimeZone.getTimeZone("GMT+8:00"));
        calendar.setTimeInMillis(timeStamp);
        calendar.set(Calendar.HOUR_OF_DAY, 0);
        calendar.set(Calendar.SECOND, 0);
        calendar.set(Calendar.MINUTE, 0);
        calendar.set(Calendar.MILLISECOND, 0);
        return new Timestamp(calendar.getTimeInMillis());
    }

    /**
     * 获取一天的最大时间
     * @param timeStamp
     * @param
     * @return
     */
    public static Timestamp getDailyEndTime(Long timeStamp) {
        Calendar calendar = Calendar.getInstance();
        calendar.setTimeZone(TimeZone.getTimeZone("GMT+8:00"));
        calendar.setTimeInMillis(timeStamp);
        calendar.set(Calendar.HOUR_OF_DAY, 23);
        calendar.set(Calendar.MINUTE, 59);
        calendar.set(Calendar.SECOND, 59);
        calendar.set(Calendar.MILLISECOND, 999);
        return new Timestamp(calendar.getTimeInMillis());
    }

    /**
     * 时间字符串转换成Timestamp
     * @param
     * @return
     * @throws ParseException
     */
    public static Timestamp convertTimestamp(String dateFormat,String s) throws ParseException {
            SimpleDateFormat simpleDateFormat = new SimpleDateFormat(dateFormat);
            Date date = simpleDateFormat.parse(s);
            long ts = date.getTime();
            return new Timestamp(ts);
    }

    /**
     * 获取当前时间 返回字符串
     * @return
     */
    public static String getCurrentDateStr() {
        Date date = new Date();
        SimpleDateFormat dateFormat= new SimpleDateFormat("yyyy-MM-dd :hh:mm:ss");
        return dateFormat.format(date);
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


    public static void main (String[] args){
        Timestamp timestamp = new Timestamp(System.currentTimeMillis());
        System.out.println(getMonthStartTime(timestamp));
        System.out.println(getMonthEndTime(timestamp));
    }

}
