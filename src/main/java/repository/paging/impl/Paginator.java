package repository.paging.impl;

import repository.paging.Page;
import repository.paging.Pageable;

import java.util.stream.StreamSupport;

public class Paginator {

    /**
     *
     * @param data
     * @param pageable
     * @param <T>
     * @return
     */
    public static <T> Page<T> paginate(Iterable<T> data, Pageable pageable) {
        return new PageImpl<>(StreamSupport.stream(data.spliterator(), false)
                .skip(pageable.getPageSize() * pageable.getPageNumber())
                .limit(pageable.getPageSize()), pageable);
    }

}
