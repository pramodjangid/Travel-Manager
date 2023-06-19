package com.masai.services;

import java.util.List;
import java.util.Map;

import com.masai.entities.TourPackage;
import com.masai.exceptions.TourPackageException;


public interface TourService {
	public String addTourPackage(TourPackage tour, Map<Integer, TourPackage> tourpackages);

	public void viewAllPackages(Map<Integer, TourPackage> tourpackages) throws TourPackageException;

	public void deleteTourPackage(int id, Map<Integer, TourPackage> tourPackages) throws TourPackageException;

	public String updateTourPackage(int id, TourPackage tour, Map<Integer, TourPackage> tourPackages) throws TourPackageException;
    
}
