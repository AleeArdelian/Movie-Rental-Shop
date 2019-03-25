package repository.BD;

import domain.BaseEntity;
import domain.Movie;
import domain.validators.ValidatorException;
import repository.paging.Page;
import repository.paging.Pageable;
import repository.paging.PagingRepository;

import java.sql.*;
import java.io.Serializable;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class MovieDBRepository<ID extends Serializable, T extends BaseEntity<ID>> implements PagingRepository<ID, T> {

    private static final String URL = "jdbc:postgresql://localhost:5432/MovieRental";
    private static final String USERNAME = "postgres";
    private static final String PASSWORD = System.getProperty("password");

    public MovieDBRepository(){

    }

    @Override
    public Page<T> findAll(Pageable pageable) {
    }

    @Override
    public Optional<T> findOne(ID id) {
        return Optional.empty();
    }

    @Override
    public Iterable<T> findAll() {
        List<Movie> movies = new ArrayList<>();
        String sql = "select * from Movie";

        try (var connection = DriverManager.getConnection(URL, USERNAME,
                PASSWORD);
             var statement = connection.prepareStatement(sql);
             var resultSet = statement.executeQuery()) {

            while (resultSet.next()) {
                Integer id = resultSet.getInt("Movie_Id");
                String name = resultSet.getString("Movie_Name");
                int year = resultSet.getInt("Movie_Year");
                String director = resultSet.getString("Movie_Director");

                Movie mov =new Movie(name,year,director);
                mov.setId(id);
                movies.add(mov);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return (Iterable<T>)movies;
    }

    @Override
    public Optional<T> save(T entity) throws ValidatorException {
        String sql = "insert into Movie(name,grade) values (?,?)";
        try (var connection = DriverManager.getConnection(URL, USERNAME,
                PASSWORD);
             var statement = connection.prepareStatement(sql)) {

            statement.setString(1, entity.getName());
            statement.setInt(2, s.getGrade());

            statement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return Optional.empty();
    }

    @Override
    public Optional<T> delete(ID id) {
        String sql = "delete from student where id=?";
        try (var connection = DriverManager.getConnection(URL, USERNAME,
                PASSWORD);
             var statement = connection.prepareStatement(sql)) {

            statement.setLong(1, id);

            statement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return Optional.empty();
    }

    @Override
    public Optional<T> update(T entity) throws ValidatorException {
        String sql = "update student set name=?, grade=? where id=?";
        try (var connection = DriverManager.getConnection(URL, USERNAME,
                PASSWORD);
             var statement = connection.prepareStatement(sql)) {

            statement.setString(1, student.getName());
            statement.setInt(2, student.getGrade());
            statement.setLong(3, student.getId());

            statement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return Optional.empty();
    }
}

