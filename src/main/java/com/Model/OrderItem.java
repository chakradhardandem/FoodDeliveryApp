package com.Model;

public class OrderItem {
	private int orderItemId;
    private int orderId;
    private int menuId;
    private double totalamount;
    private int quantity;
    public OrderItem() {
		// TODO Auto-generated constructor stub
	}
	public OrderItem(int orderItemId, int orderId, int menuId, int totalamount, int quantity) {
		super();
		this.orderItemId = orderItemId;
		this.orderId = orderId;
		this.menuId = menuId;
		this.totalamount = totalamount;
		this.quantity = quantity;
	}
	public OrderItem(int orderId, int menuId, int totalamount, int quantity) {
		super();
		this.orderId = orderId;
		this.menuId = menuId;
		this.totalamount = totalamount;
		this.quantity = quantity;
	}
	public int getOrderItemId() {
		return orderItemId;
	}
	public void setOrderItemId(int orderItemId) {
		this.orderItemId = orderItemId;
	}
	public int getOrderId() {
		return orderId;
	}
	public void setOrderId(int orderId) {
		this.orderId = orderId;
	}
	public int getMenuId() {
		return menuId;
	}
	public void setMenuId(int menuId) {
		this.menuId = menuId;
	}
	public double getTotalamount() {
		return totalamount;
	}
	public void setTotalamount(double totalPrice) {
		this.totalamount = totalPrice;
	}
	public int getQuantity() {
		return quantity;
	}
	public void setQuantity(int quantity) {
		this.quantity = quantity;
	}
	@Override
	public String toString() {
		return "OrderItem [orderItemId=" + orderItemId + ", orderId=" + orderId + ", menuId=" + menuId
				+ ", totalamount=" + totalamount + ", quantity=" + quantity + "]";
	}
    
    

}
