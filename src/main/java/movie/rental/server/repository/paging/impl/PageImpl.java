package movie.rental.server.repository.paging.impl;

import movie.rental.server.repository.paging.Page;
import movie.rental.server.repository.paging.Pageable;

import java.util.stream.Stream;

public class PageImpl<T> implements Page<T> {

    private Pageable pageableObj;
    private Stream<T> content;

    public PageImpl(Stream<T> content, Pageable pageableObj) {
        this.content = content;
        this.pageableObj = pageableObj;
    }

    @Override
    public Pageable getPageable() {
        return pageableObj;
    }

    @Override
    public Pageable nextPageable() {
        return new PageableImpl(pageableObj.getPageNumber() + 1, pageableObj.getPageSize());
    }

    @Override
    public Stream<T> getContent() {
        return content;
    }
}
