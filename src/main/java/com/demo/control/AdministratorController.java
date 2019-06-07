package com.demo.control;

import com.demo.po.*;
import com.demo.service.*;
import com.sun.tools.internal.ws.processor.model.Request;
import com.sun.tools.internal.ws.processor.model.Response;
import org.apache.catalina.servlet4preview.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;
import org.springframework.web.multipart.MultipartResolver;
import org.springframework.web.multipart.commons.CommonsMultipartResolver;

import java.io.File;
import java.io.IOException;
import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.Collections;
import java.util.Date;
import java.util.List;
import java.util.UUID;

/**
 * Created by wanyu on 2019/4/18.
 */
@RestController
@RequestMapping(value = "/administrator")
public class AdministratorController {
    @Autowired
    AdministratorService administratorService;
    @Autowired
    MessageService messageService;
    @Autowired
    DepartmentService departmentService;
    @Autowired
    MroomService mroomService;
    @Autowired
    NewsService newsService;
    @Autowired
    UserService userService;
    @RequestMapping(value = "/login",method = RequestMethod.POST)
    public ResponseEntity login(@RequestBody Administrator administrator){
        String username = administrator.getUsername();
        String password = administrator.getPassword();
        if(administratorService.administratorLogin(username).equals(password)){
            return new ResponseEntity(HttpStatus.OK);
        }
        return new ResponseEntity(HttpStatus.CONFLICT);
    }
    @RequestMapping(value = "/publish",method = RequestMethod.POST)
    public ResponseEntity publishMessage(@RequestBody Message message){
        if(message != null){
            messageService.save(message);
        }
        return new ResponseEntity(HttpStatus.OK);
    }
    @RequestMapping(value = "/queryMessage",method = RequestMethod.GET)
    public ResponseEntity<List<Message>> queryMessage(){
        List<Message> list = messageService.findAll();
        return new ResponseEntity<List<Message>>(list,HttpStatus.OK);
    }
    @RequestMapping(value = "/deleteMessage",method = RequestMethod.POST)
    public ResponseEntity deleteMessage(@RequestParam Integer mmid){
        if(mmid != null && mmid != 0){
            messageService.deleteMessage(mmid);
        }
        return new ResponseEntity(HttpStatus.OK);
    }
    @RequestMapping(value = "/queryDepartment",method = RequestMethod.GET)
    public ResponseEntity<List<Department>> queryDepartment(){
        List<Department> list = departmentService.findAll();
        return new ResponseEntity<List<Department>>(list,HttpStatus.OK);
    }
    @RequestMapping(value = "/deleteDepartment/{did}",method = RequestMethod.POST)
    public ResponseEntity deleteDepartemnt(@PathVariable Integer did){
        try {
            departmentService.deleteDepartment(did);
            return new ResponseEntity(HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
    @RequestMapping(value = "/updateDepartment",method = RequestMethod.POST)
    public ResponseEntity updateDepartment(@RequestBody Department department){
        try {
            String dname = department.getDname();
            Integer dnumber = department.getDnumber();
            String dinformation = department.getDinformation();
            Integer did = department.getDid();
            departmentService.updateDepartment(dname,dnumber,dinformation,did);
            return new ResponseEntity(HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity(HttpStatus.CONFLICT);
        }
    }
    @RequestMapping(value = "/addDepartment",method = RequestMethod.POST)
    public ResponseEntity addDepartment(@RequestBody Department department){
        try {
            departmentService.save(department);
            return new ResponseEntity(HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity(HttpStatus.CONFLICT);
    }
    }
    @RequestMapping(value = "/addRoom",method = RequestMethod.POST)
    public ResponseEntity addRoom(@RequestBody Mroom mroom){
        try {
            mroomService.save(mroom);
            return new ResponseEntity(HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity(HttpStatus.CONFLICT);
    }
    }
    @RequestMapping(value = "/sss",method = RequestMethod.POST)
    public ResponseEntity addRoom(HttpServletRequest request){
        String mrname = request.getParameter("mrname");
        String maddress = request.getParameter("maddress");
        int mcapacity = Integer.parseInt(request.getParameter("mcapacity"));
        CommonsMultipartResolver commonsMultipartResolver = new CommonsMultipartResolver(request
                .getSession().getServletContext());
//        MultipartHttpServletRequest multipartRequest = commonsMultipartResolver.resolveMultipart(request);
//        MultipartHttpServletRequest multipartHttpServletRequest = (MultipartHttpServletRequest)request;
        //文件名字中可以有中文
        commonsMultipartResolver.setDefaultEncoding("utf-8");
        //延时加载 防止上传大文件而提前解析
        commonsMultipartResolver.setResolveLazily(true);
        //上传文件的最大总和
        commonsMultipartResolver.setMaxUploadSize(1024*1024*5);
        //每个最大上传文件
        commonsMultipartResolver.setMaxUploadSizePerFile(1024*1024);
        //设置缓存
        commonsMultipartResolver.setMaxInMemorySize(1024*1024*4);
        MultipartHttpServletRequest mrequest
                =commonsMultipartResolver.resolveMultipart(request);
        String sqlPath = "";
        String fileName = "";
        String filePath = "";
        MultipartFile multipartFile = mrequest.getFile("pic");
        if(!multipartFile.isEmpty()){
            //生成uuid作为文件名称
            String uuid = UUID.randomUUID().toString().replace("-","");
            //获得文件类型
            String contentType = multipartFile.getContentType();
            //获得文件后缀名
            String suffixName = contentType.substring(contentType.indexOf("/") + 1);
            //得到文件名
            fileName = uuid + "." + suffixName;
            filePath = "upload" + File.separator + fileName;
            String pic = fileName;
            Mroom mroom = new Mroom();
            mroom.setMrname(mrname);
            mroom.setMaddress(maddress);
            mroom.setMcapacity(mcapacity);
            mroom.setPic(pic);
            mroomService.save(mroom);
            File file = new File(filePath);
            try {
                multipartFile.transferTo(file);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return new ResponseEntity(HttpStatus.OK);
    }
    @RequestMapping(value = "/deleteRoom/{mrid}",method = RequestMethod.POST)
    public ResponseEntity deleteRoom(@PathVariable Integer mrid){
        try {
            mroomService.deleteRoom(mrid);
            return new ResponseEntity(HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity(HttpStatus.CONFLICT);
        }
    }
    @RequestMapping(value = "/queryRoom",method = RequestMethod.GET)
    public ResponseEntity<List<Mroom>> queryRoom(){
        try {
            List<Mroom> list = mroomService.findAll();
            return new ResponseEntity<List<Mroom>>(list,HttpStatus.OK);
        } catch (Exception e) {
           return new ResponseEntity(HttpStatus.CONFLICT);
        }
    }
    @RequestMapping(value = "/publishNews",method = RequestMethod.POST)
    public ResponseEntity publishNews(@RequestBody News news){
        if(news != null){
            SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm");
            Timestamp posttime = new Timestamp(new Date().getTime());
            news.setPosttime(posttime);
            newsService.save(news);
        }
        return new ResponseEntity(HttpStatus.OK);
    }
    @RequestMapping(value = "/queryNews",method = RequestMethod.GET)
    public ResponseEntity<List<News>> queryNews(){
        List<News> list = newsService.findAll();
        Collections.reverse(list);
        return new ResponseEntity<List<News>>(list,HttpStatus.OK);
    }
    @RequestMapping(value = "/deleteNews",method = RequestMethod.POST)
    public ResponseEntity deleteNews(@RequestParam Integer nid){
        if(nid != null && nid != 0){
            newsService.deleteNews(nid);
        }
        return new ResponseEntity(HttpStatus.OK);
    }
    @RequestMapping(value = "/queryAllUser",method = RequestMethod.GET)
    public ResponseEntity<List<User>> queryAllUser(){
        List<User> list = userService.findAll();
        if(list.size() > 0){
            return new ResponseEntity<List<User>>(list,HttpStatus.OK);
        }
        return new ResponseEntity(HttpStatus.CONFLICT);
    }
    @RequestMapping(value = "/deleteUser/{uid}",method = RequestMethod.POST)
    public ResponseEntity deleteUser(@PathVariable Integer uid){
        if(uid != null){
            userService.deleteUser(uid);
            return new ResponseEntity(HttpStatus.OK);
        }
        return new ResponseEntity(HttpStatus.CONFLICT);
    }

}
