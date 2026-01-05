package com.Servlets;

import java.io.IOException;
import java.sql.Timestamp;

import com.DAOIMPLEMENTATION.OrderDAOImplementation;
import com.DAOIMPLEMENTATION.OrderItemDAOImplementation;
import com.Model.Cart;
import com.Model.CartItem;
import com.Model.Order;
import com.Model.OrderItem;
import com.Model.User;

import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
@WebServlet("/checkout")
public class CheckoutServlet extends HttpServlet{
	
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		
		
		
		HttpSession session=req.getSession();
		
		
		Cart cart=(Cart)session.getAttribute("cart");

		User user=(User)session.getAttribute("user");
		
		
		int restaurantId=(Integer)session.getAttribute("oldRestaurantId");
		
		String address=req.getParameter("address");
		String paymentMethod=req.getParameter("paymentMethod");
		
		if(user==null) {
			RequestDispatcher rd=req.getRequestDispatcher("login.html");
			rd.forward(req, resp);
		}
		
		if(cart!=null && user!=null &&  !cart.getItems().isEmpty()) {
			Order order=new Order();
			order.setUserId(user.getUserID());
			order.setRestaurantId(restaurantId);
			order.setOrderdate(new Timestamp(System.currentTimeMillis()));
			order.setAddress(address);
			order.setStatus("pending");
			order.setPaymentMode(paymentMethod);
			
			
			double totalAmount=0.0;
			for(CartItem item:cart.getItems().values()) {
				totalAmount=totalAmount+item.getQuantity()*item.getPrice();
			}
			
			order.setTotalamount(totalAmount);
			
			OrderDAOImplementation orderDAOImpl=new OrderDAOImplementation();
			int orderId =orderDAOImpl.addOrder(order);
			
			for(CartItem item:cart.getItems().values()) {
				int itemId=item.getItemId();
				int quantity=item.getQuantity();
				double totalPrice=item.getTotalPrice();
				
				OrderItem orderItem=new OrderItem();
				orderItem.setOrderId(orderId);
				orderItem.setMenuId(itemId);
				orderItem.setQuantity(quantity);
				orderItem.setTotalamount(totalPrice);
				
				
				OrderItemDAOImplementation orderItemDAOImpl=new OrderItemDAOImplementation();
				orderItemDAOImpl.addOrderItem(orderItem);
				
			}
			
			
			
			
			session.removeAttribute("cart");
			session.removeAttribute("oldRestaurantId");
			resp.sendRedirect("orderConfirmation.jsp");
			
			
			
			
			
			
			
		}
		else {
			resp.sendRedirect("cart.jsp");
		}
		
		
		
	}

}
