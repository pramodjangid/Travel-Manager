package com.masai.entities;

import java.io.Serializable;
import java.util.Objects;

public class TourPackage implements Serializable{

	private int id;
	private String city;
	private String location;
	private int days;
	private double price;
	private String packageType;

	public TourPackage() {
		super();
	}

	public TourPackage(int id, String city,String location, int days, double price, String packageType) {
		super();
		this.id = id;
		this.setCity(city);
		this.location=location;
		this.setDays(days);
		this.price = price;
		this.packageType=packageType;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}
	
	public double getPrice() {
		return price;
	}

	public void setPrice(int price) {
		this.price = price;
	}
	
	public String getLocation() {
		return location;
	}

	public void setLocation(String location) {
		this.location = location;
	}
    
	public String getCity() {
		return city;
	}

	public void setCity(String city) {
		this.city = city;
	}

	public int getDays() {
		return days;
	}

	public void setDays(int days) {
		this.days = days;
	}

	public String getPackageType() {
		return packageType;
	}

	public void setPackageType(String packageType) {
		this.packageType = packageType;
	}
	

	

//	@Override
//	public String toString() {
//		return "TourPackage [id=" + id + ", City=" + city + ", Location=" + location +"Days="+days + ", price=" + price + ", PackageType=" + packageType
//				+ "]";
//	}
	
	@Override
	public String toString() {
	    return "TourPackage [id=" + id + ", city=" + city + ", location=" + location + ", days=" + days + ", price=" + price + ", packageType=" + packageType + "]";
	}


	@Override
	public int hashCode() {
		return Objects.hash(id, location, city, days, price, packageType);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		TourPackage other = (TourPackage) obj;
		return Objects.equals(location, other.location) && id == other.id && days == other.days && Objects.equals(city, other.city)
				&& Double.doubleToLongBits(price) == Double.doubleToLongBits(other.price) && Objects.equals(packageType, other.packageType);
	}

	

	

}

