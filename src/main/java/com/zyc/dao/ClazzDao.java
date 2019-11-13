package com.zyc.dao;

import java.util.List;
import java.util.Map;

import com.zyc.entity.Clazz;
import org.springframework.stereotype.Repository;



/**
 * 班级dao
 * @author llq
 *
 */
@Repository
public interface ClazzDao {
    public int add(Clazz clazz);
    public int edit(Clazz clazz);
    public int delete(String ids);
    public List<Clazz> findList(Map<String,Object> queryMap);
    public List<Clazz> findAll();
    public int getTotal(Map<String,Object> queryMap);
}
