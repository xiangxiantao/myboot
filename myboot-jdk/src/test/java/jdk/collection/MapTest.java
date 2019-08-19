package jdk.collection;

import org.junit.Test;

import java.util.*;

public class MapTest {

    /**
     * 遍历map
     */
    @Test
    public void testForEach(){
        Map<String,String> map=new HashMap<>();
        map.put("key1","v1");
        map.put("key2","v2");
        map.put("key3","v3");
        map.put("key4","v4");

        map.forEach((k,v)->{
            System.out.println(k+":"+v);
            map.compute(k,(k1,v1)->{
               return k1+v1;
            });
        });

        System.out.println(map);

        map.merge("key1","newV",(oldV,newV)-> oldV+newV);
        System.out.println(map.getOrDefault("key1","def"));
    }


    @Test
    public void testWeekHashMap(){
        WeakHashMap<String,String> weakHashMap=new WeakHashMap<>();
        weakHashMap.put(new String("k1"),new String("ok"));
        weakHashMap.put(new String("k2"),new String("normal"));
        weakHashMap.put(new String("k3"),new String("bad"));
        weakHashMap.put("java",new String("perfect"));  //字符串直接量JVM会保持其强引用

        System.out.println(weakHashMap);

        System.gc();
        System.runFinalization();

        //系统在进行垃圾回收之后，将weeakhashmap中的弱引用回收
        System.out.println(weakHashMap);

    }


    /**
     * 一般的hashmap判定两个key值相等的条件是通过equals方法返回true就是相同的key，不能重复添加
     * 而IdentityHashMap认为必须两个key是相同的对象才是同一个key
     */
    @Test
    public void testIdentityMap(){
        IdentityHashMap<String,String> identityHashMap=new IdentityHashMap<>();
        identityHashMap.put(new String("yuwen"),"97");
        identityHashMap.put(new String("yuwen"),"38");
        identityHashMap.put(new String("yuwen"),"21");
        identityHashMap.put("java","22");
        identityHashMap.put("java","21");
        System.out.println(identityHashMap);
    }

    public void testEnumMap(){
        EnumMap enumMap=new EnumMap(Session.class);

    }
}
