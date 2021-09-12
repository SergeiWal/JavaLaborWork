import com.sun.messaging.ConnectionFactory;
import com.sun.messaging.jms.JMSException;

import javax.jms.*;

import static com.sun.messaging.ConnectionConfiguration.imqAddressList;

public class ObjectREciveSync {
    public static void main(String[] args) {
        ConnectionFactory factory = new com.sun.messaging.ConnectionFactory();
        JMSConsumer consumer;

            try ( JMSContext context = factory . createContext("admin","admin")){
                factory.setProperty( imqAddressList ,"mq://127.0.0.1:7676,mq://127.0.0.1:7676");
                Destination cardsQueue = context.createQueue("BankCard");
                consumer = context.createConsumer(cardsQueue);
                Message msg  = consumer.receive();
                Card card = msg.getBody(Card.class);
                System.out.println("Card: " + card.getNumber() + " " + card.getOwner());
                System.out.println("Listening to back queue");
            }catch (JMSException e){
                System. out . println("Error: " + e.getMessage());
            } catch (javax.jms.JMSException e) {
                e.printStackTrace();
            }
    }
}
