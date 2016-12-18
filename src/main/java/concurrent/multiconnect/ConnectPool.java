package concurrent.multiconnect;

import java.sql.*;
import java.util.concurrent.*;

/**
 * 连接池（内部类实现延迟加载的单例模式）
 */
public class ConnectPool
{
    private BlockingQueue<Connection> connectService;
    //线程总数
    final public static int THREAD_COUNT = 10;

    public static class Holder
    {
        static ConnectPool instance = new ConnectPool();
    }

    public static ConnectPool getInstance()
    {
        return Holder.instance;
    }

    private ConnectPool()
    {
        resetPool();
    }

    /**
     * 重置线程池
     */
    public void resetPool()
    {
        //固定长连接数的线程池
        connectService = new LinkedBlockingQueue<Connection>(THREAD_COUNT);
        for (int i = 0; i < THREAD_COUNT; i++)
        {
            try
            {
                Class.forName("oracle.jdbc.OracleDriver");
                Connection connection = DriverManager.getConnection("jdbc:oracle:thin:@192.168.1.199:1521:sinitek", "spiritweb", "sa");
                connectService.add(connection);
            }
            catch (Exception e)
            {
                throw new RuntimeException("创建链接失败", e);
            }
        }
    }

    /**
     * 获得连接池，如果连接池用尽会阻塞等待别人释放一个
     *
     * @return
     * @throws InterruptedException
     * @throws ExecutionException
     */
    public Connection getConnection() throws InterruptedException, ExecutionException
    {
        return connectService.take();
    }

    /**
     * 释放连接池
     *
     * @param connection
     */
    public void release(final Connection connection)
    {
        connectService.add(connection);
    }
}