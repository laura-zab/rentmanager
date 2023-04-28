package com.epf.rentmanager.utils;

import com.epf.rentmanager.exception.ServiceException;
import com.epf.rentmanager.modele.Client;
import com.epf.rentmanager.modele.Reservation;
import com.epf.rentmanager.service.ReservationService;
import com.epf.rentmanager.service.VehicleService;

import java.time.Period;

public class Reservations {

    private static ReservationService reservationService;
    private static VehicleService vehicleService;

    /**
     * Vérifie qu'une voiture n'est pas réservée 2 fois le même jour
     * @param reservation
     * @return
     */
    public static boolean validDate(Reservation reservation) throws ServiceException {
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
    public static boolean lessThan7Days(Reservation reservation) throws ServiceException {
        return
                Period.between(reservation.getDebut(), reservation.getFin()).getDays() <= 7;
    }

    /**
     * Vérifie qu'une voiture n'est pas réservée 30 jours de suite
     * @param reservation
     * @return
     */
    public static boolean lessThan30Days(Reservation reservation) throws ServiceException {
        boolean valid_date = true;
        int consecutive_days = 0;
        Reservation previous_reservation = reservationService.findAll().get(0);
        for (Reservation reservation_db:reservationService.findAll()) {
            if(reservation_db.getVehicleId() == reservation.getVehicleId() && previous_reservation.getVehicleId() == reservation_db.getVehicleId()) {
                consecutive_days += Period.between(reservation_db.getFin(), reservation.getDebut()).getDays();
            } else {
                consecutive_days = 0;
            }
            previous_reservation = reservation_db;
        }
        if (Period.between(previous_reservation.getFin(), reservation.getDebut()).getDays() == 1) {
            consecutive_days += Period.between(reservation.getDebut(), reservation.getFin()).getDays();
        }
        return
                consecutive_days < 30;
    }
}
