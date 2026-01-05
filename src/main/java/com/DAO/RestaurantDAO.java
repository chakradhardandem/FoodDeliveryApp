package com.DAO;

import java.util.List;

import com.Model.Restaurant;

public interface RestaurantDAO {


	void addRestaurant(Restaurant r);
	Restaurant getRestaurant(int restaurantid);
	void updateRestaurant(Restaurant u);
	void deleteRestaurant(int restuarantid);
	List<Restaurant>getallRestaurants();

}
