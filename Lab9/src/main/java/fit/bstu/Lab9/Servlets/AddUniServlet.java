package fit.bstu.Lab9.Servlets;

import fit.bstu.Lab9.DB.Uniwer;
import fit.bstu.Lab9.DB.UniwerService;
import org.apache.log4j.Logger;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.util.List;

@WebServlet(name = "UniAddServlet", value = "/add")
public class AddUniServlet extends HttpServlet {

    static final Logger LOGGER = Logger.getLogger(AddUniServlet.class);

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        AddUni(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        AddUni(req, resp);
    }

    private void AddUni(HttpServletRequest request, HttpServletResponse response){

        PrintWriter out = null;
        try {
            out = response.getWriter();
            String url = "jdbc:mysql://localhost/lab9?serverTimezone=Europe/Moscow&useSSL=false";
            String root = "root";
            String password = "123hateGnom546";
            Class.forName("com.mysql.cj.jdbc.Driver").getDeclaredConstructor().newInstance();

            try(Connection connection = DriverManager.getConnection(url, root, password)){


                String name = request.getParameter("name");
                String city = request.getParameter("city");
                String country = request.getParameter("country");

                if(name != null && name.length()>0 && city!=null && city.length()>0 &&
                country!=null && country.length()>0){
                    UniwerService uniwerService = new UniwerService();

                    Uniwer uniwer = new Uniwer();
                    uniwer.setUni_name(name);
                    uniwer.setCity(city);
                    uniwer.setCountry(country);

                    uniwerService.add(connection, uniwer);
                    LOGGER.info("Add new uniwer in DB ...");
                    request.getRequestDispatcher("/home").forward(request, response);
                }else {
                    failedProcess(request, response);
                    LOGGER.info("Add new uniwer failed...");
                }

            }
            catch (Exception e){
                out.println(e.getMessage());
                out.println(e.getStackTrace()[0]);
                LOGGER.info("Add new uniwer failed...");
            }

        }catch (Exception exc){
            out.println(exc.getMessage());
            out.println(exc.getStackTrace());
            LOGGER.info("Add new uniwer failed...");
        }finally {
            out.close();
        }

    }

    private void failedProcess(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setAttribute("error","Add new uni failed !!!");
        request.getRequestDispatcher("/home").forward(request, response);
    }
}
