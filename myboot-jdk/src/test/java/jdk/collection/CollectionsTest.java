package jdk.collection;

import org.junit.Test;

import java.util.*;

public class CollectionsTest {


    /**
     * 操作list
     */
    @Test
    public void CollectionsToList(){

        List<String> list=new ArrayList<>();
        list.add("ele1");
        list.add("ele2");
        list.add("ele3");
        list.add("ele4");
        list.add("ele5");
        list.add("ele6");
        list.add("ele4");
        System.out.println(list);
        Collections.reverse(list);//反转
        System.out.println(list);
        Collections.shuffle(list);//洗牌
        System.out.println(list);
        Collections.sort(list);//排序
        System.out.println(list);
        Collections.binarySearch(list,"ele3");//二分查找key的index，必须保证list为有序状态
        Collections.max(list);//找出最大值，可传入comparator
        Collections.min(list);
        Collections.frequency(list,"ele4");//统计元素在list中出现的次数

    }


    /**
     * 获取线程安全的集合类
     */

    @Test
    public void testSysCollection(){
        Collection collection=Collections.synchronizedCollection(new ArrayList<>());
        Set set=Collections.synchronizedSet(new HashSet<>());
        Map map=Collections.synchronizedMap(new HashMap<>());
    }


    /**
     * 获取集合的只读版本
     */
    @Test
    public void testReadOnly(){
        Map<String,String> score=new HashMap<>();
        score.put("java","80");
        score.put("php","26");

        Map readOnlyMap=Collections.unmodifiableMap(score);
        //readOnlyMap.put("python","33");  //该map为只读，不能添加元素

    }

}
