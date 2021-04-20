package fit.bstu.Lab9.Servlets;

import fit.bstu.Lab9.Classes.Hasher;
import fit.bstu.Lab9.DB.User;
import fit.bstu.Lab9.DB.UserService;

import java.io.*;
import java.sql.Connection;
import java.sql.DriverManager;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;

@WebServlet(name = "registrationServlet", value = "/registration")
public class RegistrationServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        try {
            Registration(req, resp);
        } catch (InterruptedException | ClassNotFoundException exception) {
            exception.printStackTrace();
        }
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        try {
            Registration(req, resp);
        } catch (InterruptedException | ClassNotFoundException exception) {
            exception.printStackTrace();
        }
    }

    private void Registration(HttpServletRequest req, HttpServletResponse resp)
            throws IOException, InterruptedException, ServletException, ClassNotFoundException {

        resp.setContentType("text/html; charset=Cp1251");
        String br = "<br/>";
        PrintWriter out = null;

        try{

            out = resp.getWriter();
            String url = "jdbc:mysql://localhost/lab9?serverTimezone=Europe/Moscow&useSSL=false";
            String root = "root";
            String pass = "123hateGnom546";
            Class.forName("com.mysql.cj.jdbc.Driver").getDeclaredConstructor().newInstance();

            try(Connection connection = DriverManager.getConnection(url, root, pass)){

               // out.println("Connect success ...");
                String name = req.getParameter("name");
                String login =  req.getParameter("login");
                String role = req.getParameter("role");
                String password = Hasher.getHash(req.getParameter("password"));
                //out.println("Hash success ...");
                String repeatPassword  =  Hasher.getHash(req.getParameter("repeat_password"));
                //out.println("Hash2 success ...");

                User user = new User();
                user.setName(name);
                user.setLogin(login);
                user.setRole(role);

                if(checkPassword(password, repeatPassword)){
                    //out.println("Password check success ...");
                    user.setPassword(password);
                    if(checkUser(user, getUsers(connection))){

                        successRegistration(user, connection);
                        //out.println("Registration success ...");
                        RequestDispatcher requestDispatcher = req.getRequestDispatcher("Views/Login.jsp");
                        requestDispatcher.forward(req, resp);
                    }else{
                        out.println("Login or password repeated ...");
                    }
                }
                else {
                    out.println("Password failed ...");
                }

            }

        }catch (Exception e){
            out.println("Registration failed ..." + br);
        }finally {
            out.close();
        }

    }

    private void successRegistration(User user, Connection connection) {
        UserService userService = new UserService();
        userService.add(connection, user);
    }

    private List<User> getUsers(Connection connection) {
        UserService userService = new UserService();
        List<User> users = userService.findAll(connection);
        return users;
    }


    private boolean checkUser(User user, List<User> users){
        for(User i: users){
            if(user.getLogin().equals(i.getLogin()) && user.getPassword().equals(i.getPassword())){
                return false;
            }
        }
        return true;
    }

    private boolean checkPassword(String password, String repeatPassword){
        return  password.equals(repeatPassword) ;
    }

}
