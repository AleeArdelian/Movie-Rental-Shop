package ro.mpp.movie.web.converter;

import org.springframework.stereotype.Component;
import ro.mpp.movie.core.model.Rental;
import ro.mpp.movie.web.dto.RentalDto;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

@Component
public class RentalConverter extends BaseConverter<Rental, RentalDto> {
    @Override
    public Rental convertDtoToModel(RentalDto dto) {
        DateTimeFormatter dtf = DateTimeFormatter.ofPattern("dd/MM/yyyy");
        LocalDate localDate = LocalDate.now();
        LocalDate newLocalDate = localDate.plusWeeks(1);

        Rental rental = Rental.builder()
                .clientId(dto.getClientId())
                .movieId(dto.getMovieId())
                .build();
        rental.setId(dto.getId());
        rental.setRentalDate(dtf.format(localDate));
        rental.setReturnDate(dtf.format(newLocalDate));
        return rental;
    }
    @Override
    public RentalDto convertModelToDto(Rental rental) {
        DateTimeFormatter dtf = DateTimeFormatter.ofPattern("dd/MM/yyyy");
        LocalDate localDate = LocalDate.now();
        LocalDate newLocalDate = localDate.plusWeeks(1);

        RentalDto rentaldto = RentalDto.builder()
                .clientId(rental.getClientId())
                .movieId(rental.getMovieId())
                .build();
        rentaldto.setId(rental.getId());
        rentaldto.setRentalDate(dtf.format(localDate));
        rentaldto.setReturnDate(dtf.format(newLocalDate));
        return rentaldto;
    }
}
