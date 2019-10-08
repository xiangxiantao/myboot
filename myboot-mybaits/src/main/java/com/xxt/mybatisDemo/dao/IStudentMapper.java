package com.xxt.mybatisDemo.dao;

import com.xxt.mybatisDemo.entity.Student;
import org.apache.ibatis.annotations.MapKey;
import org.apache.ibatis.annotations.Param;

import java.time.LocalDateTime;
import java.util.Date;
import java.util.List;
import java.util.Map;

public interface IStudentMapper {

    //1.单条记录，使用实体类接收
    Student selectById1(int id);

    //2.单条记录，使用map接收
    Map<String,String> selectById2(int id);

    //3.单条记录，传入包装类
    Map<String, String> selectById3(Integer i);

    //4.入参为list，返回多条记录用list封装
    List<Student> selectByIds1(@Param("ids") List<String> ids);

    //5.入参为list，返回多条记录用map封装
    @MapKey("sId")
    Map<String, Student> selectByIds2(@Param("ids") List<String> ids);

    //6.入参为list+String
    @MapKey("sId")
    Map<String, Student> selectByIds3(@Param("classId") String classId, @Param("ids") List<String> ids);

    //7.用map封装入参list+String
    @MapKey("sId")
    Map<String, Student> selectByIds4(Map<String, Object> params);

    //8.用String查询timestamp
    List<Student> selectByTime1(@Param("time") String time);

    //9.用Date查询timestamp
    List<Student> selectByTime2(@Param("time")Date date);

    //10.用localDateTime查询timestamp
    List<Student> selectByTime3(@Param("time")LocalDateTime localDateTime);
}
