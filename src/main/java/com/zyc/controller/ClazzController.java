package com.zyc.controller;

import com.alibaba.fastjson.JSONArray;
import com.zyc.entity.Clazz;
import com.zyc.entity.Grade;
import com.zyc.page.Page;
import com.zyc.service.ClazzService;
import com.zyc.service.GradeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;


import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RequestMapping("/clazz")
@Controller
public class ClazzController {


    @Autowired
    private GradeService gradeService;
    @Autowired
    private ClazzService clazzService;
    /**
     * 年级列表页
     * @param model
     * @return
     */
    @RequestMapping(value="/list",method=RequestMethod.GET)
    public ModelAndView list(ModelAndView model){
        model.setViewName("clazzlist");
        List<Grade> findAll = gradeService.findAll();
        model.addObject("gradeList",findAll );
        return model;
    }

    /**
     * 获取年级列表
     * @param name
     * @param page
     * @return
     */
    @RequestMapping(value="/get_list",method=RequestMethod.POST)
    @ResponseBody
    public Map<String, Object> getList(
            @RequestParam(value="name",required=false,defaultValue="") String name,
            @RequestParam(value="gradeId",required=false) Long gradeId,
            Page page
    ){
        Map<String, Object> ret = new HashMap<String, Object>();
        Map<String, Object> queryMap = new HashMap<String, Object>();
        queryMap.put("name", "%"+name+"%");
        if(gradeId != null){
            queryMap.put("gradeId", gradeId);
        }
        queryMap.put("offset", page.getOffset());
        queryMap.put("pageSize", page.getRows());
        ret.put("rows", clazzService.findList(queryMap));
        ret.put("total", clazzService.getTotal(queryMap));
        return ret;
    }

    /**
     * 编辑年级信息
     * @param clazz
     * @return
     */
    @RequestMapping(value="/edit",method=RequestMethod.POST)
    @ResponseBody
    public Map<String, String> edit(Clazz clazz){
        Map<String, String> ret = new HashMap<String, String>();
        if(StringUtils.isEmpty(clazz.getName())){
            ret.put("type", "error");
            ret.put("msg", "年级名称不能为空！");
            return ret;
        }
        if(clazz.getGradeId() == null){
            ret.put("type", "error");
            ret.put("msg", "所属年级不能为空！");
            return ret;
        }
        if(clazzService.edit(clazz) <= 0){
            ret.put("type", "error");
            ret.put("msg", "年级修改失败！");
            return ret;
        }
        ret.put("type", "success");
        ret.put("msg", "年级修改成功！");
        return ret;
    }


    /**
     * 添加年级信息
     * @param clazz
     * @return
     */
    @RequestMapping(value="/add",method=RequestMethod.POST)
    @ResponseBody
    public Map<String, String> add(Clazz clazz){
        Map<String, String> ret = new HashMap<String, String>();
        if(StringUtils.isEmpty(clazz.getName())){
            ret.put("type", "error");
            ret.put("msg", "年级名称不能为空！");
            return ret;
        }
        if(clazz.getGradeId() == null){
            ret.put("type", "error");
            ret.put("msg", "请选择所属年级！");
            return ret;
        }
        if(clazzService.add(clazz) <= 0){
            ret.put("type", "error");
            ret.put("msg", "年级添加失败！");
            return ret;
        }
        ret.put("type", "success");
        ret.put("msg", "年级添加成功！");
        return ret;
    }

    /**
     * 删除年级信息
     * @param ids
     * @return
     */
    @RequestMapping(value="/delete",method=RequestMethod.POST)
    @ResponseBody
    public Map<String, String> delete(
            @RequestParam(value="ids[]",required=true) Long[] ids
    ){
        Map<String, String> ret = new HashMap<String, String>();
        if(ids == null || ids.length == 0){
            ret.put("type", "error");
            ret.put("msg", "请选择要删除的数据！");
            return ret;
        }
        try {
            String idsString = "";
            for(Long id:ids){
                idsString += id + ",";
            }
            idsString = idsString.substring(0,idsString.length()-1);
            if(clazzService.delete(idsString) <= 0){
                ret.put("type", "error");
                ret.put("msg", "删除失败!");
                return ret;
            }
        } catch (Exception e) {
            ret.put("type", "error");
            ret.put("msg", "该年级下存在学生信息，请勿冲动！");
            return ret;
        }
        ret.put("type", "success");
        ret.put("msg", "年级删除成功！");
        return ret;
    }
}