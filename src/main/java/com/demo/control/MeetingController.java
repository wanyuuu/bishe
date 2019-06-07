package com.demo.control;

import com.demo.po.Bookrecord;
import com.demo.po.Meeting;
import com.demo.po.Mroom;
import com.demo.repository.BookrecordRepository;
import com.demo.service.BookrecordService;
import com.demo.service.MeetingService;
import com.demo.service.MroomService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;


import java.sql.Timestamp;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;
import java.util.List;
/**
 * Created by wanyu on 2019/5/22.
 */
@Controller
@RequestMapping(value = "/meeting")
public class MeetingController {
    @Autowired
    BookrecordService bookrecordService;
    @Autowired
    MeetingService meetingService;
    @Autowired
    MroomService mroomService;
    @RequestMapping(value = "/querySelfMeeting/{uid}",method = RequestMethod.POST)
    public ResponseEntity<List> querySelfMeeting(@PathVariable Integer uid){
        if(uid != null){
            List list = bookrecordService.querySelfBook(uid);
            Collections.reverse(list);
            return new ResponseEntity<List>(list,HttpStatus.OK);
        }
        return new ResponseEntity<List>(HttpStatus.CONFLICT);
    }
    @RequestMapping(value = "/queryMyMeeting/{uid}",method = RequestMethod.POST)
    public ResponseEntity<List> queryMyMeeting(@PathVariable Integer uid){
        if(uid != null){
            List list = bookrecordService.queryMyMeeting(uid);
            Collections.reverse(list);
            return new ResponseEntity<List>(list,HttpStatus.OK);
        }
        return new ResponseEntity<List>(HttpStatus.CONFLICT);
    }
    @RequestMapping(value = "/queryAdMeeting",method = RequestMethod.GET)
    public ResponseEntity<List> queryAdMeeting(){
        List mlist = meetingService.queryAdMeeting();
        Collections.reverse(mlist);
        return new ResponseEntity<List>(mlist,HttpStatus.OK);
    }
    @RequestMapping(value = "/deleteMeeting/{bid}",method = RequestMethod.POST)
    public ResponseEntity deleteMeeting(@PathVariable Integer bid){
        try {
            bookrecordService.deleteBook(bid);
            Integer mid = bookrecordService.queryMid(bid);
            meetingService.deleteMeeting(mid);
            return new ResponseEntity(HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity(HttpStatus.CONFLICT);
        }
    }
    @RequestMapping(value = "/createMeeting/{starttime}/{endtime}",method = RequestMethod.POST)
    public ResponseEntity createMeeting(@RequestBody Meeting meeting,@PathVariable String starttime,@PathVariable String endtime){
        String nstarttime = starttime.replace("T"," ");
        String nendtime = endtime.replace("T"," ");
        try {
            SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm");
            Date newEndtime = null;
            Date newStarttime = null;
            try {
                newStarttime = sdf.parse(nstarttime);
                newEndtime = sdf.parse(nendtime);
            } catch (ParseException e) {
                e.printStackTrace();
            }
            Timestamp sqlDate1 = new Timestamp(newStarttime.getTime());
            Timestamp sqlDate2 = new Timestamp(newEndtime.getTime());
            meeting.setStarttime(sqlDate1);
            meeting.setEndtime(sqlDate2);
            //存入meeting表中
            if(meeting.getUid() == 0){
                meeting.setUid(10000);
            }
            meetingService.save(meeting);
            //记录表
            Bookrecord bookrecord = new Bookrecord();
            bookrecord.setMid(meeting.getMid());
            bookrecord.setStarttime(sqlDate1);
            bookrecord.setMrid(meeting.getMrid());
            bookrecord.setEndtime(sqlDate2);
            bookrecord.setBooktime(new Date());
            bookrecord.setUid(meeting.getUid());
            bookrecord.setDid(meeting.getDid());
            //保存
            bookrecordService.save(bookrecord);
            return new ResponseEntity(HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity(HttpStatus.CONFLICT);
        }
    }
    @RequestMapping(value = "/joinAdMeeting",method = RequestMethod.POST)
    public ResponseEntity joinMeeting(@RequestBody Bookrecord bookrecord){
        try {
            bookrecordService.save(bookrecord);
            return new ResponseEntity(HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity(HttpStatus.CONFLICT);
        }
    }
    @RequestMapping(value = "/queryUsedroom/{starttime}/{endtime}",method = RequestMethod.POST)
    public ResponseEntity<List<Mroom>> queryUsedroom(@PathVariable String starttime,@PathVariable String endtime){
        String nstarttime = starttime.replace("T"," ");
        String nendtime = endtime.replace("T"," ");
        List<Mroom> mrooms = new ArrayList<Mroom>();
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm");
        Date newEndtime = null;
        Date newStarttime = null;
        try {
            newStarttime = sdf.parse(nstarttime);
            newEndtime = sdf.parse(nendtime);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        Timestamp sqlDate1 = new Timestamp(newStarttime.getTime());
        Timestamp sqlDate2 = new Timestamp(newEndtime.getTime());
            //查出可用的会议室mrid
        List<Integer> mridlist = meetingService.queryMridByTime(sqlDate1,sqlDate2);
        List<Integer> mridlist2 = meetingService.queryNotCommonMrid();
            //合并到一起
        mridlist2.addAll(mridlist);
            //排序
        Collections.sort(mridlist2);
        for (Integer i:mridlist2) {
            mrooms.add(mroomService.queryMroomByMrid(i));
        }
        return new ResponseEntity<List<Mroom>>(mrooms,HttpStatus.OK);
    }
}
