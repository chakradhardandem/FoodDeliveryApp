package com.Servlets;

import java.io.IOException;
import java.io.PrintWriter;

import com.DAOIMPLEMENTATION.UserDAOImplementation;
import com.Model.User;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
@WebServlet("/register")
public class RegisterServlet extends HttpServlet{
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
	
		String username=req.getParameter("username");
		String password=req.getParameter("password");
		String email=req.getParameter("email");
		String phone=req.getParameter("phone");
		String address=req.getParameter("address");
		String role=req.getParameter("role");
		UserDAOImplementation impl=new UserDAOImplementation();
		User user=new User(username,password,email,phone,address,role);
		int res=impl.addUser(user);
		PrintWriter out=resp.getWriter();
		if(res==1) {
			out.println("hi "+username+" your registration is succesfull");
			
			
		}
		else {
			out.println(username+" your registartion is unSuccessfull");
		}
		
		
	}

}
