package com.demo.service;

import com.demo.po.Mroom;
import com.demo.repository.MroomRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * Created by wanyu on 2019/4/23.
 */
@Service
@Transactional
public class MroomService {
    @Autowired
    MroomRepository mroomRepository;
    //添加会议室
    public Mroom save(Mroom mroom){
        return mroomRepository.save(mroom);
    }
    //删除会议室
    public int deleteRoom(Integer mrid){
        return mroomRepository.deleteRoom(mrid);
    }
    //显示所有会议室
    public List<Mroom> findAll(){
        return mroomRepository.findAll();
    }

    //显示可用会议室
    public Mroom queryMroomByMrid(Integer mrid){
        return mroomRepository.queryMroomByMrid(mrid);
    }
}
