package com.weapes.ntpaprseng.crawler.task;

import org.junit.Ignore;
import org.junit.Test;

/**
 * Created by 不一样的天空 on 2017/7/1.
 */
public class UpdateTopTaskTest {
    public static void main(String[]args){
        new Thread(new UpdateTopPaperTask()).start();
    }
}
