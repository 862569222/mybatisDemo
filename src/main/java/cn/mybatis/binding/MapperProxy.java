package cn.mybatis.binding;

import cn.mybatis.session.SqlSession;
import cn.mybatis.session.defaults.DefaultSqlSession;


import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;

public class MapperProxy<T> implements InvocationHandler {
  private  final DefaultSqlSession sqlSession;
  private  final Class<T> mapperInterFace;

  public MapperProxy(DefaultSqlSession sqlSession, Class<T> mapperInterFace) {
    this.sqlSession = sqlSession;
    this.mapperInterFace = mapperInterFace;
  }

  @Override
  public Object invoke(Object o, Method method, Object[] args) throws Throwable {
    MapperMethod mapperMethod = sqlSession.getConfiguration().getMapperRegistry().getKnownMappers().
      get(method.getDeclaringClass().getName()+"."+method.getName());
    if (mapperMethod !=null){
      return sqlSession.selectOne(mapperMethod,String.valueOf(args[0]));
    }
    return method.invoke(method,args);
  }
}
