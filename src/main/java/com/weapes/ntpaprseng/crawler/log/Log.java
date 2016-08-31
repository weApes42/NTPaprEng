package com.weapes.ntpaprseng.crawler.log;

import java.util.concurrent.atomic.AtomicInteger;

/**
 * Created by Programmer on 2016/8/14.
 */
public class Log {
    //单次爬取论文总量
    private static AtomicInteger URL_NUMBERS = new AtomicInteger();
    //目前正在爬取的第几篇论文
    private static final AtomicInteger CRAWLING_NUMBERS = new AtomicInteger();
    //单次爬取成功数量
    private static final AtomicInteger CRAWLING_SUCCEED_NUMBERS = new AtomicInteger();

    //单次更新指标论文总量
    private static AtomicInteger REF_NUMBERS = new AtomicInteger();
    //目前正在更新的第几篇论文
    private static final AtomicInteger REFRESHING_NUMBERS = new AtomicInteger();
    //单次更新成功数量
    private static final AtomicInteger REFRESHINGING_SUCCEED_NUMBERS = new AtomicInteger();

    private static String LAST_LINK;

    public static AtomicInteger getUrlNumbers() {
        return URL_NUMBERS;
    }

    public static AtomicInteger getCrawlingNumbers() {
        return CRAWLING_NUMBERS;
    }

    public static String getLastLink() {
        return LAST_LINK;
    }

    public static void setLastLink(String lastLink) {
        LAST_LINK = lastLink;
    }

    public static AtomicInteger getCrawlingSucceedNumbers() {
        return CRAWLING_SUCCEED_NUMBERS;
    }

    public static AtomicInteger getRefNumbers() { return REF_NUMBERS;
    }
    public static AtomicInteger getRefreshingNumbers() {
        return REFRESHING_NUMBERS;
    }
    public static AtomicInteger getRefreshingingSucceedNumbers() {
        return REFRESHINGING_SUCCEED_NUMBERS;
    }

}