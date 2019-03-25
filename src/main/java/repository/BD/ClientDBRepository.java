package repository.BD;

import domain.Client;
import domain.validators.Validator;
import domain.validators.ValidatorException;
import repository.paging.Page;
import repository.paging.Pageable;
import repository.paging.PagingRepository;
import repository.paging.impl.Paginator;

import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class ClientDBRepository implements PagingRepository<Integer, Client> {

    private static final String URL = "jdbc:postgresql://localhost:5432/jdbc";
    private static final String USERNAME = System.getProperty("username");
    private static final String PASSWORD = System.getProperty("password");

    private Validator<Client> validator;

    public ClientDBRepository(Validator<Client> validator) {
        this.validator = validator;
    }

    @Override
    public Page<Client> findAll(Pageable pageable) {
        return Paginator.paginate(this.findAll(), pageable);
    }

    @Override
    public Optional<Client> findOne(Integer integer) {
        Client c = null;
        String sql = "select * from \"Clients\" where \"Client_Id\"=?";
        try (
                var connection = DriverManager.getConnection(URL, USERNAME, PASSWORD);
                var statement = connection.prepareStatement(sql);
        ) {
            statement.setInt(1, integer);
            var resultSet = statement.executeQuery();
            if (!resultSet.wasNull()) {
                int id = resultSet.getInt("Client_Id");
                String firstName = resultSet.getString("Client_FirstName");
                String lastName = resultSet.getString("Client_LastName");
                int age = resultSet.getInt("Client_Age");
                c = new Client(firstName, lastName, age);
                c.setId(id);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return Optional.ofNullable(c);
    }

    @Override
    public Iterable<Client> findAll() {
        List<Client> clients = new ArrayList<>();
        String sql = "select * from \"Clients\"";
        try (
            var connection = DriverManager.getConnection(URL, USERNAME, PASSWORD);
            var statement = connection.prepareStatement(sql);
            var resultSet = statement.executeQuery()
        ) {
            while (resultSet.next()) {
                int id = resultSet.getInt("Client_Id");
                String firstName = resultSet.getString("Client_FirstName");
                String lastName = resultSet.getString("Client_LastName");
                int age = resultSet.getInt("Client_Age");
                Client c = new Client(firstName, lastName, age);
                c.setId(id);
                clients.add(c);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return clients;
    }

    @Override
    public Optional<Client> save(Client entity) throws ValidatorException {
        String sql = "insert into \"Clients\"(\"Client_Id\", \"Client_FirstName\", \"Client_LastName\". \"Client_Age\")" +
                " values (?, ?, ?, ?)";
        try (
                var connection = DriverManager.getConnection(URL, USERNAME, PASSWORD);
                var statement = connection.prepareStatement(sql);
        ) {
            statement.setInt(1, entity.getId());
            statement.setString(2, entity.getFirstName());
            statement.setString(3, entity.getLastName());
            statement.setInt(4, entity.getAge());
            statement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return Optional.empty();
    }

    @Override
    public Optional<Client> delete(Integer integer) {
        String sql = "delete from \"Clients\" where \"Client_Id\"=?";
        try (
                var connection = DriverManager.getConnection(URL, USERNAME, PASSWORD);
                var statement = connection.prepareStatement(sql)
        ) {
            statement.setInt(1, integer);
            statement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return Optional.empty();
    }

    @Override
    public Optional<Client> update(Client entity) throws ValidatorException {
        String sql = "update \"Clients\" set \"Client_FirstName\"=?, \"Client_LastName\"=?, \"Client_Age\"=? where \"Client_Id\"=?";
        try (
                var connection = DriverManager.getConnection(URL, USERNAME, PASSWORD);
                var statement = connection.prepareStatement(sql)
        ) {
            statement.setString(1, entity.getFirstName());
            statement.setString(2, entity.getLastName());
            statement.setInt(3, entity.getAge());
            statement.setInt(4, entity.getId());
            statement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return Optional.empty();
    }
}
