package cn.mybatis.session;

import cn.mybatis.session.defaults.DefaultSqlSessionFactory;

import java.io.IOException;

public class SqlSessionFactoryBuilder {



  public SqlSessionFactory builder(Configuration configuration) throws IOException {

    configuration.loadConfiguration();
    return  new DefaultSqlSessionFactory(configuration);
  }
}
