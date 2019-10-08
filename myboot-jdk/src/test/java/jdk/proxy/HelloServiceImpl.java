package jdk.proxy;

public class HelloServiceImpl implements HelloService {
    @Override
    public String sayHello(String name) {
        return name+"say hello";
    }
}
