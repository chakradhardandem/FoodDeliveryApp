package com.Servlets;

import java.io.IOException;
import java.io.PrintWriter;

import com.DAOIMPLEMENTATION.UserDAOImplementation;
import com.Model.User;

import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

@WebServlet("/Login")
public class LoginServlet extends HttpServlet {
    
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String email = req.getParameter("email");
        UserDAOImplementation impl = new UserDAOImplementation();
        User user = impl.getUser(email);

        if (user != null) {
            String originalPassword = user.getPassword();
            String givenPassword = req.getParameter("password");
            String username = user.getUsername();
            
            if (givenPassword.equals(originalPassword)) {
                // ✅ FIXED: Set user in session and redirect
                HttpSession session = req.getSession();
                session.setAttribute("user", user);
                 resp.sendRedirect("cart.jsp"); 
                // Redirect to cart instead of printing
                
               
                return;
            } else {
                // ✅ FIXED: Handle failed login attempts
                HttpSession session = req.getSession();
                Integer attemptsLeft = (Integer) session.getAttribute("loginAttempts");
                if (attemptsLeft == null) {
                    attemptsLeft = 3;
                }
                
                if (attemptsLeft > 1) {
                    session.setAttribute("loginAttempts", attemptsLeft - 1);
                    PrintWriter out = resp.getWriter();
                    resp.setContentType("text/html");
                    out.println("<h1>" + attemptsLeft + " more attempts remaining</h1>");
                    RequestDispatcher rd = req.getRequestDispatcher("login.html");
                    rd.include(req, resp);
                } else {
                    session.setAttribute("loginAttempts", 0);
                    PrintWriter out = resp.getWriter();
                    resp.setContentType("text/html");
                    out.println("<h1>Account Blocked - Too many failed attempts</h1>");
                }
            }
        } else {
            // User not found - redirect to register
            resp.sendRedirect("register.html");
        }
    }
}
