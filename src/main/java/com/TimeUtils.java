package com;

import org.apache.commons.lang3.time.DateUtils;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

/**
 * @author zholou, wujunbin
 */
public class TimeUtils {
    /**
     * yyyyMMdd
     */
    public static final String YYYYMMDD = "yyyyMMdd";

    public static final String DATE_FORMAT = "yyyyMMdd";

    public static final String DATE_FORMAT_LOWEAST_RATE = "MM/dd/yyyy";

    /**
     * API的格式
     */
    private static ThreadLocal<SimpleDateFormat> yyyymmdd = new ThreadLocal<SimpleDateFormat>() {
        @Override
        protected SimpleDateFormat initialValue() {
            return new SimpleDateFormat(DATE_FORMAT);
        }
    };

    /**
     * 产品中心的时间格式
     */
    private static ThreadLocal<SimpleDateFormat> mmddyyyy = new ThreadLocal<SimpleDateFormat>() {
        @Override
        protected SimpleDateFormat initialValue() {
            return new SimpleDateFormat(DATE_FORMAT_LOWEAST_RATE);
        }
    };



    /**
     * 获取今天x点日期
     *
     * @return
     */
    public static Date getTodayTimeByHours(int hours) {
        Calendar calendar = Calendar.getInstance();
        calendar.set(Calendar.HOUR_OF_DAY, hours);
        calendar.set(Calendar.SECOND, 0);
        calendar.set(Calendar.MINUTE, 0);
        calendar.set(Calendar.MILLISECOND, 0);
        return calendar.getTime();
    }


    /**
     * 根据字符串获取时间
     *
     * @param dateTime
     * @param format
     * @return
     * @throws ParseException
     */
    public static long getTimes(String dateTime, String format) {
        Date date = null;
        try {
            date = new SimpleDateFormat(format).parse(dateTime);
        } catch (ParseException e) {

        }
        if (date == null) {
            return 0;
        }
        return getToday(date.getTime()).getTime();
    }



    /**
     * 获取传入时间搓的凌晨时间
     *
     * @return
     */
    public static Date getToday(long time) {
        Calendar calendar = Calendar.getInstance();
        calendar.setTimeInMillis(time);
        calendar.set(Calendar.HOUR_OF_DAY, 6);
        calendar.set(Calendar.SECOND, 0);
        calendar.set(Calendar.MINUTE, 0);
        calendar.set(Calendar.MILLISECOND, 0);
        return calendar.getTime();
    }



    public static long getDateTimeYyyymmddHhmmss(long time) {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMddHHmmss");
        Calendar calendar = Calendar.getInstance();
        calendar.setTimeInMillis(time);
        String result = sdf.format(calendar.getTime());
        return Long.valueOf(result);
    }

    public static void main(String[] args) throws ParseException {
//        System.out.println(adjustToSixClock("20170923", "yyyyMMdd"));
//        System.out.println(convertDateFormat("02/17/2017", MMSDDSYYYY, YYYYMMDD));
//        Date date = new Date();
//        System.out.println(date.getTime());
//        System.out.println(date);
//        System.out.println(addDay(date.getTime(), 1));
//        System.out.println(new Date(addDay(date.getTime(), 1)));
//        System.out.println(format(date.getTime(), YYYYMMDD));
//        System.out.println(parse("20171023", YYYYMMDD));
//        Date date1 = new Date();
//        Date date2 = DateUtils.addHours(date1, 6);
//        System.out.println(getDateSpan(date2.getTime(), date1.getTime()));
//        System.out.println(isSameDay(date1, date2));
    }
    /*-------------------------------------*/

    /**
     * 将当前时间调整至当天凌晨6点
     *
     * @param dateStr    字符串日期
     * @param dateFormat 字符串日期格式
     * @return
     */
    public static Date adjustToSixClock(String dateStr, String dateFormat) throws ParseException {
        SimpleDateFormat sdf = new SimpleDateFormat(dateFormat);
        Date date = sdf.parse(dateStr);
        Calendar calendar = Calendar.getInstance();
        calendar.setTimeInMillis(date.getTime());
        adjustToSixClock(calendar);
        return calendar.getTime();
    }

    /**
     * 将当前时间调整至当天凌晨6点
     * 例如： 2017-09-21 12:00:00 -> 2017-09-21 06:00:00
     *
     * @param calendar 日历
     * @return
     */
    public static void adjustToSixClock(Calendar calendar) {
        //凌晨6点
        calendar.set(Calendar.HOUR_OF_DAY, 6);
        calendar.set(Calendar.SECOND, 0);
        calendar.set(Calendar.MINUTE, 0);
        calendar.set(Calendar.MILLISECOND, 0);
    }

