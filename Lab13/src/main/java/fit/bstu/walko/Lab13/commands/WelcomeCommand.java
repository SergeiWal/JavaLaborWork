package fit.bstu.walko.Lab13.commands;

import fit.bstu.walko.Lab13.Page;
import fit.bstu.walko.Lab13.exception.RepositoryError;
import fit.bstu.walko.Lab13.models.Uniwer;
import fit.bstu.walko.Lab13.repository.UniwerRepository;
import org.apache.log4j.Logger;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.sql.SQLException;
import java.util.List;

public class WelcomeCommand implements Command {

    private static final Logger LOGGER = Logger.getLogger(WelcomeCommand.class);

    @Override
    public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        welcome(request,response);
    }

    private void welcome(HttpServletRequest request, HttpServletResponse response){
        LOGGER.info("Мы попали в велком");
        try {
            UniwerRepository uniwerRepository = new UniwerRepository();
            List<Uniwer> uniwers = uniwerRepository.findAll();
            request.setAttribute("uniwers", uniwers);
            request.getRequestDispatcher(Page.WELCOME_PAGE.getPage()).forward(request, response);
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        } catch (ClassNotFoundException e) {
            LOGGER.error(e.getMessage());
        } catch (NoSuchMethodException e) {
            LOGGER.error(e.getMessage());
        } catch (InvocationTargetException e) {
            LOGGER.error(e.getMessage());
        } catch (InstantiationException e) {
            LOGGER.error(e.getMessage());
        } catch (IllegalAccessException e) {
            LOGGER.error(e.getMessage());
        } catch (ServletException e) {
            LOGGER.error(e.getMessage());
        } catch (IOException e) {
            LOGGER.error(e.getMessage());
        } catch (RepositoryError repositoryError) {
            repositoryError.printStackTrace();
        }
    }
}
