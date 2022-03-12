package com.letscode.starwarsapi.services.exceptions;

public class RebelNotFoundException extends RuntimeException {

    private static final long serialVersionUID = 1L;

    public RebelNotFoundException (Object id) {
        super("Rebel not found. Id " + id);
    }
}
