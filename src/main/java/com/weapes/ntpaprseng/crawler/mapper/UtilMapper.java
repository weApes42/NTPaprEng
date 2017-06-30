package com.weapes.ntpaprseng.crawler.mapper;


import org.apache.ibatis.annotations.Param;
import java.util.HashMap;
import java.util.List;

/**
 * Created by 不一样的天空 on 2017/6/21.
 */
public interface UtilMapper {
    int countPaperLink();
    List<String> listPaperLink();
    String getLastUrlFromLastTime();
    boolean updateLastUrl(@Param("lastUrl") String lastUrl);
    HashMap<String,Integer> getCrawlRange();
    int getTaskPeriod();
    int getTopNumber();
    HashMap<String,Double> listWeight();
    List<Double> listFinalIndexByUrl(@Param("url") String url);
}
