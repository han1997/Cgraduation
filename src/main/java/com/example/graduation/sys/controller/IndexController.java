package com.example.graduation.sys.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * @author: huiyuan
 * @date: 2020/5/8 16:06
 * @desc: 这是描述
 **/
@Controller
public class IndexController {
    @RequestMapping("/index")
    public String index(){
        return "index";
    }
}
