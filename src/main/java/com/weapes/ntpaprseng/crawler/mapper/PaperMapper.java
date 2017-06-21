package com.weapes.ntpaprseng.crawler.mapper;

import com.weapes.ntpaprseng.crawler.store.MetricsPaper;
import com.weapes.ntpaprseng.crawler.store.Paper;

/**
 * Created by 不一样的天空 on 2017/6/20.
 */
public interface PaperMapper {
    boolean savePaper(Paper paper);
    boolean saveMetricsPaper(MetricsPaper metricsPaper);
}
