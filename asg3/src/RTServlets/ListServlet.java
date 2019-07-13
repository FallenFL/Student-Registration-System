package RTServlets;

import dblib.dblib;

import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebInitParam;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

@WebServlet(name = "ListServlet", urlPatterns = {"/List"},
        initParams = { @WebInitParam(name = "uid", value = "ism6236"), @WebInitParam(name = "pass", value = "ism6236bo")})


public class ListServlet extends HttpServlet {

    private dblib dblib;

    @Override
    public void init(ServletConfig config) throws ServletException {
        super.init(config);


        String uid = config.getInitParameter("uid");
        String pass = config.getInitParameter("pass");
        dblib = new dblib(uid,pass);

    }


    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String headerstuff = "<meta charset=\"UTF-8\">\n " +
                "        <meta name=\"viewport\" content=\"width=device-width, initial-scale=1.0\">\n" +
                "          <link rel=\"stylesheet\" type =\"text/css\" href =\"bootstrap.css\" /> ";

        String year = request.getParameter("year");
        String semester = request.getParameter("semester");
        System.out.println("find it");
        List<String> res = dblib.list(year,semester);
        try (PrintWriter out = response.getWriter()) {

            out.println("<!DOCTYPE html>");
            out.println("<html>");
            out.println("<head>");
            out.println("<title>ListSections</title>");
            out.println(headerstuff);
            out.println("</head>");
            out.println("<body>");

            out.println("<form ACTION=\"Register\" METHOD=\"GET\"> ");
            out.println("<fieldset id=\"info\">");
            out.println("<legend > Sections: </legend>");
            String line = String.format("<label for=\"year\"> Year: </label> <INPUT id= \"year\" type=text size=20 name=\"year\" value=\"%s\"> <br>", year);
            out.println(line);
            line = String.format("<label for=\"semester\"> Semester: </label> <INPUT id= \"semester\" type=text size=20 name=\"semester\" disabled = \"disabled\" value=\"%s\"> <br>  ",semester);
            out.println(line);
            out.println("<label for=\"list\"> Details: </label> <br> ");
            out.println("<select id = \"details\" name =\"detaillist\" size=\"10\"> ");
            for (String o : res)
            {
                line = "<option style = 'white-space:pre'>" + o + "</option>";
                out.println(line);
            }
            out.println("</select> <br>");
            out.println(" <Input TYPE =\"submit\" formaction=\"index.html\" value=\"Main Menu\">");
            out.println("</fieldset>");
            out.println("</form>");

            out.println("</body>");
            out.println("</html>");
        }
    }
}
