package fit.bstu.Lab9.Tags;

import javax.servlet.jsp.JspException;
import javax.servlet.jsp.JspWriter;
import javax.servlet.jsp.tagext.TagSupport;
import java.io.IOException;

public class WsaSubmit extends TagSupport {

    @Override
    public int doStartTag() throws JspException {
        String html = "<input type=\"submit\" value=\"submit\"/><br/>";
        try {
             JspWriter out = pageContext.getOut();     
            out.write(html);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return SKIP_BODY;
    }

    @Override
    public int doEndTag() throws JspException {
        return EVAL_PAGE;
    }
}
