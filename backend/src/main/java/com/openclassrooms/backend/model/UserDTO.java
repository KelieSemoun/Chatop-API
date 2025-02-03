package com.openclassrooms.backend.model;

import java.time.LocalDate;

public record UserDTO(Integer id, String name, String email, LocalDate createdAt, LocalDate updatedAt) {

}