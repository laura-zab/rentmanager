package com.epf.rentmanager.service;

import java.util.ArrayList;
import java.util.List;

import com.epf.rentmanager.dao.ClientDao;
import com.epf.rentmanager.dao.ReservationDao;
import com.epf.rentmanager.dao.VehicleDao;
import com.epf.rentmanager.exception.DaoException;
import com.epf.rentmanager.exception.ServiceException;
import com.epf.rentmanager.modele.Client;
import com.epf.rentmanager.modele.Reservation;
import org.springframework.stereotype.Service;

@Service
public class ReservationService {

    private ReservationDao reservationDao;

    private ReservationService(ReservationDao reservationDao) {
        this.reservationDao = reservationDao;
    }

    public long create(Reservation reservation) throws ServiceException {
        try {
            return reservationDao.create(reservation);
        } catch (DaoException e) {
            throw new ServiceException();
        }
    }

    public List<Reservation> findResaByClientId(int clientId) throws ServiceException {
        if (clientId<0) {
            throw new RuntimeException("L'ID est inférieur à 0");
        }
        try {
            return reservationDao.findResaByClientId(clientId);
        } catch (DaoException e) {
            e.printStackTrace();
            throw new ServiceException();
        }
    }

    public List<Reservation> findResaByVehicleId(int clientId) throws ServiceException {
        if (clientId<0) {
            throw new RuntimeException("L'ID est inférieur à 0");
        }
        try {
            return reservationDao.findResaByVehicleId(clientId);
        } catch (DaoException e) {
            e.printStackTrace();
            throw new ServiceException();
        }
    }

    public List<Reservation> findAll() throws ServiceException {
        try {
            return reservationDao.findAll();
        } catch (DaoException e) {
            throw new ServiceException();
        }
    }

    public int count() throws ServiceException {
        return findAll().size();
    }

}
