package ro.mpp.movie.web.dto;

import lombok.*;

@EqualsAndHashCode(callSuper = true)
@NoArgsConstructor
@AllArgsConstructor
@Data
@ToString(callSuper = true)
@Builder
public class MovieDto extends BaseDto {

    private String movieName;
    private Integer yearOfRelease;
    private String director;
    private boolean isRented = false;

}
