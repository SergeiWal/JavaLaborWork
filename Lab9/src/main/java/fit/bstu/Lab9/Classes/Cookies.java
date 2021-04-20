package fit.bstu.Lab9.Classes;

import fit.bstu.Lab9.DB.User;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.PrintWriter;
import java.util.Date;


public class Cookies {

    public static int numb = 0;

    public static void setCookie(HttpServletResponse response, User user){

        Cookie c1 = new Cookie("DateAndTime", new Date().toString());
        Cookie c2 = new Cookie("Count", "" +  numb++);
        Cookie c3 = new Cookie("UserRole" , user.getRole());
        Cookie c4 = new Cookie("UserLogin", user.getLogin());

        response.addCookie(c1);
        response.addCookie(c2);
        response.addCookie(c3);
        response.addCookie(c4);
    }

    public  static void printCookies(HttpServletRequest request, PrintWriter out){
        Cookie[] cookies = request.getCookies();
        for(Cookie c: cookies){
            out.println("Cookie key: " + c.getName() + " Value: " +c.getValue());
        }
    }
}
