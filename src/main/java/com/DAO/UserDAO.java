package com.DAO;

import java.util.List;

import com.Model.User;

public interface UserDAO {
	int addUser(User u);
	User getUser(int id);
	User getUser(String email);
	void updateUser(User u);
	int deleteUser(int id);
	List<User> getallUsers();

}
