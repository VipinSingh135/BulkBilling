package com.acuratechglobal.bulkbilling.utils;

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
        String pattern = "dd-MM-yyyy 'at' HH:mm aa";
        SimpleDateFormat sdfTo = new SimpleDateFormat(pattern);
        Date date = sdf.parse(strDate);

        return sdfTo.format(date);
    }

    public static boolean checktimings(String time, String endtime) {

        String pattern = "HH:mm aa";
        SimpleDateFormat sdf = new SimpleDateFormat(pattern);

        try {
            Date date1 = sdf.parse(time);
            Date date2 = sdf.parse(endtime);
            if (time.contains("PM")){
                date1.setTime(date1.getTime()+(12*60*60*1000));
            }
            if (endtime.contains("PM")){
                date2.setTime(date2.getTime()+(12*60*60*1000));
            }
            return date1.before(date2);

        } catch (ParseException e){
            e.printStackTrace();
        }
        return false;
    }
}
