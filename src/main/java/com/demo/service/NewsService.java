package com.demo.service;

import com.demo.po.News;
import com.demo.repository.NewsRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * Created by wanyu on 2019/5/18.
 */
@Service
@Transactional
public class NewsService {
    @Autowired
    private NewsRepository newsRepository;
    //发布新闻
    public News save(News news){
        return newsRepository.save(news);
    }
    //展示新闻
    public List<News> findAll(){
        return newsRepository.findAll();
    }
    public int deleteNews(Integer nid){
        return newsRepository.deleteMessage(nid);
    }
}
