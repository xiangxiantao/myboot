package com.xxt;

import com.xxt.bean.Man;
import com.xxt.bean.Person;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest()
public class SpringBootConfigurationTest {

    @Autowired
    Person person;

    @Autowired
    Man man;

    @Test
    public void testAutoConfiguration(){
        System.out.println(person);
    }


    @Test
    public void testValue(){
        System.out.println(man);
    }

}
