package com.acuratechglobal.bulkbilling.utils;

import android.util.Log;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class TimeUtils {

    public static Date stringToDate(String strDate) throws ParseException {

        String pattern = "yyyy-MM-dd'T'HH:mm:ss";
        SimpleDateFormat sdf = new SimpleDateFormat(pattern);
        Date date = sdf.parse(strDate);

        return date;
    }

    public static String changeDateFormat(String strDate) throws ParseException {

        String fromPattern = "yyyy-MM-dd'T'HH:mm:ss";
        SimpleDateFormat sdf = new SimpleDateFormat(fromPattern);
        String pattern = "dd MMM yyyy EEEE";
        SimpleDateFormat sdfTo = new SimpleDateFormat(pattern);
        Date date = sdf.parse(strDate);

        return sdfTo.format(date);
    }

    public static String changeDateTimeFormat(String strDate) throws ParseException {

        String fromPattern = "yyyy-MM-dd'T'HH:mm:ss";
        SimpleDateFormat sdf = new SimpleDateFormat(fromPattern);
        String pattern = "dd-MM-yyyy 'at' hh:mm aa";
        SimpleDateFormat sdfTo = new SimpleDateFormat(pattern);
        Date date = sdf.parse(strDate);

        return sdfTo.format(date);
    }

    public static boolean checktimings(String time, String endtime) {

        String pattern = "HH:mm";
        SimpleDateFormat sdf = new SimpleDateFormat(pattern);

        try {
            Date date1 = sdf.parse(time);
            Date date2 = sdf.parse(endtime);

//            Log.d("Checktimings:", "Open time: " + date1.toString());
//            Log.d("Checktimings:", "Close time: " + date2.toString());

            if (time.contains("PM") && !time.contains("12")) {
                date1.setTime(date1.getTime() + (12 * 60 * 60 * 1000));
            }
            if (endtime.contains("PM") && !endtime.contains("12")) {
                date2.setTime(date2.getTime() + (12 * 60 * 60 * 1000));
            }
            return date1.before(date2);

        } catch (ParseException e) {
            e.printStackTrace();
        }
        return false;
    }
}
