package com.masai;

import java.io.FileOutputStream;
import java.util.List;
import java.util.Map;
import java.util.Scanner;

import com.masai.exceptions.DuplicateDataException;
import com.masai.exceptions.InvalidDetailsException;
import com.masai.exceptions.TourPackageException;
import com.masai.exceptions.TransactionException;
import com.masai.entities.TourPackage;
import com.masai.entities.Customer;
import com.masai.entities.Transaction;
import com.masai.entities.User;
import com.masai.services.TourService;
import com.masai.services.CustomerService;
import com.masai.services.TransactionService;
import com.masai.services.TourServiceImpl;
import com.masai.services.CustomerServiceimpl;
import com.masai.services.TransactionServiceImpl;
import com.masai.utility.Admin;
import com.masai.utility.FileExists;
import com.masai.utility.IDGeneration;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;




public class Main {

	// admin functionality
	private static void adminFunctionality(Scanner sc, Map<Integer, TourPackage> tourPackages, Map<String, Customer> customers,
			List<Transaction> transactions) throws InvalidDetailsException, TourPackageException, TransactionException {
		// admin login

		adminLogin(sc);

		TourService tourService = new TourServiceImpl();
		CustomerService cusService = new CustomerServiceimpl();
		TransactionService trnsactionService = new TransactionServiceImpl();
		int choice = 0;
		try {
			do {
				System.out.println("========================");
				System.out.println("Press 1 To add a new TourPackage");
				System.out.println("Press 2 To view all the TourPackage");
				System.out.println("Press 3 To delete the TourPackage");
				System.out.println("Press 4 To update the TourPackage");
				System.out.println("Press 5 To view all Customers");
				System.out.println("Press 6 to view all Transactions");
				System.out.println("Press 7 to log out");
				System.out.println("========================");
				choice = sc.nextInt();

				switch (choice) {
				case 1:
					String added = adminAddTourPackage(sc, tourPackages, tourService);
					System.out.println(added);
					break;
				case 2:

					adminViewAllTourPackages(tourPackages, tourService);
					break;
				case 3:

					adminDeleteTourPackage(sc, tourPackages, tourService);
					break;
				case 4:

					String upt = adminUpdateTourPackage(sc, tourPackages, tourService);
					System.out.println(upt);
					break;
				case 5:
					adminViewAllCustomers(customers, cusService);

					break;
				case 6:
					adminViewAllTransactions(transactions, trnsactionService);
					break;
				case 7:
					System.out.println("admin has successfully logout");
					break;
				default:
					throw new IllegalArgumentException("Unexpected value: " + choice);
				}

			} while (choice <= 6);
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}
	}

	public static void adminLogin(Scanner sc) throws InvalidDetailsException {

		System.out.println("Enter the user name");
		String userName = sc.next();
		System.out.println("Enter the password");
		String password = sc.next();
		if (userName.equals(Admin.username) && password.equals(Admin.password)) {

			System.out.println("successfully login");
		} else {
			throw new InvalidDetailsException("Invalid Admin Credentials");
		}
	}

	public static String adminAddTourPackage(Scanner sc, Map<Integer, TourPackage> tourPackages, TourService tourService) {

		String str = null;
		System.out.println("please enter the tourPackage details");
		System.out.println("Enter the city name");
		String city = sc.next();
		System.out.println("Enter the location name (Do not Put Spaces)");
		String location = sc.next();
//		sc.nextLine();
		System.out.println("Enter the number of days");
		int days = sc.nextInt();
		System.out.println("Enter the tourPackage price");
		double price = sc.nextDouble();
		System.out.println("Enter the packageType----Silver/Gold/Platinum");
		String packageType = sc.next();
		
		
		
		TourPackage tour = new TourPackage(IDGeneration.generateId(), city, location, days, price,packageType);

		str = tourService.addTourPackage(tour, tourPackages);// considering all details are valid

		return str;

	}

	public static void adminViewAllTourPackages(Map<Integer, TourPackage> tourPackages, TourService tourService)
			throws TourPackageException {
		tourService.viewAllPackages(tourPackages);
	}

	public static void adminDeleteTourPackage(Scanner sc, Map<Integer, TourPackage> tourPackages, TourService tourService)
			throws TourPackageException {

		System.out.println("please enter the id of tourPackage to be deleted");
		int id = sc.nextInt();
		tourService.deleteTourPackage(id, tourPackages);
	}

