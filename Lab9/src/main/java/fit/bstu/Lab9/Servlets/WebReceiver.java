package fit.bstu.Lab9.Servlets;

import org.apache.log4j.Logger;

import javax.jms.*;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet(name = "ReceiverServlet", value = "/receiver")
public class WebReceiver extends HttpServlet {


    private static Logger LOGGER = Logger.getLogger(WebReceiver.class);
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        Processe(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        Processe(req, resp);
    }

    private  void Processe(HttpServletRequest req, HttpServletResponse resp) {
        try {
            InitialContext context = new InitialContext();
            QueueConnectionFactory factory = (QueueConnectionFactory) context.lookup("jmcConnectionFactory");
            QueueConnection connection = factory.createQueueConnection();
            QueueSession session = connection.createQueueSession(false, Session.AUTO_ACKNOWLEDGE);
            Queue queue = (Queue) context.lookup("jms/PTPQueue");
            QueueReceiver receiver = session.createReceiver(queue);

            WebListener listener = new WebListener(resp);
            receiver.setMessageListener(listener);

            while (true) {
                Thread.sleep(1000);
            }
        } catch (NamingException e) {
            e.printStackTrace();
        } catch (JMSException e) {
            e.printStackTrace();
        } catch (InterruptedException exception) {
            exception.printStackTrace();
        }
    }
}
