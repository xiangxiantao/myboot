package com.xxt;

import org.junit.Test;

import java.math.BigDecimal;

public class BigTest {

    @Test
    public void test(){
        BigDecimal a=new BigDecimal("23.2");
        a=a.add(new BigDecimal("15.3"));
        System.out.println(a);
    }
}
