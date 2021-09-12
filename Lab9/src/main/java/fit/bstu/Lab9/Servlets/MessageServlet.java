package fit.bstu.Lab9.Servlets;

import com.sun.messaging.ConnectionFactory;
import com.sun.messaging.Destination;

import javax.annotation.Resource;
import javax.jms.*;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.jsp.JspContext;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

@WebServlet(name = "MessageServlet", value = "/message")
public class MessageServlet extends HttpServlet {

    /*
    @Resource(lookup = "jms/__defaultConnectionFactory")
    ConnectionFactory factory;

    @Resource(lookup = "jms/PTPQueue")
    Destination P2Pqueue;
*/

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        Processe(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        Processe(req, resp);
    }

    private void Processe(HttpServletRequest req, HttpServletResponse resp){

        try {
            InitialContext context = new InitialContext();
            QueueConnectionFactory factory =
                    (QueueConnectionFactory) context.lookup("jmcConnectionFactory");
            QueueConnection connection = factory.createQueueConnection();
            connection.start();
            QueueSession session = connection.createQueueSession(false, Session.AUTO_ACKNOWLEDGE);
            Queue queue = (Queue) context.lookup("jms/PTPQueue");
            QueueSender sender = session.createSender(queue);
            ObjectMessage message = session.createObjectMessage();

            message.setObject("Hello");
            sender.send(message);
            connection.close();
            resp.getWriter().println("OK");
        } catch (NamingException e) {
            e.printStackTrace();
        } catch (JMSException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
