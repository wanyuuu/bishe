package com.demo.repository;

import com.demo.po.Meeting;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.sql.Date;
import java.sql.Timestamp;
import java.util.List;

/**
 * Created by wanyu on 2019/4/23.
 */
@Repository
public interface MeetingRepository extends CrudRepository<Meeting,Integer> {
    public Meeting save(Meeting meeting);

    @Modifying
    @Query("delete from Meeting where mid =:mid")
    public int deleteMeeting(@Param("mid") Integer mid);

    @Query("select m.mid,m.mname,mr.mrname,m.starttime,m.endtime,m.information,m.mrid from Meeting m,Mroom mr where m.mrid = mr.mrid and m.uid =:uid")
    public List<Meeting> queryAdMeeting(@Param("uid") Integer uid);

    @Query("select m.mrid from Meeting m where m.endtime < ?1 or m.starttime > ?2")
    public List<Integer> queryMridByTime(Timestamp starttime,Timestamp endtime);

    @Query("select mr.mrid from Mroom mr where mr.mrid not in(select m.mrid from Meeting m)")
    public List<Integer> queryNotCommonMrid();

}
