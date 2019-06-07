package com.demo.service;

import com.demo.po.Administrator;
import com.demo.repository.AdministratorRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * Created by wanyu on 2019/4/18.
 */
@Service
@Transactional
public class AdministratorService {
    @Autowired
    private AdministratorRepository administratorRepository;

    //管理员登录
    public String administratorLogin(String username){
        return administratorRepository.administratorLogin(username);
    }
}
