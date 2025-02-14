package com.jakarta.jakarta.staff.service;

import com.jakarta.jakarta.staff.dto.StaffRequestDto;
import com.jakarta.jakarta.staff.dto.StaffResponseDto;
import com.jakarta.jakarta.staff.dto.StaffUpdateRequestDto;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface StaffService {
    StaffResponseDto createStaff(StaffRequestDto staffRequestDto);
    StaffResponseDto getStaff(Long staffId);
    Page<StaffResponseDto> getAllStaff(Pageable pageable);
    StaffResponseDto updateStaff(Long staffId, StaffUpdateRequestDto staffRequestDto);
    void deleteStaff(Long staffId);

}
