package fit.bstu.Lab9.Servlets;

import fit.bstu.Lab9.DB.Uniwer;
import fit.bstu.Lab9.DB.UniwerService;
import org.apache.log4j.Logger;
import sun.rmi.runtime.Log;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;

@WebServlet(name = "DelUniServlet", value = "/del")
public class DelUniServlet extends HttpServlet {

    static final Logger LOGGER = Logger.getLogger(DelUniServlet.class);

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        delUni(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        delUni(req, resp);
    }

    private void delUni(HttpServletRequest request, HttpServletResponse response){
        PrintWriter out = null;
        try {
            out = response.getWriter();
            String url = "jdbc:mysql://localhost/lab9?serverTimezone=Europe/Moscow&useSSL=false";
            String root = "root";
            String password = "123hateGnom546";
            Class.forName("com.mysql.cj.jdbc.Driver").getDeclaredConstructor().newInstance();

            try(Connection connection = DriverManager.getConnection(url, root, password)){

                int id = Integer.parseInt(request.getParameter("id"));

                UniwerService uniwerService = new UniwerService();

                Uniwer uniwer = new Uniwer();
                uniwer.setId(id);

                uniwerService.remove(connection, uniwer);
                LOGGER.info("Delete uniwer from db success ...");
                request.getRequestDispatcher("/home").forward(request, response);

            }
            catch (Exception e){
                out.println(e.getMessage());
                out.println(e.getStackTrace()[0]);
                LOGGER.info("Delete uniwer failed ...");
            }

        }catch (Exception exc){
            out.println(exc.getMessage());
            out.println(exc.getStackTrace());
            LOGGER.info("Delete uniwer failed ...");
        }finally {
            out.close();
        }
    }

    private void failedProcess(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setAttribute("error","Add new uni failed !!!");
        request.getRequestDispatcher("/home").forward(request, response);
    }
}
