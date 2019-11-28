import entity.User;
import org.junit.Test;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;

public class SerilizerTest {

    @Test
    public void testJdkSerializer(){
        ISerialiser serialiser=new JdkNativeSerilizer();
        User user=new User();
        user.setName("xxt");
        user.setAgr(19);

        byte[] bytes = serialiser.serilizer(user);
        FileOutputStream fileOutputStream=null;
        try {
            fileOutputStream=new FileOutputStream("user");
            fileOutputStream.write(bytes);
            fileOutputStream.flush();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }finally {
            try {
                fileOutputStream.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
    

    @Test
    public void testJdkDeSerializer(){
        FileInputStream fileInputStream=null;
        try {
            ISerialiser serialiser=new JdkNativeSerilizer();
            fileInputStream=new FileInputStream("user");
            int length=fileInputStream.available();
            byte[] bytes=new byte[length];
            fileInputStream.read(bytes);
            User user = (User)serialiser.deSerilizer(bytes);
            System.out.println(user);

        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }finally {
            try {
                fileInputStream.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }


}