	public static String adminUpdateTourPackage(Scanner sc, Map<Integer, TourPackage> tourPackages, TourService tourService)
			throws TourPackageException {
		String result = null;
		System.out.println("please enter the id of the tourPackage which is to be updated");
		int id = sc.nextInt();
		System.out.println("Enter the updated details ");

		System.out.println("Enter the city name");
		String city = sc.next();
		
		System.out.println("Enter the location name (Do not Put Spaces)");
		String location = sc.next();

		System.out.println("Enter the number of days");
		int days = sc.nextInt();

		System.out.println("Enter the tourPackage price");
		double price = sc.nextDouble();

		System.out.println("Enter the packageType----Silver/Gold/Platinum");
		String packageType = sc.next();
		
		TourPackage update = new TourPackage(id, city, location, days, price,packageType);

		result = tourService.updateTourPackage(id, update, tourPackages);
		return result;
	}

	public static void adminViewAllCustomers(Map<String, Customer> customers, CustomerService cusService)
			throws TourPackageException {
		List<Customer> list = cusService.viewAllCustomers(customers);

		for (Customer c : list) {
			System.out.println(c);
		}
	}

	public static void adminViewAllTransactions(List<Transaction> transactions, TransactionService trnsactionService)
			throws TransactionException {
		List<Transaction> allTransactions = trnsactionService.viewAllTransactions(transactions);

		for (Transaction tr : allTransactions) {
			System.out.println(tr);
		}

	}

	// customer functionality
	public static void customerFunctionality(Scanner sc, Map<String, Customer> customers,
			Map<Integer, TourPackage> tourPackages, List<Transaction> transactions)
			throws InvalidDetailsException, TransactionException {

		CustomerService cusService = new CustomerServiceimpl();
		TourService tourService = new TourServiceImpl();
		TransactionService trnsactionService = new TransactionServiceImpl();

		// Customer login
		System.out.println("please enter the following details to login");
		System.out.println("please enter the email");
		String email = sc.next();
		System.out.println("Enter the password");
		String pass = sc.next();
		customerLogin(email,pass, customers, cusService);

		try {
			int choice = 0;
			do {
				System.out.println("Select the option of your choice");
				System.out.println("Press 1 to view all tourPackages");
				System.out.println("Press 2 to book a tourPackage");
				System.out.println("Press 3 to add money to a wallet");
				System.out.println("Press 4 view wallet balance");
				System.out.println("Press 5 view my details");
				System.out.println("Press 6 view my transactions");
				System.out.println("Press 7 to logout");
				choice = sc.nextInt();

				switch (choice) {
				case 1:
					customerViewAllTourPackages(tourPackages, tourService);
					break;
				case 2:
					String result = customerBuyTourPackage(sc, email, tourPackages, customers, transactions, cusService);
					System.out.println(result);
					break;
				case 3:
					String moneyAdded = customerAddMoneyToWallet(sc, email, customers, cusService);
					System.out.println(moneyAdded);
					break;
				case 4:
					double walletBalance = customerViewWalletBalance(email, customers, cusService);
					System.out.println("Wallet balance is: " + walletBalance);
					break;
				case 5:
					customerViewMyDetails(email, customers, cusService);
					break;
				case 6:
					customerViewCustomerTransactions(email, transactions, trnsactionService);
					break;
				case 7:
					System.out.println("you have successsfully logout");
					break;
				default:
					System.out.println("invalid choice");
					break;
				}

			} while (choice <= 6);
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}
	}

	public static void customerSignup(Scanner sc, Map<String, Customer> customers) throws DuplicateDataException {
		System.out.println("please enter the following details to Signup");
		System.out.println("please enter the user name");
		String name = sc.next();
		System.out.println("Enter the password");
		String pass = sc.next();
		System.out.println("enter the address");
		String address = sc.next();
		System.out.println("Enter the email id");
		String email = sc.next();
		System.out.println("Enter the balance to be added into the wallet");
		double balance = sc.nextDouble();
		Customer cus = new Customer(balance, name, pass, address, email);

		CustomerService cusService = new CustomerServiceimpl();
		cusService.signUp(cus, customers);
		System.out.println("customer has Succefully sign up");

	}

