package com.weapes.ntpaprseng.crawler.store;

import com.weapes.ntpaprseng.crawler.log.Log;
import com.weapes.ntpaprseng.crawler.mapper.LogMapper;
import com.weapes.ntpaprseng.crawler.mapper.PaperMapper;
import com.weapes.ntpaprseng.crawler.search.ESClient;
import com.weapes.ntpaprseng.crawler.util.DateHelper;
import com.weapes.ntpaprseng.crawler.util.Helper;
import com.weapes.ntpaprseng.crawler.util.SqlHelper;
import org.apache.ibatis.session.SqlSession;
import org.slf4j.Logger;

import static com.weapes.ntpaprseng.crawler.util.Helper.getLogger;

public class Paper implements Storable {

    private static final Logger LOGGER = getLogger(Paper.class);
    private String authors;
    private String url;
    private String title;
    private String sourceTitle;
    private String issn;
    private String eissn;
    private String doi;
    private int volume;
    private int issue;
    private String pageBegin;
    private String pageEnd;
    private String affiliation;
    private String publishTime;
    private String crawlTime;

    public Paper(){

    }
    public Paper(final String url,
                 final String authors,
                 final String title,
                 final String sourceTitle,
                 final String issn,
                 final String eissn,
                 final String doi,
                 final int volume,
                 final int issue,
                 final String pageBegin,
                 final String pageEnd,
                 final String affiliation,
                 final String publishTime,
                 final String crawlTime) {
        this.url = url;
        this.authors = authors;
        this.title = title;
        this.sourceTitle = sourceTitle;
        this.issn = issn;
        this.eissn = eissn;
        this.doi = doi;
        this.volume = volume;
        this.issue = issue;
        this.pageBegin = pageBegin;
        this.pageEnd = pageEnd;
        this.affiliation = affiliation;
        this.publishTime = publishTime;
        this.crawlTime = crawlTime;
    }

