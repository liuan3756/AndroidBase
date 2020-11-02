package com.liuan.android.base.tool;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

/**
 * @author Peach Parrot
 * @date 2020年10月30日 15:11
 */
public class TimeFormatter
{
    private static final SimpleDateFormat simpleDateFormat = new SimpleDateFormat("", Locale.CHINA);
    private static final Date formatDate = new Date();
    private static final String TIME_PATTERN_1 = "yyyyMMdd";
    private static final String TIME_PATTERN_2 = "yyyy-MM-dd";
    private static final String TIME_PATTERN_3 = "yyyy MM dd";

    private static String formatTime(long time, String pattern)
    {
        formatDate.setTime(time);
        simpleDateFormat.applyPattern(pattern);
        return simpleDateFormat.format(formatDate);
    }

    private static long timeFormat(String timeString, String pattern)
    {
        simpleDateFormat.applyPattern(pattern);
        try
        {
            return simpleDateFormat.parse(timeString)
                                   .getTime();
        }
        catch (ParseException e)
        {
            e.printStackTrace();
        }
        return 0L;
    }

    public static String formatTime1(long time)
    {
        return formatTime(time, TIME_PATTERN_1);
    }

    public static long timeFormat1(String timeString)
    {
        return timeFormat(timeString, TIME_PATTERN_1);
    }

    public static String formatTime2(long time)
    {
        return formatTime(time, TIME_PATTERN_2);
    }

    public static long timeFormat2(String timeString)
    {
        return timeFormat(timeString, TIME_PATTERN_2);
    }

    public static String formatTime3(long time)
    {
        return formatTime(time, TIME_PATTERN_3);
    }

    public static long timeFormat3(String timeString)
    {
        return timeFormat(timeString, TIME_PATTERN_3);
    }
} 