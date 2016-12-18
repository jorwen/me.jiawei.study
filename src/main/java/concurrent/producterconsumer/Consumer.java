package concurrent.producterconsumer;

import java.io.*;

/**
 * todo:
 *
 * @author jiawei.fjw 2015/2/16
 */
public class Consumer extends Thread{
    private BufferedWriter writer;
    Buffer buffer;

    public Consumer(Buffer buffer){
        writer = new BufferedWriter(new OutputStreamWriter(System.out));
        this.buffer = buffer;
    }

    public String receive(){
        return buffer.receive();
    }
    public void run(){
        while (true){
            String line = receive();
            if(line != null){
                try{
                    writer.write(line);
                    writer.flush();
                }catch (IOException e){
                    e.printStackTrace();
                }
            }
        }
    }
}
