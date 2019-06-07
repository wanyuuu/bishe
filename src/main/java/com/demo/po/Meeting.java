package com.demo.po;

import com.fasterxml.jackson.annotation.JsonFormat;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;

/**
 * Created by wanyu on 2019/4/23.
 */
@Entity
@Table(name = "meeting")
public class Meeting implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "mid",nullable = false)
    private int mid;

    @Column(name = "mname",nullable = false)
    private String mname;

    @Column(name = "mrid",nullable = false)
    private int mrid;

    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss",timezone = "GMT+8")
    @Column(name = "starttime",nullable = true)
    private Date starttime;

    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss",timezone = "GMT+8")
    @Column(name = "endtime",nullable = true)
    private Date endtime;

    @Column(name = "mnumber",nullable = false)
    private int mnumber;

    @Column(name = "uid",nullable = true)
    private int uid;

    @Column(name = "did",nullable = true)
    private int did;

    @Column(name = "information",nullable = true)
    private String information;

    public int getMid() {
        return mid;
    }

    public void setMid(int mid) {
        this.mid = mid;
    }

    public String getMname() {
        return mname;
    }

    public void setMname(String mname) {
        this.mname = mname;
    }

    public int getMrid() {
        return mrid;
    }

    public void setMrid(Integer mrid) {
        this.mrid = mrid;
    }

    public Date getStarttime() {
        return starttime;
    }

    public void setStarttime(Date starttime) {
        this.starttime = starttime;
    }

    public Date getEndtime() {
        return endtime;
    }

    public void setEndtime(Date endtime) {
        this.endtime = endtime;
    }

    public int getMnumber() {
        return mnumber;
    }

    public void setMnumber(int mnumber) {
        this.mnumber = mnumber;
    }

    public int getUid() {
        return uid;
    }

    public void setUid(int uid) {
        this.uid = uid;
    }

    public int getDid() {
        return did;
    }

    public void setDid(int did) {
        this.did = did;
    }

    public String getInformation() {
        return information;
    }

    public void setInformation(String information) {
        this.information = information;
    }
}
