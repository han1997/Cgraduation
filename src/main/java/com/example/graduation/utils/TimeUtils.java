package com.example.graduation.utils;

import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * @author hhy1997
 * 2020/3/17
 */
public class TimeUtils {
    public static Timestamp getDateTime(){
        //获得系统时间. 
        Date date = new Date();
        String nowTime = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(date);
        //把时间转换 
        Timestamp goodsC_date = Timestamp.valueOf(nowTime);
        return goodsC_date;
    }

}
