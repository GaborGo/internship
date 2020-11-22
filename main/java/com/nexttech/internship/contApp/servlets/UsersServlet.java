package com.nexttech.internship.contApp.servlets;

import com.nexttech.internship.contApp.JDBC.UserDao;
import com.nexttech.internship.contApp.model.User;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

@WebServlet("/users")
public class UsersServlet extends HttpServlet {
    private UserDao userDao;

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        List<User> users = userDao.getAllUsers();
        req.setAttribute("users", users);
        RequestDispatcher requestDispatcher = getServletContext().getRequestDispatcher("/users.jsp");
        requestDispatcher.forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        if (req.getParameter("delete") != null) {
            String id = req.getParameter("delete");
            userDao.deleteUserWithId(Integer.parseInt(id));
        } else if (req.getParameter("update") != null) {
            String id = req.getParameter("update");
            User user = userDao.getUserById(Integer.parseInt(id));
            req.setAttribute("user", user);
            RequestDispatcher requestDispatcher = getServletContext().getRequestDispatcher("/editUser.jsp");
            requestDispatcher.forward(req, resp);
        } else if (req.getParameter("userId") != null)
            addUser(req, Integer.parseInt(req.getParameter("userId")));

        doGet(req, resp);
    }

    private void addUser(HttpServletRequest req, int id) {
        User user = new User();
        user.setId(id);
        user.setUsername(req.getParameter("username"));
        user.setPassword(req.getParameter("password"));
        user.setEmail(req.getParameter("email"));
        user.setRole(req.getParameter("role"));
        user.setFirmId(Integer.parseInt(req.getParameter("firmId")));
        userDao.addUser(user);
    }

    @Override
    public void init() {
        this.userDao = new UserDao();
    }

    @Override
    public void destroy() {
        this.userDao.getConn().closeConnection();
    }
}
