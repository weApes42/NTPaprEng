package com.weapes.ntpaprseng.crawler.crawler;

import com.weapes.ntpaprseng.crawler.store.Storable;

class StorableHandler<S extends Storable> implements Runnable {

    private final S storable;

    StorableHandler(final S storable) {
        this.storable = storable;
    }

    @Override
    public void run() {
        storable.store();
    }
}
