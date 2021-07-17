package servlet;

import manager.UserManager;
import model.User;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

@WebServlet(urlPatterns = "/userDetail")
public class UserDetailServlet extends HttpServlet {
    private UserManager userManager = new UserManager();


    @Override
    protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        HttpSession session = req.getSession();
        User user = (User) session.getAttribute("user");
        if (user == null) {
            resp.sendRedirect("/login.jsp");
        } else {
            String userId = req.getParameter("userId");
            int id = Integer.parseInt(userId);
            User userByID = userManager.getUserByID(id);
            if (userByID == null){
                resp.sendRedirect("login.jsp");
            }else {
                req.setAttribute("userDetail",userByID);
            }
            req.getRequestDispatcher("/WEB-INF/userDetail.jsp").forward(req,resp);
        }
    }
}
