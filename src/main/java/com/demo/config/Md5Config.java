package com.demo.config;

import org.testng.annotations.Test;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

/**
 * Created by wanyu on 2019/4/16.
 */
public class Md5Config {
    public static String Md5(String password){
        StringBuffer sb = new StringBuffer();
        try{
            //MessageDigest提供信息摘要算法的功能，就是生成散列码。

                //加密对象，指定加密的方式
                MessageDigest md5 = MessageDigest.getInstance("md5");
                //准备要加密的数据
                byte[] b = password.getBytes();
                //加密
                byte[] digest = md5.digest(b);
                char[] chars = new char[]{'0','1','2','3','4','5','6','7','8','9','A','B','C','D','E','F'};
                //处理成16进制的字符串
                for(byte bb:digest){
                    sb.append(chars[(bb >> 4) & 15]);
                    sb.append(chars[bb & 15]);
                }
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        }
        return new String(sb);
    }
    public static boolean validateMd5(String password,String inputPassword){
        //输入的密码加密后再和库中存的密码比较
        if(password.equals(Md5(inputPassword))){
            return true;
        }else {
            return false;
        }
    }
}
