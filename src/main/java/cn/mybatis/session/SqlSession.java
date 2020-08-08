package cn.mybatis.session;

import cn.mybatis.binding.MapperMethod;

import java.lang.reflect.InvocationTargetException;
import java.sql.SQLException;

public interface SqlSession {

  <T> T selectOne(MapperMethod mapperMethod, Object statement) throws ClassNotFoundException, SQLException, IllegalAccessException, NoSuchMethodException, InvocationTargetException;

  <T> T getMapper(Class<T> type);


}
