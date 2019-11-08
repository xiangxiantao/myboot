package com.xxt.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class MainController {

    @RequestMapping("/index")
    public String index(){
        return "别学了，该摸了";
    }

    @RequestMapping("/main")
    public String main(){
        return "main page";
    }



}
