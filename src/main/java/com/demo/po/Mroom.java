package com.demo.po;

import org.springframework.web.multipart.MultipartFile;

import javax.persistence.*;
import java.io.Serializable;

/**
 * Created by wanyu on 2019/4/23.
 */
@Entity
@Table(name = "mroom")
public class Mroom implements Serializable{
    @Id@GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "mrid",nullable = false)
    private int mrid;

    @Column(name = "mrname",nullable = false)
    private String mrname;

    @Column(name = "maddress",nullable = false)
    private String maddress;

    @Column(name = "mcapacity",nullable = false)
    private int mcapacity;

    @Column(name = "pic",nullable = true)
    private String pic;

    public int getMrid() {
        return mrid;
    }

    public void setMrid(int mrid) {
        this.mrid = mrid;
    }

    public String getMrname() {
        return mrname;
    }

    public void setMrname(String mrname) {
        this.mrname = mrname;
    }

    public String getMaddress() {
        return maddress;
    }

    public void setMaddress(String maddress) {
        this.maddress = maddress;
    }

    public int getMcapacity() {
        return mcapacity;
    }

    public void setMcapacity(int mcapacity) {
        this.mcapacity = mcapacity;
    }

    public String getPic() {
        return pic;
    }

    public void setPic(String pic) {
        this.pic = pic;
    }

}
