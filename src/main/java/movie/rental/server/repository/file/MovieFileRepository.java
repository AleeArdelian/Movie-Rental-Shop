package movie.rental.server.repository.file;

import movie.rental.server.domain.Movie;
import movie.rental.server.domain.validators.Validator;

import java.io.BufferedWriter;
import java.io.IOException;
import java.nio.file.StandardOpenOption;
import java.util.List;

import movie.rental.server.domain.validators.ValidatorException;
import movie.rental.server.repository.mem.InMemoryRepository;

import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Arrays;
import java.util.Optional;

public class MovieFileRepository extends InMemoryRepository<Integer, Movie> {

    private String sourceFile;

    public MovieFileRepository(Validator<Movie> validator, String file) {
        super(validator);
        this.sourceFile = file;
        loadData();
    }
    private void loadData() {
        Path path = Paths.get(sourceFile);

        try {
            Files.lines(path).forEach(line -> {
                List<String> items = Arrays.asList(line.split(","));

                Integer id = Integer.valueOf(items.get(0));
                String name = items.get(1);
                Integer year = Integer.valueOf(items.get((2)));
                String director = items.get(3);

                Movie movie = new Movie(name, year, director);
                movie.setId(id);

                try {
                    super.save(movie);
                } catch (ValidatorException e) {
                    e.printStackTrace();
                }
            });
        } catch (IOException ex) {
            ex.printStackTrace();
        }
    }

    @Override
    public Optional<Movie> save(Movie entity) throws ValidatorException {
        Optional<Movie> optional = super.save(entity);
        if (optional.isPresent()) {
            return optional;
        }
        saveToFile(entity);
        return Optional.empty();
    }


    private void saveToFile(Movie entity) {
        Path path = Paths.get(sourceFile);

        try (BufferedWriter bufferedWriter = Files.newBufferedWriter(path, StandardOpenOption.APPEND)) {
            bufferedWriter.write("\n"+
                    entity.getId() + "," + entity.getMovieName() + "," + entity.getYear() + "," + entity.getMovieDirector());
            bufferedWriter.newLine();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
