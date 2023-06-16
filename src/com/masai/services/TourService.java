package com.masai.services;

import java.util.List;
import java.util.Map;


public interface TourService {
	public String addTourPackage(TourPackage tp, Map<Integer, TourPackage> tourPackages);

	public void viewAllTouPackages(Map<Integer, TourPackage> products) throws TourPackageException;

	public void deleteTourPackage(int id, Map<Integer, TourPackage> tourPackages) throws TourPackageException;

	public String updateTourPackage(int id, TourPackage tp, Map<Integer, TourPackage> tourPackages) throws TourPackageException;
    
}
