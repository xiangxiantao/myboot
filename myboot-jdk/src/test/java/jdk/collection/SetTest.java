package jdk.collection;

import org.junit.Test;

import java.util.EnumSet;
import java.util.HashSet;
import java.util.TreeSet;

/**
 * @author ：xxt
 * @date ：Created in 2019/8/19 15:15
 * @description：
 * @modified By：
 */


public class SetTest {

    /**
     * HashSet通过对象的hashcode()方法判断元素的存储位置
     *        通过hashcode()+equals()方法判断是否为同一个元素
     *        当把元素添加到集合中之后，尽量不要去改动参与计算hashcode和equals方法的参数，否则可能会导致set中的这个被改动的元素无法被访问
     */

    @Test
    public void testPut(){
        HashSet hashSet=new HashSet();
        boolean add = hashSet.add(new A());
        boolean add1 = hashSet.add(new A());
        boolean add2 = hashSet.add(new B());
        boolean add3 = hashSet.add(new B());
        boolean add4 = hashSet.add(new C());
        boolean add5 = hashSet.add(new C());
        System.out.println(add);
        System.out.println(add1);
        System.out.println(add2);
        System.out.println(add3);
        System.out.println(add4);
        System.out.println(add5);

        System.out.println(hashSet);

    }


    @Test
    public void testRemove(){
        HashSet<R> hashSet=new HashSet();
        R r1 = new R(-3);
        hashSet.add(r1);
        hashSet.add(new R(-4));
        R r2 = new R(1);
        hashSet.add(r2);
        hashSet.add(new R(5));
        hashSet.add(new R(2));
        //hashSet.add(null);
        System.out.println(hashSet);
        R first = hashSet.iterator().next();//取出set中的第一个参数
        System.out.println(first+":"+first.getCount());
        System.out.println(r2==first);
        first.setCount(-3);
        System.out.println(hashSet);
        for (R r:hashSet){
            System.out.println(r.getCount()+":"+r.hashCode());
        }

        hashSet.remove(new R(1));
        System.out.println(hashSet.contains(new R(1)));
        System.out.println(hashSet.contains(new R(-3)));


    }

    /**
     * 测试有序set
     * treeSet采用红黑树进行排序
     * treeSet中的元素必须实现comparable接口，且必须是同一个类型的元素（同一个类的对象）
     * treeSet中的元素发生变化时，不会再次调整集合中元素的顺序，并且可能会出现错误，所以应该避免修改treeSet集合中的元素
     */
    @Test
    public void testTreeSet(){
        TreeSet treeSet=new TreeSet();
        treeSet.add(5);
        treeSet.add(7);
        treeSet.add(2);
        treeSet.add(4);
        treeSet.add(1);
        treeSet.add(6);
        System.out.println(treeSet);
        System.out.println(treeSet.first());
        System.out.println(treeSet.last());
        System.out.println(treeSet.headSet(5));
        System.out.println(treeSet.tailSet(5));
        System.out.println(treeSet.subSet(2,5));
    }


    /**
     * treeSet自定义排序规则
     */
    @Test
    public void testComparator(){
        TreeSet<Integer> treeSet=new TreeSet(((o1, o2) -> {
            return 0-((Integer) o1-(Integer) o2);//降序排列
        }));
        treeSet.add(5);
        treeSet.add(7);
        treeSet.add(2);
        treeSet.add(4);
        treeSet.add(1);
        treeSet.add(6);
        System.out.println(treeSet);

    }

    @Test
    public void testEnumSet(){
        EnumSet<Session> sessions1 = EnumSet.allOf(Session.class);//创建一个包含枚举全量的set
        System.out.println(sessions1);
        EnumSet<Session> sessions2=EnumSet.noneOf(Session.class);//创建一个指定类型为Session的空set
        sessions2.add(Session.FALL);
        System.out.println(sessions2);

        EnumSet<Session> sessions3 = EnumSet.complementOf(sessions2);
        System.out.println(sessions3);
    }

}

enum Session{
    SPRING("1","a"),
    SUMMER("2","b"),
    FALL("3","c"),
    WINTER("4","d");

    Session(String code,String msg){
        this.code=code;
        this.msg=msg;
    };

    String code;
    String msg;
}

//equals方法返回true，但hashcode不一样，可以添加
class A {
    @Override
    public boolean equals(Object obj) {
        return true;
    }
}

//equals方法返回false，但hashcode一样，可以添加
class B {
    @Override
    public int hashCode() {
        return 1;
    }
}


//equals方法返回true，hashcode一样，不可以添加
class C {

    @Override
    public boolean equals(Object obj) {
        return true;
    }

    @Override
    public int hashCode() {
        return 2;
    }

}


//当把元素添加到集合中之后，尽量不要去改动参与计算hashcode和equals方法的参数，否则可能会导致set中的这个被改动的元素无法被访问
class R{
    int count;

    public R(int count) {
        this.count = count;
    }

    public R() {
    }

    public int getCount() {
        return count;
    }

    public void setCount(int count) {
        this.count = count;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        R r = (R) o;
        return count == r.count;  //count参与equals方法的判断
    }

    @Override
    public int hashCode() {
        return this.count; //count参与hashcode的计算
    }
}
