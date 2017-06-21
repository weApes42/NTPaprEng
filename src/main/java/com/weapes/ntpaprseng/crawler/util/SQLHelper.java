package com.weapes.ntpaprseng.crawler.util;

import com.weapes.ntpaprseng.crawler.mapper.LogMapper;
import com.weapes.ntpaprseng.crawler.mapper.PaperMapper;
import com.weapes.ntpaprseng.crawler.store.MetricsPaper;
import com.weapes.ntpaprseng.crawler.store.Paper;
import org.apache.ibatis.io.Resources;
import org.apache.ibatis.mapping.Environment;
import org.apache.ibatis.session.Configuration;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;


import java.io.IOException;
import java.io.InputStream;

/**
 * Created by 不一样的天空 on 2017/6/20.
 */
public class SQLHelper {
    private static final SqlSessionFactory sqlSessionFactory=initSqlSessionFactory();
    //private static final SqlSession sqlSession=initSqlSession();
    private static final String MYBATIS_CONFIG_PATH="resources/conf/mybatis-config.xml";
    public static SqlSessionFactory initSqlSessionFactory() {
        try (InputStream inputStream = Resources.getResourceAsStream(MYBATIS_CONFIG_PATH)) {
            assert inputStream != null;
            SqlSessionFactory sqlSessionFactory = new SqlSessionFactoryBuilder()
                    .build(inputStream);
            return sqlSessionFactory;
        } catch (IOException e) {
            e.printStackTrace();
            return null;
        }
    }
    public static SqlSession getSqlSession(){
        return sqlSessionFactory.openSession();
    }

}
