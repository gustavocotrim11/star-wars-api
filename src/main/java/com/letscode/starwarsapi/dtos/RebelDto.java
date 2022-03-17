package com.letscode.starwarsapi.dtos;

import lombok.Data;
import lombok.NonNull;

import javax.validation.Valid;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

@Data
public class RebelDto {

    @NonNull
    @NotBlank(message = "name must not be null or whitespace")
    private String name;
    @NonNull
    @NotNull(message = "age must not be null")
    @Min(message = "The minimum age allowed is 18 years old", value = 18)
    private Integer age;
    @NonNull
    @NotBlank(message = "gender must not be null or whitespace")
    private String gender;
    @NonNull
    @NotNull(message = "localization must not be null")
    @Valid
    private LocalizationDto localization;
    @NonNull
    @NotNull(message = "inventory must not be null")
    @Valid
    private InventoryDto inventory;
}
