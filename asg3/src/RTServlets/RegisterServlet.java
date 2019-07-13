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

@WebServlet(name = "RegisterServlet", urlPatterns = {"/Register"},
        initParams = { @WebInitParam(name = "uid", value = "ism6236"), @WebInitParam(name = "pass", value = "ism6236bo")})
public class RegisterServlet extends HttpServlet {

    private dblib dblib;

    @Override
    public void init(ServletConfig config) throws ServletException {
        super.init(config);


        String uid = config.getInitParameter("uid");
        String pass = config.getInitParameter("pass");
        dblib = new dblib(uid,pass);

    }
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String stdID = request.getParameter("sID");
        String courseID = request.getParameter("courseid");
        String sectionID = request.getParameter("sectionid");
        String res = "";
        if (stdID == null || courseID == null || sectionID == null) res = "failed";
        else
        res = dblib.register(stdID,courseID,sectionID);
        try (PrintWriter out = response.getWriter()) {
            /* TODO output your page here. You may use following sample code. */
            out.println("<!DOCTYPE html>");
            out.println("<html>");
            out.println("<head>");
            out.println("<title>Register </title>");
            out.println("</head>");
            out.println("<body>");
            if (res == "NC")
            {
                out.println("no enough capacity<br>");
            }
            else if (res == "FK")
            {
                out.println("FK violation<br>");
            }
            else if (res == "PK")
            {
                out.println("PK violation<br>");
            }
            else if (res == "failed") out.println("Invalid Input");
            else out.println("Registered Successfully for stdID  " + stdID +" <br>");

            out.println("<a href=\"index.html\"> Main Menu </a> <br>");
            out.println("</body>");
            out.println("</html>");
        }
    }
}
