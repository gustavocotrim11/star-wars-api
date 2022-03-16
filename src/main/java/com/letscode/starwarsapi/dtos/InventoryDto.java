package com.letscode.starwarsapi.dtos;

import com.letscode.starwarsapi.models.InventoryModel;
import com.letscode.starwarsapi.models.LocalizationModel;
import com.letscode.starwarsapi.models.unums.GenderEnum;
import lombok.Data;
import lombok.NonNull;

@Data
public class InventoryDto {

    @NonNull
    private Integer weapon;
    @NonNull
    private Integer ammo;
    @NonNull
    private Integer water;
    @NonNull
    private Integer food;
}
