package ro.mpp.movie.web.converter;

import org.springframework.stereotype.Component;
import ro.mpp.movie.core.model.Movie;
import ro.mpp.movie.web.dto.MovieDto;

@Component
public class MovieConverter extends BaseConverter<Movie, MovieDto> {

    @Override
    public Movie convertDtoToModel(MovieDto dto) {
        Movie movie = Movie.builder()
                .movieName(dto.getMovieName())
                .yearOfRelease(dto.getYearOfRelease())
                .director(dto.getDirector())
                .build();
        movie.setId(dto.getId());
        return movie;
    }

    @Override
    public MovieDto convertModelToDto(Movie movie) {
        MovieDto moviedto = MovieDto.builder()
                .movieName(movie.getMovieName())
                .yearOfRelease(movie.getYearOfRelease())
                .director(movie.getDirector())
                .build();
        moviedto.setId(movie.getId());
        return moviedto;
    }

}
