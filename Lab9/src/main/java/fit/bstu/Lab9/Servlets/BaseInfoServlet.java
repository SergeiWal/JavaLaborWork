package fit.bstu.Lab9.Servlets;

import java.io.*;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Enumeration;
import javax.servlet.ServletException;
import javax.servlet.annotation.*;
import javax.servlet.http.*;

@WebServlet(name = "baseInfoServlet", value = "/base_info")
public class BaseInfoServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {
        PrintBaseInfo(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {
        PrintBaseInfo(req, resp);
    }

    private void PrintBaseInfo(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException
    {
        try {
            resp.setContentType("text/html; charset=Windows-1251");
            PrintWriter out = resp.getWriter();

            Date date = new Date();
            SimpleDateFormat formatForDateNow = new SimpleDateFormat("E yyyy.MM.dd ' and time' hh:mm:ss a zzz");
            String dateString = "Current date " + formatForDateNow.format(date);

            out.println(dateString);
            out.println("Protocol version: " + req.getProtocol() + "<br/>");
            out.println("Sender IP: " + req.getRemoteAddr() + ", Hostname:" + req.getRemoteHost()+ "<br/>");
            out.println("Method:" + req.getMethod()+ "<br/>");

            Enumeration e = req.getHeaderNames();
            out.println("HEADER info:<br/>");
                while(e.hasMoreElements()){
                    String name = (String)e.nextElement();
                    String value = req.getHeader(name);
                    out.println("<br/>" + name + "=" + value + "<br/>");
                }

            out.close();
        }
        catch (IOException ioExp)
        {
            System.err.print("IOErrorR");
        }
    }
}
