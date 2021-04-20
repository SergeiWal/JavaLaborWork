package fit.bstu.Lab9;

import java.io.*;
import javax.servlet.ServletException;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import javax.servlet.RequestDispatcher;

@WebServlet(name = "getLoginPage", value = "/login_page")
public class GetLoginPage extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        RequestDispatcher requestDispatcher = req.getRequestDispatcher("Views/Login.jsp");
        requestDispatcher.forward(req, resp);
    }

}
