package domain;

public class Movie extends BaseEntity<Integer> {

    private String movieName;
    private Integer yearOFRelease;
    private String regizor;

    public Movie(String name, Integer year, String reg)
    {
        this.movieName=name;
        this.yearOFRelease=year;
        this.regizor=reg;
    }

    public String getMovieName()
    {
        return this.movieName;
    }

    public Integer getYear()
    {
        return this.yearOFRelease;
    }

    public String getMovieRegizor()
    {
        return this.regizor;
    }

    public String toString()
    {
        return getId() + " " + movieName +" "+ yearOFRelease + " " + regizor;
    }

}
