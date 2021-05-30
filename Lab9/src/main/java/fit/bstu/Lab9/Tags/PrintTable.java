package fit.bstu.Lab9.Tags;

import fit.bstu.Lab9.DB.Uniwer;

import javax.servlet.jsp.JspException;
import javax.servlet.jsp.JspWriter;
import javax.servlet.jsp.tagext.TagSupport;
import java.io.IOException;
import java.util.List;

public class PrintTable extends TagSupport {

    private List<Uniwer> uniwers;

    public List<Uniwer> getUniwers() {
        return uniwers;
    }

    public void setUniwers(List<Uniwer> uniwers) {
        this.uniwers = uniwers;
    }

    @Override
    public int doStartTag() throws JspException {
        String html = "<table class=\"w3-table-all w3-hoverable\">\n" +
                "            <tr>\n" +
                "                <th>Id</th>\n" +
                "                <th>Name</th>\n" +
                "                <th>City</th>\n" +
                "                <th>Country</th>\n" +
                "            </tr>\n";


        for(Uniwer uni: uniwers){
            html +=  "<tr>\n" +
                    " <td>" + uni.getId() + "</td>\n" +
                    " <td>" + uni.getUni_name() + "</td>\n" +
                    " <td>" + uni.getCity() + "</td>\n" +
                    " <td>" + uni.getCountry() + "</td>\n" +
                    " </tr>\n";
        }

        html += "</table>";
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
