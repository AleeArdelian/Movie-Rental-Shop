package ro.mpp.movie.core.model;

import lombok.*;

import javax.persistence.Entity;

/**
 * Movie class for holding information about a movie.
 * The information about a movie is it's name, year of release and director.
 * Extends BaseEntity class.
 */
@Entity
@NoArgsConstructor
@AllArgsConstructor
@Data
@EqualsAndHashCode(callSuper = true)
@ToString(callSuper = true)
@Builder
public class Movie extends BaseEntity<Integer> {

    private String movieName;
    private Integer yearOfRelease;
    private String director;
    private boolean isRented = false;

}
