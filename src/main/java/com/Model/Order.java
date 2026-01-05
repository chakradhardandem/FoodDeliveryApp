package com.Model;

import java.sql.Timestamp;

public class Order {
	private int orderId;
	private int userId;
	private int restaurantId;
	private Timestamp orderdate;
	private double totalamount;
	private String status;
	private String paymentMode;
	private String address;
	
	
	public Order() {
		// TODO Auto-generated constructor stub
	}
	
	
	public Order(int orderId, int userId, int restaurantId, Timestamp orderdate, double totalamount, String status,
			String paymentMode, String address) {
		super();
		this.orderId = orderId;
		this.userId = userId;
		this.restaurantId = restaurantId;
		this.orderdate = orderdate;
		this.totalamount = totalamount;
		this.status = status;
		this.paymentMode = paymentMode;
		this.address = address;
	}


	public Order(int userId, int restaurantId, double totalamount, String status, String paymentMode, String address) {
		super();
		this.userId = userId;
		this.restaurantId = restaurantId;
		this.totalamount = totalamount;
		this.status = status;
		this.paymentMode = paymentMode;
		this.address = address;
	}


	public int getOrderId() {
		return orderId;
	}


	public void setOrderId(int orderId) {
		this.orderId = orderId;
	}


	public int getUserId() {
		return userId;
	}


	public void setUserId(int userId) {
		this.userId = userId;
	}


	public int getRestaurantId() {
		return restaurantId;
	}


	public void setRestaurantId(int restaurantId) {
		this.restaurantId = restaurantId;
	}


	public Timestamp getOrderdate() {
		return orderdate;
	}


	public void setOrderdate(Timestamp orderdate) {
		this.orderdate = orderdate;
	}


	public double getTotalamount() {
		return totalamount;
	}


	public void setTotalamount(double totalamount) {
		this.totalamount = totalamount;
	}


	public String getStatus() {
		return status;
	}


	public void setStatus(String status) {
		this.status = status;
	}


	public String getPaymentMode() {
		return paymentMode;
	}


	public void setPaymentMode(String paymentMode) {
		this.paymentMode = paymentMode;
	}


	public String getAddress() {
		return address;
	}


	public void setAddress(String address) {
		this.address = address;
	}


	@Override
	public String toString() {
		return "Order [orderId=" + orderId + ", userId=" + userId + ", restaurantId=" + restaurantId + ", orderdate="
				+ orderdate + ", totalamount=" + totalamount + ", status=" + status + ", paymentMode=" + paymentMode
				+ ", address=" + address + "]";
	}
	
	
	
	
}