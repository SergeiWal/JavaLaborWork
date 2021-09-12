package fit.bstu.walko.Lab13.commands;

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

public class DeleteUniCommand implements  Command{

    private static final Logger LOGGER = Logger.getLogger(DeleteUniCommand.class);

    @Override
    public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        deleteUni(request, response);
    }

    private void deleteUni(HttpServletRequest request, HttpServletResponse response){
        int id = Integer.parseInt(request.getParameter("id"));

        try {
            UniwerRepository repository = new UniwerRepository();
            Uniwer uniwer = new Uniwer();
            uniwer.setId(id);

            repository.remove(uniwer);
            LOGGER.info("Delete uniwer from db success ...");
            request.getRequestDispatcher("/controller?command=welcome").forward(request, response);
        } catch (SQLException throwables) {
            LOGGER.error(throwables.getMessage());
        } catch (ClassNotFoundException e) {
            LOGGER.error(e.getMessage());
        } catch (NoSuchMethodException e) {
            LOGGER.error(e.getMessage());
        } catch (InvocationTargetException e) {
            LOGGER.error(e.getMessage());
        } catch (InstantiationException | ServletException | IOException e) {
            LOGGER.error(e.getMessage());
        } catch (IllegalAccessException e) {
            LOGGER.error(e.getMessage());
        } catch (RepositoryError repositoryError) {
            LOGGER.error(repositoryError.getMessage());
            repositoryError.printStackTrace();
        }
    }
}
