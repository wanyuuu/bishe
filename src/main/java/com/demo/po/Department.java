package com.demo.po;


import javax.persistence.*;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by wanyu on 2019/3/28.
 */
@Entity
@Table(name = "department")
public class Department implements Serializable{
    @Id@GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "did",nullable = false)
    private int did;

    @Column(name = "dname",nullable = false)
    private String dname;

    @Column(name = "dnumber",nullable = false)
    private int dnumber;

    @Column(name = "dinformation",nullable = true)
    private String dinformation;
//    @OneToMany(mappedBy = "department")
//    private List<User> user = new ArrayList<User>();

    public int getDid() {
        return did;
    }

    public void setDid(int did) {
        this.did = did;
    }

    public String getDname() {
        return dname;
    }

    public void setDname(String dname) {
        this.dname = dname;
    }

    public int getDnumber() {
        return dnumber;
    }

    public void setDnumber(int dnumber) {
        this.dnumber = dnumber;
    }

    public String getDinformation() {
        return dinformation;
    }

    public void setDinformation(String dinformation) {
        this.dinformation = dinformation;
    }

    //    public List<User> getUser() {
//        return user;
//    }
//
//    public void setUser(List<User> user) {
//        this.user = user;
//    }
}
