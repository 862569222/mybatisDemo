package cn.mybatis.session.defaults;

import cn.mybatis.binding.MapperMethod;
import cn.mybatis.binding.MapperProxy;
import cn.mybatis.executor.Executor;
import cn.mybatis.session.Configuration;
import cn.mybatis.session.SqlSession;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Proxy;
import java.sql.SQLException;

public class DefaultSqlSession implements SqlSession {

  private Configuration configuration;
  private Executor executor;

  public DefaultSqlSession(Configuration configuration, Executor executor) {
    this.configuration = configuration;
    this.executor = executor;
  }

  public <T> T getMapper(Class<T> type){

    return  (T)Proxy.newProxyInstance(type.getClassLoader(), new Class[] { type }, new MapperProxy<>(this,type));
  }

  @Override
  public <T> T selectOne(MapperMethod mapperMethod, Object statement) throws ClassNotFoundException, SQLException, IllegalAccessException, NoSuchMethodException, InvocationTargetException {
    System.out.println("aaaaaaaaaaaaaaaaaaaaaaaaaaaaaa");
    return executor.query(mapperMethod,statement);
  }

  public Configuration getConfiguration() {
    return configuration;
  }

  public void setConfiguration(Configuration configuration) {
    this.configuration = configuration;
  }

  public Executor getExecutor() {
    return executor;
  }

  public void setExecutor(Executor executor) {
    this.executor = executor;
  }
}
