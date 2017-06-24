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
public class SqlHelper {
    private static  SqlSessionFactory sqlSessionFactory;
    private static ThreadLocal<SqlSession> sqlSessionThreadLocal = new ThreadLocal<>();
    private static final String MYBATIS_CONFIG_PATH="resources/conf/mybatis-config.xml";
    static {
        try (InputStream inputStream = Resources.getResourceAsStream(MYBATIS_CONFIG_PATH)) {
            assert inputStream != null;
            sqlSessionFactory = new SqlSessionFactoryBuilder()
                    .build(inputStream);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    public static SqlSession openSqlSession(){
        //从当前线程获取
        SqlSession sqlSession =sqlSessionThreadLocal.get();
        if(sqlSession == null){
            sqlSession = sqlSessionFactory.openSession(true);
            //将sqlSession与当前线程绑定
            sqlSessionThreadLocal.set(sqlSession);
        }
        return sqlSession;
    }
    public static void closeSqlSession(){
        //从当前线程获取
        SqlSession sqlSession =sqlSessionThreadLocal.get();
        if(sqlSession != null){
            sqlSession.close();
            sqlSessionThreadLocal.remove();
        }
    }
}
