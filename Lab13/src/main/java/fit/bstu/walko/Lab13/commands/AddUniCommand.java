package fit.bstu.walko.Lab13.commands;

import fit.bstu.walko.Lab13.commands.factory.CommandFactory;
import fit.bstu.walko.Lab13.commands.factory.CommandType;
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

public class AddUniCommand implements Command{

    private static final Logger LOGGER = Logger.getLogger(AddUniCommand.class);

    @Override
    public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        AddUni(request,response);
    }

    private void AddUni(HttpServletRequest request, HttpServletResponse response){
        String name = request.getParameter("name");
        String city = request.getParameter("city");
        String country = request.getParameter("country");

        try {
            UniwerRepository repository = new UniwerRepository();
            if(name != null && name.length()>0 && city!=null && city.length()>0 &&
                    country!=null && country.length()>0){
                Uniwer uniwer = new Uniwer();
                uniwer.setUni_name(name);
                uniwer.setCity(city);
                uniwer.setCountry(country);

                repository.add( uniwer);
                LOGGER.info("Add new uniwer in DB ...");
                request.getRequestDispatcher("/controller?command=welcome").forward(request, response);
            }else {
                request.setAttribute("error","Add new uni failed !!!");
                request.getRequestDispatcher("/controller?command=welcome").forward(request, response);
                LOGGER.info("Add new uniwer failed...");
            }
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
            repositoryError.printStackTrace();
        }
    }
}
