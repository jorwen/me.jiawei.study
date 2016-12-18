package concurrent.multiquery;


import java.sql.*;

/**
 * 连接池线程级别单例模式
 *
 * @author jw.fang
 * @version 1.0
 */
public class ConnectFactory
{
    //线程级共享的
    private static final ThreadLocal<Connection> connect = new ThreadLocal<Connection>();
    //持有的本身的实例
    private static final ConnectFactory single = new ConnectFactory();

    //私有的构造器
    private ConnectFactory()
    {
    }

    //返回本身持有的实例
    public static ConnectFactory getInstance()
    {
        return ConnectFactory.single;
    }

    public Connection currentConnect()
    {
        Connection conn = connect.get();
        if (conn == null)
        {
            try
            {
                Class.forName("oracle.jdbc.OracleDriver");
                conn = DriverManager.getConnection("jdbc:oracle:thin:@192.168.1.199:1521:sinitek", "spiritweb", "sa");
            }
            catch (Exception e)
            {
                throw new RuntimeException("创建链接失败", e);
            }
        }
        return conn;
    }
}