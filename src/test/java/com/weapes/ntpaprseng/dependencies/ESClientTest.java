package com.weapes.ntpaprseng.dependencies;

import com.weapes.ntpaprseng.crawler.search.ESClient;
import com.weapes.ntpaprseng.crawler.store.MetricsPaper;
import org.junit.Test;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Random;

public class ESClientTest {
    @Test
    public void testESClientConnectivity() {
        String UPDATE_TIME_FORMAT =
                "yyyy年MM月dd日 HH:mm:ss:SSS";
        SimpleDateFormat simpleDateFormat=new SimpleDateFormat(UPDATE_TIME_FORMAT);
        String time=simpleDateFormat.format(new Date());
        ESClient esClient=ESClient.getInstance();
        MetricsPaper metricsPaper=new MetricsPaper()
                .setUrl("sdfsd")
                .setUpdateTime(time);
        System.out.println(metricsPaper.getCiteUlink());
        esClient.updateMetricsPaperIntoES(metricsPaper);
    }
}
