package epf.service;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import com.epf.rentmanager.dao.ClientDao;
import com.epf.rentmanager.dao.ReservationDao;
import com.epf.rentmanager.dao.VehicleDao;
import com.epf.rentmanager.exception.DaoException;
import com.epf.rentmanager.exception.ServiceException;
import com.epf.rentmanager.modele.Client;
import com.epf.rentmanager.service.ClientService;
import com.epf.rentmanager.service.ReservationService;
import com.epf.rentmanager.service.VehicleService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.when;

/**
 * Unit test for simple App.
 */

@RunWith(MockitoJUnitRunner.class)
public class ClientServiceTest
{
    @InjectMocks
    private ClientService clientService;
    @Mock
    private ClientDao clientDao;
    @Mock
    private Client client;

    @Test
    public void findAll_should_fail_when_dao_throws_exception() throws DaoException {
        when(this.clientDao.findAll()).thenThrow(DaoException.class);
        assertThrows(ServiceException.class, () -> clientService.findAll());
    }

    @Test
    public void findById_should_fail_when_dao_throws_exception() throws DaoException {
        when(this.clientDao.findById(client.getId())).thenThrow(DaoException.class);
        assertThrows(ServiceException.class, () -> clientService.findById(client.getId()));
    }

//    @Test
//    public void findById_creates_client_in_db() throws ServiceException {
//        Client client_cree = new Client(1, "Doe", "John", "johndoe@mail.com", LocalDate.parse("1998-01-01"));
//        clientService.create(client_cree);
//        Client client_trouve = clientService.findById(client_cree.getId());
//        assertEquals(client_cree, client_trouve);
//    }
}
