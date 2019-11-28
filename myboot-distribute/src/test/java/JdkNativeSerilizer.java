import java.io.*;

/**
 * JDK原生序列化方式
 */
public class JdkNativeSerilizer implements ISerialiser {
    @Override
    public <T> byte[] serilizer(T obj) {
        ByteArrayOutputStream byteArrayOutputStream = null;
        ObjectOutputStream objectOutputStream = null;
        try {
            byteArrayOutputStream=new ByteArrayOutputStream();
            objectOutputStream=new ObjectOutputStream(byteArrayOutputStream);
            objectOutputStream.writeObject(obj);
            byte[] bytes = byteArrayOutputStream.toByteArray();
            return bytes;
        } catch (IOException e) {
            e.printStackTrace();
            return null;
        }finally {
            try {
                byteArrayOutputStream.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
            try {
                objectOutputStream.close();
            } catch (IOException e) {
                e.printStackTrace();
            }

        }
    }

    @Override
    public Object deSerilizer(byte[] bytes) {
        ByteArrayInputStream byteArrayInputStream=null;
        ObjectInputStream objectInputStream=null;
        try {
            byteArrayInputStream=new ByteArrayInputStream(bytes);
            objectInputStream=new ObjectInputStream(byteArrayInputStream);
            Object obj=objectInputStream.readObject();
            return obj;
        } catch (IOException | ClassNotFoundException e) {
            e.printStackTrace();
            return null;
        }finally {
            try {
                byteArrayInputStream.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
            try {
                objectInputStream.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
}
