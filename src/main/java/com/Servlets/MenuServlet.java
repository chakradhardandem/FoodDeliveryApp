package com.Servlets;

import java.io.IOException;
import java.util.List;

import com.DAOIMPLEMENTATION.MenuDAOImplementation;
import com.Model.Menu;

import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
@WebServlet("/menu")
public class MenuServlet extends HttpServlet {
	
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		int restaurantId=Integer.parseInt(req.getParameter("restaurantId"));
		MenuDAOImplementation menuDAOImpl=new MenuDAOImplementation();
		List<Menu> allMenusByRestaurantId=menuDAOImpl.getAllMenusByRestaurant(restaurantId);
		for(Menu menu:allMenusByRestaurantId) {
			System.out.println(menu);
		}
		
		
		req.setAttribute("allMenusByRestaurantId", allMenusByRestaurantId);
		RequestDispatcher rd=req.getRequestDispatcher("menu.jsp");
		rd.forward(req, resp);
		
	}
	

}
