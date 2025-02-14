package com.jakarta.jakarta.staff.dto;

import lombok.Data;

@Data
public class StaffResponseDto {

    private Long id;
    private Long userId;
    private String firstName;
    private String lastName;
    private String phoneNumber;

}
