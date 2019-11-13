package com.zyc.controller;

import com.zyc.page.Page;
import com.zyc.service.StudentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import com.zyc.entity.Student;
@RequestMapping("/student")
@Controller
public class StudentController {

    @Autowired
    private StudentService studentService;


    @RequestMapping(value = "/list",method = RequestMethod.GET)
    public ModelAndView selectUser(HttpServletRequest request, HttpServletResponse response,
                                   ModelAndView modelAndView) throws IOException {
        modelAndView.setViewName("studentlist");
        return modelAndView;
    }
    @RequestMapping(value="/getStudentList",method=RequestMethod.POST)
    @ResponseBody
    public Map<String, Object> getList(
            @RequestParam(value="name",required=false,defaultValue="") String name,
            @RequestParam(value="clazzId",required=false) Long clazzId,
            HttpServletRequest request,
            Page page
    ){
        Map<String, Object> ret = new HashMap<String, Object>();
        Map<String, Object> queryMap = new HashMap<String, Object>();
        queryMap.put("username", "%"+name+"%");
        Object attribute = request.getSession().getAttribute("userType");
        if("2".equals(attribute.toString())){
            //说明是学生
            Student loginedStudent = (Student)request.getSession().getAttribute("user");
            queryMap.put("username", "%"+loginedStudent.getUsername()+"%");
        }
        if(clazzId != null){
            queryMap.put("clazzId", clazzId);
        }
        queryMap.put("offset", page.getOffset());
        queryMap.put("pageSize", page.getRows());
        ret.put("rows", studentService.findList(queryMap));
        ret.put("total", studentService.getTotal(queryMap));
        return ret;
    }

}
