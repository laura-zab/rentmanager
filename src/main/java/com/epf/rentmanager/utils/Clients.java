package com.epf.rentmanager.utils;

import com.epf.rentmanager.exception.ServiceException;
import com.epf.rentmanager.modele.Client;
import com.epf.rentmanager.modele.Reservation;
import com.epf.rentmanager.modele.Vehicle;
import com.epf.rentmanager.service.ClientService;
import com.epf.rentmanager.service.ReservationService;

import java.time.LocalDate;
import java.time.Period;

public class Clients {
    private static ClientService clientService;
    private static ReservationService reservationService;

    /**
     * Renvoie true si l’utilisateur passé en paramètre a un âge >= 18 ans
     * @param client L'instance d’utilisateur à tester
     * @return Résultat du test (>= 18 ans)
     */

    public static boolean isLegal(Client client) {
        return
            (Period.between(client.getNaissance(), LocalDate.now()).getYears() >= 18);
    }

    /**
     * Renvoie true si l’utilisateur passé a un nom et un prénom supérieurs à 3 lettres
     * @param client L'instance d’utilisateur à tester
     * @return Résultat du test (> 3 lettres)
     */
    public static boolean namesLengthOK(Client client) {
        return
                (client.getNom().length() >= 3 && client.getPrenom().length() >= 3);
    }

    /**
     * Vérifie que le mail du client n'est pas utilisé
     * @param client
     * @return
     */
    public static boolean emailNotInDB(Client client) throws ServiceException {
        boolean notInDB = true;
        for (Client client_db:clientService.findAll()) {
            if (client_db.getEmail() == client.getEmail()) {
                notInDB = false;
            }
        }
        return notInDB;
    }

    /**
     * Supprime les réservations du client supprimé
     * @param client
     * @return
     */
    public static long delete(Client client) throws ServiceException {
        for (Reservation reservation : reservationService.findAll()) {
            if (client.getId() == reservation.getClientId()) {
                reservationService.delete(reservation);
            }
        }
        return clientService.delete(client);
    }

    /**
     * Vérifie qu'une date est entrée
     * @param client
     * @return
     */
    public static boolean validDate(Client client) {
        return
                client.getNaissance() != null;
    }
}
