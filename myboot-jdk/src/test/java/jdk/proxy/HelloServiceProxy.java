package jdk.proxy;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;

public class HelloServiceProxy implements InvocationHandler {

    private Object target;

    public Object bind(Object target){
        this.target=target;
        return Proxy.newProxyInstance(target.getClass().getClassLoader(),target.getClass().getInterfaces(),this);
    }

    @Override
    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
        System.out.println("动态代理--开始执行");
        Object result=null;

        System.out.println("ready");
        result=method.invoke(target,args);
        System.out.println("收尾");

        return result;
    }

    public static void main(String[] args) {
        HelloServiceProxy helloServiceProxy=new HelloServiceProxy();
        HelloService helloService = (HelloService) helloServiceProxy.bind(new HelloServiceImpl());
        helloService.sayHello("xxt");
    }
}
