package com.epf.rentmanager.servlet;
import com.epf.rentmanager.exception.ServiceException;
import com.epf.rentmanager.modele.Client;
import com.epf.rentmanager.service.ClientService;
import com.epf.rentmanager.utils.Clients;
import com.epf.rentmanager.utils.Utils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.context.support.SpringBeanAutowiringSupport;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;


@WebServlet("/users/create")
public class ClientCreateServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;

    @Autowired
    ClientService clientService;

    private Clients clientsUtils = new Clients();
    private Utils utils = new Utils();

    @Override
    public void init() throws ServletException {
        super.init();
        SpringBeanAutowiringSupport.processInjectionBasedOnCurrentContext(this);
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        this.getServletContext().getRequestDispatcher("/WEB-INF/views/users/create.jsp").forward(request, response);
    }


    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        try {
            Client client = new Client();
            client.setNom(request.getParameter("last_name"));
            client.setPrenom(request.getParameter("first_name"));
            client.setEmail(request.getParameter("email"));
            client.setNaissance(utils.readDate(request.getParameter("naissance")));
            if (clientsUtils.isLegal(client) && clientsUtils.namesLengthOK(client)
                    && clientsUtils.emailNotInDB(client) && clientsUtils.validDate(client)) {
                clientService.create(client);
            }
        } catch (ServiceException e) {
            throw new RuntimeException(e);
        }

        this.getServletContext().getRequestDispatcher("/WEB-INF/views/users/create.jsp").forward(request, response);

    }
}
