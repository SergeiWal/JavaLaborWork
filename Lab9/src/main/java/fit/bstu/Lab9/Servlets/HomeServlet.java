package fit.bstu.Lab9.Servlets;

import fit.bstu.Lab9.Classes.Cookies;
import fit.bstu.Lab9.Classes.Hasher;
import fit.bstu.Lab9.DB.Uniwer;
import fit.bstu.Lab9.DB.UniwerService;
import fit.bstu.Lab9.DB.User;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.util.ArrayList;
import java.util.List;

@WebServlet(name = "HomeServlet", value = "/home")
public class HomeServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        getDataAboutUni(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        getDataAboutUni(req, resp);
    }

    private void getDataAboutUni(HttpServletRequest request, HttpServletResponse response){

        PrintWriter out = null;
        try {
            out = response.getWriter();
            String url = "jdbc:mysql://localhost/lab9?serverTimezone=Europe/Moscow&useSSL=false";
            String root = "root";
            String password = "123hateGnom546";
            Class.forName("com.mysql.cj.jdbc.Driver").getDeclaredConstructor().newInstance();

            try(Connection connection = DriverManager.getConnection(url, root, password)){

                UniwerService uniwerService = new UniwerService();
                List<Uniwer> uniwers = uniwerService.findAll(connection);

                request.setAttribute("uniwers", uniwers);
                request.getRequestDispatcher("Views/Home.jsp").forward(request, response);
            }
            catch (Exception e){
                out.println(e.getMessage());
                out.println(e.getStackTrace());
            }

        }catch (Exception exc){
            out.println(exc.getMessage());
            out.println(exc.getStackTrace());
        }finally {
            out.close();
        }
    }

}
