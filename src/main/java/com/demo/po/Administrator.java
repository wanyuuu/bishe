package com.demo.po;

import javax.persistence.*;
import java.io.Serializable;

/**
 * Created by wanyu on 2019/4/17.
 */
@Entity
@Table(name = "administrator")
public class Administrator implements Serializable{
    @Id@GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "username",nullable = false)
    private String username;

    @Column(name = "password",nullable = false)
    private String password;

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
}
