package com.demo.po;

import javax.persistence.*;
import java.io.Serializable;

/**
 * Created by wanyu on 2019/4/17.
 */
@Entity
@Table(name = "approve")
public class Approve implements Serializable{
    @Id@GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "apid",nullable = false)
    private int apid;

    @Column(name = "username",nullable = false)
    private String username;

    @Column(name = "telephone",nullable = false)
    private String telephone;

    @Column(name = "email",nullable = false)
    private String email;

    @Column(name = "isapprove",nullable = false)
    private int isapprove;

    public int getApid() {
        return apid;
    }

    public void setApid(int apid) {
        this.apid = apid;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getTelephone() {
        return telephone;
    }

    public void setTelephone(String telephone) {
        this.telephone = telephone;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public int getIsapprove() {
        return isapprove;
    }

    public void setIsapprove(int isapprove) {
        this.isapprove = isapprove;
    }
}
