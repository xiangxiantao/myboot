package jdk.json;

import com.alibaba.fastjson.JSONObject;
import com.alibaba.fastjson.TypeReference;
import com.alibaba.fastjson.annotation.JSONField;
import com.alibaba.fastjson.serializer.SerializerFeature;
import lombok.Data;
import org.junit.Test;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

/**
 * @author ：xxt
 * @date ：Created in 2019/8/20 11:23
 * @description：
 * @modified By：
 */

public class FastJsonTest {

    /**
     * map转为json字符串
     */
    @Test
    public void testMapToJson(){
        Map<String,Object> map=new HashMap<String, Object>();
        map.put("key1","value1");
        map.put("key2",2);
        map.put("key3",new SimpleBean("name1","12"));

        Map<String,String> p=new HashMap<>();
        p.put("param1","vvv");
        map.put("key4",p);
        String string = JSONObject.toJSONString(map);
        System.out.println(string);
    }

    /**
     * json字符串转为map
     */
    @Test
    public void testJsonStringToMap(){
        String jsonStr="{\"key1\":\"value1\",\"key2\":2,\"key3\":{\"age\":\"12\",\"name\":\"name1\"},\"key4\":{\"param1\":\"vvv\"}}";
        Map map = JSONObject.parseObject(jsonStr, Map.class);  //map中存放的元素为JSONObject类型
        System.out.println(map);
        Map<String ,String > map1=(Map<String, String>) map.get("key4");//JSONObject类型可以强转为map类型
        Map<String ,String > map2=(Map<String, String>) map.get("key3");
        //SimpleBean simpleBean =(SimpleBean) map.get("key3");
        //System.out.println(simpleBean.getName());//JSONObject类型不可以强转为bean类型
        System.out.println(map1);
        System.out.println(map2);
    }

    @Test
    public void testStringToJsonObj(){
        String jsonStr="{\"key1\":\"value1\",\"key2\":2,\"key3\":{\"age\":\"12\",\"name\":\"name1\"},\"key4\":{\"param1\":\"vvv\"}}";
        Object parse = JSONObject.parse(jsonStr);
    }


    /**
     * 实体类转为json字符串
     */
    @Test
    public void testBeanToJsonStr(){
        SimpleBean simpleBean=new SimpleBean("name1","age1");
        String string = JSONObject.toJSONString(simpleBean);
        System.out.println(string);

        BodyA bodyA=new BodyA();
        bodyA.setCode("001");
        bodyA.setMsg("mmmmm");
        BassRes<BodyA> bodyABassRes=new BassRes<>();
        bodyABassRes.setBody(bodyA);
        bodyABassRes.setBean(simpleBean);

        String s=JSONObject.toJSONString(bodyABassRes);
        System.out.println(s);
    }

    /**
     *json字符串转为实体类
     */
    @Test
    public void testJsonStrToBean(){
        String s="{\"a\":\"age1\",\"n\":\"name1\"}";
        SimpleBean simpleBean = JSONObject.parseObject(s, SimpleBean.class);
        System.out.println(simpleBean);
    }

    /**
     * json字符串转为带泛型的实体类
     */
    @Test
    public void testJsonStrToBeanWithT(){
        String s="{\"bean\":{\"a\":\"age1\",\"n\":\"name1\"},\"body\":{\"code\":\"001\",\"msg\":\"mmmmm\"}}";
        BassRes<BodyA> bodyABassRes = JSONObject.parseObject(s, new TypeReference<BassRes<BodyA>>() {});
        //BassRes bodyABassRes = JSONObject.parseObject(s, BassRes.class);
        System.out.println(bodyABassRes);
    }


    /**
     *实体类带date类型时的格式设置
     */
    @Test
    public void testBeanToStringWithDate(){
        SimpleBean simpleBean=new SimpleBean();
        simpleBean.setName("aaaaa");
        simpleBean.setAge("12");
        //simpleBean.setDate(new Date());
        simpleBean.setAnEnum(TestEnum.EnumA);//默认会将其序列化为枚举的名的字符串
        String string = JSONObject.toJSONString(simpleBean/*,
                SerializerFeature.WriteMapNullValue*/);
        System.out.println(string);
    }

    /**
     *实体类带枚举类型时的格式设置
     */
    @Test
    public void testBeanToStringWithEnum(){
        //String s="{\"_date\":\"2019-08-20 14:56:15\",\"a\":\"12\",\"anEnum\":1,\"n\":\"aaaaa\"}";//使用枚举在枚举类中定义的顺序进行反序列化，可行
        //String s="{\"_date\":\"2019-08-20 15:02:14\",\"a\":\"12\",\"anEnum\":\"EnumA\",\"n\":\"aaaaa\"}";//使用枚举的名字进行反序列化，可行
        String s="{\"_date\":null,\"a\":\"12\",\"anEnum\":\"B\",\"integer\":null,\"n\":\"aaaaa\",\"num\":0}";
        SimpleBean simpleBean = JSONObject.parseObject(s, SimpleBean.class);
        System.out.println(simpleBean);
    }


    @Test
    public void testExtendsJson(){
        Simple simple=new Simple();
        simple.setCode("");
        simple.setMsg("msg");
        simple.setBody("body");
        System.out.println(JSONObject.toJSONString(simple));
        System.out.println(simple.toString());
    }




}

@Data
class SimpleBean{


    public SimpleBean(String name, String age) {
        this.name = name;
        this.age = age;
    }

    public SimpleBean() {
    }

    @JSONField(name = "n")
    String name;

    int num;

    Integer integer;

    @JSONField(name = "a")
    String age;

    @JSONField(name = "_date",format = "yyyy-MM-dd HH:mm:ss")
    Date date;

    @JSONField(deserializeUsing = EnumValueDeserializer.class , serializeUsing = EnumCodeSerializer.class)
    TestEnum anEnum;
}

@Data
class BodyA{
    String msg;
    String code;
}

@Data
class BodyB{
    String message;
    String success;
}

@Data
class BassRes<T>{
    SimpleBean bean;
    T body;
}


interface BaseEnum{
    String value();
}

enum TestEnum implements BaseEnum{
    EnumA("00","A"),
    EnumB("01","B"),
    EnumC("02","C");

    TestEnum(String code,String value){
        this.value=value;
        this.code=code;
    }

    String code;
    String value;

    @Override
    public String value() {
        return value;
    }
}

@Data
class Base{
    public String code;

    public String msg;
}

@Data
class Simple extends Base{
    public String body;


}
