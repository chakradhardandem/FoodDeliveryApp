package com.DAOIMPLEMENTATION;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.DAO.MenuDAO;
import com.Model.Menu;

public class MenuDAOImplementation implements MenuDAO{

	@Override
	public void addMenu(Menu m) {
		 try {
		        Class.forName("com.mysql.cj.jdbc.Driver");
		        Connection connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/courseproject", "root", "HHHhhh1@");

		        PreparedStatement preparedstatement = connection.prepareStatement(
		            "insert into menu(menuId,restaurantId,itemName,description,price,isAvailable,imagePath) "
		          + "values(?,?,?,?,?,?,?)");

		        preparedstatement.setInt(1, m.getMenuId());
		        preparedstatement.setInt(2, m.getRestaurantId());
		        preparedstatement.setString(3, m.getItemName());
		        preparedstatement.setString(4, m.getDescription());
		        preparedstatement.setDouble(5, m.getPrice());
		        preparedstatement.setBoolean(6, m.isAvailable());
		        preparedstatement.setString(7, m.getImagePath());

		        preparedstatement.executeUpdate();
		    } catch (ClassNotFoundException | SQLException e) {
		        e.printStackTrace();
		    }
		
	}


	@Override
	public Menu getMenu(int menuId) {
		 Menu menu = null;

		    try {
		        Class.forName("com.mysql.cj.jdbc.Driver");
		        Connection connection = DriverManager.getConnection( "jdbc:mysql://localhost:3306/courseproject","root","HHHhhh1@");

		        PreparedStatement preparedStatement =connection.prepareStatement("SELECT * FROM Menu WHERE menuId = ?");
		        preparedStatement.setInt(1, menuId);

		        ResultSet resultSet = preparedStatement.executeQuery();

		        while (resultSet.next()) {
		            int menuid             = resultSet.getInt("menuId");
		            int restaurantId   = resultSet.getInt("restaurantId");
		            String itemName    = resultSet.getString("itemName");
		            String description = resultSet.getString("description");
		            double price       = resultSet.getDouble("price");
		            boolean isAvailable= resultSet.getBoolean("isAvailable");
		            String imagePath   = resultSet.getString("imagePath");

		            menu = new Menu(menuid, restaurantId, itemName,
		                            description, price, isAvailable, imagePath);
		        }

		    } catch (ClassNotFoundException e) {
		        e.printStackTrace();
		    } catch (SQLException e) {
		        e.printStackTrace();
		    }

		    return menu; 
	}


	@Override
	public void updateMenu(Menu m) {

	    try {
	        Class.forName("com.mysql.cj.jdbc.Driver");
	        Connection connection = DriverManager.getConnection(
	                "jdbc:mysql://localhost:3306/courseproject",
	                "root",
	                "HHHhhh1@");

	        PreparedStatement preparedStatement =
	                connection.prepareStatement(
	                    "UPDATE Menu SET itemName = ? WHERE menuId = ?");

	        preparedStatement.setString(1, m.getItemName());
	        preparedStatement.setInt(2, m.getMenuId());

	        preparedStatement.executeUpdate();

	    } catch (ClassNotFoundException e) {
	        e.printStackTrace();
	    } catch (SQLException e) {
	        e.printStackTrace();
	    }
	}



	@Override
	public void deleteMenu(int menuId) {
	    try {
	        Class.forName("com.mysql.cj.jdbc.Driver");
	        Connection connection = DriverManager.getConnection(
	                "jdbc:mysql://localhost:3306/courseproject",
	                "root",
	                "HHHhhh1@");

	        PreparedStatement preparedStatement =
	                connection.prepareStatement("DELETE FROM Menu WHERE menuId = ?");
	        preparedStatement.setInt(1, menuId);

	        preparedStatement.executeUpdate();

	    } catch (ClassNotFoundException e) {
	        e.printStackTrace();
	    } catch (SQLException e) {
	        e.printStackTrace();
	    }
	}

	@Override
	public List<Menu> getAllMenus() {
	    ArrayList<Menu> arrayList = null;

	    try {
	        Class.forName("com.mysql.cj.jdbc.Driver");
	        Connection connection = DriverManager.getConnection(
	                "jdbc:mysql://localhost:3306/courseproject",
	                "root",
	                "HHHhhh1@");

	        PreparedStatement preparedStatement =
	                connection.prepareStatement("SELECT * FROM Menu");
	        ResultSet resultSet = preparedStatement.executeQuery();

	        arrayList = new ArrayList<Menu>();

	        while (resultSet.next()) {
	            int menuId        = resultSet.getInt("menuId");
	            int restaurantId  = resultSet.getInt("restaurantId");
	            String itemName   = resultSet.getString("itemName");
	            String description= resultSet.getString("description");
	            double price      = resultSet.getDouble("price");
	            boolean isAvailable = resultSet.getBoolean("isAvailable");
	            String imagePath  = resultSet.getString("imagePath");

	            Menu menu = new Menu(menuId, restaurantId, itemName,
	                                 description, price, isAvailable, imagePath);
	            arrayList.add(menu);
	        }

	    } catch (ClassNotFoundException e) {
	        e.printStackTrace();
	    } catch (SQLException e) {
	        e.printStackTrace();
	    }

	    return arrayList;
	}


	@Override
	public List<Menu> getAllMenusByRestaurant(int restaurant_Id) {
		ArrayList<Menu> arrayList = null;

	    try {
	        Class.forName("com.mysql.cj.jdbc.Driver");
	        Connection connection = DriverManager.getConnection(
	                "jdbc:mysql://localhost:3306/courseproject",
	                "root",
	                "HHHhhh1@");

	        PreparedStatement preparedStatement =
	                connection.prepareStatement("SELECT * FROM Menu where restaurantId=?");
	        preparedStatement.setInt(1, restaurant_Id);
	        ResultSet resultSet = preparedStatement.executeQuery();

	        arrayList = new ArrayList<Menu>();

	        while (resultSet.next()) {
	            int menuId        = resultSet.getInt("menuId");
	            int restaurantId  = resultSet.getInt("restaurantId");
	            String itemName   = resultSet.getString("itemName");
	            String description= resultSet.getString("description");
	            double price      = resultSet.getDouble("price");
	            boolean isAvailable = resultSet.getBoolean("isAvailable");
	            String imagePath  = resultSet.getString("imagePath");

	            Menu menu = new Menu(menuId, restaurantId, itemName,
	                                 description, price, isAvailable, imagePath);
	            arrayList.add(menu);
	        }

	    } catch (ClassNotFoundException e) {
	        e.printStackTrace();
	    } catch (SQLException e) {
	        e.printStackTrace();
	    }

	    return arrayList;
	}
	

}
