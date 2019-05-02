package ro.mpp.movie.core.model;

import lombok.*;

import javax.persistence.Entity;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

@Entity
@NoArgsConstructor
@AllArgsConstructor
@Data
@EqualsAndHashCode(callSuper = true)
@ToString(callSuper = true)
@Builder
public class Rental extends BaseEntity<Integer> {

    private int clientId;
    private int movieId;
    private String rentalDate;
    private String returnDate;

}
