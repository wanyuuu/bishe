package com.demo.service;

import com.demo.po.Meeting;
import com.demo.repository.MeetingRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.sql.Date;
import java.sql.Timestamp;
import java.util.List;

/**
 * Created by wanyu on 2019/4/23.
 */
@Service
@Transactional
public class MeetingService {
    @Autowired
    MeetingRepository meetingRepository;
    //创建会议
    public Meeting save(Meeting meeting){
        return meetingRepository.save(meeting);
    }
    //删除会议
    public int deleteMeeting(Integer mid){
        return meetingRepository.deleteMeeting(mid);
    }

    //查差集
    public List<Integer> queryNotCommonMrid(){
        return meetingRepository.queryNotCommonMrid();
    }
    //通过时间查
    public List<Integer> queryMridByTime(Timestamp starttime, Timestamp endtime){
        return meetingRepository.queryMridByTime(starttime,endtime);
    }
    //管理员发布的会议
    public List<Meeting> queryAdMeeting(){
        return meetingRepository.queryAdMeeting(10000);
    }
}
