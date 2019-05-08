package ro.mpp.movie.web.dto;

import lombok.*;
@EqualsAndHashCode(callSuper = true)
@NoArgsConstructor
@AllArgsConstructor
@Data
@ToString(callSuper = true)
@Builder
public class RentalDto extends BaseDto{
    private int clientId;
    private int movieId;
    private String rentalDate;
    private String returnDate;

}
