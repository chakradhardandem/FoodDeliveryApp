package com.DAOIMPLEMENTATION;

import java.sql.Statement; 
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

import com.DAO.OrderDAO;
import com.Model.Order;

public class OrderDAOImplementation implements OrderDAO{
	

	 

	@Override
	public int addOrder(Order o) {
	    Connection connection = null;
	    PreparedStatement preparedStatement = null;
	    ResultSet generatedKeys = null;
	    int orderId = -1;
	    
	    try {
	        Class.forName("com.mysql.cj.jdbc.Driver");
	        connection = DriverManager.getConnection(
	            "jdbc:mysql://localhost:3306/courseproject", "root", "HHHhhh1@");
	        
	        String query = "INSERT INTO `Order` (userId, restaurantId, orderdate, totalamount, status, paymentMode, address) " +
	                      "VALUES (?, ?, ?, ?, ?, ?, ?)";
	        
	        // Key: Use RETURN_GENERATED_KEYS to get auto-increment ID
	        preparedStatement = connection.prepareStatement(query, Statement.RETURN_GENERATED_KEYS);
	        
	        preparedStatement.setInt(1, o.getUserId());
	        preparedStatement.setInt(2, o.getRestaurantId());
	        preparedStatement.setTimestamp(3, new Timestamp(System.currentTimeMillis()));
	        preparedStatement.setDouble(4, o.getTotalamount());
	        preparedStatement.setString(5, o.getStatus());
	        preparedStatement.setString(6, o.getPaymentMode());
	        preparedStatement.setString(7, o.getAddress());
	        
	        int affectedRows = preparedStatement.executeUpdate();
	        
	        // Retrieve generated orderId (standard JDBC pattern)
	        if (affectedRows > 0) {
	            generatedKeys = preparedStatement.getGeneratedKeys();
	            if (generatedKeys.next()) {
	                orderId = generatedKeys.getInt(1);  // Returns the auto-increment ID
	                o.setOrderId(orderId);              // Optional: set back to Order object
	            }
	        }
	        
	        return orderId;  // Return the generated order ID
	        
	    } catch (ClassNotFoundException | SQLException e) {
	        e.printStackTrace();
	        return -1;  // Error indicator
	    } finally {
	        // Clean resource closure
	        try {
	            if (generatedKeys != null) generatedKeys.close();
	            if (preparedStatement != null) preparedStatement.close();
	            if (connection != null) connection.close();
	        } catch (SQLException ignore) {}
	    }
	}

	    @Override
	    public Order getOrder(int orderId) {

	        Order order = null;

	        try {
	            Class.forName("com.mysql.cj.jdbc.Driver");
	            Connection connection = DriverManager.getConnection(
	                    "jdbc:mysql://localhost:3306/courseproject",
	                    "root",
	                    "HHHhhh1@");

	            PreparedStatement preparedStatement = connection.prepareStatement(
	                    "SELECT * FROM `Order` WHERE orderId = ?");
	            preparedStatement.setInt(1, orderId);

	            ResultSet resultSet = preparedStatement.executeQuery();

	            while (resultSet.next()) {
	                int id            = resultSet.getInt("orderId");
	                int userId        = resultSet.getInt("userId");
	                int restaurantId  = resultSet.getInt("restaurantId");
	                Timestamp orderdate = resultSet.getTimestamp("orderdate");
	                double totalamount = resultSet.getDouble("totalamount");
	                String status     = resultSet.getString("status");
	                String paymentMode= resultSet.getString("paymentMode");
	                String address= resultSet.getString("address");

	                order = new Order(id, userId, restaurantId,
	                                  orderdate, totalamount, status, paymentMode,address);
	            }

	        } catch (ClassNotFoundException e) {
	            e.printStackTrace();
	        } catch (SQLException e) {
	            e.printStackTrace();
	        }

	        return order;
	    }

	    @Override
	    public void updateOrder(Order o) {

	        try {
	            Class.forName("com.mysql.cj.jdbc.Driver");
	            Connection connection = DriverManager.getConnection(
	                    "jdbc:mysql://localhost:3306/courseproject",
	                    "root",
	                    "HHHhhh1@");

	            PreparedStatement preparedStatement = connection.prepareStatement(
	                    "UPDATE `Order` SET " +
	                    "userId = ?, " +
	                    "restaurantId = ?, " +
	                    "orderdate = ?, " +
	                    "totalamount = ?, " +
	                    "status = ?, " +
	                    "paymentMode = ? " +
	                    "WHERE orderId = ?");

	            preparedStatement.setInt(1, o.getUserId());
	            preparedStatement.setInt(2, o.getRestaurantId());
	            preparedStatement.setTimestamp(3, o.getOrderdate());
	            preparedStatement.setDouble(4, o.getTotalamount());
	            preparedStatement.setString(5, o.getStatus());
	            preparedStatement.setString(6, o.getPaymentMode());
	            preparedStatement.setInt(7, o.getOrderId());

	            preparedStatement.executeUpdate();

	        } catch (ClassNotFoundException e) {
	            e.printStackTrace();
	        } catch (SQLException e) {
	            e.printStackTrace();
	        }
	    }

	    @Override
	    public void deleteOrder(int orderId) {

	        try {
	            Class.forName("com.mysql.cj.jdbc.Driver");
	            Connection connection = DriverManager.getConnection(
	                    "jdbc:mysql://localhost:3306/courseproject",
	                    "root",
	                    "HHHhhh1@");

	            PreparedStatement preparedStatement = connection.prepareStatement(
	                    "DELETE FROM `Order` WHERE orderId = ?");
	            preparedStatement.setInt(1, orderId);

	            preparedStatement.executeUpdate();

	        } catch (ClassNotFoundException e) {
	            e.printStackTrace();
	        } catch (SQLException e) {
	            e.printStackTrace();
	        }
	    }

	    @Override
	    public List<Order> getAllOrders() {

	        ArrayList<Order> arrayList = null;

	        try {
	            Class.forName("com.mysql.cj.jdbc.Driver");
	            Connection connection = DriverManager.getConnection(
	                    "jdbc:mysql://localhost:3306/courseproject",
	                    "root",
	                    "HHHhhh1@");

	            PreparedStatement preparedStatement = connection.prepareStatement(
	                    "SELECT * FROM `Order`");
	            ResultSet resultSet = preparedStatement.executeQuery();

	            arrayList = new ArrayList<Order>();

	            while (resultSet.next()) {
	                int orderId       = resultSet.getInt("orderId");
	                int userId        = resultSet.getInt("userId");
	                int restaurantId  = resultSet.getInt("restaurantId");
	                Timestamp orderdate = resultSet.getTimestamp("orderdate");
	                double totalamount = resultSet.getDouble("totalamount");
	                String status     = resultSet.getString("status");
	                String paymentMode= resultSet.getString("paymentMode");
	                String address=resultSet.getString("address");

	                Order order = new Order(orderId, userId, restaurantId,
	                                        orderdate, totalamount, status, paymentMode,address);
	                arrayList.add(order);
	            }

	        } catch (ClassNotFoundException e) {
	            e.printStackTrace();
	        } catch (SQLException e) {
	            e.printStackTrace();
	        }

	        return arrayList;
	    }


}
