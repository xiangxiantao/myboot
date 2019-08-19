package jdk.collection;

import org.junit.Test;

import java.util.*;

/**
 * @author ：xxt
 * @date ：Created in 2019/8/19 17:44
 * @description：
 * @modified By：
 */

public class ListTest {

    @Test
    public void testSimple(){
        List<Integer> list=new ArrayList();
        list.add(1);
        list.add(5);
        list.add(2);
        list.add(9);
        list.add(7);
        list.add(1);
        System.out.println(list);
        System.out.println(list.get(2));
        list.add(2,21);
        System.out.println(list);
        System.out.println(list.get(2));
        list.sort(((o1, o2) -> o1-o2));//传入一个conparator进行排序
        System.out.println(list);
        list.replaceAll((integer -> integer*integer));//用一定的那个规则进行全部替换
        System.out.println(list);

    }


    @Test
    public void testListIter(){
        List<Integer> list=new ArrayList();
        list.add(1);
        list.add(5);
        list.add(2);
        list.add(9);
        list.add(7);
        list.add(1);
        ListIterator<Integer> listIterator = list.listIterator(3);//从固定位置开启迭代器
        while(listIterator.hasPrevious()){//反向迭代
            System.out.println(listIterator.previous());
        }
        System.out.println(list);
    }

    //vector 及其子类Stack都是线程安全且性能低下的类，通常不使用，用Collections的封装线程安全list以及ArrayDeque实现栈这种数据结构

    @Test
    public void testArraysAsList(){
        List<String> asList = Arrays.asList("元素1", "元素2", "元素3");//通过这种方式创建出来的list是固定长度的，只能用来遍历，不可增加，删除
        //asList.add("ele4");//这行代码会报错

        ArrayList arrayList=new ArrayList(asList);
        arrayList.add("ele4");
    }



}
