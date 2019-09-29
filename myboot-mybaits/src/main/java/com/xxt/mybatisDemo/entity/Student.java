package com.xxt.mybatisDemo.entity;
/**
 *@ClassName Student
 *@Description TODO
 *@Author xiang.xiantao
 *@Date 2019/3/29 18:13
 *@Version 1.0
 **/
public class Student {

    private Integer sId;
    private String sName;
    private String classId;
    private Class aClass;
    private StudentStatus studentStatus;

    public Student(){}
    public Student(String sName, String classId) {
        this.sName=sName;
        this.classId=classId;
    }
    public Student(Integer sId, String sName, String classId) {
        this.sId=sId;
        this.sName=sName;
        this.classId=classId;
    }

    public Student(String sName, String classId, StudentStatus studentStatus) {
        this.sName = sName;
        this.classId = classId;
        this.studentStatus = studentStatus;
    }

    public Integer getsId() {
        return sId;
    }

    public void setsId(Integer sId) {
        this.sId = sId;
    }

    public String getsName() {
        return sName;
    }

    public void setsName(String sName) {
        this.sName = sName;
    }

    public String getClassId() {
        return classId;
    }

    public void setClassId(String classId) {
        this.classId = classId;
    }

    public Class getaClass() {
        return aClass;
    }

    public void setaClass(Class aClass) {
        this.aClass = aClass;
    }

    public StudentStatus getStudentStatus() {
        return studentStatus;
    }

    public void setStudentStatus(StudentStatus studentStatus) {
        this.studentStatus = studentStatus;
    }

    @Override
    public String toString() {
        return "Student{" +
                "sId=" + sId +
                ", sName='" + sName + '\'' +
                ", classId='" + classId + '\'' +
                ", aClass=" + aClass +
                ", studentStatus=" + studentStatus +
                '}';
    }
}
