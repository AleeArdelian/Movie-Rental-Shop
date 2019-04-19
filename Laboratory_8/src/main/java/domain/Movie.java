package domain;

import javax.persistence.Column;
import javax.persistence.Entity;

/**
 * Movie class for holding information about a movie.
 * The information about a movie is it's name, year of release and director.
 * Extends BaseEntity class.
 */
@Entity
public class Movie extends BaseEntity<Integer> {

    private String movieName;
    private Integer yearOfRelease;
    private String director;

    private boolean isRented = false;

    public Movie() {

    }
    /**
     * Constructor for a movie.
     * @param name a {@code String} representing the name of the movie.
     * @param year an {@code Integer} representing the release year of the movie.
     * @param director a {@code String} representing the full name of the movie director.
     */
    public Movie(String name, Integer year, String director)
    {
        this.movieName = name;
        this.yearOfRelease = year;
        this.director = director;
    }

    /**
     * Gets the name of the movie.
     * @return a {@code String} which is the name of the movie.
     */
    public String getMovieName()
    {
        return this.movieName;
    }

    /**
     * Gets the release year of the movie.
     * @return an {@code Integer} which is the release year of the movie.
     */
    public Integer getYear()
    {
        return this.yearOfRelease;
    }

    /**
     * Gets the director of the movie.
     * @return a {@code String} which is the full name of the movie director.
     */
    public String getMovieDirector()
    {
        return this.director;
    }

    /**
     * Returns the information about the movie in a form that can be printed: ID Name Year Director.
     * @return a {@code String} representing the information about the movie.
     */
    public String toString()
    {
        //return "Movie id: " + getId() + " Name: " + movieName + " Year: " + yearOfRelease + " Director: " + director +
        //        " Status: " + (isRented?"RENTED":"AVAILABLE");
        return getId() + " " + getMovieName() + " " + getYear() + " " + getMovieDirector() + " " + (isRented?"RENTED":"AVAILABLE");
    }
    /**
     * Replaces the movie`s name with the new name received as parameter
     */

    public void setMovieName( String name)
    {
        this.movieName=name;
    }

    /**
     * Replaces the movie`s year of release with the new year received as parameter
     */
    public void setYearOfRelease(Integer year)
    {
        this.yearOfRelease=year;
    }

    /**
     * Replaces the movie`s director with the new director received as parameter
     */
    public void setDirector(String director)
    {
        this.director=director;
    }

    public void setRented(boolean status){
        this.isRented = status;
    }

    public boolean isRented()
    {
        return isRented;
    }

}