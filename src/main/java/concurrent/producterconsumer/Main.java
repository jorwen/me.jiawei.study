package concurrent.producterconsumer;

/**
 * todo:
 *
 * @author jiawei.fjw 2015/2/16
 */
public class Main {
    public static void main(String[] args) {
        Buffer buffer = new PollingBuffer();//最差
//        Buffer buffer = new WaitNotifyBuffer(); //一般
//        Buffer buffer = new SemaphoreBuffer();//不错
//        Buffer buffer = new BlockingQueueBuffer();//最佳
        Producer producer = new Producer(buffer);
        Consumer consumer = new Consumer(buffer);

        producer.start();
        consumer.start();
    }
}
