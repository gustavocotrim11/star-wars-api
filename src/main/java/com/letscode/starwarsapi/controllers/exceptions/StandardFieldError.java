package com.letscode.starwarsapi.controllers.exceptions;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.*;

import java.io.Serializable;
import java.time.Instant;
import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@RequiredArgsConstructor
public class StandardFieldError implements Serializable {

    private static final long serialVersionUID = 1L;

    @NonNull
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd'T'HH:mm:ss'Z'", timezone = "GMT")
    private Instant timestamp;
    @NonNull
    private Integer status;
    @NonNull
    private String error;
    @NonNull
    private List<String> fieldErrors;
    @NonNull
    private String path;

}
