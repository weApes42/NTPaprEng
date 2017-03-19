package com.weapes.ntpaprseng.crawler.store;

import com.weapes.ntpaprseng.crawler.extract.ExtractedObject;

public interface Storable extends ExtractedObject {
    boolean store();
}
