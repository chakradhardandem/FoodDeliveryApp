package com.DAO;

import java.util.List;

import com.Model.Order;

public interface OrderDAO {
	  int addOrder(Order o);
	    Order getOrder(int orderId);
	    void updateOrder(Order o);
	    void deleteOrder(int orderId);
	    List<Order> getAllOrders();

}
