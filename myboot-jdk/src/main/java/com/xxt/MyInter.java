package com.xxt;

public interface MyInter {

    static void staticFun(){
        System.out.println("接口中允许静态方法");
    }

    default void defaultfun(){
        System.out.println("接口中的默认方法");
        fun1();
    }

    void fun1();

}
