package com.xxt.bean;

import org.hibernate.validator.constraints.Email;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.ImportResource;
import org.springframework.context.annotation.PropertySource;
import org.springframework.stereotype.Component;
import org.springframework.validation.annotation.Validated;

import java.util.Date;

@Component
@PropertySource(value = {"classpath:person.properties"})//加载指定配置文件
@ConfigurationProperties(prefix = "person1")  //该注解用于将配置文件中的值自动注入到bean中
@Validated
public class Person {

    //@Email//@ConfigurationProperties 支持JS302校验
    private String name;
    private String age;
    private Date birth;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAge() {
        return age;
    }

    public void setAge(String age) {
        this.age = age;
    }

    public Date getBirth() {
        return birth;
    }

    public void setBirth(Date birth) {
        this.birth = birth;
    }

    @Override
    public String toString() {
        return "Person{" +
                "name='" + name + '\'' +
                ", age='" + age + '\'' +
                ", birth=" + birth +
                '}';
    }
}
