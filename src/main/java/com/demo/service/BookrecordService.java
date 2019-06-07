package com.demo.service;

import com.demo.po.Bookrecord;
import com.demo.repository.BookrecordRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by wanyu on 2019/4/24.
 */
@Service
@Transactional
public class BookrecordService {
    @Autowired
    private BookrecordRepository bookrecordRepository;
    //查看所有预定记录
    public List<Bookrecord> findAll(){
        return bookrecordRepository.findAll();
    }
    //查看自己预定的会议
    public List<String> querySelfBook(Integer uid){
        return bookrecordRepository.querySelfBook(uid);
    }
    public List<String> queryMyMeeting(Integer uid){
        return bookrecordRepository.queryMyMeeting(uid);
    }
    //添加到会议记录里
    public Bookrecord save(Bookrecord bookrecord){
        return bookrecordRepository.save(bookrecord);
    }
    //删除会议记录
    public int deleteBook(Integer bid){
        return bookrecordRepository.deleteBook(bid);
    }
    //查询会议id
    public Integer queryMid(Integer bid){
        return bookrecordRepository.queryMid(bid);
    }
}
