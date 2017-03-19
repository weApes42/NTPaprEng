package com.weapes.ntpaprseng.crawler.util;

import com.weapes.ntpaprseng.crawler.follow.Followable;
import org.junit.Assert;
import org.junit.Ignore;
import org.junit.Test;

import java.util.List;

public class HelperTest {
    @Test
    @Ignore
    public void load() throws Exception {
        final List<? extends Followable> load = Helper.loadSeeds();
        Assert.assertTrue(load.size() != 0);
    }

}