/**
 * Copyright (C) 2014 Guangzhou QTONE Technologies Ltd.
 * <p/>
 * 本代码版权归广州全通教育股份有限公司所有，且受到相关的法律保护。没有经过版权所有者的书面同意，
 * 任何其他个人或组织均不得以任何形式将本文件或本文件的部分代码用于其他商业用途。
 *
 * @date 2014年11月13日 下午20:46:01
 * @version V1.0
 */
package cn.demo.wr.project.commentswiplistviewdemo.util;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;


/**
 * 时间工具类
 *
 * @author 肖亮
 * @ClassName DateUtil
 * @date 2014年11月13日 下午20:46:01
 */
public final class DateUtil {

    public static final String Formater_yyyy_MM_dd = "yyyy-MM-dd";
    public static final String Formater_yyyy_MM_dd_HH_mm_ss = "yyyy-MM-dd HH:mm:ss";
    public static final String Formater_yyyyMMddHHmmss = "yyyyMMddHHmmss";
    public static final String Formater_MMddHHmm = "MM-dd HH:mm";//czq

    public static final long DAY_SECONDS = 24 * 60 * 60 * 1000;

    public static String DEFAULT_DATE_PATTERN = "yyyy-MM-dd";

    public static String yyyy = "yyyy";

    public static String yyyy_MM = "yyyy-MM";

    public static String MM_dd = "MM-dd";
    public static String MM_dd2 = "MM月dd日";

    public static String HH_mm = "HH:mm";
    public static String MM_dd_HH_mm = "MM-dd HH:mm";

    public static String yyyy_MM_dd = "yyyy-MM-dd";

    public static String yyyy_MM_dd_HH = "yyyy-MM-dd HH";

    public static String yyyy_MM_dd_HH_mm = "yyyy-MM-dd HH:mm";

    public static String yyyy_MM_dd_HH_mm_ss = "yyyy-MM-dd HH:mm:ss";
    public static String yyyy_MM_dd_HH_mm_ss_SSS = "yyyy-MM-dd HH:mm:ss:SSS";

    public static String TIME_PATTERN = "HH:mm:ss";

    private final static ThreadLocal<SimpleDateFormat> dateFormater = new ThreadLocal<SimpleDateFormat>() {
        @Override
        protected SimpleDateFormat initialValue() {
            return new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        }
    };

    private final static ThreadLocal<SimpleDateFormat> dateFormater2 = new ThreadLocal<SimpleDateFormat>() {
        @Override
        protected SimpleDateFormat initialValue() {
            return new SimpleDateFormat("yyyy-MM-dd");
        }
    };

    /**
     * 判断当前是否为工作时间 工作时间段（6:00-24:00）
     *
     * @return
     */
    public static boolean isWorkingTime() {
        Calendar rightNow = Calendar.getInstance();
        int hour = rightNow.get(Calendar.HOUR_OF_DAY);
        return hour > 6 && hour < 24 ? true : false;
    }


    public static boolean isBusyHour() {
        Calendar rightNow = Calendar.getInstance();
        int hour = rightNow.get(Calendar.HOUR_OF_DAY);
        return hour > 6 && hour < 22 ? true : false;
    }

    public static boolean isIntervalTwoMinute(String oldDateTime,
                                              String newDateTime) {

        try {
            Date oldDate = toDate(oldDateTime);
            Date newDate = toDate(newDateTime);

            long oldDateLong = oldDate.getTime();
            long newDateLong = newDate.getTime();

            if (newDateLong - oldDateLong > 2 * 60 * 1000) {
                return false;
            }

        } catch (Exception e) {
            return true;
        }

        return true;
    }

    public static boolean isOneDay(String oldDateTime, String newDateTime) {
        try {
            Date oldDate = toDate(oldDateTime);
            Date newDate = toDate(newDateTime);

            long oldDateLong = oldDate.getTime();
            long newDateLong = newDate.getTime();

            if ((newDateLong - oldDateLong) / 60 / 60 / 1000 > 24) {
                return false;
            }

        } catch (Exception e) {
            return true;
        }

        return true;
    }

    /**
     * 判断时间是否属于今天
     *
     * @param dateTime
     * @return
     */
    public static boolean isInToday(String dateTime) {

        if (dateTime==null&&dateTime.length()==0) {
            return false;
        }

        boolean b = false;
        Date time = toDate(dateTime);
        Date today = new Date();
        if (time != null) {
            String nowDate = dateFormater2.get().format(today);
            String timeDate = dateFormater2.get().format(time);
            if (nowDate.equals(timeDate)) {
                b = true;
            }
        }
        return b;
    }

    /**
     * 判断时间是否属于今年
     *
     * @return
     */
    public static boolean isInYear(long dataTime) {

        if (dataTime <= 0) {
            return false;
        }
        long nowTime = System.currentTimeMillis();
        Date date = new Date(dataTime);
        Date date1 = new Date(nowTime);
        SimpleDateFormat fm = new SimpleDateFormat("yyyy");
        String mdate = fm.format(date);
        String mdate1 = fm.format(date1);
        return mdate.equals(mdate1);
    }

    /**
     * 判断时间是否属于本月
     *
     * @param dataTime
     * @return
     */
    public static boolean isInMonth(long dataTime) {

        if (dataTime <= 0) {
            return false;
        }
        long nowTime = System.currentTimeMillis();
        Date date = new Date(dataTime);
        Date date1 = new Date(nowTime);
        SimpleDateFormat fm = new SimpleDateFormat("mm");
        String mdate = fm.format(date);
        String mdate1 = fm.format(date1);
        return mdate.equals(mdate1);
    }

    /**
     * 将字符串转位日期类型
     *
     * @param sdate
     * @return
     */
    public static Date toDate(String sdate) {
        try {
            return dateFormater.get().parse(sdate);
        } catch (Exception e) {
            return null;
        }
    }

