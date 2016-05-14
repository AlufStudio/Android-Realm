package com.example.android_realm.util;

import android.text.format.DateUtils;

import java.util.Date;

/**
 * Created by wim on 5/14/16.
 */
public class TimeUtil {

    public static long getUnix(){
        return new Date().getTime();
    }

    public static String unixToTimeAgo(String unix){
        CharSequence timeAgo = DateUtils.getRelativeTimeSpanString(
                Long.parseLong(unix),
                System.currentTimeMillis(), DateUtils.SECOND_IN_MILLIS);

        return timeAgo.toString();
    }
}
