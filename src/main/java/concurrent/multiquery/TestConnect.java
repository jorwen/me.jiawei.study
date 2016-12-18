package concurrent.multiquery;

import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.datasource.SingleConnectionDataSource;

import java.sql.*;
import java.util.*;
import java.util.concurrent.*;

public class TestConnect
{
    public static class Task implements Callable<List>
    {
        private String sql;

        public Task(String sql)
        {
            this.sql = sql;
        }

        @Override
        public List call() throws Exception
        {
            long start = System.currentTimeMillis();
            Connection connection = ConnectFactory.getInstance().currentConnect();
            Statement sm = connection.createStatement();
            JdbcTemplate template = new JdbcTemplate(new SingleConnectionDataSource(connection, false));
            List result = template.queryForList(sql);
            System.out.println("Thread:"+Thread.currentThread().getId()+",耗时:"+(System.currentTimeMillis()-start)+", call:"+sql);
            return result;
        }
    }

    public static void main(String[] args) throws InterruptedException, ExecutionException
    {
        System.out.println("主Thread:"+Thread.currentThread().getId());

        ExecutorService pool = Executors.newFixedThreadPool(3);
        CompletionService<List> completionServcie = new ExecutorCompletionService<List>(pool);

        for(int i = 1; i <=10; i++)
        {
            completionServcie.submit(new Task("select * from SPRT_SYSMENU where objid="+i));
        }

        for (int i = 1; i <= 10; i++)
        {
            List result = completionServcie.take().get();
            if(result.isEmpty())
            {
                System.out.println("Thread:"+Thread.currentThread().getId()+", result:null");
            }
            else
            {
                Map map = (Map)result.get(0);
                System.out.println("Thread:"+Thread.currentThread().getId()+", result:"+map.get("name"));
            }
        }
    }
}
