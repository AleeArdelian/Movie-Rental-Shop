package ro.mpp.movie.core.model;

import lombok.*;

import javax.persistence.Entity;

/**
 * Client class for holding information about a client.
 * The information about a client is it's first name, last name and age.
 * Extends BaseEntity class.
 */

@Entity
@NoArgsConstructor
@AllArgsConstructor
@Data
@Getter
@Setter
@EqualsAndHashCode(callSuper = true)
@ToString(callSuper = true)
@Builder
public class Client extends BaseEntity<Integer> {

    private String firstName;
    private String lastName;
    private int age;

}
