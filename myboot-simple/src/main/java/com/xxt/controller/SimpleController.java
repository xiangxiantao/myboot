package com.xxt.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
@RequestMapping("/simple")
public class SimpleController {
    private static Logger logger = LoggerFactory.getLogger(SimpleController.class);



    @RequestMapping("/getData")
    @ResponseBody
    public String simple(){
        logger.info("Hello World");
        return "直接返回的字符串";
    }

}
