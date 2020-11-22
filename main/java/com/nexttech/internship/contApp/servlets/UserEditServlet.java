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

@WebServlet("/editUser")
public class UserEditServlet extends HttpServlet {
    private UserDao userDao;

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String id = req.getParameter("update");
        User user = userDao.getUserById(Integer.parseInt(id));
        req.setAttribute("user", user);
        RequestDispatcher requestDispatcher = getServletContext().getRequestDispatcher("/editUser.jsp");
        requestDispatcher.forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        int id = Integer.parseInt(req.getParameter("userId"));
        updateUser(req, id);
        resp.sendRedirect("users");
    }

    private void updateUser(HttpServletRequest req, int id) {
        User user = userDao.getUserById(id);
        user.setUsername(req.getParameter("username"));
        user.setPassword(req.getParameter("password"));
        user.setRole(req.getParameter("role"));
        user.setEmail(req.getParameter("email"));
        user.setFirmId(Integer.parseInt(req.getParameter("firmId")));
        userDao.updateUser(user);
    }

    @Override
    public void init() throws ServletException {
        this.userDao = new UserDao();
    }

    @Override
    public void destroy() {
        this.userDao.getConn().closeConnection();
    }
}

