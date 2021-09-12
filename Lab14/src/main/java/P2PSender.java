import com.sun.messaging.ConnectionConfiguration;

import javax.jms.*;
import com.sun.messaging.ConnectionFactory;


public class P2PSender {
    public static void main(String[] args) {
        ConnectionFactory factory;
        factory = new ConnectionFactory();
        try (JMSContext context = factory.createContext("admin", "admin", JMSContext.CLIENT_ACKNOWLEDGE)) {
            factory.setProperty(ConnectionConfiguration.imqAddressList,
                    "mq://127.0.0.1:7676,mq://127.0.0.1:7676");
            Destination Queue = context.createQueue("BankCard");

            Card card = new Card(1111,"Ivan Grishin",124124);
            ObjectMessage message = context.createObjectMessage(card);

            JMSProducer producer = context.createProducer();

            producer.send(Queue,message);
            System.out.println("OK");
        } catch (JMSException e) {
            System.out.println("Error: " + e.getMessage());
        }
    }
}
