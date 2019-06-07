package com.demo.repository;

import com.demo.po.Administrator;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

/**
 * Created by wanyu on 2019/4/18.
 */
@Repository
public interface AdministratorRepository extends CrudRepository<Administrator,String>{
    @Query("select password from Administrator where username =:username")
    public String administratorLogin(@Param("username") String username);

}
