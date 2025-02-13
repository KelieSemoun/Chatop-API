package com.openclassrooms.backend.model;

import java.time.LocalDate;

public record RentalDTO(Integer id, String name, Integer surface, Integer price, String picture, String description, Integer owner_id, LocalDate created_at, LocalDate updated_at) {

}
