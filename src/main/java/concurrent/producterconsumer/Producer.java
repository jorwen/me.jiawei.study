package concurrent.producterconsumer;

import java.io.*;

/**
 * todo:
 *
 * @author jiawei.fjw 2015/2/16
 */
public class Producer extends Thread{
    private BufferedReader reader;
    Buffer buffer;
    public Producer(Buffer buffer){
        reader = new BufferedReader(new InputStreamReader(System.in));
        this.buffer = buffer;
    }

    void send(String s){
        buffer.send(s);
    }

    public void run(){
        try{
            while(true){
                String line = reader.readLine();
                send(line);
            }
        }catch (IOException e){
            e.printStackTrace();
        }
    }
}
