package com.epf.rentmanager.service;

import java.util.ArrayList;
import java.util.List;

import com.epf.rentmanager.dao.ClientDao;
import com.epf.rentmanager.dao.VehicleDao;
import com.epf.rentmanager.exception.DaoException;
import com.epf.rentmanager.exception.ServiceException;
import com.epf.rentmanager.modele.Client;
import org.springframework.stereotype.Service;

@Service
public class ClientService {

	private ClientDao clientDao;

	private ClientService(ClientDao clientDao) {
		this.clientDao = clientDao;
	}
	
	
	public long create(Client client) throws ServiceException {
		try {
			return clientDao.create(client);
		} catch (DaoException e) {
			throw new ServiceException();
		}
	}

	public long delete(Client client) throws ServiceException {
		try {
			return clientDao.delete(client);
		} catch (DaoException e) {
			throw new ServiceException();
		}
	}

	public long edit(Client client) throws ServiceException {
		try {
			return clientDao.edit(client);
		} catch (DaoException e) {
			throw new ServiceException();
		}
	}

	public Client findById(long id) throws ServiceException {
		if (id<0) {
			throw new RuntimeException("L'ID est inférieur à 0");
		}
		try {
			return clientDao.findById(id);
		} catch (DaoException e) {
			e.printStackTrace();
			throw new ServiceException();
		}
	}

	public List<Client> findAll() throws ServiceException {
		try {
			return clientDao.findAll();
		} catch (DaoException e) {
			throw new ServiceException();
		}
	}

	public int count() throws ServiceException {
		return findAll().size();
	}
	
}
