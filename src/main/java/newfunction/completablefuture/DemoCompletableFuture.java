package newfunction.completablefuture;

import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;

/**
 * @author jiawei.fjw
 * @version 1.0
 * @since 2021/8/27
 */
public class DemoCompletableFuture {
    public static CompletableFuture<Integer> compute() {
        final CompletableFuture<Integer> future = new CompletableFuture<>();
        return future;
    }
    /**
     * 主动完成计算
     */
    public static void main(String[] args) throws Exception {
        final CompletableFuture<Integer> f = compute();
        class Client extends Thread {
            CompletableFuture<Integer> f;
            Client(String threadName, CompletableFuture<Integer> f) {
                super(threadName);
                this.f = f;
            }
            @Override
            public void run() {
                try {
                    System.out.println(this.getName() + ": " + f.get());
                } catch (InterruptedException | ExecutionException e) {
                    e.printStackTrace();
                }
            }
        }
        new Client("Client1", f).start();
        new Client("Client2", f).start();
        System.out.println("waiting");
        //通过下面的代码完成一个计算，触发客户端的等待
        f.complete(100);
        //也可以抛出一个异常，而不是一个成功的计算结果
        //f.completeExceptionally(new Exception());
        System.in.read();
    }
}
