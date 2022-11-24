package com.zklt.parsing.handler.utils;



import java.math.BigDecimal;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.Instant;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;
import java.util.Calendar;
import java.util.Date;

/**
 * [ 日期工具类 ]
 *
 * @author wenjunzhangp
 * @since 2018-08-01
 */
public class DateUtil {

    public static final String FULL_TIME_PATTERN = "yyyyMMddHHmmss";

    public static final String FULL_TIME_FORMAT = "yyyy-MM-dd HH:mm:ss";

    public static final String NORMAL_TIME_FORMAT = "yyyy-MM-dd";

    /**
     * 检测请求的时间戳是否超出历史数据范围
     *
     * @param timestamp
     * @return
     */
    public static boolean isOutOfRangeTime(String timestamp) {
        Date requestDate = DateUtil.stampToTime(timestamp);
        Date indexDate = DateUtil.reduceDay(new Date(), 2);
        if (requestDate.before(indexDate)) {
            return true;
        }
        return false;
    }

    /**
     * 时间戳转换为时间类型
     *
     * @param timestamp
     * @return
     * @throws Exception
     */
    public static Date stampToTime(String timestamp) {
        String res;
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        long lt = new Long(timestamp);
        //将时间戳转换为时间
        Date date = new Date(lt);
        //将时间调整为yyyy-MM-dd HH:mm:ss时间样式
        res = simpleDateFormat.format(date);
        return DateUtil.str2Date(res,DateUtil.FULL_TIME_FORMAT);
    }

    public static String formatFullTime(LocalDateTime localDateTime) {
        return formatFullTime(localDateTime, FULL_TIME_PATTERN);
    }

    public static String formatFullTime(LocalDateTime localDateTime, String pattern) {
        DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern(pattern);
        return localDateTime.format(dateTimeFormatter);
    }

    /**
     * 获得某天最大时间 2020-02-19 23:59:59
     *
     * @param date
     * @return
     */
    public static Date getEndOfDay(Date date) {
        LocalDateTime localDateTime = LocalDateTime.ofInstant(Instant.ofEpochMilli(date.getTime()), ZoneId.systemDefault());
        LocalDateTime endOfDay = localDateTime.with(LocalTime.MAX);
        return Date.from(endOfDay.atZone(ZoneId.systemDefault()).toInstant());
    }

    /**
     * 获得某天最小时间 2020-02-17 00:00:00
     *
     * @param date
     * @return
     */
    public static Date getStartOfDay(Date date) {
        LocalDateTime localDateTime = LocalDateTime.ofInstant(Instant.ofEpochMilli(date.getTime()), ZoneId.systemDefault());
        LocalDateTime startOfDay = localDateTime.with(LocalTime.MIN);
        return Date.from(startOfDay.atZone(ZoneId.systemDefault()).toInstant());
    }

    /**
     * 按照yyyy-MM-dd HH:mm:ss的格式，日期转字符串
     *
     * @param date
     * @return yyyy-MM-dd HH:mm:ss
     */
    public static String date2Str(Date date) {
        return date2Str(date, FULL_TIME_FORMAT);
    }

    /**
     * 按照参数format的格式，日期转字符串
     *
     * @param date
     * @param format
     * @return
     */
    public static String date2Str(Date date, String format) {
        if (date != null) {
            SimpleDateFormat sdf = new SimpleDateFormat(format);
            return sdf.format(date);
        } else {
            return "";
        }
    }

    /**
     * 按照yyyy-MM-dd的格式，字符串转日期
     *
     * @param str
     * @return
     */
    public static Date str2Date(String str) {
        return str2Date(str, "yyyy-MM-dd");
    }

    /**
     * 按照参数format的格式，字符串转日期
     *
     * @param str
     * @param format
     * @return
     */
    public static Date str2Date(String str, String format) {
            SimpleDateFormat sdf = new SimpleDateFormat(format);
            Date parseDate;
            try {
                parseDate = sdf.parse(str);
            } catch (Exception e) {
                parseDate = new Date();
            }
            return parseDate;
    }

