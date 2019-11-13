package com.zyc.service.impl;

import com.zyc.dao.StudentDao;
import com.zyc.entity.Student;
import com.zyc.service.StudentService;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
@Service
public class StudentServicelmpl implements StudentService {

    @Autowired
    private StudentDao studentDao;

    public int add(Student student) {
        // TODO Auto-generated method stub
        return studentDao.add(student);
    }


    public int edit(Student student) {
        // TODO Auto-generated method stub
        return studentDao.edit(student);
    }


    public int delete(String ids) {
        // TODO Auto-generated method stub
        return studentDao.delete(ids);
    }


    public List<Student> findList(Map<String, Object> queryMap) {
        // TODO Auto-generated method stub
        return studentDao.findList(queryMap);
    }


    public List<Student> findAll() {
        // TODO Auto-generated method stub
        return studentDao.findAll();
    }


    public int getTotal(Map<String, Object> queryMap) {
        // TODO Auto-generated method stub
        return studentDao.getTotal(queryMap);
    }


    public Student findByUserName(String username) {
        // TODO Auto-generated method stub
        return studentDao.findByUserName(username);
    }

}

