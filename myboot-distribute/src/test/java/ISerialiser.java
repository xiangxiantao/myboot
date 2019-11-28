/**
 * 序列化接口
 */
public interface ISerialiser {

    <T> byte[] serilizer(T obj);

    Object deSerilizer(byte[] bytes);

}
