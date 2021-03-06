package com.zyc.dao;

import com.zyc.entity.User;
import org.springframework.stereotype.Repository;

import java.util.*;

@Repository
public interface UserDao {
    //展示管理员的信息
    List<User> selectUser();

    User findByUserName(String username);

    public int  add(User user);

    public int edit(User user);

    public int delete(String username);

    public int getTotal(Map<String,Object> queryMap);

    public List<User> findList(Map<String,Object> queryMap);
}
