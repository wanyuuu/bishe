package com.demo.service;

import com.demo.po.Message;
import com.demo.repository.MessageRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * Created by wanyu on 2019/4/18.
 */
@Service
@Transactional
public class MessageService {
    @Autowired
    private MessageRepository messageRepository;
    //管理员发公告
    public Message save(Message message){
        return messageRepository.save(message);
    }
    //所有公告
    public List<Message> findAll(){
        return messageRepository.findAll();
    }
    //删除公告
    public int deleteMessage(Integer mmid){
        return messageRepository.deleteMessage(mmid);
    }
}
