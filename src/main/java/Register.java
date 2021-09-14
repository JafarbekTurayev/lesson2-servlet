import util.DBService;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;

@WebServlet("/register")
public class Register extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        resp.sendRedirect("register.html");
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        String email = req.getParameter("email");
        String fullName = req.getParameter("fullName");
        int age = Integer.parseInt(req.getParameter("age"));
        String password = req.getParameter("password");

        try {
            boolean registered = DBService.registerUser(email, fullName, age, password);
            if (registered) {
                resp.sendRedirect("index.html");
            } else {
//                PrintWriter writer = resp.getWriter();
//                writer.write("window.alert('User bunaqa bor!')");
                resp.sendRedirect("register.html");
            }
        } catch (SQLException | ClassNotFoundException throwables) {
            throwables.printStackTrace();
        }
    }
}
