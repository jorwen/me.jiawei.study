package aliyunmsn;

import com.aliyun.mns.client.CloudPullTopic;
import com.aliyun.mns.client.MNSClient;
import com.aliyun.mns.common.ClientException;
import com.aliyun.mns.common.ServiceException;
import com.aliyun.mns.model.QueueMeta;
import com.aliyun.mns.model.RawTopicMessage;
import com.aliyun.mns.model.TopicMessage;
import com.aliyun.mns.model.TopicMeta;

public class BroadcastProducer {
    public static void main(String[] args) {
        MNSClient client = Constants.ACCOUNT.getMNSClient();

        // build consumer name list.
        QueueMeta queueMetaTemplate = new QueueMeta();
        queueMetaTemplate.setPollingWaitSeconds(30);
        try {
            //producer code:
            // create pull topic which will send message to 3 queues for consumer.
            TopicMeta topicMeta = new TopicMeta();
            topicMeta.setTopicName(Constants.TOPIC_NAME);
            CloudPullTopic pullTopic = client.createPullTopic(topicMeta, Constants.CONSUMER_NAME_LIST, true, queueMetaTemplate);
            //publish message and consume message.
            String messageBody = "hello the world jarvis";
            // if we sent raw message,then should use getMessageBodyAsRawString to parse the message body correctly.
            TopicMessage tMessage = new RawTopicMessage();
            tMessage.setBaseMessageBody(messageBody);
            pullTopic.publishMessage(tMessage);
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
