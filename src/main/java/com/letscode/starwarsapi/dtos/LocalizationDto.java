package com.letscode.starwarsapi.dtos;

import com.letscode.starwarsapi.models.InventoryModel;
import com.letscode.starwarsapi.models.LocalizationModel;
import com.letscode.starwarsapi.models.unums.GenderEnum;
import lombok.Data;
import lombok.NonNull;

@Data
public class LocalizationDto {

    @NonNull
    private Long latitude;
    @NonNull
    private Long longitude;
    @NonNull
    private String baseName;
}
