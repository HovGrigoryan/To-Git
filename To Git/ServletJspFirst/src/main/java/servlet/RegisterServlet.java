package servlet;

import manager.LessonManager;
import manager.UserManager;
import model.Gender;
import model.Lesson;
import model.User;
import model.UserType;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Date;

@WebServlet(urlPatterns = "/register")
public class RegisterServlet extends HttpServlet {

    private LessonManager lessonManager = new LessonManager();
    private UserManager userManager = new UserManager();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.setAttribute("lessons",lessonManager.getAllLessons());
        req.getRequestDispatcher("/WEB-INF/register.jsp").forward(req,resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String name = req.getParameter("name");
        String surname = req.getParameter("surname");
        String email = req.getParameter("email");
        String password = req.getParameter("password");
        String gender = req.getParameter("gender");
        String lessonId = req.getParameter("lessonId");

        User user = new User();
        user.setName(name);
        user.setSurname(surname);
        user.setEmail(email);
        user.setPassword(password);
        user.setCreationDate(new Date());
        user.setGender(Gender.valueOf(gender));
        user.setLesson(lessonManager.getLessonByID(Integer.parseInt(lessonId)));
        user.setUserType(UserType.USER);


        userManager.addUser(user);
        req.setAttribute("message", "You have registered successfully! You can Login now!");
        req.getRequestDispatcher("login.jsp").forward(req, resp);
    }
}


