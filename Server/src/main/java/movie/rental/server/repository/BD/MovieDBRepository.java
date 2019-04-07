package movie.rental.server.repository.BD;

import movie.rental.common.domain.Movie;
import movie.rental.common.domain.validators.Validator;
import movie.rental.common.domain.validators.ValidatorException;
import movie.rental.server.repository.paging.Page;
import movie.rental.server.repository.paging.Pageable;
import movie.rental.server.repository.paging.PagingRepository;
import movie.rental.server.repository.paging.impl.Paginator;

import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class MovieDBRepository implements PagingRepository<Integer, Movie> {

    private static final String URL = "jdbc:postgresql://localhost:5432/MOVIE_SHOP";
    private static final String USERNAME = "postgres";
    private static final String PASSWORD = "bere1234";

    private Validator<Movie> validator;

    public MovieDBRepository(Validator<Movie> validator){
        this.validator = validator;
    }

    @Override
    public Page<Movie> findAll(Pageable pageable) {
        return Paginator.paginate(this.findAll(), pageable);
    }

    @Override
    public Optional<Movie> findOne(Integer integer) {
        String sql = "select from \"Movie\" where \"Movie_Id\"=?";
        Movie mov= null;
        try (var connection = DriverManager.getConnection(URL, USERNAME, PASSWORD);
             var statement = connection.prepareStatement(sql))
        {
            statement.setInt(1, integer);
            var resultSet = statement.executeQuery();

            if(resultSet.next()) {
                Integer id = resultSet.getInt("Movie_Id");
                String name = resultSet.getString("Movie_Name");
                int year = resultSet.getInt("Movie_Year");
                String director = resultSet.getString("Movie_Director");
                mov = new Movie(name, year, director);
                mov.setId(id);
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return Optional.ofNullable(mov);
    }

    @Override
    public Iterable<Movie> findAll() {
        List<Movie> movies = new ArrayList<>();
        String sql = "select * from \"Movie\"";

        try (var connection = DriverManager.getConnection(URL, USERNAME,
                PASSWORD);
             var statement = connection.prepareStatement(sql);
             var resultSet = statement.executeQuery()) {

            while (resultSet.next()) {
                Integer id = resultSet.getInt("Movie_Id");
                String name = resultSet.getString("Movie_Name");
                int year = resultSet.getInt("Movie_Year");
                String director = resultSet.getString("Movie_Name");

                Movie mov = new Movie(name,year,director);
                mov.setId(id);
                movies.add(mov);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return movies;
    }

    @Override
    public Optional<Movie> save(Movie entity) throws ValidatorException {
        String sql = "insert into \"Movie\"(\"Movie_Id\",\"Movie_Name\",\"Movie_Year\",\"Movie_Director\") values (?,?,?,?)";
        validator.validate(entity);
        Optional<Movie> m = findOne(entity.getId());
        if (m.isPresent()) {
            return m;
        }
        else {
            try (var connection = DriverManager.getConnection(URL, USERNAME,
                    PASSWORD);
                 var statement = connection.prepareStatement(sql)) {


                statement.setInt(1, entity.getId());
                statement.setString(2, entity.getMovieName());
                statement.setInt(3, entity.getYear());
                statement.setString(4, entity.getMovieDirector());

                statement.executeUpdate();
            } catch (SQLException e) {
                e.printStackTrace();
            }
            return Optional.empty();
        }
    }

    @Override
    public Optional<Movie> delete(Integer integer) {
        String sql = "delete from \"Movie\" where \"Movie_Id\"=?";
        Optional<Movie> m = findOne(integer);
        if (m.isEmpty()) {
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
            return m;
        }
    }

    @Override
    public Optional<Movie> update(Movie entity) throws ValidatorException {
        String sql = "update \"Movie\" set \"Movie_Name\"=?, \"Movie_Year\"=?, \"Movie_Director\"=? where \"Movie_Id\"=?";
        validator.validate(entity);
        Optional<Movie> m = findOne(entity.getId());
        if (m.isEmpty()) {
            return Optional.empty();
        }
        else {
            try (var connection = DriverManager.getConnection(URL, USERNAME,
                    PASSWORD);
                 var statement = connection.prepareStatement(sql)) {

                statement.setString(1, entity.getMovieName());
                statement.setInt(2, entity.getYear());
                statement.setString(3, entity.getMovieDirector());
                statement.setInt(4, entity.getId());

                statement.executeUpdate();
            } catch (SQLException e) {
                e.printStackTrace();
            }
            return m;
        }
    }
}

