package com.book.utils;

import java.text.SimpleDateFormat;
import java.util.Date;

public class DateFormatUtil {
    static SimpleDateFormat format;

    static {
        format= new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
    }

    public static String getFormatDate(Date date){
        return format.format(date);
    }
}
