package epf;

import com.epf.rentmanager.dao.ClientDao;
import com.epf.rentmanager.exception.DaoException;
import com.epf.rentmanager.exception.ServiceException;
import com.epf.rentmanager.modele.Client;
import com.epf.rentmanager.modele.Vehicle;
import com.epf.rentmanager.service.ClientService;
import com.epf.rentmanager.utils.Clients;
import com.epf.rentmanager.utils.Vehicles;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

import java.time.LocalDate;

import static org.junit.Assert.*;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.when;

/**
 * Unit test for Vehicle class
 */

@RunWith(MockitoJUnitRunner.class)
public class VehicleTest
{
    @InjectMocks
    private Vehicles vehicles;

    @Test
    public void validVehicle_should_return_true_when_seats_are_between_2_and_9_and_there_is_a_manufacturer() {
        Vehicle vehicleOk = new Vehicle(1, "Fiat", 3);
        assertTrue(vehicles.validVehicle(vehicleOk));
    }

    @Test
    public void validVehicle_should_return_false_when_seats_are_more_than_9() {
        Vehicle vehicleSeatsNotOk = new Vehicle(1, "Fiat", 10);
        assertFalse(vehicles.validVehicle(vehicleSeatsNotOk));
    }

    @Test
    public void validVehicle_should_return_false_when_seats_are_less_than_2() {
        Vehicle vehicleSeatsNotOk = new Vehicle(1, "Fiat", 1);
        assertFalse(vehicles.validVehicle(vehicleSeatsNotOk));
    }

    @Test
    public void validVehicle_should_return_false_when_null_manufacturer() {
        Vehicle vehicleNoManufacturer = new Vehicle(1, null, 3);
        assertFalse(vehicles.validVehicle(vehicleNoManufacturer));
    }

    @Test
    public void validVehicle_should_return_false_when_empty_manufacturer() {
        Vehicle vehicleNoManufacturer = new Vehicle(1, "", 3);
        assertFalse(vehicles.validVehicle(vehicleNoManufacturer));
    }
}
