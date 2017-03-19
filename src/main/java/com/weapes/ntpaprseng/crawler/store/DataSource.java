package com.weapes.ntpaprseng.crawler.store;

import com.weapes.ntpaprseng.crawler.util.Helper;
import com.zaxxer.hikari.HikariConfig;
import com.zaxxer.hikari.HikariDataSource;

public final class DataSource {
    private static final HikariConfig mysql =
            new HikariConfig(Helper.getCfg().getString("hikari"));

    private static final HikariDataSource mysqlDataSource =
            new HikariDataSource(mysql);

    private DataSource() {
    }

    public static HikariDataSource getMysqlDataSource() {
        return mysqlDataSource;
    }
}