    public static Date dealDateFormat(String oldDateStr) {
        DateFormat df = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm+08:00");
        try {
            Date date = df.parse(oldDateStr);
            return date;
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return null;
    }

    /**
     * 判断是否为今日
     *
     * @param date
     * @return
     */
    public static boolean isToday(Date date) {
        if (date == null) {
            {
                return false;
            }
        }
        Calendar cal = Calendar.getInstance();
        cal.setTime(date);
        int year1 = cal.get(Calendar.YEAR);
        int month1 = cal.get(Calendar.MONTH) + 1;
        int day1 = cal.get(Calendar.DAY_OF_MONTH);
        cal.setTime(new Date());
        int year2 = cal.get(Calendar.YEAR);
        int month2 = cal.get(Calendar.MONTH) + 1;
        int day2 = cal.get(Calendar.DAY_OF_MONTH);
        if (year1 == year2 && month1 == month2 && day1 == day2) {
            {
                return true;
            }
        }
        return false;
    }

    /**
     * 字符串时间转Date
     */

    public static Date strCastToDate(String time){
        String reg = "(\\d{4})(\\d{2})(\\d{2})(\\d{2})(\\d{2})(\\d{2})";
        time = time.replaceAll(reg, "$1-$2-$3 $4:$5:$6");

        return DateUtil.str2Date(time,DateUtil.FULL_TIME_FORMAT);
    }

    /**
     * 获取某日期当月第一天
     *
     * @param date
     * @return
     */
    public static String getFirstDayOfMonth(Date date) {
        Calendar cal = Calendar.getInstance();
        cal.setTime(date);
        cal.set(Calendar.DAY_OF_MONTH, cal.getMinimum(Calendar.DATE));
        return new SimpleDateFormat("yyyy-MM-dd ").format(cal.getTime());
    }

    /**
     * 获取某日期当月最后一天
     *
     * @param date
     * @return
     */
    public static String getLastDayOfMonth(Date date) {
        Calendar cal = Calendar.getInstance();
        cal.setTime(date);
        cal.set(Calendar.DAY_OF_MONTH, cal.getActualMaximum(Calendar.DATE));
        return new SimpleDateFormat("yyyy-MM-dd ").format(cal.getTime());
    }

    /**
     * 传入时间，减少小时，时间往前推
     *
     * @param date
     * @param hour
     * @return
     */
    public static Date reduceHour(Date date, int hour) {
        if (hour == 0) {
            return date;
        }
        Calendar c = Calendar.getInstance();
        c.setTime(date);
        c.add(Calendar.HOUR_OF_DAY, -hour);
        return c.getTime();
    }

    /**
     * 传入时间，减少天，时间往前推
     *
     * @param date
     * @param day
     * @return
     */
    public static Date reduceDay(Date date, int day) {
        if (day == 0) {
            return date;
        }
        Calendar c = Calendar.getInstance();
        c.setTime(date);
        c.add(Calendar.DAY_OF_YEAR, -day);
        return c.getTime();
    }

    /**
     * 传入时间，增加分钟
     *
     * @param date
     * @param mu
     * @return
     */
    public static Date addMinute(Date date, int mu) {
        if (mu == 0) {
            return date;
        }
        Calendar c = Calendar.getInstance();
        c.setTime(date);
        c.add(Calendar.MINUTE, mu);
        return c.getTime();
    }

    public static Date addHour(Date date, int hour) {
        if (hour == 0) {
            return date;
        }
        Calendar c = Calendar.getInstance();
        c.setTime(date);
        c.add(Calendar.HOUR, hour);
        return c.getTime();
    }

    /**
     * 传入时间，增加天
     *
     * @param date
     * @param mu
     * @return
     */
    public static Date addDay(Date date, int day) {
        if (day == 0) {
            {
                return date;
            }
        }
        Calendar c = Calendar.getInstance();
        c.setTime(date);
        c.add(Calendar.DAY_OF_YEAR, day);
        return c.getTime();
    }

    /**
     * 传入时间，增加月
     *
     * @param date
     * @param mu
     * @return
     */
    public static Date addMonth(Date date, int day) {
        if (day == 0) {
            {
                return date;
            }
        }
        Calendar c = Calendar.getInstance();
        c.setTime(date);
        c.add(Calendar.MONTH, day);
        return c.getTime();
    }

    /**
     * 传入时间，增加年
     *
     * @param date
     * @param mu
     * @return
     */
    public static Date addYear(Date date, int day) {
        if (day == 0) {
            {
                return date;
            }
        }
        Calendar c = Calendar.getInstance();
        c.setTime(date);
        c.add(Calendar.YEAR, day);
        return c.getTime();
    }

    /**
     * 日期相隔多天
     * 一个更极端的列子：newerDate是6月3日 00:01，olderDate是6月2日 23:59，则应返回1，说明相差一天，即便实际上只差2分钟
     *
     * @param newerDate
     * @param olderDate
     * @return
     */
    public static int daysBetween(Date newerDate, Date olderDate) {
        newerDate = DateUtil.str2Date(DateUtil.date2Str(newerDate, "yyyy-MM-dd HH:ss:mm"), "yyyy-MM-dd");
        olderDate = DateUtil.str2Date(DateUtil.date2Str(olderDate, "yyyy-MM-dd HH:ss:mm"), "yyyy-MM-dd");
        long todayMs = newerDate.getTime();
        long returnMs = olderDate.getTime();
        long intervalMs = todayMs - returnMs;
        return millisecondsToDays(intervalMs);
    }

    private static int millisecondsToDays(long intervalMs) {
        return (int) (intervalMs / (1000 * 86400));
    }

    /**
     * 日期相隔多少小时
     *
     * @param newerDate
     * @param olderDate
     * @return
     */
    public static int hoursBetween(Date newerDate, Date olderDate) {
        long todayMs = newerDate.getTime();
        long returnMs = olderDate.getTime();
        long intervalMs = todayMs - returnMs;
        return millisecondsToHours(intervalMs);
    }

    private static int millisecondsToHours(long intervalMs) {
        return (int) (intervalMs / (1000 * 3600));
    }

    /**
     * 日期相隔多少月
     *
     * @param newerDate
     * @param olderDate
     * @return
     */
    public static int monthBetween(Date newerDate, Date olderDate) {
        Calendar c1 = Calendar.getInstance();
        Calendar c2 = Calendar.getInstance();
        c1.setTime(newerDate);
        c2.setTime(olderDate);
        int year = c2.get(Calendar.YEAR) - c1.get(Calendar.YEAR);
        if (year < 0) {
            year = -year;
            return year * 12 + c1.get(Calendar.MONTH) - c2.get(Calendar.MONTH);
        }
        return year * 12 + c2.get(Calendar.MONTH) - c1.get(Calendar.MONTH);
    }

    public static Date parseTimeString2Date(String timeString) {
        if ((timeString == null) || (timeString.equals(""))) {
            return null;
        }
        Date date = null;
        DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
        try {
            date = new Date(dateFormat.parse(timeString).getTime());
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return date;
    }

    public static String convertDate2String(Date date, String pattern) {
        if (date == null) return null;
        DateFormat dateFormat = new SimpleDateFormat(pattern);
        return dateFormat.format(date);
    }

    public static BigDecimal getYear(Date timeString) {
        String timeStr = convertDate2String(timeString, "yyyy-MM-dd HH:mm:ss");
        return new BigDecimal(timeStr.substring(0, 4));
    }

    public static BigDecimal getMonth(Date timeString) {
        String timeStr = convertDate2String(timeString, "yyyy-MM-dd HH:mm:ss");
        return new BigDecimal(timeStr.substring(5, 7));
    }

    public static BigDecimal getDay(Date timeString) {
        String timeStr = convertDate2String(timeString, "yyyy-MM-dd HH:mm:ss");
        return new BigDecimal(timeStr.substring(8, 10));
    }

    public static BigDecimal getHour(Date timeString) {
        String timeStr = convertDate2String(timeString, "yyyy-MM-dd HH:mm:ss");
        return new BigDecimal(timeStr.substring(11, 13));
    }

    public static BigDecimal getMiute(Date timeString) {
        String timeStr = convertDate2String(timeString, "yyyy-MM-dd HH:mm:ss");
        return new BigDecimal(timeStr.substring(14, 16));
    }

    public static int getSeconds(Date timeString) {
        String timeStr = convertDate2String(timeString, "yyyy-MM-dd HH:mm:ss");
        return Integer.parseInt(timeStr.substring(17, 19));
    }

    public static void main(String[] args) {

    }
}
