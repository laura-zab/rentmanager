package com.epf.rentmanager.utils;

import com.epf.rentmanager.exception.ServiceException;
import com.epf.rentmanager.modele.Client;
import com.epf.rentmanager.modele.Reservation;
import com.epf.rentmanager.service.ClientService;
import com.epf.rentmanager.service.ReservationService;
import com.epf.rentmanager.service.VehicleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.context.support.SpringBeanAutowiringSupport;

import java.time.Period;

@Service
public class Reservations {

    @Autowired
    ReservationService reservationService;

    public Reservations() {
        SpringBeanAutowiringSupport.processInjectionBasedOnCurrentContext(this);
    }

    /**
     * Vérifie qu'une voiture n'est pas réservée 2 fois le même jour
     * @param reservation
     * @return
     */
    public boolean validDate(Reservation reservation) throws ServiceException {
        boolean valid_date = true;
        for (Reservation reservation_db:reservationService.findAll()) {
            if(reservation_db.getVehicleId()==reservation.getVehicleId() &&
                    (reservation_db.getDebut().isBefore(reservation.getFin()) && reservation_db.getFin().isAfter(reservation.getDebut()))) {
                valid_date = false;
                break;
            }
        }
        return
                valid_date;
    }

    /**
     * Vérifie qu'une voiture n'est pas réservée 7 jours de suite par le même utilisateur
     * @param reservation
     * @return
     */
    public boolean lessThan7Days(Reservation reservation) {
        return
                Period.between(reservation.getDebut(), reservation.getFin()).getDays() <= 7;
    }

    /**
     * Vérifie qu'une voiture n'est pas réservée 30 jours de suite
     * @param reservation
     * @return
     */
    public boolean lessThan30Days(Reservation reservation) throws ServiceException {
        int consecutive_days = 0;
        Reservation previous_reservation = reservationService.findAll().get(0);
        for (Reservation reservation_db:reservationService.findAll()) {
            if(reservation_db.getVehicleId() == reservation.getVehicleId()
                    && Period.between(previous_reservation.getFin(), reservation_db.getDebut()).getDays() == 1) {
                consecutive_days += Period.between(reservation_db.getDebut(), reservation_db.getFin()).getDays();
                previous_reservation = reservation_db;
            } else {
                consecutive_days = 0;
            }
        }
        if (Period.between(previous_reservation.getFin(), reservation.getDebut()).getDays() == 1) {
            consecutive_days += Period.between(reservation.getDebut(), reservation.getFin()).getDays();
        }
        return
                consecutive_days < 30;
    }
}
