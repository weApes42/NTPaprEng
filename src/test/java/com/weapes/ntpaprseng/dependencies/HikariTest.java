package com.weapes.ntpaprseng.dependencies;

import com.weapes.ntpaprseng.crawler.store.DataSource;
import com.zaxxer.hikari.HikariDataSource;
import org.junit.Assert;
import org.junit.Ignore;
import org.junit.Test;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class HikariTest {

    @Test
    @Ignore
    public void testAccessibility() throws SQLException {

        final HikariDataSource mysqlDataSource =
                DataSource.getMysqlDataSource();

        final Connection connection =
                mysqlDataSource.getConnection();

        final PreparedStatement preparedStatement =
                connection.prepareStatement("SHOW TABLES;");

        final ResultSet resultSet =
                preparedStatement.executeQuery();

        Assert.assertNotNull(resultSet);
    }
}
