package com.weapes.ntpaprseng.crawler.mapper;

import com.weapes.ntpaprseng.crawler.follow.PaperMetricsLink;
import com.weapes.ntpaprseng.crawler.store.MetricsPaper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * Created by 不一样的天空 on 2017/6/21.
 */
public interface UtilMapper {
    int countPaperMetricsLink();
    List<String> listPaperMetricsLink();
    String getLastUrlFromLastTime();
    boolean updateLastUrl(@Param("lastUrl") String lastUrl);
}
