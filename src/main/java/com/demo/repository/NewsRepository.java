package com.demo.repository;

import com.demo.po.News;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Created by wanyu on 2019/5/17.
 */
@Repository
public interface NewsRepository extends CrudRepository<News,Integer> {
    public News save(News news);

    @Query("select n from News n")
    public List<News> findAll();

    @Modifying
    @Query("delete from News where nid =:nid")
    public int deleteMessage(@Param("nid") Integer nid);
}
