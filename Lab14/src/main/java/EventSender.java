import com.sun.messaging.ConnectionConfiguration;
import com.sun.messaging.ConnectionFactory;

import javax.jms.*;

public class EventSender {
    public static void main(String[] args) {
        ConnectionFactory factory;
        factory = new ConnectionFactory();
        try (JMSContext context = factory.createContext("admin", "admin", JMSContext.CLIENT_ACKNOWLEDGE)) {
            factory.setProperty(ConnectionConfiguration.imqAddressList,
                    "mq://127.0.0.1:7676,mq://127.0.0.1:7676");
            Destination Queue = context.createTopic("PushCanal");
            JMSProducer producer = context.createProducer();

            TextMessage textMessage = context.createTextMessage();
            textMessage.setText("Remember guys, no horny");
            textMessage.setStringProperty("Symbol","A");

            producer.send(Queue,"Remember guys, no horny");
            System.out.println("OK");
        } catch (JMSException e) {
            System.out.println("Error: " + e.getMessage());
        }
    }
}
