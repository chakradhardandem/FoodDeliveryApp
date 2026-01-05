package com.DAOIMPLEMENTATION;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.DAO.OrderItemDAO;
import com.Model.OrderItem;

public class OrderItemDAOImplementation implements OrderItemDAO{
	@Override
    public void addOrderItem(OrderItem oi) {

	    try {
	        Class.forName("com.mysql.cj.jdbc.Driver");
	        Connection connection = DriverManager.getConnection(
	                "jdbc:mysql://localhost:3306/courseproject",
	                "root",
	                "HHHhhh1@");

	        // If orderItemId is AUTO_INCREMENT, you can remove it from SQL and not set param 1
	        String sql = "INSERT INTO orderitem " +
	                     "(orderId, menuId, totalamount,quantity) " +
	                     "VALUES (?, ?, ?, ?)";

	        PreparedStatement preparedStatement = connection.prepareStatement(sql);

	        preparedStatement.setInt(1, oi.getOrderId());
	      
	        preparedStatement.setInt(2, oi.getMenuId());
	        preparedStatement.setDouble(3, oi.getTotalamount());
	        preparedStatement.setInt(4, oi.getQuantity());
	        
	        

	        preparedStatement.executeUpdate();

	    } catch (ClassNotFoundException | SQLException e) {
	        e.printStackTrace();
	    }
	}



	 @Override
	    public OrderItem getOrderItem(int orderItemId) {

	        OrderItem orderItem = null;

	        try {
	            Class.forName("com.mysql.cj.jdbc.Driver");
	            Connection connection = DriverManager.getConnection(
	                    "jdbc:mysql://localhost:3306/courseproject",
	                    "root",
	                    "HHHhhh1@");

	            PreparedStatement preparedStatement = connection.prepareStatement(
	                    "SELECT * FROM orderitem WHERE orderItemId = ?");
	            preparedStatement.setInt(1, orderItemId);

	            ResultSet resultSet = preparedStatement.executeQuery();

	            while (resultSet.next()) {
	                int oiId       = resultSet.getInt("orderItemId");
	                int orderId    = resultSet.getInt("orderId");
	                int menuId     = resultSet.getInt("menuId");
	                int totalamount= resultSet.getInt("totalamount");

	                orderItem = new OrderItem(oiId, orderId, menuId, totalamount);
	            }

	        } catch (ClassNotFoundException | SQLException e) {
	            e.printStackTrace();
	        }

	        return orderItem;
	    }

	 
	 

	@Override
	public void updateOrderItem(OrderItem oi) {

	    try {
	        Class.forName("com.mysql.cj.jdbc.Driver");
	        Connection connection = DriverManager.getConnection(
	                "jdbc:mysql://localhost:3306/courseproject",
	                "root",
	                "HHHhhh1@");

	     
	        PreparedStatement preparedStatement = connection.prepareStatement(
	                "UPDATE orderitem SET totalamount = ? WHERE orderItemId = ?");

	        preparedStatement.setDouble(1, oi.getTotalamount());
	        preparedStatement.setInt(2, oi.getOrderItemId());

	        preparedStatement.executeUpdate();

	    } catch (ClassNotFoundException | SQLException e) {
	        e.printStackTrace();
	    }
	}


	 @Override
	    public void deleteOrderItem(int orderItemId) {

	        try {
	            Class.forName("com.mysql.cj.jdbc.Driver");
	            Connection connection = DriverManager.getConnection(
	                    "jdbc:mysql://localhost:3306/courseproject",
	                    "root",
	                    "HHHhhh1@");

	            PreparedStatement preparedStatement = connection.prepareStatement(
	                    "DELETE FROM orderitem WHERE orderItemId = ?");
	            preparedStatement.setInt(1, orderItemId);

	            preparedStatement.executeUpdate();

	        } catch (ClassNotFoundException | SQLException e) {
	            e.printStackTrace();
	        }
	    }

	    @Override
	    public List<OrderItem> getAllOrderItems() {

	        ArrayList<OrderItem> list = null;

	        try {
	            Class.forName("com.mysql.cj.jdbc.Driver");
	            Connection connection = DriverManager.getConnection(
	                    "jdbc:mysql://localhost:3306/courseproject",
	                    "root",
	                    "HHHhhh1@");

	            PreparedStatement preparedStatement = connection.prepareStatement(
	                    "SELECT * FROM orderitem");
	            ResultSet resultSet = preparedStatement.executeQuery();

	            list = new ArrayList<OrderItem>();

	            while (resultSet.next()) {
	                int orderItemId = resultSet.getInt("orderItemId");
	                int orderId     = resultSet.getInt("orderId");
	                int menuId      = resultSet.getInt("menuId");
	                int totalamount = resultSet.getInt("totalamount");

	                OrderItem oi = new OrderItem(orderItemId, orderId, menuId, totalamount);
	                list.add(oi);
	            }

	        } catch (ClassNotFoundException | SQLException e) {
	            e.printStackTrace();
	        }

	        return list;
	    }

}
