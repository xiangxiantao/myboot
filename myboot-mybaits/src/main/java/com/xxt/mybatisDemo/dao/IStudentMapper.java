package com.xxt.mybatisDemo.dao;

import com.xxt.mybatisDemo.entity.Student;

import java.util.Map;

public interface IStudentMapper {

    //1.单条记录，使用实体类接收
    public Student selectById1(int id);

    //2.单条记录，使用map接收
    public Map<String,String> selectById2(int id);

    Map<String, String> selectById3(Integer i);
}
