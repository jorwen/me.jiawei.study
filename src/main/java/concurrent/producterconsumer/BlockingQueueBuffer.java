package concurrent.producterconsumer;

import java.util.concurrent.*;

/**
 * 最佳实践
 * @author jiawei.fjw 2015/2/16
 */
public class BlockingQueueBuffer implements Buffer {
    BlockingQueue<String> queue;

    public BlockingQueueBuffer(int capacity) {
        queue = new ArrayBlockingQueue<String>(capacity);
    }

    @Override
    public synchronized void send(String s) {
        try {
            queue.put(s);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    @Override
    public synchronized String receive() {
        try {
            return queue.take();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        return null;
    }
}
