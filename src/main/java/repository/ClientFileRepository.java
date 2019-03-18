package repository;

import java.io.BufferedWriter;
import java.io.IOException;
import java.nio.file.StandardOpenOption;
import java.util.List;
import domain.Client;
import domain.validators.Validator;
import domain.validators.ValidatorException;

import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Arrays;
import java.util.Optional;

public class ClientFileRepository extends InMemoryRepository<Integer, Client> {

    private String sourceFile;

    public ClientFileRepository(Validator<Client> validator, String file) {
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
                String fisrtName = items.get(1);
                String lastName = items.get((2));
                int age = Integer.parseInt(items.get(3));

                Client student = new Client(fisrtName, lastName, age);
                student.setId(id);

                try {
                    super.save(student);
                } catch (ValidatorException e) {
                    e.printStackTrace();
                }
            });
        } catch (IOException ex) {
            ex.printStackTrace();
        }
    }

    @Override
    public Optional<Client> save(Client entity) throws ValidatorException {
        Optional<Client> optional = super.save(entity);
        if (optional.isPresent()) {
            return optional;
        }
        saveToFile(entity);
        return Optional.empty();
    }

    private void saveToFile(Client entity) {
        Path path = Paths.get(sourceFile);

        try (BufferedWriter bufferedWriter = Files.newBufferedWriter(path, StandardOpenOption.APPEND)) {
            bufferedWriter.write("\n"+
                    entity.getId() + "," + entity.getFirstName() + "," + entity.getLastName() + "," + entity.getAge());
            bufferedWriter.newLine();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}
