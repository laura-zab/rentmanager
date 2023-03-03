package com.epf.rentmanager.main;

import com.epf.rentmanager.exception.ServiceException;
import com.epf.rentmanager.modele.Client;
import com.epf.rentmanager.service.ClientService;
import com.epf.rentmanager.service.ReservationService;
import com.epf.rentmanager.service.VehicleService;

import java.time.LocalDate;

public class Test {
    public static void main(String[] args) {

        try {
            System.out.println(ReservationService.getInstance().findResaByClientId(1));
        } catch (ServiceException e) {
            e.printStackTrace();
        }
//        Client client1 = new Client(1, "Rapp", "Nico", "nicorappcebg@epfedu.fr", LocalDate.parse("2000-10-31"));
//        System.out.println(client1);
    }
}
