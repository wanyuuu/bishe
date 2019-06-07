package com.demo.control;

import com.demo.config.Md5Config;
import com.demo.po.Approve;
import com.demo.po.Department;
import com.demo.po.Meeting;
import com.demo.po.User;
import com.demo.service.ApproveService;
import com.demo.service.DepartmentService;
import com.demo.service.MeetingService;
import com.demo.service.UserService;
import com.sun.deploy.net.HttpResponse;
import com.sun.tools.internal.ws.processor.model.Request;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletResponse;
import javax.websocket.server.PathParam;
import java.util.List;

/**
 * Created by wanyu on 2019/4/15.
 */
@Controller
@RequestMapping(value = "/user")
public class UserController {

    @Autowired
    UserService userService;
    @Autowired
    ApproveService approveService;
    @Autowired
    MeetingService meetingService;
    @Autowired
    DepartmentService departmentService;
    @RequestMapping(value = "/register",method = RequestMethod.POST)
    public ResponseEntity register(@RequestBody User user){
        User userInfo = null;
        Approve approve = new Approve();
        if(user != null){
            String password = user.getPassword();
            //加密操作
            String newPassword = Md5Config.Md5(password);
            //将加密后的密码存入
            user.setPassword(newPassword);
            //没有重名则加入
            if(userService.isReg(user.getUsername()) == null){
                userInfo = userService.save(user);
                approve.setUsername(user.getUsername());
                approve.setTelephone(user.getTelephone());
                approve.setEmail(user.getEmail());
                approveService.save(approve);
            }
        }
        return userInfo != null ? (new ResponseEntity(HttpStatus.OK)): (new ResponseEntity(HttpStatus.CONFLICT));
    }
    @RequestMapping(value = "/login",method = RequestMethod.POST)
    public ResponseEntity<User> login(@RequestBody User user){
        //审批还未通过
        try {
            if(userService.isExist(user.getUsername()) == 0){
                return new ResponseEntity(HttpStatus.NOT_IMPLEMENTED);
            }
        } catch (Exception e) {
            return new ResponseEntity(HttpStatus.INTERNAL_SERVER_ERROR);
        }
        //取出密码
        String opassword = userService.login(user.getUsername());
        if(Md5Config.validateMd5(opassword,user.getPassword())){
            User userInfo = userService.querySelfUser(user.getUsername());
            return new ResponseEntity(userInfo,HttpStatus.OK);
        }else {
            return new ResponseEntity(HttpStatus.CONFLICT);
        }
    }
    @RequestMapping(value = "/queryDepartment",method = RequestMethod.POST)
    public ResponseEntity<List<Department>> queryDepartment(){
        List<Department> list = departmentService.findAll();
        return new ResponseEntity<List<Department>>(list,HttpStatus.OK);
    }
    //查用户信息进行更改
    @RequestMapping(value = "/queryUserInfo/{uid}",method = RequestMethod.GET)
    public ResponseEntity<String> queryUserInfo(@PathVariable("uid") Integer uid){
        if(uid != null){
            String user = userService.queryUserInfo(uid);
            return new ResponseEntity<String>(user,HttpStatus.OK);
        }
        return new ResponseEntity<String>(HttpStatus.CONFLICT);
    }
    @RequestMapping(value = "/updateUserInfo",method = RequestMethod.POST)
    public ResponseEntity<User> updateUserInfo(@RequestBody User user){
        String messages = "";
        if(user != null){
            if(userService.isReg(user.getUsername()) == null){
                userService.updateUserInfo(user.getUid(),user.getUsername(),user.getTelephone(),user.getEmail(),user.getDid());
                return new ResponseEntity(user,HttpStatus.OK);
            }
            messages = "该用户名已存在";
            return new ResponseEntity(messages,HttpStatus.CONFLICT);
        }
        return new ResponseEntity(HttpStatus.CONFLICT);
    }
    @RequestMapping(value = "/updatePassword/{uid}/{oldpassword}/{newpassword}",method = RequestMethod.POST)
    public ResponseEntity updatePassword(@PathVariable Integer uid,@PathVariable String oldpassword,@PathVariable String newpassword){
        if(uid != null){

            if(!Md5Config.validateMd5(userService.queryPassword(uid),oldpassword)){
                return new ResponseEntity(HttpStatus.CONFLICT);
            }else{
                userService.updatePassword(Md5Config.Md5(newpassword),uid);
                return new ResponseEntity(HttpStatus.OK);
            }
        }
        return new ResponseEntity(HttpStatus.OK);
    }
}
