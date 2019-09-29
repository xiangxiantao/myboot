package com.xxt.mybatisDemo.entity;

import java.util.List;

/**
 *@ClassName Class
 *@Description TODO
 *@Author xiang.xiantao
 *@Date 2019/4/1 15:29
 *@Version 1.0
 **/
public class Class {
    private Integer cId;
    private String cName;
    private String teacherId;

    private List<Student> students;

    public Class() {
    }

    public Class(Integer cId, String cName, String teacherId) {
        this.cId = cId;
        this.cName = cName;
        this.teacherId = teacherId;
    }

    public Integer getcId() {
        return cId;
    }

    public void setcId(Integer cId) {
        this.cId = cId;
    }

    public String getcName() {
        return cName;
    }

    public void setcName(String cName) {
        this.cName = cName;
    }

    public String getTeacherId() {
        return teacherId;
    }

    public void setTeacherId(String teacherId) {
        this.teacherId = teacherId;
    }

    public List<Student> getStudents() {
        return students;
    }

    public void setStudents(List<Student> students) {
        this.students = students;
    }

    @Override
    public String toString() {
        return "Class{" +
                "cId=" + cId +
                ", cName='" + cName + '\'' +
                ", teacherId='" + teacherId + '\'' +
                ", students=" + students +
                '}';
    }
}
