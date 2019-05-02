package ro.mpp.movie.web.dto;

import lombok.*;

@EqualsAndHashCode(callSuper = true)
@NoArgsConstructor
@AllArgsConstructor
@Data
@ToString(callSuper = true)
@Builder
public class ClientDto extends BaseDto {

    private String firstName;
    private String lastName;
    private int age;

}
