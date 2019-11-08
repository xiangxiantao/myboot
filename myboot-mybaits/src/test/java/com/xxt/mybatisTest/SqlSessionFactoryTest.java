package com.xxt.mybatisTest;

import com.xxt.mybatisDemo.dao.IStudentMapper;
import com.xxt.mybatisDemo.entity.Student;
import jdk.internal.util.xml.impl.Input;
import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;
import org.junit.Before;
import org.junit.Test;

import java.io.IOException;
import java.io.InputStream;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.*;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.atomic.AtomicInteger;

public class SqlSessionFactoryTest {

    private SqlSessionFactory sqlSessionFactory;

    @Before
    public void generitic() throws IOException {
        String xmlConfig="mybatis-cfg.xml";
        InputStream inputStream= Resources.getResourceAsStream(xmlConfig);
        SqlSessionFactoryBuilder sqlSessionFactoryBuilder=new SqlSessionFactoryBuilder();
        sqlSessionFactory = sqlSessionFactoryBuilder.build(inputStream);
    }

    /**
     * 1.单条记录查询，bean接收返回结果
     * 修改classId为int，当数据库查询结果class_id为null时，封装的bean中classId值为0
     * 修改classId为Interger，当数据库查询结果class_id为null时，封装的bean中classId值为null
     * 修改classId为String，当数据库查询结果class_id为null时，封装的bean中classId值为null
     *
     */
    @Test
    public void test1() throws IOException {
        SqlSession sqlSession = sqlSessionFactory.openSession();
        try {
            IStudentMapper studentMapper = sqlSession.getMapper(IStudentMapper.class);
            Student student = studentMapper.selectById1(1);
            System.out.println(student);
        }finally {
            sqlSession.close();
        }

    }

    /**
     * 2.单条记录查询，map接收返回结果
     */
    @Test
    public void test2(){
        SqlSession sqlSession = sqlSessionFactory.openSession();
        try {
            IStudentMapper studentMapper = sqlSession.getMapper(IStudentMapper.class);
            Map<String, String> studentMap = studentMapper.selectById2(1);
            System.out.println(studentMap);
        }finally {
            sqlSession.close();
        }
    }

    /**
     * 3.单条记录查询，使用Integer查询
     */
    @Test
    public void test3(){
        SqlSession sqlSession = sqlSessionFactory.openSession();
        try {
            IStudentMapper studentMapper = sqlSession.getMapper(IStudentMapper.class);
            Map<String, String> studentMap = studentMapper.selectById3(1);
            System.out.println(studentMap);
        }finally {
            sqlSession.close();
        }
    }

    /**
     * 传入多个参数的分类
     * 1.Map
     * 2.@Param
     * 3.Bean
     * 4.如果参数中既有list，又有String时，可以采用map封装，也可用@Param
     */

    /**
     * 4.测试传入单个list，用list接收返回结果集合
     */
    @Test
    public void test4(){
        SqlSession sqlSession = sqlSessionFactory.openSession();
        try {
            IStudentMapper studentMapper = sqlSession.getMapper(IStudentMapper.class);
            List<String> ids =new ArrayList<String>(Arrays.asList("1","2","3","4"));
            List<Student> students=studentMapper.selectByIds1(ids);
            System.out.println(students);
        }finally {
            sqlSession.close();
        }
    }

    /**
     * 5.测试传入Arrays.asList，用list接收返回结果集合
     */
    @Test
    public void test5(){
        SqlSession sqlSession = sqlSessionFactory.openSession();
        try {
            IStudentMapper studentMapper = sqlSession.getMapper(IStudentMapper.class);
            List<String> ids =Arrays.asList("1","2","3","4","5");
            List<Student> students=studentMapper.selectByIds1(ids);
            System.out.println(students);
        }finally {
            sqlSession.close();
        }
    }

    /**
     * 6.测试传入单个list，用map接收返回结果集合
     */
    @Test
    public void test6(){
        SqlSession sqlSession = sqlSessionFactory.openSession();
        try {
            IStudentMapper studentMapper = sqlSession.getMapper(IStudentMapper.class);
            List<String> ids =new ArrayList<String>(Arrays.asList("1","2","3","4"));
            Map<String,Student> students=studentMapper.selectByIds2(ids);
            System.out.println(students);
        }finally {
            sqlSession.close();
        }
    }

