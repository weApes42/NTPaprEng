package com.weapes.ntpaprseng.crawler.store;

import com.weapes.ntpaprseng.crawler.log.Log;
import com.weapes.ntpaprseng.crawler.mapper.LogMapper;
import com.weapes.ntpaprseng.crawler.mapper.PaperMapper;
import com.weapes.ntpaprseng.crawler.util.DateHelper;
import com.weapes.ntpaprseng.crawler.util.Helper;
import com.weapes.ntpaprseng.crawler.util.SqlHelper;
import org.apache.ibatis.session.SqlSession;
import org.slf4j.Logger;

import static com.weapes.ntpaprseng.crawler.log.Log.*;
import static com.weapes.ntpaprseng.crawler.util.Helper.firstInsertUpdateDetailLog;
import static com.weapes.ntpaprseng.crawler.util.Helper.getLogger;

public class MetricsPaper implements Storable {

    private static final Logger LOGGER =
            getLogger(Paper.class);
    private static final SqlSession sqlSession= SqlHelper.getSqlSession();
    private String url;
    private int pageViews;
    private int webOfScience;
    private int crossRef;
    private int scopus;
    private int newsOutlets;
    private int reddit;
    private int blog;
    private int tweets;
    private int facebook;
    private int google;
    private int pinterest;
    private int wikipedia;
    private int mendeley;
    private int citeUlink;
    private int zotero;
    private int f1000;
    private int video;
    private int linkedin;
    private int q_a;
    private String updateTime;
    private int finalIndex;

    public MetricsPaper(final String url,
                        final int pageViews,
                        final int webOfScience,
                        final int crossRef,
                        final int scopus,
                        final int newsOutlets,
                        final int reddit,
                        final int blog,
                        final int tweets,
                        final int facebook,
                        final int google,
                        final int pinterest,
                        final int wikipedia,
                        final int mendeley,
                        final int citeUlink,
                        final int zotero,
                        final int f1000,
                        final int video,
                        final int linkedin,
                        final int q_a) {
        this.url = url;
        this.updateTime = DateHelper.getUpdateTime();
        this.pageViews = pageViews;
        this.webOfScience = webOfScience;
        this.crossRef = crossRef;
        this.scopus = scopus;
        this.newsOutlets = newsOutlets;
        this.reddit = reddit;
        this.blog = blog;
        this.tweets = tweets;
        this.facebook = facebook;
        this.google = google;
        this.pinterest = pinterest;
        this.wikipedia = wikipedia;
        this.mendeley = mendeley;
        this.citeUlink = citeUlink;
        this.zotero = zotero;
        this.f1000 = f1000;
        this.video = video;
        this.linkedin = linkedin;
        this.q_a = q_a;
        this.finalIndex = 0;
    }

    public MetricsPaper() {
    }

    public MetricsPaper setUrl(String url) {
        this.url = url;
        return this;
    }

    public MetricsPaper setUpdateTime(String updateTime) {
        this.updateTime = updateTime;
        return this;
    }

    public String getUrl() {
        return url;
    }

    public int getPageViews() {
        return pageViews;
    }

    public int getWebOfScience() {
        return webOfScience;
    }

    public int getCrossRef() {
        return crossRef;
    }

    public int getScopus() {
        return scopus;
    }

    public int getNewsOutlets() {
        return newsOutlets;
    }

    public int getReddit() {
        return reddit;
    }

    public int getBlog() {
        return blog;
    }

    public int getTweets() {
        return tweets;
    }

    public int getFacebook() {
        return facebook;
    }

    public int getGoogle() {
        return google;
    }

    public int getPinterest() {
        return pinterest;
    }

    public int getWikipedia() {
        return wikipedia;
    }

    public int getMendeley() {
        return mendeley;
    }

    public int getCiteUlink() {
        return citeUlink;
    }

    public int getZotero() {
        return zotero;
    }

    public int getF1000() {
        return f1000;
    }

    public int getVideo() {
        return video;
    }

    public int getLinkedin() {
        return linkedin;
    }

    public int getQ_a() {
        return q_a;
    }

    public String getUpdateTime() {
        return updateTime;
    }

    public int getFinalIndex() {
        return finalIndex;
    }

