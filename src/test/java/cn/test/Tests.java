package cn.test;

import cn.mybatis.session.Configuration;
import cn.mybatis.session.SqlSession;
import cn.mybatis.session.SqlSessionFactory;
import cn.mybatis.session.SqlSessionFactoryBuilder;
import cn.test.entity.User;

import java.io.IOException;
import java.io.InputStream;

public class Tests {


  public static void main(String[] args) throws IOException {
    System.out.println("aaaa");
    String resource = "mybatis-config.xml";
    InputStream inputStream = Tests.class.getClassLoader().getResourceAsStream(resource);
    Configuration configuration =new Configuration();
    configuration.setInputStream(inputStream);

    SqlSessionFactory sqlSessionFactory = new SqlSessionFactoryBuilder().builder(configuration);
    SqlSession sqlSession = sqlSessionFactory.openSession();
    //Object o = sqlSession.selectOne("cn.test.mapper.UserMapper.s");
    UserMapper mapper = sqlSession.getMapper(UserMapper.class);
    User user = mapper.getUser(1);
    System.out.println(user.toString());

  }
}
