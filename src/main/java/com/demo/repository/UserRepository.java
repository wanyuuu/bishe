package com.demo.repository;

import com.demo.po.User;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Created by wanyu on 2019/4/15.
 */
@Repository
public interface UserRepository extends CrudRepository<User,Integer> {
    public User save(User user);

    @Modifying //告知SpringData这是个update或delete操作
    @Query("delete from User where uid =:uid")
    public int deleteUser(@Param("uid") Integer uid);

    @Query("select u.password from User u where username =:username")
    public String login(@Param("username") String username);

    @Query("select u.isexist from User u where username =:username")
    public int isExist(@Param("username") String username);

    @Query("select u.username from User u where username =:username")
    public String isReg(@Param("username") String username);

    @Query("select u from User u where username =:username")
    public User querySelfUser(@Param("username") String username);

    @Query("select u.uid,u.username,u.telephone,u.email,d.dname from User u,Department d where uid =:uid and u.did = d.did")
    public String queryUserInfo(@Param("uid") Integer uid);

    @Query("select u from User u")
    public List<User> findAll();

    @Modifying
    @Query("update User u set username =:username,telephone =:telephone,email =:email,did =:did where uid =:uid")
    public int updateUserInfo(@Param("uid") Integer uid,@Param("username") String username,
                              @Param("telephone") String telephone,@Param("email") String email,@Param("did") Integer did);
    @Modifying
    @Query("update User u set password =:password where uid =:uid")
    public int updatePassword(@Param("password") String password,@Param("uid") Integer uid);

    @Modifying
    @Query("update User set isexist =:isexist where username =:username")
    public int updateExist(@Param("username") String username,@Param("isexist") Integer isexist);

    @Query("select password from User where uid =:uid")
    public String queryPassword(@Param("uid") Integer uid);
}
