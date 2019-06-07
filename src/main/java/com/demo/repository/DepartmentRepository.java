package com.demo.repository;

import com.demo.po.Department;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Created by wanyu on 2019/3/28.
 */
@Repository
public interface DepartmentRepository extends CrudRepository<Department,Integer> {
    @Query("select c from Department c where did =:d")
    public Department findByDid(@Param("d") Integer did);

    public Department save(Department department);

    @Modifying
    @Query("delete from Department where did =:did")
    public int deleteDepartment(@Param("did") Integer did);

    @Modifying
    @Query("update Department d set dname =:dname,dnumber =:dnumber,dinformation =:dinformation where did =:did")
    public int updateDepartment(@Param("dname") String dname,@Param("dnumber") Integer dnumber,@Param("dinformation") String dinformation,@Param("did") Integer did);

    @Query("select d from Department d")
    public List<Department> findAll();
}
