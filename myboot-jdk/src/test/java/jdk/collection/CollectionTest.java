package jdk.collection;

import lombok.Data;
import org.junit.Test;

import java.util.*;
import java.util.function.Predicate;
import java.util.stream.IntStream;

public class CollectionTest {

    /**
     * collection接口的基本方法
     */
    @Test
    public void testCollection(){
        Collection<String> collection=new ArrayList();
        collection.add("firstElement");
        collection.add("secondElement");
        System.out.println(collection.size());
        collection.remove("secondElement");
        System.out.println(collection.size());
        boolean remove = collection.remove("noneElement");//删除一个不存在的元素，返回false
        System.out.println(remove);
        System.out.println(collection.size());

        collection.add("ele3");
        collection.add("ele4");
        collection.add("ele5");
        System.out.println(collection.size());

        Collection<String> collection1=new HashSet<>();
        collection1.add("ele3");
        collection1.add("ele4");
        collection1.add("ele5");
        collection1.add("ele6");

        Collection<String> collection2=new HashSet<>();
        collection1.add("ele7");
        collection1.add("ele8");
        collection1.add("ele9");
        collection1.add("ele10");

        System.out.println(collection.containsAll(collection1));//集合中是否包含另一个集合的全集
        System.out.println(collection.removeAll(collection1));//从集合中移除与另一个集合的交集
        System.out.println(collection.removeAll(collection2));//从集合中移除与另一个集合的交集，当不存在交集时返回false
        System.out.println(collection1.retainAll(collection));//只保留与另一个元素的交集
        System.out.println(collection1.size());
        System.out.println(collection.size());

    }

    /**
     * 使用lambda表达式遍历集合
     */
    @Test
    public void testForEach(){
        Collection<String> collection=new HashSet<>();
        collection.add("ele3");
        collection.add("ele4");
        collection.add("ele5");

        collection.forEach(s -> {
            //dosomething()
            System.out.println(s);
        });

    }

    /**
     * 使用迭代器遍历集合，元素为不可变类
     */
    @Test
    public void testIterator(){
        Collection<String> collection=new HashSet<>();
        collection.add("ele3");
        collection.add("ele4");
        collection.add("ele5");
        Iterator<String> iterator=collection.iterator();
        while (iterator.hasNext()){
            String next = iterator.next();
            System.out.println(next);
            if ("ele3".equals(next)){
                iterator.remove();//在迭代器中可以删除元素

            }

            if ("ele4".equals(next)){
                next="other";//对迭代器取出的值进行修改不会影响集合中的元素的值，因为java是值传递
            }
        }

        System.out.println(collection);

        System.out.println(collection.size());
    }


    /**
     * 使用迭代器遍历集合,元素为class
     */
    @Test
    public void testIteratorObj(){
        Collection<Book> collection=new HashSet<>();
        collection.add(new Book("xxx1",23));
        collection.add(new Book("xxx2",23));
        collection.add(new Book("xxx3",23));
        Iterator<Book> iterator=collection.iterator();
        while (iterator.hasNext()){
            Book next = iterator.next();
            System.out.println(next);
            if ("xxx1".equals(next.getName())){
                next.setPrice(25);
            }
        }

        System.out.println(collection);

        System.out.println(collection.size());
    }

    /**
     * 使用lambda遍历迭代器
     */
    @Test
    public void testForEachIter(){
        Collection<Book> collection=new HashSet<>();
        collection.add(new Book("xxx1",23));
        collection.add(new Book("xxx2",23));
        collection.add(new Book("xxx3",23));
        Iterator<Book> iterator=collection.iterator();
        iterator.forEachRemaining(book -> {
            //do someThing
            //collection.remove(book);//此处不能删除元素，否则报错
            System.out.println(book);
        });
    }

    /**
     * 使用forEach()方法遍历集合的方法略，注意方法中也不能修改集合中的元素，此处说的修改是指新增元素或者删除元素
     */

    /**
     * predicate操作集合
     */
    @Test
    public void testPredicate(){
        Collection<Book> collection=new HashSet<>();
        collection.add(new Book("xxx1",23));
        collection.add(new Book("xxx2",33));
        collection.add(new Book("xxx3",43));
        collection.add(new Book("xxx4",53));
        collection.add(new Book("xxx5",63));

        collection.removeIf(book -> {
            return book.getPrice()>30;//删除集合中price大于30的元素
        });

        System.out.println(collection);
    }


    /**
     * 通过pridicate抽象出一个通用的集合过滤统计器,利用命令模式
     */
    public static int callAll(Collection collection, Predicate filter){
        int total=0;

        for (Object o:collection){
            if (filter.test(o)){
                total++;
            }
        }

        return total;
    }

    /**
     * 测试抽象集合统计器
     */
    @Test
    public void testCallAll(){
        Collection<Book> collection=new HashSet<>();
        collection.add(new Book("xxx1",23));
        collection.add(new Book("xxx2",33));
        collection.add(new Book("xxx3",43));
        collection.add(new Book("xxx4",53));
        collection.add(new Book("xxx5",63));

        System.out.println(callAll(collection,ele->{
            return ((Book)ele).getPrice()>30;
        }));
    }

    /**
     * 流式操作测试
     *
     */
    @Test
    public void testIntStream(){
        IntStream intStream=IntStream.builder()
                .add(3)
                .add(5)
                .add(3)
                .add(4)
                .add(6)
                .build();

        //如下的这些聚集方法都是末端方法，调用之后流就会关闭，所以每个打开的流只能调用一个末端方法
        //System.out.println(intStream.max().getAsInt());//获得int流中的最大值
        //System.out.println(intStream.min().getAsInt());//获得int流中的最小值
        //System.out.println(intStream.sum());//求和
        //OptionalDouble average = intStream.average();//求平均数
        //System.out.println(average.getAsDouble());

        //System.out.println(intStream.count());//计数
        //System.out.println(intStream.allMatch(ele->ele>4));//是否全部满足条件
        //System.out.println(intStream.anyMatch(ele->ele>4));//是否有一个满足条件
        intStream.map(ele -> ele * 2 - 1).forEach(System.out::println);//映射为另一个流

    }

    /**
     * 用流式操作取得book集合中价格最高的book
     */
    @Test
    public void testTopPrice(){
        Collection<Book> collection=new HashSet<>();
        collection.add(new Book("xxx5",23));
        collection.add(new Book("xxx4",33));
        collection.add(new Book("xxx3",43));
        collection.add(new Book("xxx2",53));
        collection.add(new Book("xxx1",63));
        //Optional<jdk.collection.Book> max = collection.stream().max(Comparator.comparingInt(jdk.collection.Book::getPrice)); //用价格比较
        //Optional<jdk.collection.Book> max = collection.stream().max(Comparator.comparing(p -> p.getName()));  //用name比较
        Optional<Book> max = collection.stream().max((p1,p2)->{
            //return p1.getName().compareTo(p2.getName());
            return p1.getPrice()-p2.getPrice();
        });
        //System.out.println(max.get());
        //boolean xxx6 = collection.stream().anyMatch(book -> book.getName().equals("xxx6"));
        Optional<Book> xxx6 = collection.stream().filter(book -> book.getName().equals("xxx5")).findAny();
        xxx6.orElseThrow(()->new RuntimeException("not found"));
        System.out.println(xxx6.get());
    }

}

@Data
class Book{

    public Book() {
    }

    public Book(String name, int price) {
        this.name = name;
        this.price = price;
    }

    String name;
    int price;
}
