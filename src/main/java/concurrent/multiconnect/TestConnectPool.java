package concurrent.multiconnect;

import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.datasource.SingleConnectionDataSource;

import java.sql.Connection;
import java.util.*;

public class TestConnectPool
{
    private int callTimes = 100;

    private long maxCallTime = 0;

    private long minCallTime = 0;

    private long avgCallTime = 0;

    private long totalTime = 0;

    public TestConnectPool(int callTimes)
    {
        this.callTimes = callTimes;
    }

    public void call()
    {
        List<Long> resultList = new ArrayList<Long>(callTimes); //结果数组全部初始化好

        long allStartTime = System.currentTimeMillis();

        for (int i = 0; i < callTimes; i++)
        {
            long startTime = System.currentTimeMillis();
            callOne();
            long endTime = System.currentTimeMillis();
            resultList.add(endTime - startTime);
        }

        long allEndTime = System.currentTimeMillis();

        // 开始计算平均事务时间，最短时间和最长时间
        long minTime = Long.MAX_VALUE;
        long maxTime = 0;
        long allIn = 0;
        for (long timeStamp : resultList)
        {
            allIn += timeStamp;
            if (timeStamp < minTime)
            {
                minTime = timeStamp;
            }
            if (timeStamp > maxTime)
            {
                maxTime = timeStamp;
            }
        }
        this.maxCallTime = maxTime;
        this.minCallTime = minTime;
        this.avgCallTime = allIn / callTimes;
        this.totalTime = allEndTime - allStartTime;
        System.out.println("===================线程[" + Thread.currentThread().getId() + "]=====================\n" +
                "总响应时间[" + this.totalTime + "]\n" +
                "平均响应时间：[" + this.avgCallTime + "]\n" +
                "最大响应时间[" + this.maxCallTime + "]\n" +
                "最小响应时间[" + this.minCallTime + "]\n" +
                "===================线程[" + Thread.currentThread().getId() + "]=====================");

    }

    public int getCallTimes()
    {
        return callTimes;
    }

    public void setCallTimes(int callTimes)
    {
        this.callTimes = callTimes;
    }

    public long getMaxCallTime()
    {
        return maxCallTime;
    }

    public void setMaxCallTime(long maxCallTime)
    {
        this.maxCallTime = maxCallTime;
    }

    public long getMinCallTime()
    {
        return minCallTime;
    }

    public void setMinCallTime(long minCallTime)
    {
        this.minCallTime = minCallTime;
    }

    public long getAvgCallTime()
    {
        return avgCallTime;
    }

    public void setAvgCallTime(long avgCallTime)
    {
        this.avgCallTime = avgCallTime;
    }

    public long getTotalTime()
    {
        return totalTime;
    }

    public void setTotalTime(long totalTime)
    {
        this.totalTime = totalTime;
    }

    public static void main(String[] args)
    {
        TestConnectPool.callOne();
    }

    private static void callOne()
    {
        ConnectPool connectPool = ConnectPool.getInstance();
        Connection connection = null;
        try
        {
            connection = connectPool.getConnection();
            JdbcTemplate template = new JdbcTemplate(new SingleConnectionDataSource(connection, false));
            int result = template.queryForObject("select count(*) from SPRT_SYSMENU", Integer.class);

        }
        catch (Exception e)
        {
            e.printStackTrace();
        }
        finally
        {
            //用完必须还回去
            if (connection != null) connectPool.release(connection);
        }
    }
}
