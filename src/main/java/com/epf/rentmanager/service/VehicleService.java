package com.epf.rentmanager.service;

import java.util.ArrayList;
import java.util.List;

import com.epf.rentmanager.exception.DaoException;
import com.epf.rentmanager.exception.ServiceException;
import com.epf.rentmanager.modele.Client;
import com.epf.rentmanager.modele.Vehicle;
import com.epf.rentmanager.dao.ClientDao;
import com.epf.rentmanager.dao.VehicleDao;
import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;

@Service

public class VehicleService {

	private VehicleDao vehicleDao;

	private VehicleService(VehicleDao vehicleDao) {
		this.vehicleDao = vehicleDao;
	}
	
	public long create(Vehicle vehicle) throws ServiceException {
		try {
			return vehicleDao.create(vehicle);
		} catch (DaoException e) {
			e.printStackTrace();
			throw new ServiceException();
		}
	}

	public Vehicle findById(long id) throws ServiceException {
		if (id<0) {
			throw new RuntimeException("L'ID est inférieur à 0");
		}
		try {
			return vehicleDao.findById(id);
		} catch (DaoException e) {
			e.printStackTrace();
			throw new ServiceException();
		}
	}

	public List<Vehicle> findAll() throws ServiceException {
		try {
			return vehicleDao.findAll();
		} catch (DaoException e) {
			throw new ServiceException();
		}
	}

	public int count() throws ServiceException {
		return findAll().size();
	}
	
}
