package com.DAOIMPLEMENTATION;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.DAO.RestaurantDAO;
import com.Model.Restaurant;

public class RestaurantDAOImplementation implements RestaurantDAO{
	 public void addRestaurant(Restaurant r) {
			
			
			try {
				Class.forName("com.mysql.cj.jdbc.Driver");
				Connection connection=DriverManager.getConnection("jdbc:mysql://localhost:3306/courseproject","root","HHHhhh1@");
				PreparedStatement preparedStatement=connection.prepareStatement("insert into restaurant(restaurant_id,restaurant_name,cusine_type,deliverytime,address,admin_userid,rating,is_active,imagepath) values(?,?,?,?,?,?,?,?,?)");
				preparedStatement.setInt(1, r.getRestaurantID());
				preparedStatement.setString(2, r.getrestaurantName());
				preparedStatement.setString(3, r.getcusineType());
				preparedStatement.setInt(4, r.getDeliveryTime());
				preparedStatement.setString(5, r.getAddress());
				preparedStatement.setInt(6, r.getAdminUserId());
				preparedStatement.setDouble(7, r.getRating());
				preparedStatement.setBoolean(8, r.isActive());
				preparedStatement.setString(9, r.getImagePath());
				preparedStatement.executeUpdate();
				
			} catch (ClassNotFoundException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
			
		}

		@Override
		public Restaurant getRestaurant(int restaurantid) {

			 Restaurant r=null;
				
				try {
					Class.forName("com.mysql.cj.jdbc.Driver");
					Connection connection=DriverManager.getConnection("jdbc:mysql://localhost:3306/courseproject","root","HHHhhh1@");
					PreparedStatement preparedStatement=connection.prepareStatement("select * from restaurant where restaurant_id=?");
					preparedStatement.setInt(1, restaurantid);
					ResultSet resultset=preparedStatement.executeQuery();
					while(resultset.next()) {
						int restaurantID      = resultset.getInt("restaurant_id");
						String restaurantName = resultset.getString("restaurant_name");
						String cusineType     = resultset.getString("cusine_type");
						int deliveryTime      = resultset.getInt("deliverytime");
						String address        = resultset.getString("address");
						int adminUserId       = resultset.getInt("admin_userid");
						double rating         = resultset.getDouble("rating");
						boolean isActive      = resultset.getBoolean("is_active");
						String imagePath      = resultset.getString("imagepath");
						
						
						
					    r=new Restaurant(restaurantID,restaurantName,cusineType,deliveryTime,address,adminUserId,rating,isActive,imagePath);
						
						
					}
				} catch (ClassNotFoundException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				
				return r;
		}

		@Override
		public void updateRestaurant(Restaurant u) {
			try {
				Class.forName("com.mysql.cj.jdbc.Driver");
				Connection connection=DriverManager.getConnection("jdbc:mysql://localhost:3306/courseproject","root","HHHhhh1@");
				PreparedStatement preparedStatement=connection.prepareStatement("update restaurant set restaurant_name=? where restaurant_id=?");
				preparedStatement.setString(1, u.getrestaurantName());
				preparedStatement.setInt(2,u.getRestaurantID());
				preparedStatement.executeUpdate();
				
			} catch (ClassNotFoundException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
		}

		@Override
		public void deleteRestaurant(int restuarantid) {
			
			try {
				Class.forName("com.mysql.cj.jdbc.Driver");
				Connection connection=DriverManager.getConnection("jdbc:mysql://localhost:3306/courseproject","root","HHHhhh1@");
				PreparedStatement preparedStatement=connection.prepareStatement("delete from restaurant where restaurant_id=?");
				preparedStatement.setInt(1, restuarantid);
				preparedStatement.executeUpdate();
			} catch (ClassNotFoundException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
			
		}

		@Override
		public List<Restaurant> getallRestaurants() {
			ArrayList<Restaurant> arrayList=null;
			try {
				Class.forName("com.mysql.cj.jdbc.Driver");
				Connection connection=DriverManager.getConnection("jdbc:mysql://localhost:3306/courseproject","root","HHHhhh1@");
				PreparedStatement preparedStatement=connection.prepareStatement("select * from Restaurant");
				ResultSet resultset=preparedStatement.executeQuery();
				arrayList=new ArrayList<Restaurant>();
				while(resultset.next()) {
					int restaurantID      = resultset.getInt("restaurant_id");
					String restaurantName = resultset.getString("restaurant_name");
					String cusineType     = resultset.getString("cusine_type");
					int deliveryTime      = resultset.getInt("deliverytime");
					String address        = resultset.getString("address");
					int adminUserId       = resultset.getInt("admin_userid");
					double rating         = resultset.getDouble("rating");
					boolean isActive      = resultset.getBoolean("is_active");
					String imagePath      = resultset.getString("imagepath");
					
					
					
				    Restaurant restaurant=new Restaurant(restaurantID,restaurantName,cusineType,deliveryTime,address,adminUserId,rating,isActive,imagePath);
					arrayList.add(restaurant);
					
					
					
				}
			} catch (ClassNotFoundException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			return arrayList;
		}

}
