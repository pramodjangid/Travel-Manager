package com.masai;

import java.io.FileOutputStream;

public class Main {
	// admin functionality
		private static void adminFunctionality(Scanner sc, Map<Integer, Product> products, Map<String, Customer> customers,
				List<Transaction> transactions) throws InvalidDetailsException, ProductException, TransactionException {
			// admin login

			adminLogin(sc);

			ProductService prodService = new ProductServicesImpl();
			CustomerService cusService = new CustomerServiceImpl();
			TransactionService trnsactionService = new TransactionServiceImpl();
			int choice = 0;
			try {
				do {
					System.out.println("Press 1 add the product");
					System.out.println("Press 2 view all the product");
					System.out.println("Press 3 delete the product");
					System.out.println("Press 4 update the product");
					System.out.println("Press 5 view all customers");
					System.out.println("Press 6 to view all transactions");
					System.out.println("Press 7 to log out");
					choice = sc.nextInt();

					switch (choice) {
					case 1:
						String added = adminAddProduct(sc, products, prodService);
						System.out.println(added);
						break;
					case 2:

						adminViewAllProducts(products, prodService);
						break;
					case 3:

						adminDeleteProduct(sc, products, prodService);
						break;
					case 4:

						String upt = adminUpdateProduct(sc, products, prodService);
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
}
