package concurrent.producterconsumer;

/**
 * todo:
 *
 * @author jiawei.fjw 2015/2/16
 */
public interface Buffer {
    void send(String s);
    String receive();
}
