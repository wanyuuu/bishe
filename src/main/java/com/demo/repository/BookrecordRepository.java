package com.demo.repository;

import com.demo.po.Bookrecord;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.awt.print.Book;
import java.util.List;

/**
 * Created by wanyu on 2019/4/23.
 */
@Repository
public interface BookrecordRepository extends CrudRepository<Bookrecord,Integer> {
    public Bookrecord save(Bookrecord bookrecord);

    @Query("select b.bid,m.mname,b.starttime,b.endtime,b.booktime,mr.mrname from " +
            "Bookrecord b,Meeting m,Mroom mr where m.uid =:uid and b.mid = m.mid and b.mrid = mr.mrid")
    public List<String> querySelfBook(@Param("uid") Integer uid);

    @Query("select b.bid,m.mname,b.starttime,b.endtime,mr.mrname from Bookrecord b,Mroom mr,Meeting m where b.uid =:uid and b.mrid = mr.mrid and m.mid = b.mid")
    public List<String> queryMyMeeting(@Param("uid") Integer uid);

    @Modifying
    @Query("delete from Bookrecord where bid =:bid")
    public int deleteBook(@Param("bid") Integer bid);

    @Query("select mid from Bookrecord where bid =:bid")
    public Integer queryMid(@Param("bid") Integer bid);

    @Query("select b from Bookrecord b")
    public List<Bookrecord> findAll();
}
