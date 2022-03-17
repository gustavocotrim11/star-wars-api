package com.letscode.starwarsapi.dtos;

import com.letscode.starwarsapi.models.InventoryModel;
import com.letscode.starwarsapi.models.LocalizationModel;
import com.letscode.starwarsapi.models.unums.GenderEnum;
import lombok.Data;
import lombok.NonNull;

import javax.validation.constraints.PositiveOrZero;

@Data
public class InventoryDto {

    @NonNull
    @PositiveOrZero(message = "weapon must be strictly positive, or positive including 0")
    private Integer weapon;
    @PositiveOrZero(message = "ammo must be strictly positive, or positive including 0")
    @NonNull
    private Integer ammo;
    @PositiveOrZero(message = "water must be strictly positive, or positive including 0")
    @NonNull
    private Integer water;
    @PositiveOrZero(message = "food must be strictly positive, or positive including 0")
    @NonNull
    private Integer food;
}
