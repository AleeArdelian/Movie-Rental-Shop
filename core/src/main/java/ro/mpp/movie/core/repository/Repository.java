package ro.mpp.movie.core.repository;

import ro.mpp.movie.core.model.BaseEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.io.Serializable;


/**
 * Interface for generic CRUD operations on a repository for a specific type.
 *
 * @author radu.
 *
 */
public interface Repository<ID extends Serializable, T extends BaseEntity<ID>> extends JpaRepository<T, ID> {
}
