package com.masai.services;

import java.util.List;

public interface CustomerService {

	public boolean login(String email,String password, Map<String, Customer> customers) throws InvalidDetailsException;

	public void signUp(Customer cus, Map<String, Customer> customers) throws DuplicateDataException;

	public boolean buyProduct(int id, int days, int numOfOPlaces, String email, Map<Integer, TourPackage> tourPackages,
			Map<String, Customer> customers, List<Transaction> transactions)
			throws InvalidDetailsException, TourException;

	public boolean addMoneyToWallet(double amount, String email, Map<String, Customer> customers);

	public double viewWalletBalance(String email, Map<String, Customer> customers);

	public Customer viewCustomerDetails(String email, Map<String, Customer> customers);

	public List<Customer> viewAllCustomers(Map<String, Customer> customers) throws TourException;

}
