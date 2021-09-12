import javax.jms.*;
import javax.naming.InitialContext;
import javax.naming.NamingException;

public class WebReceiver {
    public static void main(String[] args) {
        try {
            InitialContext context = new InitialContext();
            QueueConnectionFactory factory = (QueueConnectionFactory) context.lookup("jmcConnectionFactory");
            QueueConnection connection = factory.createQueueConnection();
            QueueSession session = connection.createQueueSession(false, Session.AUTO_ACKNOWLEDGE);
            Queue queue = (Queue) context.lookup("jms/PTPQueue");
            QueueReceiver receiver = session.createReceiver(queue);
            MessageConsumer consumer = session.createConsumer(queue);
            Message message = consumer.receive();
            if(message instanceof  ObjectMessage){
                ObjectMessage objectMessage = (ObjectMessage) message;
                String msg = (String) objectMessage.getObject();
                System.out.println("Message: " + msg);
            }
        } catch (NamingException e) {
            e.printStackTrace();
        } catch (JMSException e) {
            e.printStackTrace();
        }
    }
}