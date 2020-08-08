package cn.mybatis.session.defaults;

import cn.mybatis.executor.Executor;
import cn.mybatis.executor.SimpleExecutor;
import cn.mybatis.session.Configuration;
import cn.mybatis.session.SqlSession;
import cn.mybatis.session.SqlSessionFactory;

public class DefaultSqlSessionFactory implements SqlSessionFactory {

  private final Configuration configuration;

  public DefaultSqlSessionFactory(Configuration configuration) {
    this.configuration = configuration;
  }



  @Override
  public SqlSession openSession() {

    return new DefaultSqlSession(configuration, new SimpleExecutor(configuration));
  }
}
