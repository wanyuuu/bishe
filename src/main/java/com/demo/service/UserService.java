package com.demo.service;

import com.demo.po.User;
import com.demo.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

/**
 * Created by wanyu on 2019/4/15.
 */
@Service
@Transactional
public class UserService {
    @Autowired
    UserRepository userRepository;
    //新用户注册
    public User save(User user){
        return userRepository.save(user);
    }
    //删除
    public int deleteUser(Integer uid){
        return userRepository.deleteUser(uid);
    }
    //查询用户密码，登陆验证
    public String login(String username) {
        return userRepository.login(username);
    }
    //查自己所有信息 登录时候用 返回user
    public User querySelfUser(String username){
        return userRepository.querySelfUser(username);
    }
    //所有用户
    public List<User> findAll(){
        return userRepository.findAll();
    }
    //查询审批是否通过 通过方可登录
    public int isExist(String username){
        return userRepository.isExist(username);
    }
    //是否已经注册
    public String isReg(String username){
        return userRepository.isReg(username);
    }
    //查询用户信息好进行更改
    public String queryUserInfo(Integer uid){
        return userRepository.queryUserInfo(uid);
    }
    //修改用户信息
    public int updateUserInfo(Integer uid,String username,String telephone,String email,Integer did){
        return userRepository.updateUserInfo(uid,username,telephone,email,did);
    }
    //修改用户密码
    public int updatePassword(String password,Integer uid){
        return userRepository.updatePassword(password,uid);
    }
    //根据uid查密码
    public String queryPassword(Integer uid){
        return userRepository.queryPassword(uid);
    }
    //更改注册状态
    public int updateExist(String username){
        return userRepository.updateExist(username,1);
    }
}