    /**
     * 判断字符串是否为空
     *
     * @param str
     * @return
     */
    public static boolean isNull(String str) {
        str = str != null ? str.trim() : str;
        return str == null || "".equals(str) ? true : false;
    }

    /**
     * Date 对象转换成对应格式的 字符串
     *
     * @param dateSource 默认："yyyy-MM-dd HH:mm:ss"
     * @return
     */
    public static Date timeStrToDate(String dateSource) {
        if (isNull(dateSource))
            return null;
        return timeStrToDate(dateSource, null);
    }

    /**
     * Date 对象转换成对应格式的 字符串
     *
     * @param dateSource
     * @param format     如："yyyy-MM-dd HH:mm:ss"，默认："yyyy-MM-dd HH:mm:ss"
     * @return
     */
    public static Date timeStrToDate(String dateSource, String format) {
        if (isNull(dateSource))
            return null;
        SimpleDateFormat formater = new SimpleDateFormat(
                isNull(format) ? "yyyy-MM-dd HH:mm:ss" : format.trim()
                        .replace(".", "-").replace("/", "-"));
        try {
            return formater.parse(dateSource);
        } catch (ParseException e) {
            e.printStackTrace();
            return null;
        }
    }

    /**
     * Date 对象转换成对应格式的 字符串
     *
     * @param date
     * @param format 如："yyyy-MM-dd HH:mm:ss"，默认："yyyy-MM-dd HH:mm:ss"
     * @return
     */
    public static String dateToString(Date date, String format) {
        if (date == null)
            return "";
        SimpleDateFormat formater = new SimpleDateFormat(
                isNull(format) ? "yyyy-MM-dd HH:mm:ss" : format.trim());
        return formater.format(date);
    }

    /**
     * Date 对象转换成对应格式的 字符串
     *
     * @param date 默认："yyyy-MM-dd HH:mm:ss"
     * @return
     */
    public static String dateToString(Date date) {
        if (date == null)
            return "";
        return dateToString(date, null);
    }

    /**
     * 获得手机端当前时间
     *
     * @return
     */
    public final static long getCurrentTime() {

        Calendar calendar = Calendar.getInstance();
        long time = calendar.getTimeInMillis();

        return time;
    }

    public final static long getCurrentWeekday(String dateTime) {
        // 先计算日期
        Calendar calendar = Calendar.getInstance();
        Date queryDate = DateUtil.timeStrToDate(dateTime,
                DateUtil.Formater_yyyy_MM_dd);
        calendar.setTime(queryDate);
        String date = DateUtil.dateToString(queryDate, "yyyy年MM月dd日");
        long week = calendar.get(Calendar.DAY_OF_WEEK);
        week = week == 1 ? 7 : week - 1;
        return week;
    }

    /**
     * 获得指定时间的毫秒数
     *
     * @return
     */
    public final static long getCurrentTime(Date date) {

        return date.getTime();
    }

    /**
     * 获取Date对象
     *
     * @return
     */
    public final static Date getDate() {

        return new Date();
    }

    /**
     * 获取指定的时间的Date对象
     *
     * @param time
     * @return
     */
    public final static Date getDate(long time) {

        return new Date(time);
    }

    /**
     * 获取当前时间（24小时制）
     *
     * @return
     */
    public final static String getStandardFormatTimeTo24() {
        return getStandardFormatTimeTo24(new Date());
    }

    /**
     * 将返回的毫秒值转换为yyyy-MM-dd 时间格式
     *
     * @return
     */
    public final static String getMillisecondFormatTime(long datetiem) {

        Date date = new Date(datetiem);
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");

        return sdf.format(date);

    }

    /**
     * 将返回的毫秒值转换为 yyyy年MM月dd日  时间格式
     *
     * @return
     */
    public final static String getMillisecondFormatDate(long datetiem) {

        Date date = new Date(datetiem);
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy年MM月dd日");

        return sdf.format(date);

    }

    /**
     * 将返回的毫秒值转换为yyyy-MM-dd 时间格式
     *
     * @return
     */
    public final static String getMillisecondFormatData(long datetiem) {

        Date date = new Date(datetiem);
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd EEEE");

        return sdf.format(date);

    }

    /**
     * 将返回的毫秒值转换为yyyy-MM-dd HH:mm:ss 时间格式
     *
     * @return
     */
    public final static String getMillisecondFormatDateTo24(long datetiem) {

        Date date = new Date(datetiem);
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

        return sdf.format(date);

    }

    /**
     * 将返回的毫秒值转换为MM-dd HH:mm 时间格式
     *
     * @return
     */
    public final static String getMillisecondFormatDataAndTime(long datetiem) {

        Date date = new Date(datetiem);
        SimpleDateFormat sdf = new SimpleDateFormat("MM-dd HH:mm");

        return sdf.format(date);

    }


    /**
     * 获得"yyyy年MM月dd日   星期N"格式的时间输出
     *
     * @param date
     * @return
     */
    public final static String getStandardFormatTime(Date date) {

        SimpleDateFormat sf = new SimpleDateFormat("yyyy年MM月dd日   EEEE");

        return sf.format(date);

    }

    /**
     * 获得"yyyy-MM-dd hh:mm:ss"格式的时间输出
     *
     * @param date
     * @return
     */
    public final static String getStandardFormatTime12(Date date) {

        SimpleDateFormat sf = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");

        return sf.format(date);

    }

    /**
     * 获得"yyyyMMddHHmmss"格式的时间输出
     *
     * @param date
     * @return
     */
    public final static String getYYmmddhhmmss(Date date) {
        SimpleDateFormat sf = new SimpleDateFormat("yyyyMMddHHmmss");
        return sf.format(date);
    }

