package jdk.json;

import com.alibaba.fastjson.parser.DefaultJSONParser;
import com.alibaba.fastjson.parser.deserializer.ObjectDeserializer;

import java.lang.reflect.Type;

/**
 * @author ：xxt
 * @date ：Created in 2019/8/20 15:16
 * @description：枚举类型的json自定义反序列化
 * @modified By：
 */

public class EnumValueDeserializer implements ObjectDeserializer {
    @Override
    public BaseEnum deserialze(DefaultJSONParser defaultJSONParser, Type type, Object o) {
        String str = defaultJSONParser.parseObject(String.class);
        Class cls = (Class) type;
        if (cls.isEnum()){
            if (BaseEnum.class.isAssignableFrom(cls)){//判断BaseEnum的类是否为cls的父类
                Object[] baseEnum=cls.getEnumConstants();
                for (Object obj:baseEnum){
                    BaseEnum aEnum=(BaseEnum)obj;
                    if (aEnum.value().equals(str)){
                        return aEnum;
                    }
                }
            }
        }
        return null;
    }

    @Override
    public int getFastMatchToken() {
        return 0;
    }
}
