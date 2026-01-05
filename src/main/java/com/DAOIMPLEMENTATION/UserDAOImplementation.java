package com.DAOIMPLEMENTATION;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.DAO.UserDAO;
import com.Model.User;

public class UserDAOImplementation implements UserDAO{
	
	int res;
	@Override
	public int addUser(User u) {
		
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
			Connection connection=DriverManager.getConnection("jdbc:mysql://localhost:3306/courseproject","root","HHHhhh1@");
			PreparedStatement preparedStatement=connection.prepareStatement("insert into users(username,password,email,phone,address,role) values(?,?,?,?,?,?)");
			
			preparedStatement.setString(1, u.getUsername());
			preparedStatement.setString(2, u.getPassword());
			preparedStatement.setString(3, u.getEmail());
			preparedStatement.setString(4, u.getPhone());
			preparedStatement.setString(5, u.getAddress());
			preparedStatement.setString(6,u.getRole());
			res=preparedStatement.executeUpdate();
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return res;
		
	}
	
	
	@Override
	public User getUser(String email) {
		User user=null;
		
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
			Connection connection=DriverManager.getConnection("jdbc:mysql://localhost:3306/courseproject","root","HHHhhh1@");
			PreparedStatement preparedStatement=connection.prepareStatement("select * from users where email=?");
			preparedStatement.setString(1, email);
			ResultSet resultset=preparedStatement.executeQuery();
			while(resultset.next()) {
				int userid=resultset.getInt("userid");
				String username=resultset.getString("username");
				String password=resultset.getString("password");
				String emailid=resultset.getString("email");
				String phone=resultset.getString("phone");
				String address=resultset.getString("address");
				String role=resultset.getString("role");
				user=new User(userid,username,password,emailid,phone,address,role);
				
				
			}
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return user;
	}

	@Override
	public User getUser(int id) {
        User user=null;
		
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
			Connection connection=DriverManager.getConnection("jdbc:mysql://localhost:3306/courseproject","root","HHHhhh1@");
			PreparedStatement preparedStatement=connection.prepareStatement("select * from users where userid=?");
			preparedStatement.setInt(1, id);
			ResultSet resultset=preparedStatement.executeQuery();
			while(resultset.next()) {
				int userid=resultset.getInt("userid");
				String username=resultset.getString("username");
				String password=resultset.getString("password");
				String emailid=resultset.getString("email");
				String phone=resultset.getString("phone");
				String address=resultset.getString("address");
				String role=resultset.getString("role");
				user=new User(userid,username,password,emailid,phone,address,role);
				
				
			}
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return user;
	}

	@Override
	public void updateUser(User u) {
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
			Connection connection=DriverManager.getConnection("jdbc:mysql://localhost:3306/courseproject","root","HHHhhh1@");
			PreparedStatement preparedStatement=connection.prepareStatement("update users set password=? where userid=?");
			preparedStatement.setString(1, u.getPassword());
			preparedStatement.setInt(2,u.getUserID());
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
	public int deleteUser(int id) {
		int res=0;
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
			Connection connection=DriverManager.getConnection("jdbc:mysql://localhost:3306/courseproject","root","HHHhhh1@");
			PreparedStatement preparedStatement=connection.prepareStatement("delete from users where userid=?");
			preparedStatement.setInt(1, id);
			res=preparedStatement.executeUpdate();
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return res;
		
		
	}

	@Override
	public List<User> getallUsers() {
		ArrayList<User> arrayList=null;
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
			Connection connection=DriverManager.getConnection("jdbc:mysql://localhost:3306/courseproject","root","HHHhhh1@");
			PreparedStatement preparedStatement=connection.prepareStatement("select * from users");
			ResultSet resultSet=preparedStatement.executeQuery();
			arrayList=new ArrayList<User>();
			while(resultSet.next()) {
				int id=resultSet.getInt("userid");
				String username=resultSet.getString("username");
				String password=resultSet.getString("password");
				String email=resultSet.getString("email");
				String phone=resultSet.getString("phone");
				String address=resultSet.getString("address");
				String role=resultSet.getString("role");
				User user=new User(id,username,password,email,phone,address,role);
				arrayList.add(user);
				
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
