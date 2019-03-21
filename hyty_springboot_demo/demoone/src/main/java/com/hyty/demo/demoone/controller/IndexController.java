package com.hyty.demo.demoone.controller;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class IndexController {
    private static Logger logger = LogManager.getLogger(IndexController.class);
    @RequestMapping("/index")
    public String index(){
        logger.info("启动错误：");
        return "index"; //当浏览器输入/index时，会返回 /templates/home.html页面
    }

}
