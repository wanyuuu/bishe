package com.demo.po;

import com.fasterxml.jackson.annotation.JsonFormat;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;


/**
 * Created by wanyu on 2019/3/28.
 */
@Entity
@Table(name = "user")
public class User implements Serializable {
    public User(){}

    @Id@GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "uid",nullable = false)
    private int uid;

    @Column(name = "username",nullable = false)
    private String username;

    @Column(name = "password",nullable = false)
    private String password;

    @Column(name = "telephone",nullable = false)
    private String telephone;

    @Column(name = "email",nullable = false)
    private String email;

    @Column(name = "did",nullable = false)
    private int did;

    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss",timezone = "GMT+8")
    @Column(name = "regtime",nullable = true)
    private Date regtime;

    @Column(name = "isexist",nullable = false)
    private int isexist;

//    @ManyToOne
//    @JoinColumn(name = "department_id")
//    private Department department;

    public int getUid() {
        return uid;
    }

    public void setUid(int uid) {
        this.uid = uid;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public int getDid() {
        return did;
    }

    public void setDid(int did) {
        this.did = did;
    }

    public Date getRegtime() {
        return regtime;
    }

    public void setRegtime(Date regtime) {
        this.regtime = regtime;
    }

    public int getIsexist() {
        return isexist;
    }

    public void setIsexist(int isexist) {
        this.isexist = isexist;
    }

    public String getTelephone() {
        return telephone;
    }

    public void setTelephone(String telephone) {
        this.telephone = telephone;
    }

//    public Department getDepartment() {
//        return department;
//    }
//
//    public void setDepartment(Department department) {
//        this.department = department;
//    }
}
