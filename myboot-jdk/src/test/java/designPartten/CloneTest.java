package designPartten;

import lombok.Data;

//原型模式
//浅拷贝，引用类型的数据只是新建了一个指向对象的引用，并没有复制对象 利用clone或者构造方法实现
//浅拷贝，引用类型的数据只是新建了一个指向对象的引用，并没有复制对象 clone或者序列化方法实现
public class CloneTest {
    public static void main(String[] args) {
        Temple temple=new Temple();
        temple.setAge(10);
        temple.setName("mydog");
        Module module=new Module();
        module.setModulName("head");
        temple.setModule(module);

        try {
            Temple temple1= (Temple) temple.clone();
            System.out.println(temple1.getModule().getModulName());
            System.out.println(temple1==temple);
            System.out.println(temple.getModule()==temple1.getModule());  //浅拷贝，引用类型的数据只是新建了一个指向对象的引用，并没有复制对象

            temple.getModule().setModulName("hand");
            System.out.println(temple1.getModule().getModulName());
            temple.setAge(100);
            temple.setName("mycat");
            System.out.println(temple1.getAge());
            System.out.println(temple1.getName());  //String是不可变类，对String值得改变会重新创建一个字符串对象而不会改变原来得字符串对象
        } catch (CloneNotSupportedException e) {
            e.printStackTrace();
        }

    }
}

@Data
class Temple implements Cloneable{

    private String name;
    private int age;
    private Module module;

    @Override
    protected Object clone() throws CloneNotSupportedException {
        return super.clone();
    }
}

@Data
class Module implements Cloneable{
    private String modulName;
}