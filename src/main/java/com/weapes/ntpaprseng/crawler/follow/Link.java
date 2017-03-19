package com.weapes.ntpaprseng.crawler.follow;

public abstract class Link
        implements Followable {

    private final String url;

    Link(final String url) {
        this.url = url;
    }

    public String getUrl() {
        return url;
    }

}
