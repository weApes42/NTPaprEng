package com.weapes.ntpaprseng.crawler.follow;

import com.weapes.ntpaprseng.crawler.extract.AdvSearchedWebPage;
import com.weapes.ntpaprseng.crawler.util.Helper;

import java.io.IOException;

public class AdvSearchLink extends Link {
    public AdvSearchLink(final String urlParsed) {
        super(urlParsed);
    }

    @Override
    public AdvSearchedWebPage follow() throws IOException {
        final String webPage = Helper.fetchWebPage(getUrl());
        return new AdvSearchedWebPage(webPage, getUrl());
    }
}
