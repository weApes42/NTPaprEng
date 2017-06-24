package com.weapes.ntpaprseng.crawler.mapper;

import org.apache.ibatis.annotations.Param;

/**
 * Created by 不一样的天空 on 2017/6/20.
 */
public interface LogMapper {
    boolean saveCrawlDetailLog(@Param("url") String url,
                            @Param("articlePosition") int articlePosition,
                            @Param("totalNumber") int totalNumber,
                            @Param("isSuccessful") boolean isSuccessful,
                            @Param("crawlTime") String crawlTime
    );

    boolean saveCrawlTotalLog(@Param("crawlTime") String crawlTime,
                           @Param("successfulNumber") int successfulNumber,
                           @Param("failedNumber") int failedNumber,
                           @Param("totalNumber") int totalNumber,
                           @Param("averageTime") String averageTime
    );

    boolean saveUpdateDetailLog(@Param("url") String url,
                             @Param("articlePosition") int articlePosition,
                             @Param("totalNumber") int totalNumber,
                             @Param("isSuccessful") boolean isSuccessful,
                             @Param("updateTime") String updateTime
    );

    boolean saveUpdateTotalLog(@Param("updateTime") String updateTime,
                            @Param("successfulNumber") int successfulNumber,
                            @Param("failedNumber") int failedNumber,
                            @Param("totalNumber") int totalNumber,
                            @Param("averageTime") String averageTime
    );
}
