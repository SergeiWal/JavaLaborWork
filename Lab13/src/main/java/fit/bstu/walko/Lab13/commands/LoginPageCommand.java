package fit.bstu.walko.Lab13.commands;

import fit.bstu.walko.Lab13.Page;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class LoginPageCommand implements Command {
    @Override
    public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.getRequestDispatcher(Page.LOGIN_PAGE.getPage()).forward(request,response);
    }
}
