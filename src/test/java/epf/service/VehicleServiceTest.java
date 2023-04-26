package epf.service;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import com.epf.rentmanager.dao.ClientDao;
import com.epf.rentmanager.dao.ReservationDao;
import com.epf.rentmanager.dao.VehicleDao;
import com.epf.rentmanager.exception.DaoException;
import com.epf.rentmanager.exception.ServiceException;
import com.epf.rentmanager.modele.Client;
import com.epf.rentmanager.modele.Vehicle;
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
public class VehicleServiceTest
{
    @InjectMocks
    private VehicleService vehicleService;
    @Mock
    private VehicleDao vehicleDao;
    @Mock
    private Vehicle vehicle;

    @Test
    public void findAll_should_fail_when_dao_throws_exception() throws DaoException {
        when(this.vehicleDao.findAll()).thenThrow(DaoException.class);
        assertThrows(ServiceException.class, () -> vehicleService.findAll());
    }

    @Test
    public void findById_should_fail_when_dao_throws_exception() throws DaoException {
        when(this.vehicleDao.findById(vehicle.getId())).thenThrow(DaoException.class);
        assertThrows(ServiceException.class, () -> vehicleService.findById(vehicle.getId()));
    }

}
