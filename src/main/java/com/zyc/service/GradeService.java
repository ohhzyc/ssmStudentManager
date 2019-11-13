package com.zyc.service;

import java.util.List;
import java.util.Map;

import com.zyc.entity.Grade;
import org.springframework.stereotype.Service;


/**
 * 年级service
 * @author llq
 *
 */
@Service
public interface GradeService {
    public int add(Grade grade);
    public int edit(Grade grade);
    public int delete(String ids);
    public List<Grade> findList(Map<String,Object> queryMap);
    public List<Grade> findAll();
    public int getTotal(Map<String,Object> queryMap);
}
