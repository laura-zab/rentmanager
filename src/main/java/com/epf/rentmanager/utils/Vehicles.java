package com.epf.rentmanager.utils;

import com.epf.rentmanager.exception.ServiceException;
import com.epf.rentmanager.modele.Reservation;
import com.epf.rentmanager.modele.Vehicle;
import com.epf.rentmanager.service.ReservationService;
import com.epf.rentmanager.service.VehicleService;

public class Vehicles {

    private static VehicleService vehicleService;
    private static ReservationService reservationService;

    /**
     * Supprime les réservations du véhicule supprimé
     * @param vehicle
     * @return
     */
    public static long delete(Vehicle vehicle) throws ServiceException {
        for (Reservation reservation : reservationService.findAll()) {
            if (vehicle.getId() == reservation.getVehicleId()) {
                reservationService.delete(reservation);
            }
        }
        return vehicleService.delete(vehicle);
    }
}
