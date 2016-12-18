package aliyunmsn;

import com.aliyun.mns.client.CloudPullTopic;
import com.aliyun.mns.client.CloudQueue;
import com.aliyun.mns.client.MNSClient;
import com.aliyun.mns.common.ClientException;
import com.aliyun.mns.common.ServiceException;
import com.aliyun.mns.model.*;

public class BroadcastConsumer {
    public static void main(String[] args) {
        MNSClient client = Constants.ACCOUNT.getMNSClient();

        // build consumer name list.
        QueueMeta queueMetaTemplate = new QueueMeta();
        queueMetaTemplate.setPollingWaitSeconds(30);
        try {
            String topicName = "demo-topic-";
            TopicMeta topicMeta = new TopicMeta();
            topicMeta.setTopicName(topicName);
            CloudPullTopic pullTopic = client.createPullTopic(topicMeta, Constants.CONSUMER_NAME_LIST, true, queueMetaTemplate);

            CloudQueue queueForConsumer1 = client.getQueueRef(Constants.CONSUMER_NAME_LIST.get(0));
            CloudQueue queueForConsumer2 = client.getQueueRef(Constants.CONSUMER_NAME_LIST.get(1));
            CloudQueue queueForConsumer3 = client.getQueueRef(Constants.CONSUMER_NAME_LIST.get(2));
            Message consumer1Msg = queueForConsumer1.popMessage(30);
            if(consumer1Msg != null)
            {
                System.out.println("consumer1 receive message:" + consumer1Msg.getMessageBodyAsRawString());
            }else{
                System.out.println("the queue is empty");
            }
            Message consumer2Msg = queueForConsumer2.popMessage(30);
            if(consumer2Msg != null)
            {
                System.out.println("consumer2 receive message:" + consumer2Msg.getMessageBodyAsRawString());
            }else{
                System.out.println("the queue is empty");
            }
            Message consumer31Msg = queueForConsumer3.popMessage(30);
            if(consumer31Msg != null)
            {
                System.out.println("consumer3.1 receive message:" + consumer31Msg.getMessageBodyAsRawString());
            }else{
                System.out.println("the queue is empty");
            }

            Message consumer32Msg = queueForConsumer3.popMessage(30);
            if(consumer32Msg != null)
            {
                System.out.println("consumer3.2 receive message:" + consumer32Msg.getMessageBodyAsRawString());
            }else{
                System.out.println("the queue is empty");
            }
            // delete the fullTopic.
            pullTopic.delete();
        }catch(ClientException ce)
        {
            System.out.println("Something wrong with the network connection between client and MNS service."
                    + "Please check your network and DNS availablity.");
            ce.printStackTrace();
        }
        catch(ServiceException se)
        {
            /*you can get more MNS service error code in following link.
              https://help.aliyun.com/document_detail/mns/api_reference/error_code/error_code.html?spm=5176.docmns/api_reference/error_code/error_response
            */
            se.printStackTrace();
        }
        client.close();
    }
}
