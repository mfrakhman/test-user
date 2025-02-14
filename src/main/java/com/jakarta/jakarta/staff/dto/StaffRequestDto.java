package com.jakarta.jakarta.staff.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

@Data
public class StaffRequestDto {

    @NotNull
    private Long userId;

    @NotBlank
    private String firstName;

    @NotBlank
    private String lastName;

    @NotBlank
    private String phoneNumber;
}
