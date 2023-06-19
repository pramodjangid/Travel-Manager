package com.masai.services;

import java.util.List;
import java.util.Map;

import com.masai.entities.Customer;
import com.masai.entities.TourPackage;
import com.masai.entities.Transaction;
import com.masai.exceptions.DuplicateDataException;
import com.masai.exceptions.InvalidDetailsException;
import com.masai.exceptions.TourPackageException;

public interface CustomerService {

	public boolean login(String email,String password, Map<String, Customer> customers) throws InvalidDetailsException;

	public void signUp(Customer cus, Map<String, Customer> customers) throws DuplicateDataException;

	

	public boolean addMoneyToWallet(double amount, String email, Map<String, Customer> customers);

	public double viewWalletBalance(String email, Map<String, Customer> customers);

	public Customer viewCustomerDetails(String email, Map<String, Customer> customers);

	public List<Customer> viewAllCustomers(Map<String, Customer> customers) throws TourPackageException;

	boolean buyPackage(int id, int days,String city, String email,
			Map<Integer, TourPackage> tourPackages, Map<String, Customer> customers,
			List<Transaction> transactions) throws InvalidDetailsException, TourPackageException;

}
