import com.sun.messaging.ConnectionFactory;
import com.sun.messaging.jms.JMSException;

import javax.jms.*;

import static com.sun.messaging.ConnectionConfiguration.imqAddressList;
import static com.sun.messaging.ConnectionConfiguration.imqConnectionFlowLimitEnabled;

public class ObjectReceiver implements MessageListener{
    ConnectionFactory factory = new com.sun.messaging.ConnectionFactory();
    JMSConsumer consumer;

    ObjectReceiver(){
        try ( JMSContext context = factory . createContext("admin","admin")){
            factory.setProperty( imqAddressList ,"mq://127.0.0.1:7676,mq://127.0.0.1:7676");
            Destination cardsQueue = context.createQueue("BankCard");
            consumer = context.createConsumer(cardsQueue);
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
            Card card = message.getBody(Card.class);
            System.out.println("Card: " + card.getNumber() + " " + card.getOwner());
        } catch (javax.jms.JMSException e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
        new ObjectReceiver();
    }
}