    public void setAuthors(String authors) {
        this.authors = authors;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public void setSourceTitle(String sourceTitle) {
        this.sourceTitle = sourceTitle;
    }

    public void setIssn(String issn) {
        this.issn = issn;
    }

    public void setEissn(String eissn) {
        this.eissn = eissn;
    }

    public void setDoi(String doi) {
        this.doi = doi;
    }

    public void setVolume(int volume) {
        this.volume = volume;
    }

    public void setIssue(int issue) {
        this.issue = issue;
    }

    public void setPageBegin(String pageBegin) {
        this.pageBegin = pageBegin;
    }

    public void setPageEnd(String pageEnd) {
        this.pageEnd = pageEnd;
    }

    public void setAffiliation(String affiliation) {
        this.affiliation = affiliation;
    }

    public void setPublishTime(String publishTime) {
        this.publishTime = publishTime;
    }

    public void setCrawlTime(String crawlTime) {
        this.crawlTime = crawlTime;
    }

    public String getUrl() {
        return url;
    }

    public String getAuthors() {
        return authors;
    }

    public String getTitle() {
        return title;
    }

    public String getSourceTitle() {
        return sourceTitle;
    }

    public String getIssn() {
        return issn;
    }

    public String getEissn() {
        return eissn;
    }

    public String getDoi() {
        return doi;
    }

    public int getVolume() {
        return volume;
    }

    public int getIssue() {
        return issue;
    }

    public String getPageBegin() {
        return pageBegin;
    }

    public String getPageEnd() {
        return pageEnd;
    }

    public String getAffiliation() {
        return affiliation;
    }

    public String getPublishTime() {
        return publishTime;
    }

    public String getCrawlTime() {
        return crawlTime;
    }


    @Override
    public boolean store() {
        LOGGER.info("本次爬取论文" + Log.getUrlNumbers().get() + "篇，"
                + "正在爬取第" + Log.getCrawlingNumbers().incrementAndGet() + "篇\n"
                + "链接为：" + getUrl());

        if (Log.getUrlNumbers().get() == Log.getCrawlingNumbers().get()) { //记录最后一篇论文链接
            Log.setLastLink(getUrl());
        }
        final SqlSession sqlSession = SqlHelper.openSqlSession();
        LOGGER.info("保存爬取的数据: type=Paper");
        PaperMapper paperMapper = sqlSession.getMapper(PaperMapper.class);
        boolean isSucceed = paperMapper.savePaper(this);
        if (isSucceed) {
            LOGGER.info("当前共有" + Log.getCrawlingSucceedNumbers().incrementAndGet() + "篇爬取成功..."
                    + "url=" + getUrl());
        } else {
            LOGGER.error("当前共有" + Log.getCrawlingFailedNumber().incrementAndGet() + "篇爬取失败..."
                    + "url=" + getUrl());
        }
        MetricsPaper metricsPaper = new MetricsPaper()
                .setUrl(getMetricsUrl())
                .setUpdateTime(DateHelper.getUpdateTime());
        isSucceed = paperMapper.saveMetricsPaper(metricsPaper);//初始化PaperMetrics
        if (isSucceed) {
            LOGGER.info("url=" + getUrl() + "的PaperMetrics初始化成功");
        } else {
            LOGGER.error("url=" + getUrl() + "的PaperMetrics初始化失败");
        }

        isSucceed = ESClient.getInstance().savePaperIntoES(this);//保存论文信息到ES中
        if (isSucceed) {
            LOGGER.info("保存论文信息到ElasticSearch中的PAPER成功");
        } else {
            System.err.println("保存论文信息到ElasticSearch中的PAPER失败");
        }
        //保存论文爬取详细日志
        LogMapper logMapper = sqlSession.getMapper(LogMapper.class);
        isSucceed = logMapper.saveCrawlDetailLog(getUrl(),
                Log.getCrawlingNumbers().get(),
                Log.getUrlNumbers().get(),
                isSucceed,
                DateHelper.getCrawlTime());
        if (isSucceed) {
            LOGGER.info("爬取过程具体日志保存成功");
        } else {
            System.err.print("爬取过程具体日志保存失败");
        }
        //爬取完成，打印、保存日志和更新任务状态
        if (Log.getLastLink().equals(getUrl())) {
            LOGGER.info("爬取完成，本次爬取论文总量：" + Log.getUrlNumbers().get()
                    + " 成功数：" + Log.getCrawlingSucceedNumbers().get()
                    + " 失败数：" + Log.getCrawlingFailedNumber().get());

            long endTime = System.currentTimeMillis();//结束爬取的时间
            String averageTime = DateHelper.getSeconds((endTime - DateHelper.getCrawlStartTimeMills()) / Log.getUrlNumbers().get());
            //保存爬取完成的总体情况日志到数据库中
            isSucceed = logMapper.saveCrawlTotalLog(
                    DateHelper.getCrawlStartDate(),
                    Log.getCrawlingSucceedNumbers().get(),
                    Log.getCrawlingFailedNumber().get(),
                    Log.getUrlNumbers().get(),
                    averageTime);
            if (isSucceed) {
                LOGGER.info("爬取过程总体日志保存成功");
            } else {
                System.err.print("爬取过程总体日志保存失败");
            }
            Helper.isFirstUrl = true; //下次任务开始时有第一条论文url
            Helper.isCrawlFinished = true; //爬取任务结束
            Log.getCrawlingSucceedNumbers().set(0);
            Log.getCrawlingFailedNumber().set(0);
            Log.getCrawlingNumbers().set(0);
            Helper.firstInsertCrawlDetailLog = true;
            Log.getUrlNumbers().set(0); //重置爬取论文总量
            SqlHelper.closeSqlSession();
        }
        return isSucceed;
    }

    // 将原来的论文详细页面url进行字符串处理，转化为metric相关指标页面url
    public String getMetricsUrl() {
        int index1 = getUrl().indexOf("/full");
        String subString = getUrl().substring(0, index1);
        String subString1 = getUrl().substring(index1);
        int index2 = subString1.indexOf(".html");
        String subString2 = subString1.substring(5, index2);
        return subString + subString2 + "/metrics";
    }
}