package repository.BD;

import domain.Rental;
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

public class RentalDBRepository implements PagingRepository<Integer, Rental> {

    private static final String URL = "jdbc:postgresql://localhost:2253/MovieRental";
    private static final String USERNAME = "postgres";
    private static final String PASSWORD = "parola";

    private Validator<Rental> validator;

    public RentalDBRepository(Validator<Rental> validator){
        this.validator=validator;
    }

    @Override
    public Page<Rental> findAll(Pageable pageable) {
        return Paginator.paginate(this.findAll(), pageable);
    }

    @Override
    public Optional<Rental> findOne(Integer integer) {
        String sql = "select from \"Rental\" where \"Rental_Id\"=?";
        Rental rent= null;
        try (var connection = DriverManager.getConnection(URL, USERNAME, PASSWORD);
             var statement = connection.prepareStatement(sql))
        {
            statement.setInt(1, integer);
            var resultSet = statement.executeQuery();

            if (!resultSet.next()) {
                Integer id = resultSet.getInt("Rental_Id");
                Integer client_id = resultSet.getInt("Client_Id");
                Integer movie_id = resultSet.getInt("Movie_Id");
                String rent_date = resultSet.getString("Rental_Date");
                String return_date = resultSet.getString("Return_Date");

                rent = new Rental(client_id,movie_id,rent_date,return_date);
                rent.setId(id);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return Optional.ofNullable(rent);
    }

    @Override
    public Iterable<Rental> findAll() {
        List<Rental> rentals = new ArrayList<>();
        String sql = "select * from \"Rental\"";

        try (var connection = DriverManager.getConnection(URL, USERNAME, PASSWORD);
             var statement = connection.prepareStatement(sql);
             var resultSet = statement.executeQuery()) {

            while (resultSet.next()) {
                Integer id = resultSet.getInt("Rental_Id");
                Integer client_id = resultSet.getInt("Client_Id");
                Integer movie_id = resultSet.getInt("Movie_Id");
                String rent_date = resultSet.getString("Rental_Date");
                String return_date = resultSet.getString("Return_Date");

                Rental rent = new Rental(client_id,movie_id,rent_date,return_date);
                rent.setId(id);
                rentals.add(rent);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return rentals;
    }

    @Override
    public Optional<Rental> save(Rental entity) throws ValidatorException {
        String sql = "insert into \"Rental\"(\"Rental_Id\",\"Client_Id\",\"Movie_Id\",\"Rental_date\", \"Return_date\") values (?,?,?,?)";
        validator.validate(entity);
        Optional<Rental> r = findOne(entity.getId());
        if (r.isPresent()) {
            return r;
        }
        else {
            try (var connection = DriverManager.getConnection(URL, USERNAME,
                    PASSWORD);
                 var statement = connection.prepareStatement(sql)) {

                statement.setInt(1, entity.getId());
                statement.setInt(2, entity.getClientId());
                statement.setInt(3, entity.getMovieId());
                statement.setString(4, entity.getRentalDate());
                statement.setString(5, entity.getReturnDate());


                statement.executeUpdate();
            } catch (SQLException e) {
                e.printStackTrace();
            }
            return Optional.empty();
        }
    }

    @Override
    public Optional<Rental> delete(Integer integer) {
        String sql = "delete from \"Rental\" where \"Rental_Id\"=?";
        Optional<Rental> r = findOne(integer);
        if (r.isEmpty()) {
            return Optional.empty();
        }
        else {
            try (var connection = DriverManager.getConnection(URL, USERNAME, PASSWORD);
                 var statement = connection.prepareStatement(sql)) {

                statement.setInt(1, integer);

                statement.executeUpdate();
            } catch (SQLException e) {
                e.printStackTrace();
            }
            return r;
        }
    }

    @Override
    public Optional<Rental> update(Rental entity) throws ValidatorException {
        String sql = "update \"Rental\" set \"Client_Id\"=?, \"Movie_Id\"=?, \"Rental_Date\"=? where \"Return_Date\"=?";
        validator.validate(entity);
        Optional<Rental> r = findOne(entity.getId());
        if (r.isEmpty()) {
            return Optional.empty();
        }
        else {
            try (var connection = DriverManager.getConnection(URL, USERNAME,
                    PASSWORD);
                 var statement = connection.prepareStatement(sql)) {

                statement.setInt(1, entity.getClientId());
                statement.setInt(2, entity.getMovieId());
                statement.setString(3, entity.getRentalDate());
                statement.setString(4, entity.getReturnDate());

                statement.executeUpdate();
            } catch (SQLException e) {
                e.printStackTrace();
            }
            return r;
        }
    }
}
