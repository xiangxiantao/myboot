package jdk.json;

import com.alibaba.fastjson.serializer.JSONSerializer;
import com.alibaba.fastjson.serializer.ObjectSerializer;
import org.junit.Test;

import java.io.IOException;
import java.lang.reflect.Type;

/**
 * @author ：xxt
 * @date ：Created in 2019/8/20 15:40
 * @description： 序列化时输出枚举的cede
 * @modified By：
 */

public class EnumCodeSerializer implements ObjectSerializer {
    @Override
    public void write(JSONSerializer jsonSerializer, Object object, Object fieldName, Type fieldType, int features) throws IOException {
        TestEnum testEnum=(TestEnum)object;
        jsonSerializer.write(testEnum.code);

    }
}
