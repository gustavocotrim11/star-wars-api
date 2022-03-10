package com.letscode.starwarsapi.exceptions;

import java.util.UUID;

public class LocalizationNotFoundException extends RuntimeException{

        public LocalizationNotFoundException(UUID id) {
            super("Could not find localization " + id);
        }
}
