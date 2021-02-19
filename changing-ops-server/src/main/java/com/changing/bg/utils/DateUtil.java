package com.changing.bg.utils;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Objects;

/**
 * @author chenjun
 * @version V1.0
 * @desc 日期工具类
 * @since 2020-03-17 13:24
 */
public class DateUtil {

    public static final String YYYY_MM_DD = "yyyy-MM-dd";
    public static final String YYYYMMDD = "yyyyMMdd";

    public static String dateToString(Date date, String format) {
        SimpleDateFormat sdf = new SimpleDateFormat(format);
        return sdf.format(date);
    }

    public static Date stringToDate(String date, String format) throws ParseException {
        SimpleDateFormat sdf = new SimpleDateFormat(format);
        return sdf.parse(date);
    }

    /**
     * 对日期进行计算
     *
     * @param date 计算前的日期
     * @param unit 计算单位，例如：Calendar.DAY_OF_YEAR {@link Calendar}
     * @param val  偏移量，正负数都可以
     * @return 计算后的日期
     */
    public static Date calculateDate(Date date, int unit, int val) {
        if (Objects.isNull(date)) {
            return null;
        }

        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        calendar.add(unit, val);

        return calendar.getTime();
    }

    /**
     * 工具方法测试
     *
     * @param args
     */
    public static void main(String[] args) throws ParseException {
        System.out.println(DateUtil.dateToString(new Date(), DateUtil.YYYY_MM_DD));

        System.out.println(DateUtil.dateToString(new Date(), DateUtil.YYYYMMDD));

        System.out.println(DateUtil.stringToDate("2020-05-01", DateUtil.YYYY_MM_DD).toString());
    }

}