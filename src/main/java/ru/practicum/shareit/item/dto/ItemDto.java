package ru.practicum.shareit.item.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Data;

@Data
public class ItemDto {
    @NotBlank
    @Size(
            max = 200,
            message = "Название не может быть более 200 символов"
    )
    String name;
    @NotBlank
    @Size(
            max = 1000,
            message = "Название не может быть более 1000 символов"
    )
    String description;
    @NotNull
    Boolean available;
}