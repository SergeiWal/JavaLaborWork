package fit.bstu.Lab9.Servlets;

import java.io.*;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import javax.servlet.RequestDispatcher;
import java.io.IOException;

@WebServlet(name = "secondServlet", value = "/second")
class SecondServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        HttpServletRequest firstReq = (HttpServletRequest) req.getAttribute("req");
        HttpServletResponse firstRes = (HttpServletResponse) req.getAttribute("resp");

        String respString= null;

        if(firstReq!=null && firstRes!=null){
            respString= "QueryString: "+firstReq.getRequestURI() + "<br/>RespString: " +
                firstRes.getHeaderNames();
        }

        resp.setContentType("text/html");
        resp.setHeader("Data",respString);
        req.getRequestDispatcher("first").forward(req,resp);
    }
}
