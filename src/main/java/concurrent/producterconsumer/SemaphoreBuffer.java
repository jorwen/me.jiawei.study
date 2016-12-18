package concurrent.producterconsumer;

import java.util.concurrent.Semaphore;

/**
 *
 *
 * @author jiawei.fjw 2015/2/16
 */
public class SemaphoreBuffer implements Buffer {
    String line;
    Semaphore full,empty;

    public SemaphoreBuffer(){
        full = new Semaphore(1);
        empty = new Semaphore(1);
    }

    @Override
    public synchronized void send(String s) {
        try{
            full.acquireUninterruptibly();
            line = s;
        }finally {
            empty.release();
        }
    }

    @Override
    public synchronized String receive() {
        try{
            empty.acquireUninterruptibly();
            return line;
        }finally {
            full.release();
            line = null;
        }
    }
}
