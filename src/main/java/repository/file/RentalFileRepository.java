package repository.file;
import domain.Rental;
import domain.validators.Validator;
import domain.validators.ValidatorException;
import repository.mem.InMemoryRepository;

import java.io.BufferedWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

public class RentalFileRepository extends InMemoryRepository<Integer, Rental> {
     private String sourceFile;


    public RentalFileRepository(Validator<Rental> validator, String file) {
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
                Integer clientId = Integer.valueOf(items.get(1));
                Integer movieId = Integer.valueOf(items.get(2));

                Rental Rental = new Rental(clientId, movieId);
                Rental.setId(id);

                try {
                    super.save(Rental);
                } catch (ValidatorException e) {
                    e.printStackTrace();
                }
            });
        } catch (IOException ex) {
            ex.printStackTrace();
        }
    }

    @Override
    public Optional<Rental> save(Rental entity) throws ValidatorException {
        Optional<Rental> optional = super.save(entity);
        if (optional.isPresent()) {
            return optional;
        }
        saveToFile(entity);
        return Optional.empty();
    }


    private void saveToFile(Rental entity) {
        Path path = Paths.get(sourceFile);

        try (BufferedWriter bufferedWriter = Files.newBufferedWriter(path, StandardOpenOption.APPEND)) {
            bufferedWriter.write( "\n"+
                    entity.getId() + "," + entity.getClientId() + "," + entity.getMovieId() + "," + entity.getRentalDate()+ ","+entity.getReturnDate());
            bufferedWriter.newLine();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    
}
