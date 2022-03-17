package com.letscode.starwarsapi.dtos;

import com.letscode.starwarsapi.models.InventoryModel;
import com.letscode.starwarsapi.models.LocalizationModel;
import com.letscode.starwarsapi.models.unums.GenderEnum;
import lombok.Data;
import lombok.NonNull;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

@Data
public class LocalizationDto {

    @NonNull
    @NotNull(message = "latitude must not be null or empty")
    private Long latitude;
    @NonNull
    @NotNull(message = "longitude must not be null or empty")
    private Long longitude;
    @NonNull
    @NotBlank(message = "baseName must not be null or whitespace")
    private String baseName;
}