    /**
     * 7.测试传入list和一个String，用map接收返回结果集合
     */
    @Test
    public void test7(){
        SqlSession sqlSession = sqlSessionFactory.openSession();
        try {
            IStudentMapper studentMapper = sqlSession.getMapper(IStudentMapper.class);
            List<String> ids =new ArrayList<String>(Arrays.asList("1","2","3","4","5"));
            String classId="1";
            Map<String,Student> students=studentMapper.selectByIds3(classId,ids);
            System.out.println(students);
        }finally {
            sqlSession.close();
        }
    }

    /**
     * 8.测试用map封装list和一个String，用map接收返回结果集合
     */
    @Test
    public void test8(){
        SqlSession sqlSession = sqlSessionFactory.openSession();
        try {
            IStudentMapper studentMapper = sqlSession.getMapper(IStudentMapper.class);
            List<String> ids =new ArrayList<String>(Arrays.asList("1","2","3","4","5"));
            String classId="1";
            Map<String,Object> params=new HashMap<String, Object>();
            params.put("classId",classId);
            params.put("ids",ids);
            Map<String,Student> students=studentMapper.selectByIds4(params);
            System.out.println(students);
        }finally {
            sqlSession.close();
        }
    }

    /**
     * 9.测试查询日期字段
     *      1.通过String接收数据库的timestamp字段返回结果：2019-09-30 14:57:49.0
     *      2.通过Date接收数据库的timestamp字段返回结果：返回一个Date实例，可通过dateFormatter转为字符串
     *      3.通过LocalDateTime接收数据库的timestamp字段返回结果：返回一个LocalDateTime实例，可通过student.getCreateTime().format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss"))转为字符串
     */
    @Test
    public void test9(){
        SqlSession sqlSession = sqlSessionFactory.openSession();
        try {
            IStudentMapper studentMapper = sqlSession.getMapper(IStudentMapper.class);
            Student student = studentMapper.selectById1(1);
            System.out.println(student);
            System.out.println(student.getCreateTime().format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss")));
        }finally {
            sqlSession.close();
        }
    }

    /**
     * 10.测试日期：通过String类型查询，入参格式：2019-09-30 14:57:49  能够查出正确数据
     *
     */
    @Test
    public void test10(){
        SqlSession sqlSession = sqlSessionFactory.openSession();
        try {
            IStudentMapper studentMapper = sqlSession.getMapper(IStudentMapper.class);
            List<Student> students = studentMapper.selectByTime1("2019-09-30 14:57:49");
            System.out.println(students.get(0).getCreateTime().format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss")));
        }finally {
            sqlSession.close();
        }
    }

    /**
     * 11.测试日期：通过Date类型查询，入参格式： Date date=sdf.parse("2019-09-30 14:57:49")  能够查出正确数据
     *
     */
    @Test
    public void test11(){
        SqlSession sqlSession = sqlSessionFactory.openSession();
        try {
            IStudentMapper studentMapper = sqlSession.getMapper(IStudentMapper.class);
            SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
            Date date=sdf.parse("2019-09-30 14:57:49");
            List<Student> students = studentMapper.selectByTime2(date);
            System.out.println(students.get(0).getCreateTime().format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss")));
        } catch (ParseException e) {
            e.printStackTrace();
        } finally {
            sqlSession.close();
        }
    }

    /**
     * 12.测试日期：通过LocalDateTime类型查询，入参格式：LocalDateTime localDateTime=LocalDateTime.parse("2019-09-30 14:57:49",DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss")) 能够查出正确数据
     *
     */
    @Test
    public void test12(){
        SqlSession sqlSession = sqlSessionFactory.openSession();
        try {
            IStudentMapper studentMapper = sqlSession.getMapper(IStudentMapper.class);
            LocalDateTime localDateTime=LocalDateTime.parse("2019-09-30 14:57:49",DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss"));
            List<Student> students = studentMapper.selectByTime3(localDateTime);
            System.out.println(students.get(0).getCreateTime().format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss")));
        }finally {
            sqlSession.close();
        }
    }







}
