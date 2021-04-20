package fit.bstu.Lab9;

import java.io.*;
import javax.servlet.ServletException;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import javax.servlet.RequestDispatcher;

@WebServlet(name = "geteRegistrePage", value = "/registre-page")
public class GetRegistrePage extends HttpServlet{
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        RequestDispatcher requestDispatcher = req.getRequestDispatcher("Views/Registration.jsp");
        requestDispatcher.forward(req, resp);
    }
}
