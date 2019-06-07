package com.demo.service;

import com.demo.po.Department;
import com.demo.repository.DepartmentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * Created by wanyu on 2019/3/28.
 */
@Service
@Transactional
public class DepartmentService {
    @Autowired
    private DepartmentRepository departmentRepository;

    public String findByDid(Integer did) {
        return (departmentRepository.findByDid(did)).getDname();
    }
    //添加新部门
    public Department save(Department department){
        return departmentRepository.save(department);
    }
    //查看所有部门
    public List<Department> findAll(){
        return departmentRepository.findAll();
    }
    //删除部门
    public int deleteDepartment(Integer did){
        return departmentRepository.deleteDepartment(did);
    }
    //修改部门信息
    public int updateDepartment(String dname,Integer dnumber,String dinformation,Integer did){
        return departmentRepository.updateDepartment(dname,dnumber,dinformation,did);
    }

}