    @Override
    public boolean store() {
        LOGGER.info("本次更新论文" + Log.getUpdateTotalNumbers().get() + "篇，"
                + "正在更新第" + Log.getCurrentUpdateNumbers().incrementAndGet() + "篇\n"
                + "链接为：" + getUrl());
        LOGGER.error("*********************"+toString()+"*******************");
        PaperMapper paperMapper = sqlSession.getMapper(PaperMapper.class);
        boolean succeed = paperMapper.saveMetricsPaper(this);
        LOGGER.info("保存爬取的数据: type = MetricsPaper.");
        if (succeed) {
            LOGGER.info("当前共有" + getUpdateSucceedNumbers().incrementAndGet() + "篇论文相关指标更新成功..."
                    + "链接为:" + getUrl());
        } else {
            LOGGER.error("当前共有" + getUpdateFailedNumbers().incrementAndGet() + "篇论文相关指标更新失败..."
                    + "链接为:" + getUrl());
        }
//        boolean isSuccess = ESClient.getInstance().updateMetricsPaperIntoES(this);//更新论文指标到ElasticSearch中的REF_DATA
//        if (isSuccess) {
//            LOGGER.info("更新论文指标到ElasticSearch中的METRICS_PAPER成功");
//        } else {
//            LOGGER.error("更新论文指标到ElasticSearch中的METRICS_PAPER失败");
//        }
        //保存更新的具体日志数据到数据库中
        LogMapper logMapper = sqlSession.getMapper(LogMapper.class);
        succeed = logMapper.saveUpdateDetailLog(getUrl(),
                getCurrentUpdateNumbers().get(),
                getUpdateTotalNumbers().get(),
                succeed,
                DateHelper.getUpdateStartDate()
        );
        if (succeed) {
            LOGGER.info("更新过程具体日志保存成功");
        } else {
            LOGGER.error("更新过程具体日志保存失败");
        }
        //更新完成，打印、保存日志和更新任务状态
        if (getCurrentUpdateNumbers().get() == getUpdateTotalNumbers().get()) {
            LOGGER.info("更新完成，本次更新相关指标论文总量：" + getUpdateTotalNumbers().get()
                    + " 成功数：" + getUpdateSucceedNumbers().get()
                    + " 失败数：" + getUpdateFailedNumbers());

            long startTime = DateHelper.getUpdateStartTimeMills();  //开始更新的时间
            long endTime = System.currentTimeMillis();//更新结束的时间
            String averageTime = DateHelper.getSeconds((endTime - startTime) / getUpdateTotalNumbers().get());
            //保存更新完成后的总体情况数据到数据库中
            succeed = logMapper.saveUpdateTotalLog(1,
                    DateHelper.getUpdateStartDate(),
                    getUpdateSucceedNumbers().get(),
                    getUpdateFailedNumbers().get(),
                    getUpdateTotalNumbers().get(),
                    averageTime);
            if (succeed) {
                LOGGER.info("更新过程总体日志保存成功");
            } else {
                LOGGER.error("更新过程总体日志保存失败");
            }
            getUpdateSucceedNumbers().set(0);
            getUpdateFailedNumbers().set(0);
            Log.getCurrentUpdateNumbers().set(0);
            getCurrentUpdateNumbers().set(0);
            firstInsertUpdateDetailLog = true;
            Helper.isUpdateFinished = true;
        }
        sqlSession.commit();
        return succeed;
    }

    @Override
    public String toString() {
        return "MetricsPaper{" +
                "url='" + url + '\'' +
                ", pageViews=" + pageViews +
                ", webOfScience=" + webOfScience +
                ", crossRef=" + crossRef +
                ", scopus=" + scopus +
                ", newsOutlets=" + newsOutlets +
                ", reddit=" + reddit +
                ", blog=" + blog +
                ", tweets=" + tweets +
                ", facebook=" + facebook +
                ", google=" + google +
                ", pinterest=" + pinterest +
                ", wikipedia=" + wikipedia +
                ", mendeley=" + mendeley +
                ", citeUlink=" + citeUlink +
                ", zotero=" + zotero +
                ", f1000=" + f1000 +
                ", video=" + video +
                ", linkedin=" + linkedin +
                ", q_a=" + q_a +
                ", updateTime='" + updateTime + '\'' +
                ", finalIndex=" + finalIndex +
                '}';
    }
}
