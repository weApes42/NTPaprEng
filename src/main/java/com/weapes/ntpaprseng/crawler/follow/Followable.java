package com.weapes.ntpaprseng.crawler.follow;

import com.weapes.ntpaprseng.crawler.extract.Extractable;
import com.weapes.ntpaprseng.crawler.extract.ExtractedObject;

import java.io.IOException;

public interface Followable<E extends Extractable> extends ExtractedObject {
    E follow() throws IOException;
}
