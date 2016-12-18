package concurrent.blockingqueue;

import java.util.HashMap;
import java.util.concurrent.ArrayBlockingQueue;

/**
 * 新统计字数类，生产者消费者模式
 */
public class NewWordCount {
    public static void main(String[] args) throws Exception {
        ArrayBlockingQueue<Page> queue = new ArrayBlockingQueue<>(100);
        HashMap<String, Integer> counts = new HashMap<>();
        Thread counter = new Thread(new Counter(queue, counts));
        Thread parser = new Thread(new Parser(queue));
        counter.start();
        parser.start();
        parser.join();
        queue.put(new PoisonPill()); //终止标记
        counter.join();
    }
}
