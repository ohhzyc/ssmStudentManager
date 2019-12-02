package com.zyc.dao;

import java.util.List;
import java.util.Map;

import com.zyc.entity.Grade;
import org.apache.ibatis.annotations.Update;
import org.springframework.stereotype.Repository;



/**
 * 年级dao
 * @author llq
 *
 */
@Repository
public interface GradeDao {
    public int add(Grade grade);
    public int edit(Grade grade);
    public int delete(String ids);
    public List<Grade> findList(Map<String,Object> queryMap);
    public List<Grade> findAll();
    public int getTotal(Map<String,Object> queryMap);
}
