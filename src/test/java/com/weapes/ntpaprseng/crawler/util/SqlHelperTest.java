package com.weapes.ntpaprseng.crawler.util;

import com.weapes.ntpaprseng.crawler.mapper.UtilMapper;
import org.apache.ibatis.session.SqlSession;
import org.junit.Ignore;
import org.junit.Test;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;

public class SqlHelperTest {
    @Test
    @Ignore
    public void addColumns() throws ParseException {
        //http://www.nature.com/nature/journal/v542/n7640/full/nature21080.html
        SqlSession sqlSession= SqlHelper.getSqlSession();
        UtilMapper utilMapper=sqlSession.getMapper(UtilMapper.class);
        boolean flag = utilMapper.updateLastUrl("www.baidu.com");
        System.out.println(flag);
        String time="15 Oct 2017";
        DateFormat originalFormat = new SimpleDateFormat("dd MMM yyyy", Locale.ENGLISH);
        Date date = originalFormat.parse(time);
        System.out.println("date: "+date);
        Calendar calendar=Calendar.getInstance(Locale.ENGLISH);
        calendar.setTime(date);
        System.out.println("年 "+calendar.get(Calendar.YEAR)+"  "+date.getYear());
        System.out.println("月 "+calendar.get(Calendar.MONTH)+" "+date.getMonth());
        System.out.println("日 "+calendar.get(Calendar.DAY_OF_MONTH)+" "+date.getDay());
        DateFormat targetFormat = new SimpleDateFormat("yyyy年MM月dd日");
        String formattedDate = targetFormat.format(date);
        System.out.println(formattedDate);
        String target = "Thu Sep 28 20:29:30 JST 2000";
        DateFormat df = new SimpleDateFormat("EEE MMM dd kk:mm:ss z yyyy", Locale.ENGLISH);
        Date result =  df.parse(target);
        System.out.println(result);
    }
}
