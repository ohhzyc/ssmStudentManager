package com.zyc.service;

import com.zyc.entity.User;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface UserService {

    public List<User> selectUser();
    public User findByUserName(String username);
    public int add(User user);
    public int edit(User user);
    public int delete(String username);
}
