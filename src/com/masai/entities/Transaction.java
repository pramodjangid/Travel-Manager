package com.masai.entities;

import java.io.Serializable;
import java.time.LocalDate;



public class Transaction implements Serializable {

	private String username;
	private String email;
	private int tourPackageId;
	private String location;
	private String city;
	private int day;
	private double price;
	private double total;
	private LocalDate dt;
	private String packageType;
	
	public Transaction() {
		
	}


	public Transaction(String username, String email, int tourPackageId, String location, int days, double price, double total,
			LocalDate dt,String packageType) {
		super();
		this.username = username;
		this.email = email;
		this.tourPackageId = tourPackageId;
		this.location = location;
		this.day = days;
		this.price = price;
		this.total = total;
		this.dt = dt;
		this.setPackageType(packageType);
	}


	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public int getTourPackageId() {
		return tourPackageId;
	}

	public void setTourPackageId(int tourPackageId) {
		this.tourPackageId = tourPackageId;
	}

	public int getdays() {
		return day;
	}

	public void setdays(int days) {
		this.day = days;
	}

	public double getPrice() {
		return price;
	}

	public void setPrice(double price) {
		this.price = price;
	}

	public double getTotal() {
		return total;
	}

	public void setTotal(double total) {
		this.total = total;
	}

	public LocalDate getDt() {
		return dt;
	}

	public void setDt(LocalDate dt) {
		this.dt = dt;
	}
	
	public String getLocation() {
		return location;
	}

	public void setLocation(String location) {
		this.location = location;
	}


	@Override
	public String toString() {
		return "Transaction [username=" + username + ", email=" + email + ", TourPackageId=" + tourPackageId + ", location="
				+ location + ", days=" + day + ", price=" + price + ", total=" + total + ", dt=" + dt + "]";
	}





	public String getCity() {
		return city;
	}





	public void setCity(String city) {
		this.city = city;
	}





	public String getPackageType() {
		return packageType;
	}





	public void setPackageType(String packageType) {
		this.packageType = packageType;
	}





	


	
	
	
	
}
