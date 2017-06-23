package com.weapes.ntpaprseng.crawler.util;

import com.weapes.ntpaprseng.crawler.search.ESClient;
import com.weapes.ntpaprseng.crawler.store.MetricsPaper;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;

/**
 * Created by 不一样的天空 on 2017/6/21.
 */
public class DateHelper {
    private static final String DATE_FORMAT =
            "yyyy年MM月dd日 HH:mm";
    private static final String UPDATE_TIME_FORMAT =
            "yyyy年MM月dd日 HH:mm:ss:SSS";
    private static final String PUBLISH_TIME_FORMAT=
            "dd MMMM yyyy";
    public static long crawlStartTimeMills;//爬取开始时刻
    public static String crawlStartDate;//爬取开始日期
    public static long updateStartTimeMills;//更新开始时刻
    public static String updateStartDate;//更新开始日期
    public static String getCrawlTime() {
        final Date now = new Date();
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat(DATE_FORMAT);
        String time = simpleDateFormat.format(now);
        return time;
    }

    public static String getUpdateTime() {
        final Date now = new Date();
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat(UPDATE_TIME_FORMAT);
        String time = simpleDateFormat.format(now);
        return time;
    }

    public static String getSeconds(long millis) {
        float second = (float) (millis / 1000.0);
        return second + "秒";
    }

    public static long getCrawlStartTimeMills() {
        return crawlStartTimeMills;
    }

    public static void setCrawlStartTimeMills(long crawlStartTimeMills) {
        DateHelper.crawlStartTimeMills = crawlStartTimeMills;
    }

    public static long getUpdateStartTimeMills() {
        return updateStartTimeMills;
    }

    public static void setUpdateStartTimeMills(long updateStartTimeMills) {
        DateHelper.updateStartTimeMills = updateStartTimeMills;
    }

    public static String getCrawlStartDate() {
        return crawlStartDate;
    }

    public static void setCrawlStartDate(String crawlStartDate) {
        DateHelper.crawlStartDate = crawlStartDate;
    }

    public static String getUpdateStartDate() {
        return updateStartDate;
    }

    public static void setUpdateStartDate(String updateStartDate) {
        DateHelper.updateStartDate = updateStartDate;
    }

    public static Calendar formatPublishTime(String publishTime) {
        Calendar calendar=Calendar.getInstance();
        SimpleDateFormat dateFormat=new SimpleDateFormat(PUBLISH_TIME_FORMAT, Locale.ENGLISH);
        try {
            Date date = dateFormat.parse(publishTime);
            calendar.setTime(date);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return calendar;
    }
    public static Calendar formatUpdateTime(final String updateTime) {
        Calendar calendar=Calendar.getInstance();
        SimpleDateFormat dateFormat=new SimpleDateFormat(UPDATE_TIME_FORMAT);
        try {
            Date date = dateFormat.parse(updateTime);
            calendar.setTime(date);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return calendar;
    }

}
