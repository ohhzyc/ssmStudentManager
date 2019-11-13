package com.zyc.service;

import java.util.List;
import java.util.Map;

import com.zyc.entity.Clazz;
import org.springframework.stereotype.Service;


/**
 * 班级service
 * @author llq
 *
 */
@Service
public interface ClazzService {
    public int add(Clazz clazz);
    public int edit(Clazz clazz);
    public int delete(String ids);
    public List<Clazz> findList(Map<String,Object> queryMap);
    public List<Clazz> findAll();
    public int getTotal(Map<String,Object> queryMap);
}