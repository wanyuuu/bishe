package com.demo.repository;

import com.demo.po.Message;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Created by wanyu on 2019/4/18.
 */
@Repository
public interface MessageRepository extends CrudRepository<Message,Integer>{
    public Message save(Message message);

    @Query("select m from Message m")
    public List<Message> findAll();

    @Modifying
    @Query("delete from Message where mmid =:mmid")
    public int deleteMessage(@Param("mmid") Integer mmid);
}
