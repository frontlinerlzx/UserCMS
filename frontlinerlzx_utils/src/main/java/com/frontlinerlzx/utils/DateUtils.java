package com.frontlinerlzx.utils;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * 完成日期和字符串的转换
 */
public class DateUtils {
    /**
     * 日期转字符串
     */

    public static String dateToString(Date date, String patt){
        SimpleDateFormat sdf = new SimpleDateFormat(patt);
        String format = sdf.format(date);
        return format;

    }

    /**
     * 字符串转日期
     */
    public static Date stringToDate(String str ,String patt) throws ParseException {
        SimpleDateFormat sdf = new SimpleDateFormat(patt);
        Date date = sdf.parse(str);
        return date;

    }

}
