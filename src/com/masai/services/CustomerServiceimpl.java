package com.masai.services;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.text.ParseException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Collection;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import com.masai.entities.Customer;
import com.masai.entities.TourPackage;
import com.masai.entities.Transaction;
import com.masai.exceptions.DuplicateDataException;
import com.masai.exceptions.InvalidDetailsException;
import com.masai.exceptions.TourPackageException;
import com.masai.exceptions.TransactionException;

public class CustomerServiceimpl implements CustomerService {

	@Override
	public void signUp(Customer cus, Map<String, Customer> customers) throws DuplicateDataException {

		if (customers.containsKey(cus.getEmail())) {
			throw new DuplicateDataException("Customer already exists , please login");
		} else {

			customers.put(cus.getEmail(), cus);

		}

	}

	@Override
	public boolean login(String email,String password, Map<String, Customer> customers) throws InvalidDetailsException {

		if (customers.containsKey(email) ) {
			
			if(customers.get(email).getPassword().equals(password)) {
				return true;
			}
			else {
				throw new InvalidDetailsException("Invalid Credentials");
			}
			
		} else {
			throw new InvalidDetailsException("you have not sign up yet, please signup");
		}

	}

	@Override
	public boolean buyPackage(int id, int days,String city, String email, Map<Integer, TourPackage> tourPackages,
			Map<String, Customer> customers, List<Transaction> transactions)
			throws InvalidDetailsException, TourPackageException {

		if (tourPackages.size() == 0)
			throw new TourPackageException("TourPackage list is empty");

		if (tourPackages.containsKey(id)) {

			TourPackage tour = tourPackages.get(id);

//			if (tour.getCity() == city) {

				Customer cus = customers.get(email);
				
//				if(tourPackages.get("packageType").equals("Silver")) {
//					double buyingPrice = days * tour.getPrice();
//				}
//				else if(tourPackages.get("packageType").equals("Gold")) {
//					double buyingPrice = days * tour.getPrice() * 1.5;
//				}
//				else if(tourPackages.get("packageType").equals("Platinum")) {
//					double buyingPrice = days * tour.getPrice()* 3.0;
//				}

				double buyingPrice = days * tour.getPrice();

				if (cus.getWalletBalance() >= buyingPrice) {
					cus.setWalletBalance(cus.getWalletBalance() - buyingPrice);

					tourPackages.put(id, tour);

					Transaction tr = new Transaction(cus.getUsername(), email, id,tour.getLocation(), days, tour.getPrice(),
							tour.getPrice() * days, LocalDate.now(),tour.getPackageType());

					transactions.add(tr);

				} else {
					throw new InvalidDetailsException("wallet balance is not sufficient");
				}

//			} else {
//				throw new InvalidDetailsException("TourPackage is not available for this city");
//			}

		} else {
			throw new InvalidDetailsException("TourPackage is not available with id: " + id);
		}

		return false;
	}

	@Override
	public boolean addMoneyToWallet(double amount, String email, Map<String, Customer> customers) {
		// TODO Auto-generated method stub

		Customer cus = customers.get(email);

		cus.setWalletBalance(cus.getWalletBalance() + amount);

		customers.put(email, cus);

		return true;
	}

	@Override
	public double viewWalletBalance(String email, Map<String, Customer> customers) {
		// TODO Auto-generated method stub

		Customer cus = customers.get(email);

		return cus.getWalletBalance();
	}

	@Override
	public Customer viewCustomerDetails(String email, Map<String, Customer> customers) {

		if (customers.containsKey(email)) {

			return customers.get(email);

		}

		return null;
	}

	@Override
	public List<Customer> viewAllCustomers(Map<String, Customer> customers) throws TourPackageException {
		// TODO Auto-generated method stub
		List<Customer> list = null;

		if (customers != null && customers.size() > 0) {
			Collection<Customer> coll = customers.values();
			list = new ArrayList<>(coll);
		} else {
			throw new TourPackageException("Customer list is empty");
		}

		return list;
	}

	

}
