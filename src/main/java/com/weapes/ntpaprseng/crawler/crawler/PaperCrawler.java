package com.weapes.ntpaprseng.crawler.crawler;

import com.weapes.ntpaprseng.crawler.util.DateHelper;
import com.weapes.ntpaprseng.crawler.util.Helper;
import org.slf4j.Logger;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

class PaperCrawler implements Crawler {

    //生产者消费者线程数
    private static final int CREATOR_THREAD_NUM = 8;
    private static final int CONSUMER_THREAD_NUM = 4;

    /*
     * 生产者负责把Followable解析为Storable,
     * 消费者负责把Storable存储。
     */
    private static final ExecutorService CREATOR =
            Executors.newFixedThreadPool(CREATOR_THREAD_NUM);
    private static final ExecutorService CONSUMER =
            Executors.newFixedThreadPool(CONSUMER_THREAD_NUM);

    private static final Logger LOGGER =
            Helper.getLogger(PaperCrawler.class);


    @Override
    public void crawl() {
        DateHelper.setCrawlStartDate(DateHelper.getCrawlTime());
        DateHelper.setCrawlStartTimeMills(System.currentTimeMillis());
        System.out.print("开始爬取论文信息。系统时间：" + DateHelper.getCrawlStartDate() + "\n");

        // 获取上次爬取的最后一篇论文URL
        Helper.initLastUrlForLastTime();
        // 种子解析为followable
        // 对每个种子,交给生产者处理为Storable
        Helper.loadSeeds().forEach(seed ->
                CREATOR.submit(new StorableFetcher<>(CREATOR, CONSUMER, seed)));

        LOGGER.info("种子分发完成...");

    }
    public static void main(String[]args){
        new PaperCrawler().crawl();
    }
}
