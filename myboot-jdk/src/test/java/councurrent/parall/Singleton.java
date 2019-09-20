package councurrent.parall;

/**
 * @author ：xxt
 * @date ：Created in 2019/9/4 15:53
 * @description：
 * @modified By：
 */

public class Singleton {

    private Singleton(){
        System.out.println("singleton is instanced");
    }

    //将静态内部类声明为private，可以控制该内部类的初始化触发时机一定时可控的
    //从而控制单例的实例化时机，因为类初始化只有一次，所以这种单例是线程安全的又是实例化时机可控的
    private static class SingletonHolder{
        private static Singleton instance=new Singleton();
    }

    public static Singleton getInstance(){
        return SingletonHolder.instance;
    }

    public static void main(String[] args) {
        Singleton instance = Singleton.getInstance();

    }

}
