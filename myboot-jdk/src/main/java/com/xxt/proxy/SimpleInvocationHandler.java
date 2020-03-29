package com.xxt.proxy;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;

public class SimpleInvocationHandler implements InvocationHandler {

    private Simple simple;

    public SimpleInvocationHandler(Simple simple) {
        this.simple = simple;
    }

    @Override
    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
        System.out.println("before handle");
        Object invoke = method.invoke(simple);
        invoke+="被代理了";
        return invoke;
    }

    public static void main(String[] args) {
        Simple simple=new SimpleImpl();
        SimpleInvocationHandler simpleInvocationHandler=new SimpleInvocationHandler(simple);
        Simple simpleProxy = (Simple) Proxy.newProxyInstance(simple.getClass().getClassLoader(),
                simple.getClass().getInterfaces(),
                simpleInvocationHandler);
        String s = simpleProxy.otherJob();
        System.out.println(s);

    }
}
