package com.demo.control;

import com.demo.po.Administrator;
import com.demo.po.Approve;
import com.demo.service.ApproveService;
import com.demo.service.UserService;
import org.apache.catalina.servlet4preview.http.HttpServletRequest;
import org.apache.commons.lang3.StringUtils;
import org.omg.CORBA.Request;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.repository.query.Param;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by wanyu on 2019/4/17.
 */
@Controller
@RequestMapping(value = "/approve")
public class ApproveController {
    @Autowired
    ApproveService approveService;
    @Autowired
    UserService userService;
    @RequestMapping(value = "/queryAll",method = RequestMethod.GET)
    public ResponseEntity<List<Approve>> queryAll(){
        List<Approve> userList = approveService.findAll();
        return new ResponseEntity<List<Approve>>(userList,HttpStatus.OK);
    }
    @RequestMapping(value = "/agree/{apid}",method = RequestMethod.POST)
    public ResponseEntity agree(@PathVariable("apid") Integer apid){
        try {
            approveService.agree(apid);
            String username = approveService.getUsername(apid);
            userService.updateExist(username);
            return new ResponseEntity(HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity(HttpStatus.CONFLICT);
        }
    }
}
