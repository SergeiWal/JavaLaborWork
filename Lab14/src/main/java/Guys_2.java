import com.sun.messaging.ConnectionFactory;
import com.sun.messaging.jms.JMSException;

import javax.jms.*;

import static com.sun.messaging.ConnectionConfiguration.imqAddressList;

public class Guys_2 implements MessageListener {

    ConnectionFactory factory = new com.sun.messaging.ConnectionFactory();
    JMSConsumer consumer;

    Guys_2(){
        try ( JMSContext context = factory . createContext("admin","admin")){
            context.setClientID("admin123");
            factory.setProperty( imqAddressList ,"mq://127.0.0.1:7676,mq://127.0.0.1:7676");
            Destination topic = context.createTopic("PushCanal");
            consumer = context.createDurableConsumer((Topic) topic,"SecurityCenter");
            consumer.setMessageListener(this);
            System.out.println("Listening to back queue");
            Thread.sleep(100000);
        } catch (InterruptedException e)
        {
            System. out . println("Error: " + e.getMessage());
        }catch (JMSException e){
            System. out . println("Error: " + e.getMessage());
        } catch (javax.jms.JMSException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void onMessage(Message message) {
        try {
            System.out.println("Guys_1 message listener received: " + message.getBody(String.class));
        } catch (javax.jms.JMSException e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
        new Guys_2();
    }
}
