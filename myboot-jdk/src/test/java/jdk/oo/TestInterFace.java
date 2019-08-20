package jdk.oo;

/**
 * @author ：xxt
 * @date ：Created in 2019/8/20 17:57
 * @description：
 * @modified By：
 */

public interface TestInterFace {

    //接口的成员变量只能是常量
    int MAX_INT=50;

    void fun();

    //接口中定义默认方法，接口不能直接调用默认方法，只能用接口的实现类的实例进行调用
    default void defun(){
        System.out.println("this is a default function");
    }

    //接口中的定义静态方法，可以用接口直接调用
    static void stafun(){
        System.out.println("this is a static fun");
    }
}
