package com.zyc.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@RequestMapping("/student")
@Controller
public class StudentController {


    @RequestMapping(value = "/list",method = RequestMethod.GET)
    public ModelAndView selectUser(HttpServletRequest request, HttpServletResponse response,
                                   ModelAndView modelAndView) throws IOException {
        modelAndView.setViewName("studentlist");
        return modelAndView;
    }


}
