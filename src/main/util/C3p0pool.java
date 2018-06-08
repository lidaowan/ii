import com.mchange.v2.c3p0.ComboPooledDataSource;

import java.sql.Connection;

public class C3p0pool {

    private static ComboPooledDataSource ds;

    //静态初始化块进行初始化
    static{
        try {
            ds = new ComboPooledDataSource();//创建连接池实例

            ds.setDriverClass("com.mysql.jdbc.Driver");//设置连接池连接数据库所需的驱动

            ds.setJdbcUrl("jdbc:mysql://localhost:3306/jianli?useSSL=false");//设置连接数据库的URL

            ds.setUser("root");//设置连接数据库的用户名

            ds.setPassword("root");//设置连接数据库的密码

            ds.setMaxPoolSize(40);//设置连接池的最大连接数

            ds.setMinPoolSize(2);//设置连接池的最小连接数

            ds.setInitialPoolSize(10);//设置连接池的初始连接数

            ds.setMaxStatements(100);//设置连接池的缓存Statement的最大数
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    //从连接池返回一个连接
    public static Connection getConnection(){
        Connection conn = null;
        try {
            conn = ds.getConnection();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return conn;
    }

}