    /**
     * 将当前时间调整至当天凌晨6点
     * 例如： 2017-09-21 12:00:00 -> 2017-09-21 06:00:00
     *
     * @param timestamp 时间戳
     * @return
     */
    public static Date adjustToSixClock(long timestamp) {
        Calendar calendar = Calendar.getInstance();
        calendar.setTimeInMillis(timestamp);
        adjustToSixClock(calendar);
        return calendar.getTime();
    }


    /**
     * 格式化时间戳， 根据指定format，进行格式转化。
     * 例如
     * input: 1509017818218, yyyyMMdd
     * output: 20171026
     *
     * @param time
     * @param format
     * @return
     */
    public static String format(long time, String format) {
        SimpleDateFormat sdf = new SimpleDateFormat(format);
        Date date = new Date(time);
        return sdf.format(date);
    }


    /**
     * 传入两个时间戳判定是否是在同一天，
     * 比如2017-09-23 11:30:21, 2017-09-23 06:30:21 处于同一天返回true
     * 比如2017-09-23 11:30:21, 2017-09-24 06:30:21 不处于同一天返回false
     *
     * @param d1 时间戳，单位毫秒
     * @param d2 时间戳，单位毫秒
     * @return true 代表相同
     */
    public static boolean isSameDay(long d1, long d2) {
        Date date1 = new Date(d1);
        Date date2 = new Date(d2);
        return isSameDay(date1, date2);
    }


    /**
     * 传入两个时间戳判定是否是在同一天，
     * 比如2017-09-23 11:30:21, 2017-09-23 06:30:21 处于同一天返回true
     * 比如2017-09-23 11:30:21, 2017-09-24 06:30:21 不处于同一天返回false
     *
     * @return true 代表相同
     */
    public static boolean isSameDay(Date date1, Date date2) {
        if (date1 != null && date2 != null) {
            Calendar cal1 = Calendar.getInstance();
            cal1.setTime(date1);
            Calendar cal2 = Calendar.getInstance();
            cal2.setTime(date2);
            return isSameDay(cal1, cal2);
        } else {
            throw new IllegalArgumentException("The date must not be null");
        }
    }

    /**
     * 传入两个时间戳判定是否是在同一天，
     * 比如2017-09-23 11:30:21, 2017-09-23 06:30:21 处于同一天返回true
     * 比如2017-09-23 11:30:21, 2017-09-24 06:30:21 不处于同一天返回false
     *
     * @return true 代表相同
     */
    public static boolean isSameDay(final Calendar cal1, final Calendar cal2) {
        if (cal1 == null || cal2 == null) {
            throw new IllegalArgumentException("The date must not be null");
        }
        return (cal1.get(Calendar.ERA) == cal2.get(Calendar.ERA) &&
                cal1.get(Calendar.YEAR) == cal2.get(Calendar.YEAR) &&
                cal1.get(Calendar.DAY_OF_YEAR) == cal2.get(Calendar.DAY_OF_YEAR));
    }

    /**
     * 只计算日期相差的天数，与时分秒无关
     *
     * @param t1 起始时间
     * @param t2 结束时间
     * @return 返回相差的天数, 一定为正数
     */
    public static int getDateSpan(long t1, long t2) {
        t1 = adjustToSixClock(t1).getTime();
        t2 = adjustToSixClock(t2).getTime();
        long diff = t1 - t2;
        return Math.abs((int) (diff / DateUtils.MILLIS_PER_DAY));
    }

    /**
     * 只计算日期与今天的差值
     *
     * @param t1 起始时间
     * @return 返回相差的天数, 一定为正数
     */
    public static int getDateMmddyyyySpan(String t1) {
        long time1 = adjustToSixClock(TimeUtils.getTimes(t1, TimeUtils.DATE_FORMAT_LOWEAST_RATE)).getTime();
        long time2 = adjustToSixClock(System.currentTimeMillis()).getTime();
        long diff = time1 - time2;
        return (int) (diff / DateUtils.MILLIS_PER_DAY);
    }

    /**
     * 获取日期yymmdd
     * @param span
     * @return
     */
    public static String getMmddyyyyBySpan(int span) {
        Calendar calendar = Calendar.getInstance();
        //凌晨6点!!!
        calendar.set(Calendar.HOUR_OF_DAY, 0);
        calendar.set(Calendar.SECOND, 0);
        calendar.set(Calendar.MINUTE, 0);
        calendar.set(Calendar.MILLISECOND, 0);
        calendar.add(Calendar.DATE, span);
        return format(calendar.getTime().getTime(), DATE_FORMAT_LOWEAST_RATE);
    }


}
