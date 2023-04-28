package epf;

import com.epf.rentmanager.dao.ClientDao;
import com.epf.rentmanager.exception.DaoException;
import com.epf.rentmanager.exception.ServiceException;
import com.epf.rentmanager.modele.Client;
import com.epf.rentmanager.modele.Reservation;
import com.epf.rentmanager.modele.Vehicle;
import com.epf.rentmanager.service.ClientService;
import com.epf.rentmanager.utils.Clients;
import com.epf.rentmanager.utils.Reservations;
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
 * Unit test for Reservation class
 */

@RunWith(MockitoJUnitRunner.class)
public class ReservationTest
{
    @InjectMocks
    private Reservations reservations;

    @Test
    public void lessThan7Days_should_return_true_when_rented_under_7_days() {
        Reservation shortReservation = new Reservation(1, 1, 2, LocalDate.parse("2023-04-26"), LocalDate.parse("2023-04-28"));
        assertTrue(reservations.lessThan7Days(shortReservation));
    }

    @Test
    public void lessThan7Days_should_return_false_when_rented_over_7_days() {
        Reservation longReservation = new Reservation(1, 1, 2, LocalDate.parse("2023-04-20"), LocalDate.parse("2023-04-28"));
        assertFalse(reservations.lessThan7Days(longReservation));
    }

}
