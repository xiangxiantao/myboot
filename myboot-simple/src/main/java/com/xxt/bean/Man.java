package com.xxt.bean;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.util.Date;

@Component
public class Man {

    @Value("${person1.name}")
    private String name;

    //@Value("${person1.age}")
    @Value("#{12*5}")  //@Value支持el表达式
    private String age;

    @Value("${person1.birth}")

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
