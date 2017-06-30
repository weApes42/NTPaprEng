package com.weapes.ntpaprseng.crawler.mapper;

import com.weapes.ntpaprseng.crawler.store.MetricsPaper;
import com.weapes.ntpaprseng.crawler.store.Paper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import java.util.HashMap;
import java.util.List;

/**
 * Created by 不一样的天空 on 2017/6/20.
 */
public interface PaperMapper {
    boolean savePaper(Paper paper);
    boolean saveMetricsPaper(MetricsPaper metricsPaper);
    String getPaperTitleByUrl(@Param("url") String url);
    void saveTopPaperInfo(@Param("url") String url,
                             @Param("title") String title);
    boolean clearTopPapers();
}
