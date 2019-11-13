package com.zyc.controller;

import com.zyc.entity.User;
import com.zyc.service.UserService;
import com.zyc.utils.CpachaUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import javax.annotation.Resource;
import javax.imageio.ImageIO;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;


@Controller
@RequestMapping("/zyc")
public class LoginController {

    @Autowired
    private UserService userService;

    /**
     * 登陆页面
     * @param modelAndView
     * @return
     */
    @RequestMapping(value = "/login",method = RequestMethod.GET)
    public ModelAndView login(ModelAndView modelAndView){
        modelAndView.setViewName("login");
        return modelAndView;
    }

    @RequestMapping("index")
    public ModelAndView index(ModelAndView model,HttpServletRequest request){
        model.setViewName("index");
        User user = (User) request.getSession().getAttribute("user");
        request.setAttribute("user",user);
        return model;
    }

    /**
     * 登出
     * @param request
     * @param modelAndView
     * @return
     */
    @RequestMapping("/logout")
    public ModelAndView logout(HttpServletRequest request, ModelAndView modelAndView){
        request.getSession().invalidate();
        modelAndView.setViewName("login");
        return modelAndView;
    }
    /**
     * 验证码
     * @param request
     * @param response
     */
    @RequestMapping("/getCode")
    public void getCode(HttpServletRequest request , HttpServletResponse response){
        CpachaUtil cpachaUtil = new CpachaUtil(4,98,40);
        String generatorVCode = cpachaUtil.generatorVCode();
        request.getSession().setAttribute("loginCpacha",generatorVCode);
        BufferedImage generatorVCodeImage = cpachaUtil.generatorVCodeImage(generatorVCode,true);
        try{
            ImageIO.write(generatorVCodeImage,"gif",response.getOutputStream());
        }catch (IOException e){
            e.printStackTrace();
        }
    }

    /**
     * 检验账号密码
     */
    @RequestMapping(value = "/login",method = RequestMethod.POST)
    @ResponseBody
    public Map<String,String> login(@RequestParam(value = "username",required = true) String username,
                                         @RequestParam(value = "password",required = true) String password,
                                         @RequestParam(value = "vcode",required = true) String vcode,
                                         @RequestParam(value = "type",required = true) int type,
                                         HttpServletResponse response,HttpServletRequest request
                                        ){

        Map<String,String> ret = new HashMap<String, String>();
        if(StringUtils.isEmpty(username)){
            ret.put("type","error");
            ret.put("msg","用户名不能为空！");
            return ret;
        }

        if(StringUtils.isEmpty(password)){
            ret.put("type","error");
            ret.put("msg","密码不能为空！");
            return ret;
        }
        if(StringUtils.isEmpty(vcode)){
            ret.put("type","error");
            ret.put("msg","验证码不能为空！");
            return ret;
        }
        String loginCpacha = (String)request.getSession().getAttribute("loginCpacha");
        if(StringUtils.isEmpty(loginCpacha)){
            ret.put("type","error");
            ret.put("msg","长时间未操作，会话已失效！");
            return ret;
        }
        if (!vcode.toUpperCase().equals(loginCpacha.toUpperCase())){
            ret.put("type","error");
            ret.put("msg","验证码错误！");
            return ret;
        }
        request.getSession().setAttribute("loginCpacha",null);
        //通过数据库校验账号密码
        if (type == 1){
            User user = userService.findByUserName(username);
            if(user==null){
                ret.put("type","error");
                ret.put("msg","该用户不存在！");
                return  ret;
            }
            if (!password.equals(user.getPassword())){
                ret.put("type","error");
                ret.put("msg","密码错误！");
                return  ret;
            }
            request.getSession().setAttribute("user",user);
        }
        if (type==2){
            //学生
        }

        ret.put("type","success");
        ret.put("msg","登录成功！");
        return  ret;

    }
}
