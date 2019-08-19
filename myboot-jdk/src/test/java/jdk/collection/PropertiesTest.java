package jdk.collection;

import org.junit.Test;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Properties;

/**
 * properties相当于key和value都是string的map
 *
 */
public class PropertiesTest {

    @Test
    public void testPro() throws IOException {
        Properties properties=new Properties();

        properties.setProperty("key1","v1");
        properties.setProperty("key2","v1");
        properties.setProperty("key3","v3");
        properties.setProperty("key4","v5");
        properties.store(new FileOutputStream("a.ini"),"comment line");

        Properties properties1=new Properties();
        properties1.load(new FileInputStream("a.ini"));
        properties1.setProperty("gender","male");
        System.out.println(properties1);
    }

}
