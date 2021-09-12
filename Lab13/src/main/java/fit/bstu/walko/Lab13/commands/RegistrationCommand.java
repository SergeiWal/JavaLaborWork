package fit.bstu.walko.Lab13.commands;

import fit.bstu.walko.Lab13.Hasher;
import fit.bstu.walko.Lab13.Page;
import fit.bstu.walko.Lab13.models.User;
import fit.bstu.walko.Lab13.repository.UserRepository;
import org.apache.log4j.Logger;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;
import java.util.List;


public class RegistrationCommand implements Command {

    private static final Logger LOGGER = Logger.getLogger(LoginCommand.class.getName());

    @Override
    public void execute(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        try {
            registration(request, response);
        } catch (InterruptedException exception) {
           LOGGER.info(exception.getMessage());
        } catch (ClassNotFoundException e) {
            LOGGER.info(e.getMessage());
        }
    }

    private void registration(HttpServletRequest req, HttpServletResponse resp)
            throws IOException, InterruptedException, ServletException, ClassNotFoundException {

        resp.setContentType("text/html; charset=Cp1251");
        String br = "<br/>";
        PrintWriter out = null;
        UserRepository repository = null;

        try{
                repository = new UserRepository();
                String name = req.getParameter("name");
                String login =  req.getParameter("login");
                String role = req.getParameter("role");
                String password = Hasher.getHash(req.getParameter("password"));
                String repeatPassword  =  Hasher.getHash(req.getParameter("repeat_password"));

                User user = new User();
                user.setName(name);
                user.setLogin(login);
                user.setRole(role);

                if(checkPassword(password, repeatPassword)){
                    user.setPassword(password);
                    if(checkUser(user, repository.findAll())){
                        repository.add(user);
                        LOGGER.info("Create new user ...");
                        req.getRequestDispatcher(Page.LOGIN_PAGE.getPage()).forward(req,resp);
                    }else{
                        LOGGER.info("Registration failed ...");
                        req.setAttribute("error","User with this password or login exists");
                        req.getRequestDispatcher(Page.REGISTER_PAGE.getPage()).forward(req,resp);
                    }
                }
                else {
                    LOGGER.info("Registration failed ...");
                    req.setAttribute("error","Passwords not equals");
                    req.getRequestDispatcher(Page.REGISTER_PAGE.getPage()).forward(req,resp);
                }

        }catch (Exception e){
            LOGGER.info("Registration failed ...");
        }finally {
            out.close();
        }
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
