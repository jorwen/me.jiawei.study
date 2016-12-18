package aliyunmsn;

import com.aliyun.mns.client.CloudAccount;

import java.util.Vector;

public class Constants {
    public static final CloudAccount ACCOUNT = new CloudAccount("bbiH1cusPKa7yCdG", "iLjgXiduGSNmlZkma45VzmosZfbbOf", "http://1680426316651926.mns.cn-hangzhou.aliyuncs.com/");

    public static final String QUEUE_NAME = "demo-mq";

    public static final String TOPIC_NAME = "demo-topic";

    public static final Vector<String> CONSUMER_NAME_LIST = new Vector<String>();

    static{
        String consumerName1 = "consumer001";
        String consumerName2 = "consumer002";
        String consumerName3 = "consumer003";
        CONSUMER_NAME_LIST.add(consumerName1);
        CONSUMER_NAME_LIST.add(consumerName2);
        CONSUMER_NAME_LIST.add(consumerName3);
    }
}