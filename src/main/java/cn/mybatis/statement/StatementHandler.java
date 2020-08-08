package cn.mybatis.statement;

import cn.mybatis.binding.MapperMethod;
import cn.mybatis.resultSet.ResultSetHandler;
import cn.mybatis.util.DbUtil;

import java.lang.reflect.InvocationTargetException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class StatementHandler {

  public <T> T query(MapperMethod mapperMethod, Object param) throws SQLException, ClassNotFoundException, IllegalAccessException, NoSuchMethodException, InvocationTargetException {

    Connection connection = DbUtil.getConn();
    PreparedStatement preparedStatement = connection.prepareStatement(String.format(mapperMethod.getSql()), Integer.parseInt(String.valueOf(param)));

    preparedStatement.execute();

    return new ResultSetHandler().handler(preparedStatement,mapperMethod);
  }


}
