package cn.mybatis.executor;

import cn.mybatis.binding.MapperMethod;
import cn.mybatis.session.Configuration;
import cn.mybatis.statement.StatementHandler;

import java.lang.reflect.InvocationTargetException;
import java.sql.SQLException;

public class SimpleExecutor implements Executor {

  private Configuration configuration;

  public SimpleExecutor(Configuration configuration) {
    this.configuration = configuration;
  }

  @Override
  public <E> E query(MapperMethod mapperMethod, Object statement) throws ClassNotFoundException, SQLException, InvocationTargetException, NoSuchMethodException, IllegalAccessException {

    StatementHandler statementHandler = new StatementHandler();


    System.out.println("执行查询方法");
    return statementHandler.query(mapperMethod,statement);
  }
}
