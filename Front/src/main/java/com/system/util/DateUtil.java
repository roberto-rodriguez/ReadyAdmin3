/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.system.util;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 *
 * @author roberto.rodriguez
 */
public class DateUtil {

    public static final DateFormat DateFormat_YYMMDD = new SimpleDateFormat("YYMMdd");
    public static final DateFormat DateFormat_YYMMddHHmmss = new SimpleDateFormat("YYMMddHHmmss");

    public static String getTodayYYMMDD() {
        return DateFormat_YYMMDD.format(new Date());
    }

    public static String getTodayDateFormat_YYMMddHHmmss() {
        return DateFormat_YYMMddHHmmss.format(new Date());
    }

    public static void main(String[] args) {
        System.out.println(getTodayDateFormat_YYMMddHHmmss());
    }

    public static Boolean wasTokenWithinAMinute(Date date) {
        try {
            if (date != null) {
                long diff = (new Date()).getTime() - date.getTime(); //This will tell if that date was within a minute
                return diff < 60000;
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return false;
    }
}
