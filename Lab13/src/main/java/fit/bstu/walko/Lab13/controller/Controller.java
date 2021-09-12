package fit.bstu.walko.Lab13.controller;

import fit.bstu.walko.Lab13.Page;
import fit.bstu.walko.Lab13.commands.Command;
import fit.bstu.walko.Lab13.commands.CommandResult;
import fit.bstu.walko.Lab13.commands.factory.CommandFactory;
import fit.bstu.walko.Lab13.exception.ControllerError;
import org.apache.log4j.Logger;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class Controller extends HttpServlet {

    private static final String COMMAND = "command";
    private static final String ERROR_MESSAGE = "error";
    private static final Logger LOGGER = Logger.getLogger(Controller.class);

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        try {
            ControllerProcess(req, resp);
        } catch (ControllerError controllerError) {
            LOGGER.error(controllerError.getMessage());
            controllerError.printStackTrace();
        }
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        try {
            ControllerProcess(req, resp);
        } catch (ControllerError controllerError) {
            LOGGER.error(controllerError.getMessage());
            controllerError.printStackTrace();
        }
    }

    private void ControllerProcess(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException, ControllerError {
        String command = req.getParameter(COMMAND);
        LOGGER.info("Command = " + command);
        Command action = CommandFactory.Create(command);
        try {
            LOGGER.info("Команда получена");
            action.execute(req,resp);
        } catch (ServletException e) {
            LOGGER.error(e.getMessage(),e);
            req.setAttribute(ERROR_MESSAGE,e);
            req.getRequestDispatcher(Page.ERROR_PAGE.getPage()).forward(req,resp);
            throw new ControllerError("Controller exception");
        } catch (IOException e) {
            LOGGER.error(e.getMessage(),e);
            req.setAttribute(ERROR_MESSAGE,e);
            req.getRequestDispatcher(Page.ERROR_PAGE.getPage()).forward(req,resp);
            throw new ControllerError("Controller exception");
        }
    }

}
