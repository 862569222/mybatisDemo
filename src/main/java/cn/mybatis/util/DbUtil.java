package cn.mybatis.util;

import java.sql.*;

public class DbUtil {
  // 适用于mysql和ora等数据库
  // 因为这些基本不变，且到项目处都要连接，所以static final。
  // 常量大写。且只需要修改这里，省去了其他地方的修改。
  private static final String PATH = "com.mysql.jdbc.Driver";// 导入驱动相对路径
  private static final String URL = "jdbc:mysql://localhost:3306/test?serverTimezone=GMT&useUnicode=true&characterEncoding=UTF-8&zeroDateTimeBehavior=convertToNull";// 定位数据库
  private static final String NAME = "root";// 数据库账号
  private static final String pwd = "";// 密码

  // 定义全局变量，方便后面关闭资源。
  private static Connection conn = null;
  private static PreparedStatement pst = null;
  private static ResultSet rs = null;

  public static Connection getConn() throws SQLException, ClassNotFoundException {

    Class.forName(PATH);
    return DriverManager.getConnection(URL, NAME, pwd);
  }
  /*public static void main(String[] args) {
    List<User> list = new ArrayList<User>();//暂时可以略过此句。
    // 1加载驱动
    try {
      Class.forName(PATH);

      // 2获取连接

      conn = DriverManager.getConnection(URL, NAME, pwd);

      // 判断数据库是否连接成功，这个判断只是方便自己。有无皆可。

      if (conn != null) {
        System.out.println("连接成功");

      } else {
        System.out.println("连接失败");
      }

      // 3创建statement 执行数据库语句，第一不预处理，第二步才是正式。执行的语句可以修改.
      // 不过为了防止SQl注入。骗取登录，所以最好创建它的子类PreparedStatement

      pst = conn.prepareStatement("SELECT * FROM users");// 预处理

      rs = pst.executeQuery();// 这里才是执行，获得数据。

      // 遍历处理结果集，用while 方法next方法,相当于指针依次下移，获得每一行表的数据

      while (rs.next()) {// 两种get,一种是根据下标 从1开始每一列，
        // 第一个是根据列名查找。根据表的列数据类型使用不同的个体方法
        rs.getInt(1);
        rs.getString(2);
        rs.getString(3);
      }

      *//*
       * 每一个数据库表都是实体，最好的方法是创建对照的实体类。 将获得的每一行数据储存到实体类里面再添加到集合中去，避免被覆盖。
       * 然后遍历集合，这样有利于多行数据的输出. 如下； while (rs.next()) {
       *
       * User user=new User(); user.setId(rs.getInt(1));
       * user.setName(rs.getString(2)); user.setPwd(rs.getString(3));
       * list.add(user);
       *
       * }
       *//*

    } catch (ClassNotFoundException e) {
      // 两个catch 一个不能少，一个是驱动的异常，一个是数据库的异常。
      e.printStackTrace();
    } catch (SQLException e) {
      // TODO Auto-generated catch block
      e.printStackTrace();
    } finally {

      // 5关闭资源.一定要关闭资源。数据库的每一个连接都占据服务器资源。
      // 我们写代码的时候感觉不到，
      // 但是一旦服务器运行个三五天，连接就会不断地增加，最终导致资源不足，
      // 服务器将自动关机来强行关闭连接。
      // 这算不算一种黑客手段，嘿嘿.软件影响硬件。
      // 而且这里面，不仅这里，全部过程都不要抛出异常，而是要捕捉处理异常，不然也会导致资源浪费。
      //即使前面抛出异常，程序中断，也会执行关闭资源，而不影响浪费。

      try {// 这里最好要先判断是否为空，不为空了，才去关闭。
        // 而且要从小到大的关闭，顺序不能乱
        if (rs != null)
          rs.close();
        if (pst != null)
          pst.close();
        if (conn != null)
          conn.close();
      } catch (SQLException e) {
        // TODO Auto-generated catch block
        e.printStackTrace();
      }

    }

  }*/


}
