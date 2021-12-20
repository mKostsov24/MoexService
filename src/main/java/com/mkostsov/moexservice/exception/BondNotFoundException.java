package com.mkostsov.moexservice.exception;

public class BondNotFoundException extends RuntimeException {
    public BondNotFoundException(String m) {
        super(m);
    }
}
