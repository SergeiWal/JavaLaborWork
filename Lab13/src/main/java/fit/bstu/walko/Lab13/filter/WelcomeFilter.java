package fit.bstu.walko.Lab13.filter;

import fit.bstu.walko.Lab13.Page;
import org.apache.log4j.Logger;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import java.io.IOException;

@WebFilter(urlPatterns = "/views/Welcome.jsp")
public class WelcomeFilter implements Filter {

    private static final String COMMAND = "command";
    private static final String ERROR_MESSAGE = "error6е5к";
    private static final Logger LOGGER = Logger.getLogger(WelcomeFilter.class);

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain)
            throws IOException, ServletException {
        HttpServletRequest request = (HttpServletRequest) servletRequest;
        String command = request.getParameter(COMMAND);
        LOGGER.info("Filter is working to " + command);
        if(!command.equals("welcome")){
            filterChain.doFilter(servletRequest,servletResponse);
        }else {
            if (request.getSession().getAttribute("Username") != null ){
                filterChain.doFilter(servletRequest, servletResponse);
            }else{
                request.setAttribute( ERROR_MESSAGE , "Page is closed for you");
                request.getRequestDispatcher(Page.ERROR_PAGE.getPage()).forward(servletRequest,servletResponse);
            }
        }
    }
}
