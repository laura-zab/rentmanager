package com.epf.rentmanager.servlet;
import com.epf.rentmanager.exception.ServiceException;
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
import java.util.ArrayList;
import java.util.List;

@WebServlet("/users/details")
public class ClientDetailsServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;

    @Autowired
    ClientService clientService;

    @Autowired
    ReservationService reservationService;

    @Autowired
    VehicleService vehicleService;

    @Override
    public void init() throws ServletException {
        super.init();
        SpringBeanAutowiringSupport.processInjectionBasedOnCurrentContext(this);
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        try {
            int id = Integer.valueOf(request.getParameter("id"));
            List<Reservation> rents = reservationService.findResaByClientId(id);
            List<Vehicle> vehicles = new ArrayList<>();
            for (Reservation rent : rents) {
                vehicles.add(vehicleService.findById(rent.getVehicleId()));
            }
            request.setAttribute("rents", rents);
            request.setAttribute("vehicles", vehicles);
            request.setAttribute("vehicleService", vehicleService);
            request.setAttribute("client", clientService.findById(id));
        } catch (ServiceException e) {
            throw new RuntimeException(e);
        }

        this.getServletContext().getRequestDispatcher("/WEB-INF/views/users/details.jsp").forward(request, response);
    }
}
