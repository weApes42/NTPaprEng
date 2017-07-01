package com.weapes.ntpaprseng.crawler.crawler;

import com.weapes.ntpaprseng.crawler.util.Helper;
import java.util.concurrent.TimeUnit;

public class Launcher {

    public static void main(String args[]) throws InterruptedException {
        System.out.print("系统运行。\n");
        // 串行执行爬取、更新，每次任务完成任务读取间隔时间并休眠，无限循环
        while (true) {
            System.out.print("本次任务开始。\n");
           // 爬取
            new PaperCrawler().crawl();
            // 如果没有结束爬取，则主线程休眠，爬取线程继续
            while (!Helper.isCrawlFinished) Thread.sleep(2000);
          //  更新
            new DetailCrawler().crawl();
            while (!Helper.isUpdateFinished) Thread.sleep(2000);

            System.out.print("本次任务完成。\n");
            //爬取、更新状态重置 为下周期做准备
            Helper.isCrawlFinished = false;
            Helper.isUpdateFinished = false;
            // 获取本次休眠周期并休眠
            int period = Helper.getTaskPeriod();
            System.out.print("系统暂停：" + period + "小时\n");
            TimeUnit.HOURS.sleep(period);
        }
    }
}

