package cn.mybatis.resultSet;

import cn.mybatis.binding.MapperMethod;
import org.apache.ibatis.reflection.factory.DefaultObjectFactory;

import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class ResultSetHandler {

  public <T> T handler(PreparedStatement preparedStatement, MapperMethod mapperMethod) throws SQLException, IllegalAccessException, NoSuchMethodException, InvocationTargetException {
    Object object = new DefaultObjectFactory().create(mapperMethod.getType());

    ResultSet resultSet = preparedStatement.getResultSet();
    if(resultSet.next()){
      int i=0;
      for (Field field :object.getClass().getDeclaredFields()){
        setValue(object,field,resultSet,i);
      }
    }

    return (T) object;
  }

  private void setValue(Object object, Field field, ResultSet resultSet, int i) throws InvocationTargetException, IllegalAccessException, NoSuchMethodException, SQLException {
    Method method = object.getClass().getMethod("set" + upperCapitial(field.getName()), field.getType());
    method.invoke(object,getResult(field,resultSet));
  }

  private Object getResult(Field field, ResultSet resultSet) throws SQLException {
    Class<?> type = field.getType();
    if(Integer.class == type){
      return resultSet.getInt(field.getName());
    }
    if(String.class == type){
      return resultSet.getString(field.getName());
    }
    if(Long.class == type){
      return resultSet.getLong(field.getName());
    }
    if(int.class == type){
      return resultSet.getInt(field.getName());
    }
    return resultSet.getString(field.getName());
  }

  private String upperCapitial(String name) {
    String first = name.substring(0, 1);
    String tail = name.substring(1);
    return first.toUpperCase()+tail;
  }


}
