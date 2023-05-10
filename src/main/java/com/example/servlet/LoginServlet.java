package com.example.servlet;

import com.example.Users;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("/login")
public class LoginServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        if (req.getSession() == null
                || req.getSession().getAttribute("user") == null) {
            req.getRequestDispatcher("login.jsp")
                    .forward(req, resp);
        } else {
            req.getRequestDispatcher("/user/hello.jsp")
                    .forward(req, resp);
        }
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        if (Users.getInstance().getUsers().contains(req.getParameter("login"))
                && req.getParameter("password") != null
                && req.getParameter("password").length() > 0) {
            req.getSession(true).setAttribute("user", req.getParameter("login"));
            req.getRequestDispatcher("user/hello.jsp")
                    .forward(req, resp);
        } else {
            req.getRequestDispatcher("login.jsp").forward(req, resp);
        }
    }
}
