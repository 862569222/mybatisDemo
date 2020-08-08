package cn.mybatis.session;

import cn.mybatis.binding.MapperMethod;
import cn.mybatis.binding.MapperRegistry;
import com.sun.org.apache.xml.internal.resolver.readers.SAXCatalogReader;
import org.dom4j.Document;
import org.dom4j.DocumentException;
import org.dom4j.Element;
import org.dom4j.io.SAXReader;

import java.io.IOException;
import java.io.InputStream;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 将xml文件解析
 */
public class Configuration {

  private InputStream inputStream;

  private  MapperRegistry mapperRegistry = new MapperRegistry();


   public void loadConfiguration() throws IOException {
    try {
      Document document = new SAXReader().read(inputStream);
      Element rootElement = document.getRootElement();
      List<Element> mappers = rootElement.element("mappers").elements("mapper");
      for(Element mapper :mappers){
        //获取不同方式映射的mapper 注册
        if(mapper.attribute("resource") !=null){
          mapperRegistry.setKnownMappers(loadXMLConfiguration(mapper.attribute("resource").getText()));
        }

        if(mapper.attribute("class") !=null){

        }
      }
    } catch (DocumentException e) {
      e.printStackTrace();
    }
  }

  private Map<String, MapperMethod> loadXMLConfiguration(String resource) throws IOException {
     Map<String,MapperMethod> map = new HashMap<>();
    InputStream resourceAsStream = null;
    try {
      resourceAsStream = this.getClass().getClassLoader().getResourceAsStream(resource);


      Document document = new SAXReader().read(resourceAsStream,"utf-8");
      Element rootElement = document.getRootElement();
      if(rootElement.getName().equalsIgnoreCase("mapper")){
        String namespace = rootElement.attribute("namespace").getText();
        for (Element select :(List<Element>)rootElement.elements("select")){
          MapperMethod mapperMethod = new MapperMethod();
          mapperMethod.setSql(select.getText().trim());
          mapperMethod.setType(Class.forName(select.attribute("resultType").getText()));
          map.put(namespace+"."+select.attribute("id").getText(),mapperMethod);
        }
      }
    } catch (Exception e) {
      e.printStackTrace();
    }finally {
      resourceAsStream.close();
    }

    return map;
  }




  public InputStream getInputStream() {
    return inputStream;
  }

  public void setInputStream(InputStream inputStream) {
    this.inputStream = inputStream;
  }

  public MapperRegistry getMapperRegistry() {
    return mapperRegistry;
  }

  public void setMapperRegistry(MapperRegistry mapperRegistry) {
    this.mapperRegistry = mapperRegistry;
  }
}
