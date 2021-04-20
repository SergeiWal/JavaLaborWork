package fit.bstu.Lab9;

import java.io.*;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import javax.servlet.RequestDispatcher;

public class GetHelloServlet extends  HttpServlet{
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        redirect(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        redirect(req, resp);
    }

    private void redirect(HttpServletRequest req, HttpServletResponse resp)throws ServletException, IOException{
        ServletContext context = getServletContext();
        context.setAttribute("req",req);
        context.setAttribute("resp",resp);
        resp.sendRedirect(req.getContextPath() + "/hello-servlet");
    }
}
