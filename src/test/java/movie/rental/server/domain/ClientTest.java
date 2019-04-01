package movie.rental.server.domain;

import movie.rental.server.domain.validators.ClientValidator;
import movie.rental.server.domain.validators.Validator;
import movie.rental.server.domain.validators.ValidatorException;
import org.junit.After;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;

/**
 * Tests for Client class.
 */
public class ClientTest {

    /**
     * final variables cannot be changed after first assignment => error
     */

    private static final int id = 5;
    private static final String firstName = "Pop";
    private static final String lastName = "Alice";
    private static final Integer age = 35;

    private  Client client;

    /** statement passed to @Rule methods will run @Before methods,
     * then @Test methods and then @After
     */
    @Rule
    public ExpectedException expectedEx = ExpectedException.none();

    /**
     * before functions are called before each test
     * @throws ValidatorException if asd
     */
    @Before
    public void setUp() throws ValidatorException {
        Validator<Client> clientValidator = new ClientValidator();
        client = new Client(firstName,lastName,age);
        client.setId(id);
    }

    /**
     *  after functions are called after each test
     */
    @After
    public void tearDown() {
        client =null;
    }

    @Test
    public void getFirstName() {
        assertEquals("Pop", client.getFirstName());
    }

    @Test
    public void setFirstName() {
        client.setFirstName("Dumbrava");
        assertEquals("Dumbrava",client.getFirstName());
    }

    @Test
    public void getLastName() {
        assertEquals("Alice", client.getLastName());
    }

    @Test
    public void setLastName() {
        client.setLastName("Andrei");
        assertEquals("Andrei",client.getLastName());
    }

    @Test
    public void getAge() {
        assertEquals(35, client.getAge());
    }

    @Test
    public void setAge() {
        client.setAge(56);
        assertEquals(56,client.getAge());

    }
}