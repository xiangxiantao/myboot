package com.xxt.proxy;

public class Outer {

    private String name;

    public class Inner{
        String innerName;
    }

    public static void main(String[] args) {
        Inner inner = new Outer().new Inner();
    }
}
