package domain.validators;

import domain.Client;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;
import repository.mem.InMemoryRepository;
import repository.Repository;

/**
 * Tests for ClientValidator class.
 */
public class ClientValidatorTest {

    private static final int id = -5;
    private static final String firstName = "Pop";
    private static final String lastName = "Alice";
    private static final Integer age = 335;

    private Repository<Integer, Client> clientRepository ;
    private Validator<Client> clientValidator = new ClientValidator();
    private Client client;

    @Rule
    public ExpectedException expectedEx = ExpectedException.none();

    @Before
    public void setUp(){
        clientRepository = new InMemoryRepository<>(clientValidator);
        client = new Client(firstName,lastName,age);
        client.setId(id);
    }

    /**
     * Test case for validate method.
     */
    @Test
    public void validate() {
        expectedEx.expect(RuntimeException.class);
        expectedEx.expectMessage("Invalid client: Id must not be a negative number; Invalid age; ");
        clientRepository.save(client);

    }
}