package com.weapes.ntpaprseng.crawler.task;

import com.weapes.ntpaprseng.crawler.mapper.PaperMapper;
import com.weapes.ntpaprseng.crawler.mapper.UtilMapper;
import com.weapes.ntpaprseng.crawler.store.Paper;
import com.weapes.ntpaprseng.crawler.util.Helper;
import com.weapes.ntpaprseng.crawler.util.SqlHelper;
import org.apache.ibatis.session.SqlSession;

import java.util.*;

/**
 * Created by 不一样的天空 on 2017/7/1.
 */
public class UpdateTopPaperTask implements Runnable {
    @Override
    public void run() {
        SqlSession sqlSession = SqlHelper.openSqlSession();
        UtilMapper utilMapper = sqlSession.getMapper(UtilMapper.class);
        PaperMapper paperMapper = sqlSession.getMapper(PaperMapper.class);
        paperMapper.clearTopPapers();
        List<String> paperUrls = utilMapper.listPaperLink();
        HashMap<String,Double> hashMap = new HashMap<>();
        paperUrls.stream().forEach(paperUrl ->{
            String paperMetricsUrl = Helper.getPaperMetricsUrl(paperUrl);
            List<Double> indices = utilMapper.listFinalIndexByUrl(paperMetricsUrl);
            if (indices.size() == 2){
                Double difference = indices.get(0) - indices.get(1);
                hashMap.put(paperUrl,Math.abs(difference));
            }else {
                hashMap.put(paperUrl,0.0);
            }
        });
        List<Map.Entry<String,Double>> list = new ArrayList<>(hashMap.entrySet());
        Collections.sort(list,(entity1, entity2) ->
                entity2.getValue().compareTo(entity1.getValue())
        );
        int topNumber = 100;
        topNumber = topNumber < list.size() ? topNumber :list.size();
        for (int i = 0; i < topNumber; i++) {
            String url = list.get(i).getKey();
            String title = paperMapper.getPaperTitleByUrl(url);
          //  System.out.println(url+"  "+title);
            paperMapper.saveTopPaperInfo(url,title);
            sqlSession.commit();
        }
        SqlHelper.closeSqlSession();
    }


}
