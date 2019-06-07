package com.demo.repository;

import com.demo.po.Approve;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Created by wanyu on 2019/4/17.
 */
@Repository
public interface ApproveRepository extends CrudRepository<Approve,Integer> {
    public Approve save(Approve approve);

    @Modifying
    @Query("update Approve ap set ap.isapprove = 1 where ap.apid =:apid")
    public int agree(@Param("apid") Integer apid);

    @Query("select username from Approve where apid =:apid")
    public String getUsername(@Param("apid") Integer apid);

    @Query("select a from Approve a")
    public List<Approve> findAll();
}
