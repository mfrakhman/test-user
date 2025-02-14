package com.jakarta.jakarta.staff.mapper;

import com.jakarta.jakarta.staff.dto.StaffRequestDto;
import com.jakarta.jakarta.staff.dto.StaffResponseDto;
import com.jakarta.jakarta.staff.entity.Staff;
import org.springframework.stereotype.Component;

@Component
public class StaffMapper {
    public Staff toEntity(StaffResponseDto responseDto) {
        Staff staff = new Staff();
        staff.setFirstName(responseDto.getFirstName());
        staff.setLastName(responseDto.getLastName());
        staff.setPhoneNumber(responseDto.getPhoneNumber());
        return staff;
    };

    public StaffResponseDto toDto(Staff staff) {
        StaffResponseDto staffDto = new StaffResponseDto();
        staffDto.setId(staff.getId());
        staffDto.setFirstName(staff.getFirstName());
        staffDto.setLastName(staff.getLastName());
        staffDto.setPhoneNumber(staff.getPhoneNumber());
        staffDto.setUserId(staff.getUser().getId());
        return staffDto;
    };

    public Staff toEntity(StaffRequestDto responseDto) {
        Staff staff = new Staff();
        staff.setFirstName(responseDto.getFirstName());
        staff.setLastName(responseDto.getLastName());
        staff.setPhoneNumber(responseDto.getPhoneNumber());
        return staff;
    };
}

