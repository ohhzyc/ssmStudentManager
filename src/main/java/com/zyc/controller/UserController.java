package com.zyc.controller;



import javax.servlet.http.HttpServletRequest;

import com.zyc.entity.User;
import com.zyc.service.UserService;
import org.apache.ibatis.session.SqlSession;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Controller;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Controller
@RequestMapping("/user")
public class UserController {

    @Resource
    private UserService userService;

    @RequestMapping(value = "/list",method = RequestMethod.GET)
    public ModelAndView selectUser(HttpServletRequest request, HttpServletResponse response,
                                   ModelAndView modelAndView) throws IOException {
        modelAndView.setViewName("userlist");
        return modelAndView;
    }

    @RequestMapping(value = "/getlist",method = RequestMethod.POST)
    @ResponseBody
    public Map<String,Object> list() throws IOException {
        Map<String,Object> ret = new HashMap<String, Object>();
        ret.put("rows",userService.selectUser());
        return  ret;
    }

    @RequestMapping(value = "/add",method = RequestMethod.POST)
    @ResponseBody
    public Map<String,String> addUser(User user){//笔记：自动
        System.out.println(user.getUsername()+" "+user.getPassword());
        Map<String,String> ret = new HashMap<String, String>();
        User user1 = userService.findByUserName(user.getUsername());
        if (user1 != null){
            ret.put("type","error");
            ret.put("msg","该用户已经存在");
            return  ret;
        }
        if (userService.add(user)<=0){
            ret.put("type","error");
            ret.put("msg","添加失败");
            return  ret;
        }
        ret.put("type","success");
        ret.put("msg","添加成功");
        return ret;

    }

    @RequestMapping(value = "/edit",method = RequestMethod.POST)
    @ResponseBody
    public Map<String,String> editUser(User user){//笔记： @Component
        Map<String,String> ret = new HashMap<String, String>();
        User user1 = userService.findByUserName(user.getUsername());
        if(user1 !=null){
            ret.put("type","error");
            ret.put("msg","有人叫这个名字了");
            return ret;
        }
        userService.edit(user);
        ret.put("type","success");
        ret.put("msg","修改成功");
        return ret;
    }

    @RequestMapping(value="/delete",method=RequestMethod.POST)
    @ResponseBody
    public Map<String, String> delete(
            @RequestParam(value="ids[]",required=true) Long[] ids
    ){
        Map<String, String> ret = new HashMap<String, String>();
        if(ids == null){
            ret.put("type", "error");
            ret.put("msg", "请选择要删除的数据!");
            return ret;
        }
        String idsString = "";
        for(Long id:ids){
            idsString += id + ",";
        }
        idsString = idsString.substring(0,idsString.length()-1);
        if(userService.delete(idsString) <= 0){
            ret.put("type", "error");
            ret.put("msg", "删除失败!");
            return ret;
        }
        ret.put("type", "success");
        ret.put("msg", "删除成功!");
        return ret;
    }

}
