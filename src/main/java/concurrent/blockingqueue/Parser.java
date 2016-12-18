package concurrent.blockingqueue;

import java.util.concurrent.BlockingQueue;

/**
 * 生产者，专门把页面放入队列
 */
public class Parser implements Runnable {
    private BlockingQueue<Page> queue;

    public Parser(BlockingQueue<Page> queue) {
        this.queue = queue;
    }

    public void run() {
        try {
            Iterable<Page> pages = new Pages(100000, "enwiki.xml").getPages();
            for (Page page : pages)
                queue.put(page);
        }
        catch (Exception e) {
            e.printStackTrace();
        }
    }
}