package com.letscode.starwarsapi.services.exceptions;

import java.util.UUID;

public class LocalizationNotFoundException extends RuntimeException{

        public LocalizationNotFoundException(UUID id) {
            super("Could not find localization " + id);
        }
}