    /**
     * 获得"yyyy-MM-dd hh:mm:"格式的时间输出
     *
     * @return
     */
    public final static String getYyMmDdHh(long time) {
        Date date = new Date(time);
        SimpleDateFormat sf = new SimpleDateFormat("yyyy-MM-dd HH:mm");
        return sf.format(date);

    }

    /**
     * 获得"yyyy"格式的时间输出
     *
     * @param time
     * @return
     */
    public final static String getYear(long time) {
        Date date = new Date(time);
        SimpleDateFormat sf = new SimpleDateFormat("yyyy");
        return sf.format(date);
    }

    /**
     * 获得"yyyy-MM-dd HH:mm:"格式的时间输出
     *
     * @return
     */
    public final static String getYyMmDdHH(long time) {
        if (time < 0) return "时间异常";
        Date date = new Date(time);
        SimpleDateFormat sf = new SimpleDateFormat("yyyy-MM-dd HH:mm");
        return sf.format(date);

    }

    /**
     * 获得"yyyy-MM-dd HH:mm:ss"格式的时间输出
     *
     * @param date
     * @return
     */
    public final static String getStandardFormatTimeTo24(Date date) {

        SimpleDateFormat sf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

        return sf.format(date);

    }

    /**
     * 获得"yyyy-MM-dd"格式的时间输出
     *
     * @param date
     * @return
     */
    public final static String getStandardFormatTime3(Date date) {

        SimpleDateFormat sf = new SimpleDateFormat("yyyy-MM-dd");

        return sf.format(date);

    }

    /**
     * 获得"MM-dd"格式的时间输出
     *
     * @param date
     * @return
     */
    public static String getStandardFormatTime4(Date date) {

        SimpleDateFormat sf = new SimpleDateFormat("MM-dd");

        return sf.format(date);

    }

    /**
     * 获得"MM-dd"格式的时间输出
     *
     * @param date
     * @return
     */
    public static String getStandardFormatTime5(Date date) {

        SimpleDateFormat sf = new SimpleDateFormat("HH:mm");

        return sf.format(date);

    }


    /**
     * 获取给定日期的前一天
     *
     * @param date
     * @return
     */
    public static Date getSpecifiedDayBefore(Date date) {

        Calendar c = Calendar.getInstance();
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        c.setTime(date);
        int day = c.get(Calendar.DATE);
        c.set(Calendar.DATE, day - 1);

        return c.getTime();
    }

    /**
     * 获取给定日期的hou一天
     *
     * @param date
     * @return
     */
    public static Date getSpecifiedDayAfter(Date date) {

        Calendar c = Calendar.getInstance();
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        c.setTime(date);
        int day = c.get(Calendar.DATE);
        c.set(Calendar.DATE, day + 1);

        return c.getTime();
    }

    /**
     * 获得标准的时间输出
     *
     * @param time
     * @return
     */
    public final static String getStandardFormatTime(long time) {

        Date date = new Date(time);

        SimpleDateFormat format = new SimpleDateFormat("yyyy年MM月dd日   EEEE");

        return format.format(date);

    }

    /**
     * 获得考勤时间
     *
     * @param time
     * @return
     */
    public static String getAttendanceTime(long time) {
        Date date = new Date(time);
        SimpleDateFormat sf = new SimpleDateFormat("HH:mm");
        return sf.format(date);
    }

    public static String getAttendanceYearAndMounth(Date date) {
//		Date date = new Date(time);
        SimpleDateFormat sf = new SimpleDateFormat("yyyy年MM月");
        return sf.format(date);
    }

    /**
     * 比较两"yyyy-MM-dd HH:mm:ss"格式的时间，获取最近时间
     *
     * @param t1
     * @param t2
     * @return
     * @throws ParseException
     */
    public static String getLeastTime(String t1, String t2)
            throws ParseException {

        if (t1 == null) {
            return t2 == null ? "" : t2;
        }

        if (t1.equals(t2)) {
            return t1;
        }
        if ("".equals(t1)) {
            return t2;
        }
        if ("".equals(t2)) {
            return t1;
        }

        SimpleDateFormat formater = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        Date d = formater.parse(t1);
        Date d2 = formater.parse(t2);

        int result = d.compareTo(d2);

        if (result > 0) {
            return t1;
        } else {
            return t2;
        }

    }

    /**
     * 获取动态格式时间
     *
     * @param date
     * @return
     * @throws ParseException
     */
    public static String getDynamicFormateDate(String date) {
        SimpleDateFormat formater = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        Date history;
        try {
            history = formater.parse(date);
        } catch (ParseException e) {
            return date; // 格式不对，直接返回原来数据
        }
        Date current = new Date();
        if(current.compareTo(history) <= 0){
            return date.substring(0, date.indexOf(" "));
        }
        // 判断年月
        if (current.getYear() > history.getYear()
                || current.getMonth() > history.getMonth()) {
            return date.substring(0, date.indexOf(" "));
        }

        int intervalDay = current.getDay() - history.getDay();

        int minutes = history.getMinutes();

        String StrMinutes = null;

        if (minutes < 10) {
            StrMinutes = "0" + minutes;
        } else {
            StrMinutes = "" + minutes;
        }

        // 超过2天，直接显示原来格式时间
        if (intervalDay > 2) {
            return date.substring(0, date.indexOf(" "));
        } else if (intervalDay > 1) {
            return "前天" + history.getHours() + ":" + StrMinutes;
        } else if (intervalDay > 0) {
            return "昨天" + history.getHours() + ":" + StrMinutes;
        }

        int intervalMinute = (int) ((current.getTime() - history.getTime()) / (60 * 1000)); // 获取间隔了多少分钟

        // 时间超前，显示原来时间
        if (intervalMinute < 0) {
            return date.substring(0, date.indexOf(" "));
        }

        // 今天
        if (intervalMinute < 3) {
            return "刚刚";
        }
        if (intervalMinute < 60) {
            return intervalMinute + "分钟前";
        } else if (intervalMinute < 24 * 60) {
            return "今天" + history.getHours() + ":" + StrMinutes;
        } else {
            return date.substring(0, date.indexOf(" "));
        }

    }

