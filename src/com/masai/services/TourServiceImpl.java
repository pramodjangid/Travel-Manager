package com.masai.services;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Map;

import com.masai.entities.Customer;
import com.masai.entities.TourPackage;
import com.masai.entities.Transaction;
import com.masai.exceptions.TourPackageException;
import com.masai.exceptions.TransactionException;

public class TourServiceImpl implements TourService {

	@Override
	public String addTourPackage(TourPackage tour, Map<Integer, TourPackage> tourpackages) {
		// TODO Auto-generated method stub
//as our id are always unique thats why directly putting into products
		tourpackages.put(tour.getId(), tour);

		return "TourPackage added successfully";

	}

	@Override
	public void viewAllPackages(Map<Integer, TourPackage> tourpackages) throws TourPackageException {
		// TODO Auto-generated method stub
		if (tourpackages != null && tourpackages.size() > 0) {
			for (Map.Entry<Integer, TourPackage> me : tourpackages.entrySet()) {
				System.out.println(me.getValue());
			}

		} else {
			throw new TourPackageException("TourPackage List is empty");
		}
	}

	@Override
	public void deleteTourPackage(int id, Map<Integer, TourPackage> tourPackages) throws TourPackageException {

		// System.out.println(products);
		if (tourPackages != null && tourPackages.size() > 0) {

			if (tourPackages.containsKey(id)) {
				tourPackages.remove(id);
				System.out.println("TourPackage deleted successfully");

			} else {
				throw new TourPackageException("TourPackage not found");
			}

		} else {
			throw new TourPackageException("TourPackage list is empty");
		}

	}

	@Override
	public String updateTourPackage(int id, TourPackage tour, Map<Integer, TourPackage> tourPackages) throws TourPackageException {

		if (tourPackages != null && tourPackages.size() > 0) {

			if (tourPackages.containsKey(id)) {
				tourPackages.put(id, tour);
				return "TourPackage has successfully updated";
			} else {
				throw new TourPackageException("TourPackage not found");
			}

		} else {
			throw new TourPackageException("TourPackage list is empty");
		}

	}

}
