package com.weapes.ntpaprseng.crawler.util;

import com.weapes.ntpaprseng.crawler.mapper.UtilMapper;
import com.weapes.ntpaprseng.crawler.store.MetricsPaper;
import org.apache.ibatis.session.SqlSession;

import java.util.HashMap;

/**
 * Created by 不一样的天空 on 2017/6/25.
 */
public class IndexHelper {
    private  int pageViews;
    private  int webOfScience;
    private  int crossRef;
    private  int scopus;
    private  int newsOutlets;
    private  int reddit;
    private  int blog;
    private  int tweets;
    private  int facebook;
    private  int google;
    private  int pinterest;
    private  int wikipedia;
    private  int mendeley;
    private  int citeUlink;
    private  int zotero;
    private  int f1000;
    private  int video;
    private  int linkedin;
    private  int q_a;
    private IndexHelper(){

    }
    public static IndexHelper build(){
        return  new IndexHelper();
    }
    public IndexHelper addPageViews(int pageViews) {
        this.pageViews = pageViews;
        return this;
    }

    public IndexHelper addWebOfScience(int webOfScience) {
        this.webOfScience = webOfScience;
        return this;
    }

    public IndexHelper addCrossRef(int crossRef) {
        this.crossRef = crossRef;
        return this;
    }

    public IndexHelper addNewsOutlets(int newsOutlets) {
        this.newsOutlets = newsOutlets;
        return this;
    }

    public IndexHelper addScopus(int scopus) {
        this.scopus = scopus;
        return this;
    }

    public IndexHelper addReddit(int reddit) {
        this.reddit = reddit;
        return this;
    }

    public IndexHelper addTweets(int tweets) {
        this.tweets = tweets;
        return this;
    }

    public IndexHelper addBlog(int blog) {
        this.blog = blog;
        return this;
    }

    public IndexHelper addFacebook(int facebook) {
        this.facebook = facebook;
        return this;
    }

    public IndexHelper addGoogle(int google) {
        this.google = google;
        return this;
    }

    public IndexHelper addPinterest(int pinterest) {
        this.pinterest = pinterest;
        return this;
    }

    public IndexHelper addWikipedia(int wikipedia) {
        this.wikipedia = wikipedia;
        return this;
    }

    public IndexHelper addMendeley(int mendeley) {
        this.mendeley = mendeley;
        return this;
    }

    public IndexHelper addCiteUlink(int citeUlink) {
        this.citeUlink = citeUlink;
        return this;
    }

    public IndexHelper addZotero(int zotero) {
        this.zotero = zotero;
        return this;
    }

    public IndexHelper addF1000(int f1000) {
        this.f1000 = f1000;
        return this;
    }

    public IndexHelper addVideo(int video) {
        this.video = video;
        return this;
    }

    public IndexHelper addLinkedin(int linkedin) {
        this.linkedin = linkedin;
        return this;
    }

    public IndexHelper addQ_a(int q_a) {
        this.q_a = q_a;
        return this;
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

    public  Double getFinalIndex() {
        final SqlSession sqlSession = SqlHelper.openThreadSqlSession();
        UtilMapper mapper = sqlSession.getMapper(UtilMapper.class);
        HashMap<String, Double> weights = mapper.listWeight();
        double finalIndex = weights.get("pageViews") * getPageViews();
        finalIndex += weights.get("webOfScience") * getWebOfScience();
        finalIndex += weights.get("crossRef") * getCrossRef();
        finalIndex += weights.get("scopus") * getScopus();
        finalIndex += weights.get("newsOutlets") * getNewsOutlets();
        finalIndex += weights.get("reddit") * getReddit();
        finalIndex += weights.get("blog") * getBlog();
        finalIndex += weights.get("tweets") * getTweets();
        finalIndex += weights.get("facebook") * getFacebook();
        finalIndex += weights.get("google") * getGoogle();
        finalIndex += weights.get("pinterest") * getPinterest();
        finalIndex += weights.get("wikipedia") * getWikipedia();
        finalIndex += weights.get("mendeley") * getMendeley();
        finalIndex += weights.get("citeUlink") * getCiteUlink();
        finalIndex += weights.get("zotero") * getZotero();
        finalIndex += weights.get("f1000") * getF1000();
        finalIndex += weights.get("video") * getVideo();
        finalIndex += weights.get("linkedin") * getLinkedin();
        finalIndex += weights.get("q_a") * getQ_a();
        SqlHelper.closeSqlSession();
        return finalIndex;
    }

}
