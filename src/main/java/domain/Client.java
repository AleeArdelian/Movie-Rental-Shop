package domain;

/**
 * Client class for holding information about a client.
 * The information about a client is it's first name, last name and age.
 * Extends BaseEntity class.
 */
public class Client extends BaseEntity<Integer> {

    private String firstName;
    private String lastName;
    private int age;

    /**
     * Constructor for a client.
     * @param firstName a {@code String} representing the first name of the client.
     * @param lastName a {@code String} representing the last name of the client.
     * @param age an {@code int} representing the age of the client.
     */
    public Client(String firstName, String lastName, int age) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.age = age;
    }

    /**
     * Gets the first name of the client.
     * @return a {@code String} which is the first name of the client.
     */
    public String getFirstName() {
        return firstName;
    }

    /**
     * Sets the first name the client.
     * @param firstName a {@code String} representing the new name to be set.
     */
    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    /**
     * Gets the last name of the client.
     * @return a {@code String} which is the last name of the client.
     */
    public String getLastName() {
        return lastName;
    }

    /**
     * Sets the last name of the client.
     * @param lastName a {@code String} representing the new name to be set.
     */
    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    /**
     * Gets the age of the client.
     * @return an {@code int} which is the age of the client.
     */
    public int getAge() {
        return age;
    }

    /**
     * Sets the age of the client.
     * @param age an {@code int} representing the new age of the client.
     */
    public void setAge(int age) {
        this.age = age;
    }

    /**
     * Returns the information about the client in a form that can be printed: ID LastName FirstName Age.
     * @return a {@code String} representing the information about the client.
     */
    public String toString()
    {
        return "Client id:" + getId() + " Last name: " + lastName + " First name: " + firstName + " Age: " + age;
    }

}