	public static void customerLogin(String email,String pass, Map<String, Customer> customers, CustomerService cusService)
			throws InvalidDetailsException {
		cusService.login(email, pass,customers);
		System.out.println("Customer has successfully login");

	}

	public static void customerViewAllTourPackages(Map<Integer, TourPackage> tourPackages, TourService tourService)
			throws TourPackageException {
		tourService.viewAllPackages(tourPackages);
	}

	public static String customerBuyTourPackage(Scanner sc, String email, Map<Integer, TourPackage> tourPackages,
			Map<String, Customer> customers, List<Transaction> transactions, CustomerService cusService)
			throws InvalidDetailsException, TourPackageException {
		System.out.println("Enter the TourPackage id");
		int id = sc.nextInt();
		System.out.println("enter the city name");
		String city = sc.next();
		
		System.out.println("enter the number of days for your trip");
		int days = sc.nextInt();
		cusService.buyPackage(id, days, city, email, tourPackages, customers, transactions);
		
		return "You have successfully booked a package for "+ city;

	}

	public static String customerAddMoneyToWallet(Scanner sc, String email, Map<String, Customer> customers,
			CustomerService cusService) {
		System.out.println("please enter the amount");
		double money = sc.nextDouble();
		boolean added = cusService.addMoneyToWallet(money, email, customers);

		return "Amount: " + money + " successfully added to your wallet";
	}

	public static double customerViewWalletBalance(String email, Map<String, Customer> customers,
			CustomerService cusService) {
		double walletBalance = cusService.viewWalletBalance(email, customers);
		return walletBalance;
	}

	public static void customerViewMyDetails(String email, Map<String, Customer> customers,
			CustomerService cusService) {
		Customer cus = cusService.viewCustomerDetails(email, customers);
		System.out.println("name : " + cus.getUsername());
		System.out.println("address : " + cus.getAddress());
		System.out.println("email : " + cus.getEmail());
		System.out.println("wallet balance : " + cus.getWalletBalance());
	}

	public static void customerViewCustomerTransactions(String email, List<Transaction> transactions,
			TransactionService trnsactionService) throws TransactionException {
		List<Transaction> myTransactions = trnsactionService.viewCustomerTransactions(email, transactions);

		for (Transaction tr : myTransactions) {
			System.out.println(tr);
		}
	}

	
	
	public static void main(String[] args) {
//file check
		Map<Integer, TourPackage> tourPackages = FileExists.tourPackagesFile();
		Map<String, Customer> customers = FileExists.customerFile();
		List<Transaction> transactions = FileExists.transactionFile();
//		System.out.println(tourPackages.size());
//		System.out.println(customers.size());
//		System.out.println(transactions.size());

		Scanner sc = new Scanner(System.in);

		System.out.println("----------Welcome , I am Your Travel Manager-----");

		try {

			int preference = 0;
			do {
				System.out.println("----------Please enter your preference-----------");
				System.out.println();
				System.out.println();
				System.out.println("----------Press 1 for --> Admin login------------");
				System.out.println("----------Press 2 for --> Customer login---------");
				System.out.println("----------Press 3 for --> Customer signup--------");
				System.out.println("----------Press 4 for --> exit the application---");
				preference = sc.nextInt();
				switch (preference) {
				case 1:
					adminFunctionality(sc, tourPackages, customers, transactions);
					break;
				case 2:
					customerFunctionality(sc, customers, tourPackages, transactions);
					break;

				case 3:
					customerSignup(sc, customers);
					break;

				case 0:
					System.out.println("successfully existed from the system");

					break;

				default:
					throw new IllegalArgumentException("Invalid Selection");
				}

			}

			while (preference != 0);

		} catch (Exception e) {

			System.out.println(e.getMessage());
		} finally {
			// serialization (finally always executed)
			try {
				ObjectOutputStream poos = new ObjectOutputStream(new FileOutputStream("TourPackage.ser"));
				poos.writeObject(tourPackages);
				ObjectOutputStream coos = new ObjectOutputStream(new FileOutputStream("Customer.ser"));
				coos.writeObject(customers);

				ObjectOutputStream toos = new ObjectOutputStream(new FileOutputStream("Transactions.ser"));
				toos.writeObject(transactions);
			//	System.out.println("serialized..........");
			} catch (Exception e) {
				// TODO: handle exception
				System.out.println(e.getMessage());
			}
		}

	}

}