    /**
     * 获取动态格式时间
     *
     * @param date
     * @return
     * @throws ParseException
     */
    public static String getDynamicFormateDate2(String date) {
        SimpleDateFormat formater = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        Date history;
        try {
            history = formater.parse(date);
        } catch (ParseException e) {
            return date; // 格式不对，直接返回原来数据
        }
        Date current = new Date();
        // 判断年月
        if (current.getYear() > history.getYear()
                || current.getMonth() > history.getMonth()) {
            return date.substring(0, date.indexOf(" "));
        }

        int intervalDay = current.getDate() - history.getDate();

        int minutes = history.getMinutes();

        String StrMinutes = null;

        if (minutes < 10) {
            StrMinutes = "0" + minutes;
        } else {
            StrMinutes = "" + minutes;
        }

        // 超过2天，直接显示原来格式时间
        if (intervalDay > 2) {
            return date.substring(0, date.indexOf(" "));
        } else if (intervalDay == 2) {
            return "前天";
        } else if (intervalDay == 1) {
            return "昨天";
        } else if (intervalDay == 0) {
            return "今天";
        }

        return date.substring(0, date.indexOf(" "));
    }

    /**
     * 获取动态格式时间
     *
     * @param date
     * @return
     * @throws ParseException
     */
    public static String getDynamicFormateDate3(String date) {
        SimpleDateFormat formater = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        Date history;
        try {
            history = formater.parse(date);
        } catch (ParseException e) {
            return date; // 格式不对，直接返回原来数据
        }
        Date current = new Date();
        // 判断年月
        if (current.getYear() > history.getYear() || current.getMonth() > history.getMonth()) {
            return date.substring(0, date.indexOf(" "));
        }
        int intervalDay = current.getDate() - history.getDate();

        // 超过2天，直接显示原来格式时间
        if (intervalDay > 1) {
            return date.substring(0, date.indexOf(" "));
        } else if (intervalDay == 1) {
            return "昨天";
        } else if (intervalDay == 0) {
            return "今天";
        }
        return date.substring(0, date.indexOf(" "));

    }

    /**
     * 获取动态格式时间
     *
     * @param date
     * @return
     * @throws ParseException
     */
    public static String getTimeFormateDate(String date) {
        SimpleDateFormat formater = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        Date history;
        try {
            history = formater.parse(date);
        } catch (ParseException e) {
            return date; // 格式不对，直接返回原来数据
        }
        Date current = new Date();
        // 判断年月
        if (current.getYear() > history.getYear()
                || current.getMonth() > history.getMonth()) {
            return date.substring(0, date.indexOf(" "));
        }

        int intervalDay = current.getDate() - history.getDate();
        int minutes = history.getMinutes();
        String StrMinutes = null;

        if (minutes < 10) {
            StrMinutes = "0" + minutes;
        } else {
            StrMinutes = "" + minutes;
        }

        // 超过2天，直接显示原来格式时间
        if (intervalDay > 2) {
            return "2天前";
        } else if (intervalDay > 1) {
            return "前天";
        } else if (intervalDay > 0) {
            return "昨天";
        }

        int intervalMinute = (int) ((current.getTime() - history.getTime()) / (60 * 1000)); // 获取间隔了多少分钟

        // 时间超前，显示原来时间
        if (intervalMinute < 0) {
            return date.substring(0, date.indexOf(" "));
        }

        // 今天
        if (intervalMinute < 3) {
            return "刚刚";
        }
        if (intervalMinute < 60) {
            return intervalMinute + "分钟前";
        } else if (intervalMinute < 24 * 60) {
            return history.getHours() + "小时前";
        } else {
            return date.substring(0, date.indexOf(" "));
        }
    }

    /**
     * 获取动态格式时间
     *
     * @param date
     * @return
     * @throws ParseException
     */
    public static String getModularizationDate(Date date) {
        SimpleDateFormat fm1 = new SimpleDateFormat("yyyy-MM-dd HH:mm");
        SimpleDateFormat fm2 = new SimpleDateFormat("HH:mm");
        String beforeTime1 = fm1.format(date);
        String beforeTime2 = fm2.format(date);
        Date current = new Date();
        // 判断年月
        if (current.getYear() > date.getYear() || current.getMonth() > date.getMonth()) {
            return beforeTime1;
        }
        int intervalDay = current.getDate() - date.getDate();

        // 超过2天，直接显示原来格式时间
        if (intervalDay == 0) {
            return "今天  " + beforeTime2;
        } else if (intervalDay == 1) {
            return "昨天  " + beforeTime2;
        } else {
            return beforeTime1;
        }
    }

