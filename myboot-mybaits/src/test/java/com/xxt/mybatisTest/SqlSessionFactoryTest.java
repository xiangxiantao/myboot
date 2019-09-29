package com.xxt.mybatisTest;

import com.xxt.mybatisDemo.dao.IStudentMapper;
import com.xxt.mybatisDemo.entity.Student;
import jdk.internal.util.xml.impl.Input;
import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.io.IOException;
import java.io.InputStream;
import java.util.Map;

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
     * 单条记录查询，bean接收返回结果
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
     * 单条记录查询，map接收返回结果
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
     * 单条记录查询，使用Integer查询
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



}
