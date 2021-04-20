package fit.bstu.Lab9.Servlets;


import java.io.*;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import javax.servlet.RequestDispatcher;

@WebServlet(name = "firstServlet", value = "/first")
public class FirstServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String respString = resp.getHeader("Data");
        if(respString==null || respString.equals("")) {
            req.setAttribute("req",req);
            req.setAttribute("resp",resp);
            req.getRequestDispatcher("second").forward(req, resp);
        }
        else{
            resp.getWriter().println(respString);
        }
    }
}
