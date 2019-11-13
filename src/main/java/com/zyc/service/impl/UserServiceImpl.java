package com.zyc.service.impl;

import com.zyc.dao.UserDao;
import com.zyc.entity.User;
import com.zyc.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserDao userDao;

    public List<User> selectUser() {
        return this.userDao.selectUser();
    }

    @Override
    public User findByUserName(String username) {
        return userDao.findByUserName(username);
    }

    public int add(User user){
        return userDao.add(user);
    }

    public  int edit(User user){
        return userDao.edit( user);
    }
    public int delete(String username){
        return userDao.delete(username);
    }
}
