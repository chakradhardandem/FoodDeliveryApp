package com.DAO;

import java.util.List;

import com.Model.OrderItem;

public interface OrderItemDAO {
	   void addOrderItem(OrderItem oi);
	    OrderItem getOrderItem(int orderItemId);
	    void updateOrderItem(OrderItem oi);
	    void deleteOrderItem(int orderItemId);
	    List<OrderItem> getAllOrderItems();

}
