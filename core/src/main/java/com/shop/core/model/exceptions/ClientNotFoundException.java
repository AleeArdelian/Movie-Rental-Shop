package com.shop.core.model.exceptions;

public class ClientNotFoundException extends RuntimeException{

    public ClientNotFoundException(String msg) {
        super(msg);
    }

}
