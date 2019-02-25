package com.appetiser.ituneflix.helper;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

public class TimeHelper {

    public static String getCurrent() {
        /**
         * Get Current time and save this time to session
         * to be use in list header
         */
        Calendar cal = Calendar.getInstance();
        Date date = cal.getTime();
        DateFormat dateFormat = new SimpleDateFormat("MMMM dd, yyyy HH:mm:ss");
        String formattedDate = dateFormat.format(date);
        return formattedDate;
    }
}
