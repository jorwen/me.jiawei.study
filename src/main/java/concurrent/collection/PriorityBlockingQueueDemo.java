package concurrent.collection;

import java.util.concurrent.PriorityBlockingQueue;

/**
 * 用优先级对使用阻塞线程安全的列表排序
 * 你想要添加到PriorityBlockingQueue中的所有元素必须实现Comparable接口。这个接口有一个compareTo()方法，它接收同样类型的对象，
 * 你有两个比较的对象：一个是执行这个方法的对象，另一个是作为参数接收的对象。如果本地对象小于参数，则该方法返回小于0的数值。
 * 如果本地对象大于参数，则该方法返回大于0的数值。如果本地对象等于参数，则该方法返回等于0的数值。
 *
 * @author jw.fang
 * @version 1.0
 */
public class PriorityBlockingQueueDemo
{
    public static void main(String[] args)
    {
        PriorityBlockingQueue<Event> queue = new PriorityBlockingQueue<Event>();
        Thread taskThreads[] = new Thread[5];
        for (int i = 0; i < taskThreads.length; i++)
        {
            Task task = new Task(i, queue);
            taskThreads[i] = new Thread(task);
        }
        for (Thread taskThread : taskThreads)
        {
            taskThread.start();
        }

        for (Thread taskThread : taskThreads)
        {
            try
            {
                taskThread.join();
            }
            catch (InterruptedException e)
            {
                e.printStackTrace();
            }
        }
        System.out.printf("Main: Queue Size: %d\n", queue.size());
        for (int i = 0; i < taskThreads.length * 1000; i++)
        {
            Event event = queue.poll();
            System.out.printf("Thread %s: Priority %d\n", event.getThread(), event.getPriority());
        }
        System.out.printf("Main: Queue Size: %d\n", queue.size());
        System.out.printf("Main: End of the program\n");
    }

    static class Event implements Comparable<Event>
    {
        private int thread;
        private int priority;

        public Event(int thread, int priority)
        {
            this.thread = thread;
            this.priority = priority;
        }

        public int getThread()
        {
            return thread;
        }

        public int getPriority()
        {
            return priority;
        }

        @Override
        public int compareTo(Event e)
        {
            if (this.priority > e.getPriority())
            {
                return -1;
            }
            else if (this.priority < e.getPriority())
            {
                return 1;
            }
            else
            {
                return 0;
            }
        }
    }

    static class Task implements Runnable
    {
        private int id;
        private PriorityBlockingQueue<Event> queue;

        public Task(int id, PriorityBlockingQueue<Event> queue)
        {
            this.id = id;
            this.queue = queue;
        }

        @Override
        public void run()
        {
            for (int i = 0; i < 1000; i++)
            {
                Event event = new Event(id, i);
                queue.add(event);
            }
        }
    }
}



