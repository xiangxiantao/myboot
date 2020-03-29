package com.xxt.proxy;

import sun.misc.Launcher;

import java.net.URL;

public class MyInterImpl implements MyInter {
    @Override
    public void fun1() {
        System.out.println("implement fun1");
    }

    public static void main(String[] args) {
        URL[] urLs = Launcher.getBootstrapClassPath().getURLs();
        ClassLoader systemClassLoader = ClassLoader.getSystemClassLoader();
        for (URL url:urLs){
            System.out.println(url.toExternalForm());
        }
    }
}
