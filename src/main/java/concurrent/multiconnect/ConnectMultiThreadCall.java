package concurrent.multiconnect;

import org.apache.commons.collections.list.SynchronizedList;

import java.util.*;

public class ConnectMultiThreadCall
{
    public synchronized void run() throws Exception
        {
            int threadCount = 1;
            final int callTimes = 1;

            final List<Thread> runThreadList = new ArrayList<Thread>(threadCount);
            final List<Long> resultList = SynchronizedList.decorate(new ArrayList<Long>(threadCount));

            for (int i = 0; i < threadCount; i++)
            {
                Thread runThread = new Thread(new Runnable()
                {
                    @Override
                    public void run()
                    {
                        TestConnectPool caller = new TestConnectPool(callTimes);
                        caller.call();
                        resultList.add(caller.getTotalTime());
                    }
                });
                runThreadList.add(runThread);
            }

            long allStartTime = System.currentTimeMillis();
            for (Thread runThread : runThreadList)
            {
                runThread.start();
            }

            while (resultList.size() < threadCount)
            {
                //            System.out.println( "已完成：[" + resultList.size()  + "]线程" );
                Thread.sleep(1000);
            }
            //        System.out.println( "已完成：[" + resultList.size()  + "]线程" );
            long allEndTime = System.currentTimeMillis();

            // 统计结果
            System.out.println("全部执行时间(ms)：[" + (allEndTime - allStartTime) + "]");
            System.out.println("执行效率(次/s)：[" + (threadCount * callTimes * 1000 / (allEndTime - allStartTime)) + "]");
        }

        public static void main(String[] args) throws Exception
        {
            ConnectMultiThreadCall caller = new ConnectMultiThreadCall();
            caller.run();
        }
}
