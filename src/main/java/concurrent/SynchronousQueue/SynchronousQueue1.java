package concurrent.SynchronousQueue;

import java.util.Random;
import java.util.concurrent.*;

/**
 * 一种阻塞队列，其中每个 put 必须等待一个 take，反之亦然。同步队列没有任何内部容量，甚至连一个队列的容量都没有。
 * 不能在同步队列上进行 peek，因为仅在试图要取得元素时，该元素才存在；除非另一个线程试图移除某个元素，
 * 否则也不能（使用任何方法）添加元素；也不能迭代队列，因为其中没有元素可用于迭代。队列的头 是尝试添加到队列中的首个已排队线程元素；
 * 如果没有已排队线程，则不添加元素并且头为 null。对于其他 Collection 方法（例如 contains），SynchronousQueue 作为一个空集合。此队列不允许 null 元素。
 * <p/>
 * 同步队列类似于 CSP 和 Ada 中使用的 rendezvous 信道。它非常适合于传递性设计，在这种设计中，在一个线程中运行的对象要将某些信息、
 * 事件或任务传递给在另一个线程中运行的对象，它就必须与该对象同步。
 * <p/>
 * 对于正在等待的生产者和使用者线程而言，此类支持可选的公平排序策略。默认情况下不保证这种排序。
 * 但是，使用公平设置为 true 所构造的队列可保证线程以 FIFO 的顺序进行访问。公平通常会降低吞吐量，但是可以减小可变性并避免得不到服务。
 */
public class SynchronousQueue1
{
    public static void main(String[] args) throws InterruptedException
    {
        final SynchronousQueue<Integer> queue = new SynchronousQueue<Integer>();
        new Thread(new Runnable()
        {
            @Override
            public void run()
            {
                try
                {
                    System.out.println("等待数据传入...");
                    System.out.println("##获取的数据为:" + queue.take());
                }
                catch (InterruptedException e)
                {
                    e.printStackTrace();
                }
            }
        }).start();
        TimeUnit.SECONDS.sleep(3);//三秒之后传入数据
        System.out.println("准备传入数据..");
        queue.offer(new Random().nextInt(1000));
    }
}
