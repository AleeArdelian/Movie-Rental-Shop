package com.shop.core.model.exceptions;

public class MovieAlreadyRentedException extends RuntimeException {

        public MovieAlreadyRentedException(String msg) {
            super(msg);
        }
}
