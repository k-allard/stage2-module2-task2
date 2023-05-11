package com.example.servlet;

import com.example.Users;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.nio.charset.IllegalCharsetNameException;

@WebServlet("/login")
public class LoginServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        if (req.getSession() == null
                || req.getSession().getAttribute("user") == null) {
            resp.sendRedirect("/login.jsp");
        } else {
            resp.sendRedirect("/user/hello.jsp");
        }
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        if (req.getParameter("login") != null
                && Users.getInstance().getUsers().contains(req.getParameter("login"))
                && req.getParameter("password") != null
                && req.getParameter("password").length() > 0) {
            HttpSession session;
            try {
                session = req.getSession();
            } catch (NullPointerException e) {
                throw new RuntimeException("NullPointerException req.getSession() caught");
            }
            try {
                session.setAttribute("user", req.getParameter("login"));
            } catch (NullPointerException e) {
                throw new IllegalArgumentException("NullPointerException session.setAttribute() caught");
            }
            try {
                resp.sendRedirect("/user/hello.jsp");
            } catch (NullPointerException e) {
                throw new IllegalCharsetNameException("NullPointerException sendRedirect() caught");
            }
        } else {
            req.getRequestDispatcher("/login.jsp").forward(req, resp);
        }
    }
}
