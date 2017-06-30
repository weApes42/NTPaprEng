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
      Helper.loadMetricsLinks().stream().forEach(
              paperMetricsLink -> System.out.println(paperMetricsLink.getUrl())
      );
    }

}