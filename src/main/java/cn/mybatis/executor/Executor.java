package cn.mybatis.executor;

import cn.mybatis.binding.MapperMethod;

import java.lang.reflect.InvocationTargetException;
import java.sql.SQLException;

public interface Executor {

  <E> E query(MapperMethod mapperMethod, Object statement) throws ClassNotFoundException, SQLException, InvocationTargetException, NoSuchMethodException, IllegalAccessException;

}
