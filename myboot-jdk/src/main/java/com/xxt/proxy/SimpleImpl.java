package com.xxt.proxy;

public class SimpleImpl implements Simple {
    @Override
    public String mainJob() {
        return "原方法";
    }

    @Override
    public String otherJob() {
        return "另一个方法";
    }
}
