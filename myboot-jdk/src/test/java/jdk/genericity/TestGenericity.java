package jdk.genericity;

import com.alibaba.fastjson.annotation.JSONField;
import lombok.Data;
import org.junit.Test;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;
import java.util.List;

/**
 * @author ：xxt
 * @date ：Created in 2019/8/20 16:47
 * @description：
 * @modified By：泛型类测试
 * 静态方法，静态代码块，静态变量的声明和初始化中不允许使用类型形参（T）
 * 带类型形参的类也不能用于instensof运算
 */

public class TestGenericity {

    @Test
    public void testGenClass(){
        BassRes<String> bassRes=new BassRes<>();
        BassRes<Integer> bassRes1=new BassRes<>();
        System.out.println(bassRes.getClass()==bassRes1.getClass());//返回true
    }

    /**
     * 测试通配符
     */
    @Test
    public void testWildCard(){
        List<String> list=new ArrayList<>();
        fun1(list);//未经检查的泛型
        //fun2(list);// 类型转换异常：fun2(java.util.List<java.lang.Object>)in TestGenericity cannot be appliedto (java.util.List<java.lang.String>)
        fun3(list);
    }

    public void fun1(List list){
        System.out.println("不带类型参数的形参");
    }


    public void fun2(List<Object> list){
        System.out.println("固定类型参数的形参");
    }

    public void fun3(List<?> list){
        System.out.println("利用通配符声明类型参数");
    }

    public void fun4(List<? extends String> list){
        System.out.println("利用通配符声明类型参数,并说明通配符的类型上限（？必须是String或其子类类型）");
        //list.add(new String("xxx"));  //由于无法判定list中元素的具体类型，所以无法添加
    }

}

@Data
class BassRes<T>{
    SimpleBean bean;
    T body;
}

/**
 * 定义带泛型的类时，指定类型参数的上限
 * @param <T>
 */
@Data
class BassRes1<T extends String>{
    SimpleBean bean;
    T body;
}


/**
 * 测试普通类的泛型方法
 */
@Data
class SimpleBean{

    static void change1(Object[] objects, Collection<Object> collection){ }

    /**
     * 类型通配符的使用，此时方法中不能添加，删除collection中的元素
     * @param objects
     * @param collection
     */
    static void change2(Object[] objects, Collection<?> collection){ }

    /**
     * 方法形参的使用
     * @param objects
     * @param collection
     * @param <T>
     */
    static <T> void change3(T[] objects, Collection<T> collection){ }

    /**
     * 类型通配符和泛型方法形参配合使用
     * @param objects
     * @param collection
     * @param <T>
     */
    static <T> void change4(T[] objects, Collection<? extends T> collection){ }


}

