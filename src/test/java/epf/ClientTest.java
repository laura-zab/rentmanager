package epf;

import com.epf.rentmanager.dao.ClientDao;
import com.epf.rentmanager.exception.DaoException;
import com.epf.rentmanager.exception.ServiceException;
import com.epf.rentmanager.modele.Client;
import com.epf.rentmanager.service.ClientService;
import com.epf.rentmanager.utils.Clients;
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
 * Unit test for Client class
 */

@RunWith(MockitoJUnitRunner.class)
public class ClientTest
{
    @InjectMocks
    private Clients clients;

    @Test
    public void isLegal_should_return_true_when_age_is_over_18() {
        Client legalClient = new Client(1, "Doe", "John", "johndoe@mail.com", LocalDate.parse("2000-01-01"));
        assertTrue(clients.isLegal(legalClient));
    }

    @Test
    public void isLegal_should_return_false_when_age_is_under_18() {
        Client underageClient = new Client(1, "Doe", "John", "johndoe@mail.com", LocalDate.parse("2007-01-01"));
        assertFalse(clients.isLegal(underageClient));
    }

    @Test
    public void namesLengthOk_should_return_true_when_names_are_3_characters_or_more() {
        Client okClient = new Client(1, "Doe", "John", "johndoe@mail.com", LocalDate.parse("2007-01-01"));
        assertTrue(clients.namesLengthOK(okClient));
    }

    @Test
    public void namesLengthOk_should_return_false_when_a_name_is_less_than_3_characters() {
        Client shortClient = new Client(1, "Do", "John", "johndo@mail.com", LocalDate.parse("2007-01-01"));
        assertFalse(clients.namesLengthOK(shortClient));
    }

    @Test
    public void namesLengthOk_should_return_false_when_a_firstname_is_less_than_3_characters() {
        Client shortClient = new Client(1, "Doe", "Jo", "jodoe@mail.com", LocalDate.parse("2007-01-01"));
        assertFalse(clients.namesLengthOK(shortClient));
    }
}
