package movie.rental.server.repository.paging.impl;

import movie.rental.server.repository.paging.Pageable;

public class PageableImpl implements Pageable {

    private int pageNumber;
    private int pageSize;

    public PageableImpl(int pageNumber, int pageSize) {
        this.pageNumber = pageNumber;
        this.pageSize = pageSize;
    }

    @Override
    public int getPageNumber() {
        return pageNumber;
    }

    @Override
    public int getPageSize() {
        return pageSize;
    }
}
