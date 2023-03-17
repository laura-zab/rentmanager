package com.epf.rentmanager.main;

import com.epf.rentmanager.configuration.AppConfiguration;
import com.epf.rentmanager.exception.ServiceException;
import com.epf.rentmanager.modele.Client;
import com.epf.rentmanager.service.ClientService;
import com.epf.rentmanager.service.ReservationService;
import com.epf.rentmanager.service.VehicleService;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import java.time.LocalDate;

public class Test {
    public static void main(String[] args) {

        ApplicationContext context = new AnnotationConfigApplicationContext(AppConfiguration.class);
        ClientService clientService = context.getBean(ClientService.class);
        VehicleService vehicleService = context.getBean(VehicleService.class);


//        Client client1 = new Client(1, "Rapp", "Nico", "nicorappcebg@epfedu.fr", LocalDate.parse("2000-10-31"));
//        System.out.println(client1);
    }
}
