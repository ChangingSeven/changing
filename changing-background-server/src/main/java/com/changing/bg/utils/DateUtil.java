package com.changing.bg.utils;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

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