import javax.jms.JMSException;
import javax.jms.Message;
import javax.jms.MessageListener;
import javax.jms.ObjectMessage;

public class WebListener implements MessageListener {
    @Override
    public void onMessage(Message message) {
        if(message instanceof ObjectMessage){
            ObjectMessage objectMessage = (ObjectMessage) message;
            String str = "";
            try{
                str = (String)objectMessage.getObject();
            } catch (JMSException e) {
                e.printStackTrace();
            }
            System.out.println("Message: " + str);
        }
    }
}
