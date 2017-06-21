package com.weapes.ntpaprseng.crawler.store;

import com.weapes.ntpaprseng.crawler.log.Log;
import com.weapes.ntpaprseng.crawler.mapper.LogMapper;
import com.weapes.ntpaprseng.crawler.mapper.PaperMapper;
import com.weapes.ntpaprseng.crawler.util.DateHelper;
import com.weapes.ntpaprseng.crawler.util.Helper;
import com.weapes.ntpaprseng.crawler.util.SQLHelper;
import org.apache.ibatis.session.SqlSession;
import org.slf4j.Logger;

import static com.weapes.ntpaprseng.crawler.log.Log.*;
import static com.weapes.ntpaprseng.crawler.util.Helper.firstInsertUpdateDetailLog;
import static com.weapes.ntpaprseng.crawler.util.Helper.getLogger;

public class MetricsPaper implements Storable {

    private static final Logger LOGGER =
            getLogger(Paper.class);
    private static final SqlSession sqlSession= SQLHelper.getSqlSession();
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

    public String getUrl() {
        return url;
    }

    public MetricsPaper setUrl(String url) {
        this.url = url;
        return this;
    }

    public int getPageViews() {
        return pageViews;
    }

    public MetricsPaper setPageViews(int pageViews) {
        this.pageViews = pageViews;
        return this;
    }

    public int getWebOfScience() {
        return webOfScience;
    }

    public MetricsPaper setWebOfScience(int webOfScience) {
        this.webOfScience = webOfScience;
        return this;
    }

    public int getCrossRef() {
        return crossRef;
    }

    public MetricsPaper setCrossRef(int crossRef) {
        this.crossRef = crossRef;
        return this;
    }

    public int getScopus() {
        return scopus;
    }

    public MetricsPaper setScopus(int scopus) {
        this.scopus = scopus;
        return this;
    }

    public int getNewsOutlets() {
        return newsOutlets;
    }

    public MetricsPaper setNewsOutlets(int newsOutlets) {
        this.newsOutlets = newsOutlets;
        return this;
    }

    public int getReddit() {
        return reddit;
    }

    public MetricsPaper setReddit(int reddit) {
        this.reddit = reddit;
        return this;
    }

    public int getBlog() {
        return blog;
    }

    public MetricsPaper setBlog(int blog) {
        this.blog = blog;
        return this;
    }

    public int getTweets() {
        return tweets;
    }

    public MetricsPaper setTweets(int tweets) {
        this.tweets = tweets;
        return this;
    }

    public int getFacebook() {
        return facebook;
    }

    public MetricsPaper setFacebook(int facebook) {
        this.facebook = facebook;
        return this;
    }

    public int getGoogle() {
        return google;
    }

    public MetricsPaper setGoogle(int google) {
        this.google = google;
        return this;
    }

    public int getPinterest() {
        return pinterest;
    }

    public MetricsPaper setPinterest(int pinterest) {
        this.pinterest = pinterest;
        return this;
    }

    public int getWikipedia() {
        return wikipedia;
    }

    public MetricsPaper setWikipedia(int wikipedia) {
        this.wikipedia = wikipedia;
        return this;
    }

    public int getMendeley() {
        return mendeley;
    }

    public MetricsPaper setMendeley(int mendeley) {
        this.mendeley = mendeley;
        return this;
    }

    public int getCiteUlink() {
        return citeUlink;
    }

    public MetricsPaper setCiteUlink(int citeUlink) {
        this.citeUlink = citeUlink;
        return this;
    }

    public int getZotero() {
        return zotero;
    }

    public MetricsPaper setZotero(int zotero) {
        this.zotero = zotero;
        return this;
    }

    public int getF1000() {
        return f1000;
    }

    public MetricsPaper setF1000(int f1000) {
        this.f1000 = f1000;
        return this;
    }

    public int getVideo() {
        return video;
    }

    public MetricsPaper setVideo(int video) {
        this.video = video;
        return this;
    }

    public int getLinkedin() {
        return linkedin;
    }

    public MetricsPaper setLinkedin(int linkedin) {
        this.linkedin = linkedin;
        return this;
    }

    public int getQ_a() {
        return q_a;
    }

    public MetricsPaper setQ_a(int q_a) {
        this.q_a = q_a;
        return this;
    }

    public int getFinalIndex() {
        return finalIndex;
    }

    public MetricsPaper setFinalIndex(int finalIndex) {
        this.finalIndex = finalIndex;
        return this;
    }

    public String getUpdateTime() {
        return updateTime;
    }

    public MetricsPaper setUpdateTime(String updateTime) {
        this.updateTime = updateTime;
        return this;
    }

    @Override
    public boolean store() {
        LOGGER.info("本次更新论文" + Log.getUpdateTotalNumbers().get() + "篇，"
                + "正在更新第" + Log.getCurrentUpdateNumbers().incrementAndGet() + "篇\n"
                + "链接为：" + getUrl());
        PaperMapper paperMapper = sqlSession.getMapper(PaperMapper.class);
        boolean succeed = paperMapper.saveMetricsPaper(this);
        System.out.println("保存爬取的数据: type = MetricsPaper.");
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

}
