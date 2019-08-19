package com.xxt;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ImportResource;

@SpringBootApplication
//@ImportResource(locations = {"pathName"})//用于导入xml形式的springbean配置文件
public class App {

    public static void main(String[] args) {
        SpringApplication.run(App.class, args);
    }
}
