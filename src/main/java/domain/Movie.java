package domain;

public class Movie extends BaseEntity<Integer> {

    private String movieName;
    private Integer yearOfRelease;
    private String director;

    public Movie(String name, Integer year, String director)
    {
        this.movieName = name;
        this.yearOfRelease = year;
        this.director = director;
    }

    public String getMovieName()
    {
        return this.movieName;
    }

    public Integer getYear()
    {
        return this.yearOfRelease;
    }

    public String getMovieRegizor()
    {
        return this.director;
    }

    public String toString()
    {
        return getId() + " " + movieName + " " + yearOfRelease + " " + director;
    }

}
