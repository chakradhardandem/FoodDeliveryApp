package com.Servlets;

import java.io.IOException;
import java.util.List;

import com.DAOIMPLEMENTATION.RestaurantDAOImplementation;
import com.Model.Restaurant;

import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
@WebServlet("/restaurant")
public class RestaurantServlet extends HttpServlet{
	
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		RestaurantDAOImplementation implementation=new RestaurantDAOImplementation();
		List<Restaurant>  allRestaurants =implementation.getallRestaurants();
		for(Restaurant restaurant:allRestaurants) {
			System.out.println(restaurant);
		}
		
		req.setAttribute("allRestaurants", allRestaurants);
		RequestDispatcher rd=req.getRequestDispatcher("restaurant.jsp");
		rd.forward(req, resp);
	}
	
	

}
