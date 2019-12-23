package jdk;


import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

public class ConditionBreakTest {

    @Test
    public void test(){
        List<String> strings=new ArrayList<String>();
        strings.add("aaa");
        strings.add("bbb");
        strings.add("ccc");
        strings.add("ddd");
        strings.add("eee");
        strings.add("fff");
        for (String s:strings){
            System.out.println(s);
        }
    }

}
