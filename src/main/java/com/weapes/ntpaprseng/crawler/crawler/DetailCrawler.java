package com.weapes.ntpaprseng.crawler.crawler;

import com.weapes.ntpaprseng.crawler.log.Log;
import com.weapes.ntpaprseng.crawler.util.DateHelper;
import com.weapes.ntpaprseng.crawler.util.Helper;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class DetailCrawler implements Crawler {

    //生产者消费者线程数,可以根据环境进行调整
    private static final int CREATOR_THREAD_NUM = 10;
    private static final int CONSUMER_THREAD_NUM = 1;
   /*
     * 生产者负责把Followable解析为Storable,
     * 消费者负责把Storable存储。
     */
    private static final ExecutorService CREATOR =
            Executors.newFixedThreadPool(CREATOR_THREAD_NUM);
    private static final ExecutorService CONSUMER =
            Executors.newFixedThreadPool(CONSUMER_THREAD_NUM);

    @Override
    public void crawl() {
        DateHelper.setUpdateStartDate(DateHelper.getUpdateTime());
        DateHelper.setUpdateStartTimeMills(System.currentTimeMillis());
        System.out.print("开始更新指标。系统时间： " + DateHelper.getCrawlStartDate() + "\n");

        Log.getUpdateTotalNumbers().set(Helper.getRefDataNum()); //更新论文总数
        // 种子解析为followable
        // 对每个种子,交给生产者处理为Storable
        Helper.loadMetricsLinks().forEach(paper ->
                CREATOR.submit(new StorableFetcher<>(CREATOR, CONSUMER, paper)));
    }

}

