package com.suleimanov.manager.controller.payload;

//import jakarta.validation.constraints.NotNull;
//import jakarta.validation.constraints.Size;

public record UpdateProductPayload(
//        @NotNull(message = "{catalog.products.update.errors.title_is_null}")
//        @Size(min = 3, max = 50, message = "{catalog.products.update.errors.title_is_invalid}")
        String title,
//        @Size(max = 1000, message = "{catalog.products.update.errors.details_is_invalid}")
        String details) {
}
