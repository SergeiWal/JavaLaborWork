package fit.bstu.Lab9.Servlets;

import org.apache.log4j.Logger;

import javax.ejb.MessageDriven;
import javax.jms.JMSException;
import javax.jms.Message;
import javax.jms.MessageListener;
import javax.jms.ObjectMessage;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;


public class WebListener implements MessageListener {


    private static final Logger LOGGER = Logger.getLogger(WebListener.class);
    private HttpServletResponse response;


    public WebListener(HttpServletResponse response){
        this.response = response;
    }

    @Override
    public void onMessage(Message message) {
        if(message instanceof ObjectMessage){
            ObjectMessage objectMessage = (ObjectMessage) message;
            String str = "";
            try{
                str = (String)objectMessage.getObject();
                LOGGER.info("Message: " + str);
                System.out.println("Message: " + str);
            } catch (JMSException e) {
                e.printStackTrace();
            }
        }
    }
}
