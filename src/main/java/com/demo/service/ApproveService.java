package com.demo.service;

import com.demo.po.Approve;
import com.demo.repository.ApproveRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

/**
 * Created by wanyu on 2019/4/17.
 */
@Service
@Transactional
public class ApproveService {
    @Autowired
    private ApproveRepository approveRepository;
    //注册时先添加进这个审批表中
    public Approve save(Approve approve){
        return approveRepository.save(approve);
    }
    //管理员修改标识 同意注册
    public int agree(Integer apid){
       return approveRepository.agree(apid);
    }
    public String getUsername(Integer apid){
        return approveRepository.getUsername(apid);
    }
    //查看所有的审批或未审批用户
    public List<Approve> findAll(){

        return approveRepository.findAll();
    }

}
