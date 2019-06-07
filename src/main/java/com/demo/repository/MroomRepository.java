package com.demo.repository;

import com.demo.po.Mroom;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Created by wanyu on 2019/4/23.
 */
@Repository
public interface MroomRepository extends CrudRepository<Mroom,String>{
    public Mroom save(Mroom mroom);

    @Modifying
    @Query("delete from Mroom where mrid =:mrid")
    public int deleteRoom(@Param("mrid") Integer mrid);

    @Query("select m from Mroom m where mrid =:mrid")
    public Mroom queryMroomByMrid(@Param("mrid") Integer mrid);

    @Query("select m from Mroom m")
    public List<Mroom> findAll();
}
