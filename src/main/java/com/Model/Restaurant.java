package com.Model;


public class Restaurant {
	private int restaurantID;
	private String restaurantName;
	private String cusineType;
	private int deliveryTime;
	private String address;
	private int adminUserId;
	private double rating;
	private boolean isActive;
	private String imagePath;
	
	
	public Restaurant() {
		// TODO Auto-generated constructor stub
	}


	public Restaurant(int restaurantID, String restaurantName, String cusineType, int deliveryTime,
			String address, int adminUserId, double rating, boolean isActive, String imagePath) {
		super();
		this.restaurantID = restaurantID;
		this.restaurantName = restaurantName;
		this.cusineType = cusineType;
		this.deliveryTime = deliveryTime;
		this.address = address;
		this.adminUserId = adminUserId;
		this.rating = rating;
		this.isActive = isActive;
		this.imagePath = imagePath;
	}


	public int getRestaurantID() {
		return restaurantID;
	}


	public void setRestaurantID(int restaurantID) {
		this.restaurantID = restaurantID;
	}


	public String getrestaurantName() {
		return restaurantName;
	}


	public void setrestaurantName(String restaurantName) {
		this.restaurantName = restaurantName;
	}


	public String getcusineType() {
		return cusineType;
	}


	public void setcusineType(String cusineType) {
		this.cusineType = cusineType;
	}


	public int getDeliveryTime() {
		return deliveryTime;
	}


	public void setDeliveryTime(int deliveryTime) {
		this.deliveryTime = deliveryTime;
	}


	public String getAddress() {
		return address;
	}


	public void setAddress(String address) {
		this.address = address;
	}


	public int getAdminUserId() {
		return adminUserId;
	}


	public void setAdminUserId(int adminUserId) {
		this.adminUserId = adminUserId;
	}


	public double getRating() {
		return rating;
	}


	public void setRating(double rating) {
		this.rating = rating;
	}


	public boolean isActive() {
		return isActive;
	}


	public void setActive(boolean isActive) {
		this.isActive = isActive;
	}


	public String getImagePath() {
		return imagePath;
	}


	public void setImagePath(String imagePath) {
		this.imagePath = imagePath;
	}


	@Override
	public String toString() {
		return "Restaurant [restaurantID=" + restaurantID + ", restaurantName=" + restaurantName
				+ ", cusineType=" + cusineType + ", deliveryTime=" + deliveryTime + ", address=" + address
				+ ", adminUserId=" + adminUserId + ", rating=" + rating + ", isActive=" + isActive + ", imagePath="
				+ imagePath + "]";
	}
	
	
	
	
	
	
	
	
	
	
	
	
	
	

}

