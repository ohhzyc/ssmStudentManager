package com.zyc.service;

import com.zyc.entity.User;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

@Service
public interface UserService {

    public List<User> selectUser();
    public User findByUserName(String username);
    public int add(User user);
    public int edit(User user);
    public int delete(String username);
    public int getTotal(Map<String,Object> queryMap);
    public List<User> findList(Map<String,Object> queryMap);
}
