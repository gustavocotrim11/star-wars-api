package com.letscode.starwarsapi.dtos;

import lombok.Data;
import lombok.NonNull;

@Data
public class RebelDto {

    @NonNull
    private String name;
    @NonNull
    private Integer age;
    @NonNull
    private String gender;
    @NonNull
    private LocalizationDto localization;
    @NonNull
    private InventoryDto inventory;
}
