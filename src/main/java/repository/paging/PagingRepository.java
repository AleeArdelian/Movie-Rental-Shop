package repository.paging;

import domain.BaseEntity;
import repository.Repository;

import java.io.Serializable;

/**
 * author: radu
 */
public interface PagingRepository<ID extends Serializable, T extends BaseEntity<ID>> extends Repository<ID, T> {

    Page<T> findAll(Pageable pageable);

    //TODO: any other methods are allowed...

}