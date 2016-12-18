package concurrent.producterconsumer;

/**
 * 最愚蠢实现
 *
 * @author jiawei.fjw 2015/2/16
 */
public class PollingBuffer implements Buffer {
    String line = null;

    @Override
    public void send(String s) {
        while( line != null){
            pause(1);
        }
        line = s;
    }

    @Override
    public String receive() {
        while(line == null){
            pause(1);
        }
        String s = line;
        line = null;
        return s;
    }

    void pause(int ms){
        try{
            Thread.sleep(ms);
        }catch (InterruptedException e){
            e.printStackTrace();
        }
    }
}
