package com.epf.rentmanager.servlet;
import com.epf.rentmanager.exception.ServiceException;
import com.epf.rentmanager.modele.Client;
import com.epf.rentmanager.modele.Reservation;
import com.epf.rentmanager.modele.Vehicle;
import com.epf.rentmanager.service.ClientService;
import com.epf.rentmanager.service.ReservationService;
import com.epf.rentmanager.service.VehicleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.context.support.SpringBeanAutowiringSupport;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.time.LocalDate;

@WebServlet("/vehicles/delete")
public class VehicleDeleteServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;

    @Autowired
    VehicleService vehicleService;

    @Autowired
    ReservationService reservationService;

    @Override
    public void init() throws ServletException {
        super.init();
        SpringBeanAutowiringSupport.processInjectionBasedOnCurrentContext(this);
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        try {
            int id = Integer.valueOf(request.getParameter("id"));
            Vehicle vehicle = vehicleService.findById(id);
            boolean present = false;
            for (Reservation reservation : reservationService.findAll()) {
                if (id == reservation.getVehicleId()) {
                    present = true;
                    break;
                }
            }
            if (!present) {
                vehicleService.delete(vehicle);
            }
            request.setAttribute("vehicles", vehicleService.findAll());
        } catch (ServiceException e) {
            throw new RuntimeException(e);
        }
        response.sendRedirect("../cars");    }
}