    /**
     * 获取班圈动态格式时间
     *
     * @param date
     * @return
     * @throws ParseException
     */
    public static String getClassCircleDate(Date date) {
        Date current = new Date();

        SimpleDateFormat dfs = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        long between = 0;
        try {
            Date begin = dfs.parse(dfs.format(date));
            Date end = dfs.parse(dfs.format(current));

            between = (end.getTime() - begin.getTime());// 得到两者的毫秒数
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        long day = between / (24 * 60 * 60 * 1000);
        long hour = (between / (60 * 60 * 1000) - day * 24);
        long min = ((between / (60 * 1000)) - day * 24 * 60 - hour * 60);

        if (day == 0) {
            if (hour == 0) {
                if (min == 0) {
                    return "1分钟前";
                } else {
                    return min + "分钟前";
                }
            } else {
                return hour + "小时前";
            }
        } else if (day < 365) {
            return day + "天前";
        } else {
            return "更早";
        }
    }


    /**
     * 获取动态格式时间
     *
     * @param date
     * @return
     * @throws ParseException
     */
    public static String getModularizationDateForHome(Date date) {
        SimpleDateFormat fm1 = new SimpleDateFormat("yyyy-MM-dd");
        SimpleDateFormat fm2 = new SimpleDateFormat("HH:mm");
        String beforeTime1 = fm1.format(date);
        String beforeTime2 = fm2.format(date);
        Date current = new Date();
        // 判断年月
        if (current.getYear() > date.getYear() || current.getMonth() > date.getMonth()) {
            return beforeTime1;
        }
        int intervalDay = current.getDate() - date.getDate();

        // 超过2天，直接显示原来格式时间
        if (intervalDay == 0) {
            return "今天";
        } else if (intervalDay == 1) {
            return "昨天  " + beforeTime2;
        } else {
            return beforeTime1;
        }
    }

    /**
     * 获取动态格式时间
     *
     * @return
     * @throws ParseException
     */
    @SuppressWarnings("deprecation")
    public static String getModularizationDateForMsgNotice(long milliseconds) {
        SimpleDateFormat fm1 = new SimpleDateFormat("yyyy-MM-dd");
        SimpleDateFormat fm2 = new SimpleDateFormat("MM-dd");
        SimpleDateFormat fm3 = new SimpleDateFormat("HH:mm");
        SimpleDateFormat fm4 = new SimpleDateFormat("MM-dd HH:mm");
        if (milliseconds < 0) return "时间异常";
        Date history = new Date(milliseconds);
        Date current = new Date();
        // 判断年
        if (current.getYear() != history.getYear()) {
            return fm1.format(history);
        }
        // 判断月
        if (current.getMonth() != history.getMonth()) {
            return fm2.format(history);
        }
        // 判断日
        int intervalDay = current.getDate() - history.getDate();
        // 超过2天
        if (intervalDay > 2) {
            return fm2.format(history);
        } else if (intervalDay > 1) {
            return "前天";
        } else if (intervalDay > 0) {
            return "昨天 " + fm3.format(history);
        } else if (intervalDay < 0) { //时间超前1天及以上，一个月以内用fm4
            return fm4.format(history);
        }
        //今天
        int intervalHour = current.getHours() - history.getHours(); // 获取间隔了多少小时
        int intervalMinute = (int) ((current.getTime() - history.getTime()) / (60 * 1000)); // 获取间隔了多少分钟
        // 时间超前
        if (intervalMinute < 0) {
            return "今天 " + fm3.format(history);
        }
        //刚刚
        if (intervalMinute < 3) {
            return "刚刚";
        }
        if (intervalMinute < 60) {
            return intervalMinute + "分钟前";
        } else {
            return intervalHour + "小时前";
        }
    }

    /**
     * 获取动态格式时间
     *
     * @param milliseconds
     * @return
     * @throws ParseException
     */
    @SuppressWarnings("deprecation")
    public static String getModularizationDateForMsgNotice2(long milliseconds) {
        SimpleDateFormat fm1 = new SimpleDateFormat("yyyy-MM-dd");
//		SimpleDateFormat fm2 = new SimpleDateFormat("MM-dd");
        SimpleDateFormat fm3 = new SimpleDateFormat("HH:mm");
        SimpleDateFormat fm4 = new SimpleDateFormat("MM-dd HH:mm");
        if (milliseconds < 0) return "时间异常";
        Date history = new Date(milliseconds);
        Date current = new Date();
        // 判断年
        if (current.getYear() != history.getYear()) {
            return fm1.format(history);
        }
        // 判断月
        if (current.getMonth() != history.getMonth()) {
            return fm1.format(history);
        }
        // 判断日
        int intervalDay = current.getDate() - history.getDate();
        // 超过2天
        if (intervalDay > 2) {
            return fm1.format(history);
        } else if (intervalDay > 1) {
            return "前天";
        } else if (intervalDay > 0) {
            return "昨天 " + fm3.format(history);
        } else if (intervalDay < 0) { //时间超前1天及以上，一个月以内用fm4
            return fm4.format(history);
        }
        //今天
        int intervalHour = current.getHours() - history.getHours(); // 获取间隔了多少小时
        int intervalMinute = (int) ((current.getTime() - history.getTime()) / (60 * 1000)); // 获取间隔了多少分钟
        // 时间超前
        if (intervalMinute < 0) {
            return "今天 " + fm3.format(history);
        }
        //刚刚
        if (intervalMinute < 3) {
            return "刚刚";
        }
        if (intervalMinute < 60) {
            return intervalMinute + "分钟前";
        } else {
            return intervalHour + "小时前";
        }
    }

    /**
     * 获取动态格式时间
     *
     * @return
     * @throws ParseException
     */
    @SuppressWarnings("deprecation")
    public static String getModularizationDateForMsgNotice1(long milliseconds) {
        SimpleDateFormat fm1 = new SimpleDateFormat("yyyy-MM-dd");
        SimpleDateFormat fm2 = new SimpleDateFormat("MM-dd HH:mm");
        SimpleDateFormat fm3 = new SimpleDateFormat("HH:mm");
        if (milliseconds < 0) return "时间异常";
        Date history = new Date(milliseconds);
        Date current = new Date();
        // 判断年
        if (current.getYear() != history.getYear()) {
            return fm1.format(history);
        }
        // 判断是否今天
        if (current.getMonth() == history.getMonth() && current.getDate() == history.getDate()) {
            return "今天 " + fm3.format(history);
        }

        return fm2.format(history);
    }

    /**
     * 获取当前日期是星期几<br>
     *
     * @param dt
     * @return 返回int：1 / 2/ 3/ 4/ 5/6/ 7    //周一到周日
     */
    public static int getWeekOfDate(long dt) {
        Calendar cal = Calendar.getInstance();
        cal.setTime(getDate(dt));
        int w = cal.get(Calendar.DAY_OF_WEEK) - 1;
        if (w < 0)
            w = 0;
        return w;
    }

    /**
     * 获取当前日期是星期几<br>
     *
     * @param dt
     * @return 当前日期是星期几
     */
    public static String getWeekOfDate(Date dt) {
        String[] weekDays = {"星期日", "星期一", "星期二", "星期三", "星期四", "星期五", "星期六"};
        Calendar cal = Calendar.getInstance();
        cal.setTime(dt);
        int w = cal.get(Calendar.DAY_OF_WEEK) - 1;
        if (w < 0)
            w = 0;
        return weekDays[w];
    }

    /**
     * 获取当前日期是星期几<br>
     *
     * @param dt
     * @return 当前日期是星期几
     */
    public final static String getWeekOfDate1(Date dt) {
        String[] weekDays = {"周日", "周一", "周二", "周三", "周四", "周五", "周六"};
        Calendar cal = Calendar.getInstance();
        cal.setTime(dt);
        int w = cal.get(Calendar.DAY_OF_WEEK) - 1;
        if (w < 0)
            w = 0;
        return weekDays[w];
    }

    /**
     * 作业时间格式
     *
     * @param datetime
     * @return
     */
    public static String getHomeworkDate(long datetime) {

        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        Date history = new Date(datetime);
        Date current = new Date();

        int intervalDay = current.getDate() - history.getDate();

        if (intervalDay == 0) {
            return "今天的作业";
        } else if (intervalDay == 1) {
            return "昨天的作业";
        } else if (intervalDay == 2) {
            return "前天的作业";
        } else {
            return sdf.format(history) + "  " + getWeekOfDate(history);
        }
    }

    // public final static String getMillisecondFormatTime(long datetiem) {
    //
    // Date date = new Date(datetiem);
    // SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
    //
    // return sdf.format(date);
    //
    // }

    /**
     * 返回时间戳
     *
     * @param dateTimeString
     * @return
     * @throws ParseException
     */
    public static long geLongTime(String dateTimeString) {
        try {
            SimpleDateFormat formater = new SimpleDateFormat(
                    "yyyy-MM-dd HH:mm:ss");
            Date d = formater.parse(dateTimeString);
            long lt = d.getTime();
            return lt;
        } catch (Exception e) {
            return 0;
        }
    }

    /**
     * 时间字符串转成字符串
     *
     * @param dateTimeString
     * @return
     * @throws ParseException
     */
    public static String getDateTimeString(String dateTimeString) {
        try {
            SimpleDateFormat formater = new SimpleDateFormat("yyyy-MM-dd");
            String mdate = formater.format(toDate(dateTimeString));
            return mdate;
        } catch (Exception e) {
            return "";
        }
    }

    /**
     * 比较时间先后
     *
     * @param t1
     * @param t2
     * @return
     * @throws ParseException
     */
    public static int compare(String t1, String t2) throws ParseException {

        SimpleDateFormat formater = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        Date d = formater.parse(t1);
        Date d2 = formater.parse(t2);

        return d.compareTo(d2);
    }

    /**
     * 获取指定星期的日期
     *
     * @param c
     * @param currentDay 当前星期
     * @param queryDay   查询的星期
     * @return
     */
    public static String computeDate(Calendar c, int currentDay, int queryDay) {

        int space = 0;
        switch (queryDay) {

            case Calendar.SUNDAY:
                space = currentDay - Calendar.SUNDAY - 7; // 根据中国人的习惯，星期日为一个星期的最后一天
                break;
            case Calendar.MONTH:
                space = currentDay - Calendar.MONTH;
                break;
            case Calendar.TUESDAY:
                space = currentDay - Calendar.TUESDAY;
                break;
            case Calendar.WEDNESDAY:
                space = currentDay - Calendar.WEDNESDAY;
                break;
            case Calendar.THURSDAY:
                space = currentDay - Calendar.THURSDAY;
                break;
            case Calendar.FRIDAY:
                space = currentDay - Calendar.FRIDAY;
                break;
            case Calendar.SATURDAY:
                space = currentDay - Calendar.SATURDAY;
                break;

            default:
                break;
        }

        final long temp = c.getTime().getTime() - space * 24 * 60 * 60 * 1000;

        return getStandardFormatTimeTo24(new Date(temp));

    }

    /**
     * 得到一个时间延后或前移几天的时间,nowdate为时间,delay为前移或后延的天数
     *
     * @param nowdate
     * @param delay   可为负数
     * @return
     */
    public static String getNextDay(String nowdate, int delay) {
        try {
            SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
            String mdate = "";
            Date d = timeStrToDate(nowdate);
            long myTime = (d.getTime() / 1000) + delay * 24 * 60 * 60;
            d.setTime(myTime * 1000);
            mdate = format.format(d);
            return mdate;
        } catch (Exception e) {
            return "";
        }
    }

    /**
     * 输入String字符串格式时间 放回月日时间格式
     *
     * @return
     */
    public static String ForToData(String date) {

        int i = date.length();
        String d1 = date.substring(0, 10);
        String d2 = date.substring(10, i);
        d1 = d1 + "日";
        int j = d1.length();
        d1 = d1.substring(5, j);
        d1 = d1.replace("-", "月");
        String mdate = d1 + "  " + d2;

        if (mdate.startsWith("0")) {
            mdate = mdate.substring(1, mdate.length() - 3);
        } else {
            mdate = mdate.substring(0, mdate.length() - 3);
        }

        return mdate;

    }

    /**
     * 聊天时间显示格式
     *
     * @param sdate
     * @return
     */
    public static String ToChatDataTime(String sdate) {
        Date time = toDate(sdate);
        if (time == null) {
            return sdate; // 原样返回
        }
        String ftime = "";
        String datetime = "";
        String oldtime = "";

        // 2014-09-11 15:21:54
        datetime = sdate.substring(11, 16);
        oldtime = sdate.substring(0, 10);
        oldtime = oldtime.replace("-", "/");

        // 判断是否是同一天
        Calendar cal = Calendar.getInstance();
        String curDate = dateFormater2.get().format(cal.getTime());
        String paramDate = dateFormater2.get().format(time);
        if (curDate.equals(paramDate)) {
            ftime = "今天   " + datetime;
            return ftime;
        }
        long lt = time.getTime() / 86400000;
        long ct = cal.getTimeInMillis() / 86400000;
        int days = (int) (ct - lt);
        if (days == 0) {
            ftime = "今天   " + datetime;
        } else if (days == 1) {
            ftime = "昨天  " + datetime;
        } else if (days == 2) {
            ftime = "前天  " + datetime;
        } else {
            ftime = oldtime + "   " + datetime;
        }
        return ftime;
    }

    public static boolean compareDate(long dt1, long dt2) {
        SimpleDateFormat sf = new SimpleDateFormat("yyyy-MM-dd");
        String sp_time = sf.format(dt1);
        String current_time = sf.format(dt2);
        return sp_time.equals(current_time);
    }

    /**
     * 拨号返回妙，转化成分钟和秒
     *
     * @return
     */
    public static String CallDurationTime(int minutes) {
        return "用时:" + minutes + "分";
    }

    /**
     * 判断今天，昨天，前天等
     * 0.今天 1,昨天  2,更早
     *
     * @param createTime
     * @return
     */
    private static int parseDate(long createTime) {
        try {
            int ret = 2;
            Calendar now = Calendar.getInstance();
            long day_ms = 1000 * 24 * 3600;// 一天毫秒数
            long ms = 1000 * (now.get(Calendar.HOUR_OF_DAY) * 3600 + now.get(Calendar.MINUTE) * 60 + now.get(Calendar.SECOND));// 今天过去了毫秒数
            long now_ms = System.currentTimeMillis();

            long today_millions = now_ms - ms;//今天凌晨的毫秒数
            long yesterday_millions = now_ms - ms - day_ms;//昨天凌晨的毫秒数

            if (createTime > today_millions) {//今天
                ret = 0;
            } else if (createTime > yesterday_millions) {//昨天
                ret = 1;
            } else {//更早
                ret = 2;
            }
            return ret;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return 2;
    }

    public static String CallTime(long dt) {
        SimpleDateFormat sdf = null;
        switch (parseDate(dt)) {
            case 0://今天
                sdf = new SimpleDateFormat("HH:mm");
                return sdf.format(new Date(dt));

            case 1://昨天
                return "昨天";

            case 2://更早
                sdf = new SimpleDateFormat("yyyy-MM-dd");
                return sdf.format(new Date(dt));
        }
        return "";
    }

    /**
     * 获取动态格式时间
     * 作业
     *
     * @param dt
     * @return
     * @throws ParseException
     */
    @SuppressWarnings("deprecation")
    public static String getDateForAttention(long dt) {

        SimpleDateFormat fm1 = new SimpleDateFormat("yyyy-MM-dd");//不是今年
        SimpleDateFormat fm2 = new SimpleDateFormat("HH:mm");//今天
        SimpleDateFormat fm3 = new SimpleDateFormat("MM-dd HH:mm"); //前天或之前

        if (dt <= 0)
            return "时间异常";

        Date history = new Date(dt);
        Date current = new Date();

        // 判断年
        if (current.getYear() != history.getYear()) {
            return fm1.format(history);
        }

        // 判断日
        int intervalDay = current.getDate() - history.getDate();

        if (intervalDay == 0) { //今天
            return fm2.format(history);
        } else {
            return fm3.format(history);
        }
    }


    /**
     * 获取动态格式时间
     * 作业
     *
     * @param dt
     * @return
     * @throws ParseException
     */
    @SuppressWarnings("deprecation")
    public static String getDateForHomework(long dt) {

        SimpleDateFormat fm1 = new SimpleDateFormat("yyyy-MM-dd");//不是今年
        SimpleDateFormat fm2 = new SimpleDateFormat("HH:mm");//今天
        SimpleDateFormat fm3 = new SimpleDateFormat("MM-dd HH:mm"); //前天或之前

        if (dt <= 0)
            return "时间异常";

        Date history = new Date(dt);
        Date current = new Date();

        // 判断年
        if (current.getYear() != history.getYear())
            return fm1.format(history);

        // 判断日
        int intervalDay = current.getDate() - history.getDate();

        if (intervalDay == 0) { //今天
            return "今天  " + fm2.format(history);
        } else if (intervalDay == 1) {
            return "昨天  " + fm2.format(history);
        } else {
            return fm3.format(history);
        }
    }

    /**
     * 获取动态格式时间
     * 作业
     *
     * @param enddt
     * @return
     * @throws ParseException
     */
    @SuppressWarnings("deprecation")
    public static String getEndDateForHomework(long enddt) {

        SimpleDateFormat fm1 = new SimpleDateFormat("yyyy-MM-dd HH:mm");//不是今年
        SimpleDateFormat fm2 = new SimpleDateFormat("MM-dd HH:mm"); //今年

        if (enddt <= 0)
            return "时间异常";

        Date history = new Date(enddt);
        Date current = new Date();

        // 判断年
        if (current.getYear() != history.getYear())
            return fm1.format(history);
        else
            return fm2.format(history);

    }

    /**
     * 广东作业是否过期,true 表示 过期 false 表示未过期
     * @param historytime
     * @return
     */
    public static boolean isExceedTime(long historytime) {

        if (historytime <= 0)
            return false;

        long nowtime = System.currentTimeMillis();//当前时间秒数
        Date historyDate = new Date(historytime);
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(historyDate);
        int day = 0;
        switch (calendar.get(Calendar.DAY_OF_WEEK)) {
            case 1:
            case 2:
            case 3:
            case 4:
            case 5:
                day = 1;
                break;
            case 6:
                day = 3;
                break;
            case 7:
                day = 2;
                break;
            default:
                break;
        }

        long millis = historytime + 1000 * 60 * 60 * 24 * day;//下一个工作日作业到期
        Date dt = new Date();
        SimpleDateFormat formater = new SimpleDateFormat("yyyy-MM-dd 08:00:00");
        try {
            String str = formater.format(new Date(millis));//转换为08:00:00格式。因为作业第二天8点到期

            SimpleDateFormat formater1 = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");
            dt = formater1.parse(str);//转换为普通的时间格式
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return (dt.getTime() -nowtime)<= 0;
    }

    /**
     * 浙江业务订购
     */
    @SuppressWarnings("deprecation")
    public static String getDateForBusiness(long dt) {
        SimpleDateFormat fm1 = new SimpleDateFormat("yyyy年MM月dd日");//不是今年

        if (dt <= 0)
            return "时间异常";

        Date history = new Date(dt);
        return fm1.format(history);
    }


    /**
     * 获取动态格式时间
     *
     * @param milliseconds
     * @return
     * @throws ParseException
     */
    @SuppressWarnings("deprecation")
    public static String getMonth(long milliseconds) {
        if (milliseconds < 0) return "时间异常";
        Date date = new Date(milliseconds);
        return (date.getMonth() + 1) + "月";
    }

    /**
     * 某年某月的最后一天
     *
     * @param year
     * @param month
     * @return
     */
    public static long getLastDayOfMonth(int year, int month) {
        Calendar cal = Calendar.getInstance();
        //设置年份
        cal.set(Calendar.YEAR, year);
        //设置月份
        cal.set(Calendar.MONTH, month - 1);
        //获取某月最小天数
        int firstDay = cal.getMaximum(Calendar.DAY_OF_MONTH);
        //设置日历中月份的最小天数
        cal.set(Calendar.DAY_OF_MONTH, firstDay);
        cal.set(Calendar.HOUR, 0);
        cal.set(Calendar.MINUTE, 0);


        return cal.getTimeInMillis();
    }

    /**
     * 某年某月的第一天
     *
     * @param year
     * @param month
     * @return
     */
    public static long getFisrtDayOfMonth(int year, int month) {
        Calendar cal = Calendar.getInstance();
        //设置年份
        cal.set(Calendar.YEAR, year);
        //设置月份
        cal.set(Calendar.MONTH, month - 1);
        //获取某月最小天数
        int firstDay = cal.getActualMinimum(Calendar.DAY_OF_MONTH);
        //设置日历中月份的最小天数
        cal.set(Calendar.DAY_OF_MONTH, firstDay);
        cal.set(Calendar.HOUR, 0);
        cal.set(Calendar.MINUTE, 0);


        return cal.getTimeInMillis();
    }

    /**
     * 某年某月的第一天
     *
     * @param year
     * @param month
     * @return
     */
    public static long getDayOfMonth(int year, int month, int day) {
        Calendar cal = Calendar.getInstance();
        //设置年份
        cal.set(Calendar.YEAR, year);
        //设置月份
        cal.set(Calendar.MONTH, month - 1);

        //设置日历中月份的最小天数
        cal.set(Calendar.DAY_OF_MONTH, day);
        cal.set(Calendar.HOUR, 0);
        cal.set(Calendar.MINUTE, 0);


        return cal.getTimeInMillis();
    }

    /**
     * 获取动态格式时间
     *
     * @param date
     * @return
     * @throws ParseException
     */
    public static String getModularizationDateForWeek(Date date) {
        SimpleDateFormat fm1 = new SimpleDateFormat("yyyy-MM-dd");
        SimpleDateFormat fm2 = new SimpleDateFormat("HH:mm");
        String beforeTime1 = fm1.format(date);
        String beforeTime2 = fm2.format(date);
        Calendar calendar = Calendar.getInstance();
        Calendar c = Calendar.getInstance();
        c.setTime(date);

        Date current = new Date();
        // 判断年月
        if (current.getYear() > date.getYear() || current.getMonth() > date.getMonth()) {
            return beforeTime1;
        }
        int intervalDay = current.getDate() - date.getDate();

        // 超过2天，直接显示原来格式时间
        if (intervalDay == 0) {
            return "今天";
        } else if (intervalDay == 1) {
            return "昨天  " + beforeTime2;
        } else if (c.get(Calendar.DAY_OF_WEEK_IN_MONTH) == calendar.get(Calendar.DAY_OF_WEEK_IN_MONTH)) {
            return getWeekStr(new SimpleDateFormat("EEEE").format(date.getTime()));
        } else {
            return beforeTime1;
        }
    }

    public static String getWeekStr(String str) {

        if ("1".equals(str)) {
            str = "星期日";
        } else if ("2".equals(str)) {
            str = "星期一";
        } else if ("3".equals(str)) {
            str = "星期二";
        } else if ("4".equals(str)) {
            str = "星期三";
        } else if ("5".equals(str)) {
            str = "星期四";
        } else if ("6".equals(str)) {
            str = "星期五";
        } else if ("7".equals(str)) {
            str = "星期六";
        }
        return str;
    }

    public static final String convertDate2String(Date date, String format) {
        SimpleDateFormat df = null;
        String returnValue = "";
        if (date == null) {
        } else {
            df = new SimpleDateFormat(format);
            returnValue = df.format(date);
        }
        return returnValue;
    }

    public static final String convertDate2String(long millisecond, String format) {
        SimpleDateFormat df = null;
        String returnValue = "";
        if (millisecond > 0) {
            Date date = new Date(millisecond);
            df = new SimpleDateFormat(format);
            returnValue = df.format(date);
        } else {
        }

        return returnValue;
    }
}
