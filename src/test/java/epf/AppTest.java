package epf;

import static org.junit.Assert.assertTrue;

import com.epf.rentmanager.dao.ClientDao;
import com.epf.rentmanager.dao.ReservationDao;
import com.epf.rentmanager.dao.VehicleDao;
import com.epf.rentmanager.exception.DaoException;
import com.epf.rentmanager.exception.ServiceException;
import com.epf.rentmanager.service.ClientService;
import com.epf.rentmanager.service.ReservationService;
import com.epf.rentmanager.service.VehicleService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.when;

/**
 * Unit test for simple App.
 */

@RunWith(MockitoJUnitRunner.class)
public class AppTest 
{
    /**
     * Rigorous Test :-)
     */
    @InjectMocks
    private ClientService clientService;
    @Mock
    private ClientDao clientDao;
    @InjectMocks
    private VehicleService vehicleService;
    @Mock
    private VehicleDao vehicleDao;
    @InjectMocks
    private ReservationService reservationService;
    @Mock
    private ReservationDao reservationDao;

    @Test
    public void shouldAnswerWithTrue()
    {
        assertTrue( true );
    }

    @Test
    public void clientService_methods_should_fail_when_dao_throws_exception() throws DaoException {
        when(this.clientDao.findAll()).thenThrow(DaoException.class);
        assertThrows(ServiceException.class, () -> clientService.findAll());
    }

    @Test
    public void vehicleService_findAll_should_fail_when_dao_throws_exception() throws DaoException {
        when(this.vehicleDao.findAll()).thenThrow(DaoException.class);
        assertThrows(ServiceException.class, () -> vehicleService.findAll());
    }

    @Test
    public void reservationService_findAll_should_fail_when_dao_throws_exception() throws DaoException {
        when(this.reservationDao.findAll()).thenThrow(DaoException.class);
        assertThrows(ServiceException.class, () -> reservationService.findAll());
    }
}
