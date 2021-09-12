package com.example.SigApp;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

@WebServlet(name = "controller", value = "/controller")
public class Contoller extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        action(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        action(req, resp);
    }

    private void action(HttpServletRequest req, HttpServletResponse resp) {

        DbWorker worker = new DbWorker();

        String submit = req.getParameter("submit");



        if(submit.equals("ADD")){

            String FIO = req.getParameter("FIO");
            Double number = Double.parseDouble(req.getParameter("number"));

            Person newPer = new Person();
            newPer.setAvgNumber(number);
            newPer.setFIO(FIO);

            worker.add(newPer);
            try {
                req.getRequestDispatcher("/index.jsp").forward(req,resp);
            } catch (ServletException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            }

        }
        else if(submit.equals("GET")){
            List<Person> personList = worker.FindAll();

            String result = "<table border = \"1\">\n\" +\n" +
                    "                \"            <tr>\\n\" +\n" +
                    "                \"                <th>FIO</th>\\n\" +\n" +
                    "                \"                <th>Number</th>\\n\" +\n" +
                    "                \"            </tr>\\n";

            for(Person person: personList){
                result += "<tr>\n" +
                "                <th>" + person.getFIO() + "</th>\n" +
                        "                <th>" + person.getAvgNumber() + "</th>\n" +
                        "            </tr>\n";
            }

            req.setAttribute("result", result);

            try {
                req.getRequestDispatcher("/get.jsp").forward(req,resp);
            } catch (ServletException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

    }
}
