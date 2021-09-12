package fit.bstu.walko.Lab13.commands;

import fit.bstu.walko.Lab13.Hasher;
import fit.bstu.walko.Lab13.Page;
import fit.bstu.walko.Lab13.models.User;
import fit.bstu.walko.Lab13.repository.UserRepository;
import org.apache.log4j.Logger;

import javax.jws.soap.SOAPBinding;
import javax.servlet.ServletException;
import javax.servlet.SessionCookieConfig;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.List;


public class LoginCommand implements Command{

    private static final Logger LOGGER = Logger.getLogger(LoginCommand.class.getName());

    @Override
    public void execute(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        login(request,response);
    }

    private void login(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        try {
            LOGGER.info("Мы в команде");
            UserRepository repository = new UserRepository();
            LOGGER.info("Создан репозиторий");

            String login = req.getParameter("login");
            String pass = Hasher.getHash(req.getParameter("password"));
            LOGGER.info("Параметры: " + login + " " + pass);

            if(login.isEmpty() || pass.isEmpty()){
                req.setAttribute("error","invalid login or password");
                req.getRequestDispatcher(Page.LOGIN_PAGE.getPage()).forward(req,resp);
            }

            User user = new User();
            user.setLogin(login);
            user.setPassword(pass);

            List<User> users = repository.findAll();
            LOGGER.info("Список юзеров: " + users.size());
            user = checkUser(user,users);

            if(user!=null){
                HttpSession session = req.getSession();
                session.setAttribute("Username", user.getName());
                LOGGER.info("Тут должен быть переход");
                req.getRequestDispatcher("/controller?command=welcome")
                        .forward(req,resp);
            }
            else{
                LOGGER.info("user with such login and password not exist");
                req.setAttribute("error","user with such login and password not exist");
                req.getRequestDispatcher(Page.LOGIN_PAGE.getPage()).forward(req,resp);
            }
        }
        catch (Exception exc){
            LOGGER.info("LoginException");
        }
    }

    private User checkUser(User user, List<User> users){
        for (User i: users){
            if(user.getLogin().equals(i.getLogin()) && user.getPassword().equals(i.getPassword())){
                return  i;
            }
        }
        return null;
    }

}
